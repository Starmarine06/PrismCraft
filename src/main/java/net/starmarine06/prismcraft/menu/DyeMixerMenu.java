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
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.DyedItemColor;
import net.starmarine06.prismcraft.block.ModBlocks;

public class DyeMixerMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;
    private final Container resultContainer;

    private static final int DYE_SLOT_1 = 0;
    private static final int DYE_SLOT_2 = 1;
    private static final int INPUT_SLOT = 2;
    private static final int RESULT_SLOT = 3;

    // Client constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, new SimpleContainer(3), new SimpleContainerData(0));
    }

    // Server constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory,
                        Container container, ContainerData data) {
        super(ModMenuTypes.DYE_MIXER.get(), containerId);
        this.container = container;
        this.data = data;
        this.resultContainer = new SimpleContainer(1);

        // Dye slots (0, 1)
        this.addSlot(new Slot(container, DYE_SLOT_1, 27, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof DyeItem;
            }

            @Override
            public void setChanged() {
                super.setChanged();
                updateResultPreview();
            }
        });
        this.addSlot(new Slot(container, DYE_SLOT_2, 51, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof DyeItem;
            }

            @Override
            public void setChanged() {
                super.setChanged();
                updateResultPreview();
            }
        });

        // Input slot (2)
        this.addSlot(new Slot(container, INPUT_SLOT, 80, 35) {
            @Override
            public void setChanged() {
                super.setChanged();
                updateResultPreview();
            }
        });

        // Result preview slot
        this.addSlot(new Slot(resultContainer, 0, 134, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public boolean mayPickup(Player player) {
                return false;
            }
        });

        // Player inventory
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for (int k = 0; k < 9; ++k)
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));

        this.addDataSlots(data);

        // Initial preview update
        updateResultPreview();
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        updateResultPreview();
    }

    private void updateResultPreview() {
        ItemStack result = calculateMixedColor();
        System.out.println("UPDATE PREVIEW: " + result);
        this.resultContainer.setItem(0, result);
    }

    private ItemStack calculateMixedColor() {
        ItemStack dye1 = this.container.getItem(DYE_SLOT_1);
        ItemStack dye2 = this.container.getItem(DYE_SLOT_2);
        ItemStack input = this.container.getItem(INPUT_SLOT);

        System.out.println("CALCULATE: dye1=" + dye1 + ", dye2=" + dye2 + ", input=" + input);

        if (input.isEmpty() || (dye1.isEmpty() && dye2.isEmpty())) {
            System.out.println("EMPTY RESULT - missing inputs");
            return ItemStack.EMPTY;
        }

        int color = mixColors(dye1, dye2);
        System.out.println("MIXED COLOR: " + color);

        ItemStack result = new ItemStack(ModBlocks.PRISM_CONCRETE_POWDER.get());
        result.set(DataComponents.DYED_COLOR, new DyedItemColor(color));

        System.out.println("CREATED RESULT: " + result);
        return result;
    }

    private int mixColors(ItemStack dye1, ItemStack dye2) {
        int color1 = 0;
        int color2 = 0;
        int count = 0;

        if (!dye1.isEmpty() && dye1.getItem() instanceof DyeItem dyeItem1) {
            color1 = dyeItem1.getDyeColor().getTextureDiffuseColor();
            count++;
        }

        if (!dye2.isEmpty() && dye2.getItem() instanceof DyeItem dyeItem2) {
            color2 = dyeItem2.getDyeColor().getTextureDiffuseColor();
            count++;
        }

        if (count == 0) return 0xFFFFFF;
        if (count == 1) return color1 != 0 ? color1 : color2;

        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        int r = (r1 + r2) / 2;
        int g = (g1 + g2) / 2;
        int b = (b1 + b2) / 2;

        return (r << 16) | (g << 8) | b;
    }

    public void craftItem(Player player) {
        ItemStack dye1 = this.container.getItem(DYE_SLOT_1);
        ItemStack dye2 = this.container.getItem(DYE_SLOT_2);
        ItemStack input = this.container.getItem(INPUT_SLOT);

        if (input.isEmpty() || (dye1.isEmpty() && dye2.isEmpty())) {
            return;
        }

        int color = mixColors(dye1, dye2);
        ItemStack result = new ItemStack(ModBlocks.PRISM_CONCRETE_POWDER.get());
        result.set(DataComponents.DYED_COLOR, new DyedItemColor(color));

        if (!dye1.isEmpty()) dye1.shrink(1);
        if (!dye2.isEmpty()) dye2.shrink(1);
        if (!input.isEmpty()) input.shrink(1);

        if (!player.getInventory().add(result)) {
            player.drop(result, false);
        }

        updateResultPreview();
    }

    public boolean canCraft() {
        boolean can = !this.resultContainer.getItem(0).isEmpty();
        System.out.println("CAN CRAFT: " + can + ", result=" + this.resultContainer.getItem(0));
        return can;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            itemstack = slotStack.copy();

            if (index == RESULT_SLOT) {
                return ItemStack.EMPTY;
            } else if (index < 4) {
                if (!this.moveItemStackTo(slotStack, 4, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (slotStack.getItem() instanceof DyeItem) {
                    if (!this.moveItemStackTo(slotStack, DYE_SLOT_1, INPUT_SLOT, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(slotStack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
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
        return true;
    }
}