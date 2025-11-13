package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import net.starmarine06.prismcraft.item.ModItems;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiConsumer;

public class DyeMixerBlockEntity extends BlockEntity implements MenuProvider, Container {

    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);

    private final ContainerData data = new ContainerData() {
        @Override public int get(int index) { return 0; }
        @Override public void set(int index, int value) {}
        @Override public int getCount() { return 0; }
    };

    public DyeMixerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DYE_MIXER.get(), pos, state);
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack stack = ContainerHelper.removeItem(items, slot, amount);
        if (!stack.isEmpty()) setChanged();
        return stack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        items.set(slot, stack);
        stack.limitSize(getMaxStackSize(stack));
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return switch (slot) {
            case 0, 1 -> stack.getItem() instanceof DyeItem;
            case 2 -> isPrismBlock(stack);
            default -> false;
        };
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
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items, true);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, items);
    }

    public void drops() {
        for (int i = 0; i < getContainerSize(); i++) {
            Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), getItem(i));
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, DyeMixerBlockEntity blockEntity) {
        if (!level.isClientSide() && blockEntity.canCraft()) {
            blockEntity.craft();
            ItemStack input1 = blockEntity.getItem(0);
            ItemStack input2 = blockEntity.getItem(1);
            ItemStack input3 = blockEntity.getItem(2);
            ItemStack output = blockEntity.getItem(3);

            // Check for two titanium dyes + colored block
            boolean hasTwoTitaniumDyes =
                    input1.is(ModItems.TITANIUM_DYE.get()) && input2.is(ModItems.TITANIUM_DYE.get());

            boolean isColoredBlock =
                    input3.getItem() instanceof IPrismColoredBlock ||
                            input3.getHoverName().getString().contains("Prism"); // fallback if needed

            if (hasTwoTitaniumDyes && isColoredBlock && output.isEmpty()) {
                // Remove inputs
                input1.shrink(1);
                input2.shrink(1);
                input3.shrink(1);

                // Create reset (white) version of the block
                ItemStack resetBlock = new ItemStack(input3.getItem());
                PrismColoredBlockEntity pe = new PrismColoredBlockEntity(pos, state);
                pe.setColor(0xFFFFFF); // reset to white

                blockEntity.setItem(3,resetBlock);
                setChanged(level, pos, state);
            }
            level.playSound(null, pos, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 0, 0.1, 0);

        }
    }

    private boolean canCraft() {
        ItemStack dye1 = getItem(0);
        ItemStack dye2 = getItem(1);
        ItemStack input = getItem(2);
        ItemStack output = getItem(3);
        return !dye1.isEmpty() && !dye2.isEmpty() && !input.isEmpty()
                && dye1.getItem() instanceof DyeItem
                && dye2.getItem() instanceof DyeItem
                && isPrismBlock(input)
                && output.isEmpty();
    }

    private void craft() {
        ItemStack dye1 = getItem(0);
        ItemStack dye2 = getItem(1);
        ItemStack input = getItem(2);

        int color1 = ((DyeItem) dye1.getItem()).getDyeColor().getTextureDiffuseColor();
        int color2 = ((DyeItem) dye2.getItem()).getDyeColor().getTextureDiffuseColor();
        int existingColor = getBlockColor(input);
        int mixedColor = mixThreeColors(existingColor, color1, color2);

        ItemStack result = new ItemStack(input.getItem(), 1);
        setBlockColor(result, mixedColor);

        ListTag dyeList = new ListTag();

        BiConsumer<ResourceLocation, Integer> addOrMergeDye = (dyeRL, amount) -> {
            for (int i = 0; i < dyeList.size(); i++) {
                CompoundTag tag = dyeList.getCompoundOrEmpty(i);
                if (tag.getString("id").equals(dyeRL.toString())) {
                    int current = tag.getIntOr("count",1);
                    tag.putInt("count", current + amount);
                    return;
                }
            }
            CompoundTag dyeTag = new CompoundTag();
            dyeTag.putString("id", dyeRL.toString());
            dyeTag.putInt("count", amount);
            dyeList.add(dyeTag);
        };

// Get dye item keys safely
        ResourceLocation dye1RL = BuiltInRegistries.ITEM.getKey(dye1.getItem());
        ResourceLocation dye2RL = BuiltInRegistries.ITEM.getKey(dye2.getItem());

        addOrMergeDye.accept(dye1RL, 1);
        addOrMergeDye.accept(dye2RL, 1);

// Safely unwrap CustomData tag
        CustomData inputCustomData = input.get(DataComponents.CUSTOM_DATA);
        if (inputCustomData != null) {
            CompoundTag customTag = inputCustomData.copyTag().asCompound().orElse(null);
            if (customTag != null && customTag.contains("prismcraft:dye_ingredients")) {
                ListTag oldList = customTag.getListOrEmpty("prismcraft:dye_ingredients");
                for (int i = 0; i < oldList.size(); i++) {
                    CompoundTag t = oldList.getCompoundOrEmpty(i);
                    String idStr = t.getStringOr("id","id");
                    ResourceLocation rl = ResourceLocation.parse(idStr);
                    int count = t.getIntOr("count",1);
                    addOrMergeDye.accept(rl, count);
                }
            }
        }

        CompoundTag custom = new CompoundTag();
        custom.put("prismcraft:dye_ingredients", dyeList);
        result.set(DataComponents.CUSTOM_DATA, CustomData.of(custom));

        setItem(3, result);
        dye1.shrink(1);
        dye2.shrink(1);
        input.shrink(1);
        setChanged();
    }

    private int getBlockColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }

    private void setBlockColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
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
        if (!(stack.getItem() instanceof BlockItem blockItem)) return false;
        return blockItem.getBlock() instanceof IPrismColoredBlock;
    }
}

