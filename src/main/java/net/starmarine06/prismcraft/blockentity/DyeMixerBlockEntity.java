package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import net.starmarine06.prismcraft.item.ModItems;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class DyeMixerBlockEntity extends BlockEntity implements MenuProvider, Container {

    // Configuration - change these to add more slots
    private static final int TOTAL_SLOTS = 4; // Total container size
    private static final int INPUT_SLOT = TOTAL_SLOTS - 2; // Second to last slot
    private static final int RESULT_SLOT = TOTAL_SLOTS - 1; // Last slot

    private NonNullList<ItemStack> items = NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY);

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
        return TOTAL_SLOTS;
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
        if (!stack.isEmpty()) {
            stack.limitSize(getMaxStackSize(stack));
        }
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
        if (slot == INPUT_SLOT) {
            return isPrismBlock(stack);
        } else if (slot == RESULT_SLOT) {
            return false; // Result slot - cannot place
        } else {
            // All other slots are dye slots - accept DyeItem OR titanium dye
            return stack.getItem() instanceof DyeItem || stack.is(ModItems.TITANIUM_DYE.get());
        }
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

    // Tick method - updates the result preview
    public static void tick(Level level, BlockPos pos, BlockState state, DyeMixerBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            blockEntity.updateResultPreview();
        }
    }

    private void updateResultPreview() {
        if (canCraft()) {
            ItemStack result = calculateResult();
            setItem(RESULT_SLOT, result);

            // ensure clients see the new result
            if (level != null && !level.isClientSide()) {
                setChanged(); // mark dirty for saving
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                System.out.println("[DyeMixerBlockEntity] server set result -> " + result); // debug log
            }
        } else {
            if (!getItem(RESULT_SLOT).isEmpty()) {
                setItem(RESULT_SLOT, ItemStack.EMPTY);
                if (level != null && !level.isClientSide()) {
                    setChanged();
                    level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
                    System.out.println("[DyeMixerBlockEntity] server cleared result");
                }
            }
        }
    }


    private boolean canCraft() {
        ItemStack input = getItem(INPUT_SLOT);
        if (input.isEmpty() || !isPrismBlock(input)) {
            return false;
        }

        // Check if we can do normal dyeing OR reset
        return canDye() || canReset();
    }

    private boolean canDye() {
        // Check if at least one regular dye slot has a dye
        for (int i = 0; i < INPUT_SLOT; i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof DyeItem) {
                return true;
            }
        }
        return false;
    }

    private boolean canReset() {
        ItemStack input = getItem(INPUT_SLOT);
        if (input.isEmpty() || !isPrismBlock(input)) {
            return false;
        }

        // Check if we have at least one titanium dye and NO regular dyes
        boolean hasTitanium = false;
        boolean hasRegularDye = false;

        for (int i = 0; i < INPUT_SLOT; i++) {
            ItemStack stack = getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.is(ModItems.TITANIUM_DYE.get())) {
                hasTitanium = true;
            } else if (stack.getItem() instanceof DyeItem) {
                hasRegularDye = true;
            }
        }

        // Can reset if we have titanium dye(s) and NO regular dyes
        return hasTitanium && !hasRegularDye;
    }

    private ItemStack calculateResult() {
        ItemStack input = getItem(INPUT_SLOT);

        // Priority 1: Check for reset recipe
        if (canReset()) {
            return processResetRecipe(input);
        }

        // Priority 2: Normal dyeing
        if (canDye()) {
            List<ItemStack> dyes = getRegularDyeSlots();
            List<Integer> colors = new ArrayList<>();

            // Add existing color from input block
            int existingColor = getBlockColor(input);
            colors.add(existingColor);

            // Add all dye colors
            for (ItemStack dye : dyes) {
                if (dye.getItem() instanceof DyeItem dyeItem) {
                    colors.add(dyeItem.getDyeColor().getTextureDiffuseColor());
                }
            }

            int mixedColor = mixColors(colors);

            ItemStack result = new ItemStack(input.getItem(), 1);
            setBlockColor(result, mixedColor);

            // Track dye ingredients
            addDyeIngredients(result, dyes, input);

            return result;
        }

        return ItemStack.EMPTY;
    }

    // Get only regular dye slots (not titanium)
    private List<ItemStack> getRegularDyeSlots() {
        List<ItemStack> dyes = new ArrayList<>();
        for (int i = 0; i < INPUT_SLOT; i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof DyeItem) {
                dyes.add(stack);
            }
        }
        return dyes;
    }

    // Get all dye slots including titanium
    private List<ItemStack> getDyeSlots() {
        List<ItemStack> dyes = new ArrayList<>();
        for (int i = 0; i < INPUT_SLOT; i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty() && (stack.getItem() instanceof DyeItem || stack.is(ModItems.TITANIUM_DYE.get()))) {
                dyes.add(stack);
            }
        }
        return dyes;
    }

    // Called when player takes from result slot
    public void onResultTaken(Player player, ItemStack result) {
        // Consume all dye slots
        for (int i = 0; i < INPUT_SLOT; i++) {
            ItemStack dye = getItem(i);
            if (!dye.isEmpty()) {
                dye.shrink(1);
            }
        }

        // Consume input
        ItemStack input = getItem(INPUT_SLOT);
        if (!input.isEmpty()) {
            input.shrink(1);
        }

        // Play sound and particle effects
        if (level != null) {
            level.playSound(null, worldPosition, SoundEvents.BREWING_STAND_BREW, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.addParticle(ParticleTypes.END_ROD, worldPosition.getX() + 0.5, worldPosition.getY() + 1.0, worldPosition.getZ() + 0.5, 0, 0.1, 0);
        }

        setChanged();
    }

    private void addDyeIngredients(ItemStack result, List<ItemStack> dyes, ItemStack input) {
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

        // Get existing dye ingredients from input
        CustomData inputCustomData = input.get(DataComponents.CUSTOM_DATA);
        if (inputCustomData != null) {
            CompoundTag customTag = inputCustomData.copyTag();
            if (customTag.contains("prismcraft:dye_ingredients")) {
                ListTag oldList = customTag.getListOrEmpty("prismcraft:dye_ingredients");
                for (int i = 0; i < oldList.size(); i++) {
                    CompoundTag t = oldList.getCompoundOrEmpty(i);
                    String idStr = t.getStringOr("id","");
                    if (!idStr.isEmpty()) {
                        ResourceLocation rl = ResourceLocation.parse(idStr);
                        int count = t.getIntOr("count",1);
                        if (count == 0) count = 1;
                        addOrMergeDye.accept(rl, count);
                    }
                }
            }
        }

        // Add all new dyes
        for (ItemStack dye : dyes) {
            ResourceLocation dyeRL = BuiltInRegistries.ITEM.getKey(dye.getItem());
            addOrMergeDye.accept(dyeRL, 1);
        }

        // Save dye ingredients to result
        CompoundTag custom = new CompoundTag();
        custom.put("prismcraft:dye_ingredients", dyeList);
        result.set(DataComponents.CUSTOM_DATA, CustomData.of(custom));
    }

    private static ItemStack processResetRecipe(ItemStack input) {
        if (input.isEmpty()) return ItemStack.EMPTY;

        ItemStack result = input.copyWithCount(1);

        // Remove all components from the item
        result.remove(DataComponents.DYED_COLOR);
        result.remove(DataComponents.CUSTOM_DATA);

        return result;
    }

    private int getBlockColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }

    private void setBlockColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
    }

    // Mix any number of colors
    private int mixColors(List<Integer> colors) {
        if (colors.isEmpty()) return 0xFFFFFF;
        if (colors.size() == 1) return colors.get(0);

        int totalR = 0, totalG = 0, totalB = 0;

        for (int color : colors) {
            totalR += (color >> 16) & 0xFF;
            totalG += (color >> 8) & 0xFF;
            totalB += color & 0xFF;
        }

        int r = totalR / colors.size();
        int g = totalG / colors.size();
        int b = totalB / colors.size();

        return (r << 16) | (g << 8) | b;
    }

    private boolean isPrismBlock(ItemStack stack) {
        if (!(stack.getItem() instanceof BlockItem blockItem)) return false;
        return blockItem.getBlock() instanceof IPrismColoredBlock;
    }

    // Helper methods for menu to know slot configuration
    public static int getInputSlotIndex() {
        return INPUT_SLOT;
    }

    public static int getResultSlotIndex() {
        return RESULT_SLOT;
    }

    public static int getTotalSlots() {
        return TOTAL_SLOTS;
    }


    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        // reuse saveWithoutMetadata like the Pedestal example - this returns a CompoundTag and includes inventory serialization
        return this.saveWithoutMetadata(pRegistries);
    }

}