package net.starmarine06.prismcraft.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.starmarine06.prismcraft.blockentity.DyeMixerBlockEntity;

public class DyeMixerMenu extends AbstractContainerMenu {
    private final DyeMixerBlockEntity blockEntity;
    private final ContainerData data;

    // Client constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, null, new SimpleContainerData(0));
    }

    // Server constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory,
                        DyeMixerBlockEntity blockEntity, ContainerData data) {
        super(ModMenuTypes.DYE_MIXER.get(), containerId);
        this.blockEntity = blockEntity;
        this.data = data;

        ItemStackHandler handler = blockEntity != null ?
                blockEntity.getItemHandler() : new ItemStackHandler(4);

        // Add dye slots (0, 1)
        this.addSlot(new SlotItemHandler(handler, 0, 27, 35));
        this.addSlot(new SlotItemHandler(handler, 1, 51, 35));

        // Add input block slot (2)
        this.addSlot(new SlotItemHandler(handler, 2, 80, 35));

        // Add output slot (3)
        this.addSlot(new SlotItemHandler(handler, 3, 134, 35));

        // Player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // Player hotbar
        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();

            if (index < 4) {
                // Moving from container to inventory
                if (!this.moveItemStackTo(slotStack, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Moving from inventory to container
                if (!this.moveItemStackTo(slotStack, 0, 4, false)) {
                    return ItemStack.EMPTY;
                }
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
        return blockEntity != null &&
                player.distanceToSqr(blockEntity.getBlockPos().getX() + 0.5,
                        blockEntity.getBlockPos().getY() + 0.5,
                        blockEntity.getBlockPos().getZ() + 0.5) <= 64.0;
    }
}
