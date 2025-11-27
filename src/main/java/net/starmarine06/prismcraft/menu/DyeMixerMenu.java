package net.starmarine06.prismcraft.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.DyeItem;
import net.starmarine06.prismcraft.blockentity.DyeMixerBlockEntity;

public class DyeMixerMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    private static final int DYE_SLOT_1 = 0;
    private static final int DYE_SLOT_2 = 1;
    private static final int INPUT_SLOT = 2;
    private static final int RESULT_SLOT = 3;

    // Client constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, new SimpleContainer(4), new SimpleContainerData(0));
    }

    // Server constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory,
                        Container container, ContainerData data) {
        super(ModMenuTypes.DYE_MIXER.get(), containerId);
        this.container = container;
        this.data = data;

        // Dye slots (0, 1)
        this.addSlot(new Slot(container, DYE_SLOT_1, 27, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof DyeItem;
            }
        });
        this.addSlot(new Slot(container, DYE_SLOT_2, 51, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof DyeItem;
            }
        });

        // Input slot (2)
        this.addSlot(new Slot(container, INPUT_SLOT, 80, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return container.canPlaceItem(INPUT_SLOT, stack);
            }
        });

        // Result slot (3) - craft when taken
        this.addSlot(new Slot(container, RESULT_SLOT, 134, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return this.hasItem();
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                // Notify block entity that result was taken
                if (container instanceof DyeMixerBlockEntity blockEntity) {
                    blockEntity.onResultTaken(player, stack);
                }
                super.onTake(player, stack);
            }
        });

        // Player inventory
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for (int k = 0; k < 9; ++k)
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));

        this.addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();

            if (index == RESULT_SLOT) {
                // Taking from result slot
                if (!this.moveItemStackTo(slotStack, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(slotStack, itemstack);
            } else if (index < 4) {
                // From input slots to player inventory
                if (!this.moveItemStackTo(slotStack, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // From player inventory to input slots
                if (slotStack.getItem() instanceof DyeItem) {
                    // Try to add dyes to dye slots
                    if (!this.moveItemStackTo(slotStack, DYE_SLOT_1, INPUT_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (container.canPlaceItem(INPUT_SLOT, slotStack)) {
                    // Try to add prism blocks to input slot
                    if (!this.moveItemStackTo(slotStack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 31) {
                    // Player inventory to hotbar
                    if (!this.moveItemStackTo(slotStack, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    // Hotbar to player inventory
                    if (!this.moveItemStackTo(slotStack, 4, 31, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, slotStack);
        }
        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }
}