package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.starmarine06.prismcraft.block.*;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.core.component.DataComponents;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class DyeMixerBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            // Slots 0 and 1 are dye slots
            if (slot == 0 || slot == 1) {
                return stack.getItem() instanceof DyeItem;
            }
            // Slot 2 is input slot (prism blocks only)
            else if (slot == 2) {
                return isPrismBlock(stack);
            }
            // Slot 3 is output slot (no manual insertion)
            else if (slot == 3) {
                return false;
            }
            return super.isItemValid(slot, stack);
        }
    };

    private final ContainerData data = new ContainerData() {
        @Override public int get(int index) { return 0; }
        @Override public void set(int index, int value) {}
        @Override public int getCount() { return 0; }
    };

    public DyeMixerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.DYE_MIXER.get(), pos, blockState);
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.prismcraft.dye_mixer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new DyeMixerMenu(containerId, playerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    public void drops() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(),
                    worldPosition.getZ(), itemHandler.getStackInSlot(i));
        }
    }

    // TICK METHOD - Called every game tick
    public static void tick(Level level, BlockPos pos, BlockState state, DyeMixerBlockEntity blockEntity) {
        if (level.isClientSide()) { return; }
        // Try to craft every tick
        if (blockEntity.canCraft()) { blockEntity.craft(); }
    }

    // CHECK IF CRAFTING IS POSSIBLE
    private boolean canCraft() {
        ItemStack dye1 = itemHandler.getStackInSlot(0);
        ItemStack dye2 = itemHandler.getStackInSlot(1);
        ItemStack input = itemHandler.getStackInSlot(2);
        ItemStack output = itemHandler.getStackInSlot(3);

        // Check all inputs are present
        if (dye1.isEmpty() || dye2.isEmpty() || input.isEmpty()) { return false; }
        // Verify items are dyes
        if (!(dye1.getItem() instanceof DyeItem) || !(dye2.getItem() instanceof DyeItem)) { return false; }
        // Verify input is a prism block
        if (!isPrismBlock(input)) { return false; }
        // Check if output slot is empty (we only craft when output is empty)
        if (!output.isEmpty()) { return false; }
        return true;
    }

    // PERFORM THE CRAFTING
    private void craft() {
        ItemStack dye1 = itemHandler.getStackInSlot(0);
        ItemStack dye2 = itemHandler.getStackInSlot(1);
        ItemStack input = itemHandler.getStackInSlot(2);

        int color1 = ((DyeItem) dye1.getItem()).getDyeColor().getTextureDiffuseColor();
        int color2 = ((DyeItem) dye2.getItem()).getDyeColor().getTextureDiffuseColor();
        int existingColor = getBlockColor(input);
        int mixedColor = mixThreeColors(existingColor, color1, color2);

        ItemStack result = new ItemStack(input.getItem(), 1);
        setBlockColor(result, mixedColor);

        // --- DYE HISTORY NBT (Minecraft 1.21.4+) ---
        ListTag dyeList = new ListTag();

        BiConsumer<ResourceLocation, Integer> addOrMergeDye = (dyeRL, amount) -> {
            for (int i = 0; i < dyeList.size(); i++) {
                CompoundTag tag = dyeList.getCompound(i);
                if (tag.getString("id").equals(dyeRL.toString())) {
                    tag.putInt("count", tag.getInt("count") + amount);
                    return;
                }
            }
            CompoundTag dyeTag = new CompoundTag();
            dyeTag.putString("id", dyeRL.toString());
            dyeTag.putInt("count", amount);
            dyeList.add(dyeTag);
        };

        ResourceLocation dye1RL = BuiltInRegistries.ITEM.getKey(dye1.getItem());
        ResourceLocation dye2RL = BuiltInRegistries.ITEM.getKey(dye2.getItem());

        addOrMergeDye.accept(dye1RL, 1);
        addOrMergeDye.accept(dye2RL, 1);

        CustomData inputCustomData = input.get(DataComponents.CUSTOM_DATA);
        if (inputCustomData != null) {
            CompoundTag customTag = inputCustomData.copyTag();
            if (customTag.contains("prismcraft:dye_ingredients", Tag.TAG_LIST)) {
                ListTag oldList = customTag.getList("prismcraft:dye_ingredients", Tag.TAG_COMPOUND);
                for (int i = 0; i < oldList.size(); i++) {
                    CompoundTag t = oldList.getCompound(i);
                    ResourceLocation rl = ResourceLocation.tryParse(t.getString("id"));
                    if (rl != null) {
                        addOrMergeDye.accept(rl, t.getInt("count"));
                    }
                }
            }
        }

        CompoundTag custom = new CompoundTag();
        custom.put("prismcraft:dye_ingredients", dyeList);
        result.set(DataComponents.CUSTOM_DATA, CustomData.of(custom));

        itemHandler.setStackInSlot(3, result);
        dye1.shrink(1);
        dye2.shrink(1);
        input.shrink(1);
        setChanged();
    }

    // GET COLOR FROM BLOCK ITEM
    private int getBlockColor(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) { return 0xFFFFFF; }
        Block block = blockItem.getBlock();
        if (block instanceof PrismWoodBlock) {
            return PrismWoodBlock.getColor(stack);
        } else if (block instanceof PrismSlabBlock) {
            return PrismSlabBlock.getColor(stack);
        } else if (block instanceof PrismStairsBlock) {
            return PrismStairsBlock.getColor(stack);
        } else if (block instanceof PrismFenceBlock) {
            return PrismFenceBlock.getColor(stack);
        } else if (block instanceof PrismFenceGateBlock) {
            return PrismFenceGateBlock.getColor(stack);
        } else if (block instanceof PrismButtonBlock) {
            return PrismButtonBlock.getColor(stack);
        } else if (block instanceof PrismPressurePlateBlock) {
            return PrismPressurePlateBlock.getColor(stack);
        } else if (block instanceof PrismDoorBlock) {
            return PrismDoorBlock.getColor(stack);
        } else if (block instanceof PrismTrapdoorBlock) {
            return PrismTrapdoorBlock.getColor(stack);
        } else if (block instanceof PrismConcreteBlock) {
            return PrismConcreteBlock.getColor(stack);
        }
        return 0xFFFFFF;
    }

    // SET COLOR ON BLOCK ITEM
    private void setBlockColor(ItemStack stack, int color) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) { return; }
        Block block = blockItem.getBlock();
        if (block instanceof PrismWoodBlock) {
            PrismWoodBlock.setColor(stack, color);
        } else if (block instanceof PrismSlabBlock) {
            PrismSlabBlock.setColor(stack, color);
        } else if (block instanceof PrismStairsBlock) {
            PrismStairsBlock.setColor(stack, color);
        } else if (block instanceof PrismFenceBlock) {
            PrismFenceBlock.setColor(stack, color);
        } else if (block instanceof PrismFenceGateBlock) {
            PrismFenceGateBlock.setColor(stack, color);
        } else if (block instanceof PrismButtonBlock) {
            PrismButtonBlock.setColor(stack, color);
        } else if (block instanceof PrismPressurePlateBlock) {
            PrismPressurePlateBlock.setColor(stack, color);
        } else if (block instanceof PrismDoorBlock) {
            PrismDoorBlock.setColor(stack, color);
        } else if (block instanceof PrismTrapdoorBlock) {
            PrismTrapdoorBlock.setColor(stack, color);
        } else if (block instanceof PrismConcreteBlock) {
            PrismConcreteBlock.setColor(stack, color);
        }
    }

    private int mixThreeColors(int color1, int color2, int color3) {
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;
        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;
        int r3 = (color3 >> 16) & 0xFF;
        int g3 = (color3 >> 8) & 0xFF;
        int b3 = color3 & 0xFF;
        int r = (r1 + r2 + r3) / 3;
        int g = (g1 + g2 + g3) / 3;
        int b = (b1 + b2 + b3) / 3;
        return (r << 16) | (g << 8) | b;
    }

    private boolean isPrismBlock(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) { return false; }
        Block block = blockItem.getBlock();
        return block instanceof PrismWoodBlock ||
                block instanceof PrismSlabBlock ||
                block instanceof PrismStairsBlock ||
                block instanceof PrismFenceBlock ||
                block instanceof PrismFenceGateBlock ||
                block instanceof PrismButtonBlock ||
                block instanceof PrismPressurePlateBlock ||
                block instanceof PrismDoorBlock ||
                block instanceof PrismTrapdoorBlock ||
                block instanceof PrismConcreteBlock;
    }
}