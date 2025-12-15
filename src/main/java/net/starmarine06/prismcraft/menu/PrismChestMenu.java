package net.starmarine06.prismcraft.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.starmarine06.prismcraft.blockentity.PrismChestBlockEntity;
import org.jetbrains.annotations.Nullable;

public class PrismChestMenu extends AbstractContainerMenu {
    private final Container container;
    private final int chestColor;

    public PrismChestMenu(int containerId, Inventory playerInventory, @Nullable PrismChestBlockEntity blockEntity) {
        super(ModMenuTypes.PRISM_CHEST.get(), containerId);
        this.container = blockEntity != null ? blockEntity : new SimpleContainer(27);
        this.chestColor = blockEntity != null ? blockEntity.getColor() : 0xFFFFFF;

        // Chest slots (27 slots, 3 rows of 9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(container, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }

        // Player inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 86 + row * 18));
            }
        }

        // Player hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 144));
        }
    }

    public int getChestColor() {
        return chestColor;
    }

    public Container getContainer() {
        return this.container;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();
            if (index < 27) {
                if (!this.moveItemStackTo(slotStack, 27, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(slotStack, 0, 27, false)) {
                return ItemStack.EMPTY;
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        if (container instanceof PrismChestBlockEntity chest) {
            return chest.stillValid(player);
        }
        return true;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (container instanceof PrismChestBlockEntity chest && !player.level().isClientSide()) {
            chest.stopOpen(player);
        }
    }
}