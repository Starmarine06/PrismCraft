package net.starmarine06.prismcraft.blocks.entity;

import net.starmarine06.prismcraft.blocks.ModBlocks;
import net.starmarine06.prismcraft.recipes.DyeMixerMenu;
import net.starmarine06.prismcraft.recipes.DyeMixerRecipe;
import net.starmarine06.prismcraft.recipes.ModRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DyeMixerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0, 1 -> stack.getItem() instanceof DyeItem;
                case 2 -> ModBlocks.isColorableBlock(stack.getItem().getDefaultInstance().getItem().defaultBlockState());
                case 3 -> false;
                default -> false;
            };
        }
    };

    private static final int INPUT_SLOT_1 = 0;
    private static final int INPUT_SLOT_2 = 1;
    private static final int BLOCK_SLOT = 2;
    private static final int OUTPUT_SLOT = 3;

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public DyeMixerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DYE_MIXER_BLOCK_ENTITY.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> DyeMixerBlockEntity.this.progress;
                    case 1 -> DyeMixerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DyeMixerBlockEntity.this.progress = value;
                    case 1 -> DyeMixerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.prismcraft.dye_mixer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new DyeMixerMenu(containerId, playerInventory, this, this.data);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("dye_mixer.progress", progress);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("dye_mixer.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    private void craftItem() {
        Optional<DyeMixerRecipe> recipe = getCurrentRecipe();
        if (recipe.isPresent()) {
            ItemStack result = recipe.get().getResultItem(level.registryAccess());

            // Mix colors from the two dyes
            ItemStack dye1 = itemHandler.getStackInSlot(INPUT_SLOT_1);
            ItemStack dye2 = itemHandler.getStackInSlot(INPUT_SLOT_2);

            if (dye1.getItem() instanceof DyeItem dyeItem1 &&
                    dye2.getItem() instanceof DyeItem dyeItem2) {

                int color1 = getDyeColor(dyeItem1);
                int color2 = getDyeColor(dyeItem2);
                int mixedColor = mixColors(color1, color2);

                itemHandler.extractItem(INPUT_SLOT_1, 1, false);
                itemHandler.extractItem(INPUT_SLOT_2, 1, false);
                itemHandler.extractItem(BLOCK_SLOT, 1, false);

                // Set color in output ItemStack NBT!
                ItemStack outputStack = new ItemStack(result.getItem(), result.getCount());
                outputStack.getOrCreateTag().putInt("prismcraft:color", mixedColor);

                itemHandler.setStackInSlot(OUTPUT_SLOT, outputStack);
            }
        }
    }


    private int getDyeColor(DyeItem dyeItem) {
        return switch (dyeItem.getDyeColor()) {
            case WHITE -> 0xFFFFFF;
            case ORANGE -> 0xFF8000;
            case MAGENTA -> 0xFF00FF;
            case LIGHT_BLUE -> 0x80FFFF;
            case YELLOW -> 0xFFFF00;
            case LIME -> 0x80FF00;
            case PINK -> 0xFF8080;
            case GRAY -> 0x808080;
            case LIGHT_GRAY -> 0xC0C0C0;
            case CYAN -> 0x00FFFF;
            case PURPLE -> 0x8000FF;
            case BLUE -> 0x0000FF;
            case BROWN -> 0x804000;
            case GREEN -> 0x008000;
            case RED -> 0xFF0000;
            case BLACK -> 0x000000;
        };
    }

    private int mixColors(int color1, int color2) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        int mixedR = (r1 + r2) / 2;
        int mixedG = (g1 + g2) / 2;
        int mixedB = (b1 + b2) / 2;

        return (mixedR << 16) | (mixedG << 8) | mixedB;
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<DyeMixerRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());
        return canInsertAmountIntoOutputSlot(result.getCount()) &&
                canInsertItemIntoOutputSlot(result.getItem());
    }

    private Optional<DyeMixerRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(ModRecipes.DYE_MIXER_TYPE.get(), inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <=
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}