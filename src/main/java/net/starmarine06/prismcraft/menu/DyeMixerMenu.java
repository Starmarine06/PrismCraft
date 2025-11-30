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
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.DyeItem;
import net.starmarine06.prismcraft.blockentity.DyeMixerBlockEntity;
import net.starmarine06.prismcraft.item.ModItems;

public class DyeMixerMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData data;

    private static final int INPUT_SLOT = 16;
    private static final int RESULT_SLOT = 17;

    private static final int FIRST_INPUT_SLOT = 0;
    private static final int LAST_INPUT_SLOT = 15; // inclusive

    private static final int FIRST_PLAYER_SLOT = 18;
    private static final int LAST_PLAYER_SLOT = 44; // inclusive

    private static final int FIRST_HOTBAR_SLOT = 45;
    private static final int LAST_HOTBAR_SLOT = 53; // inclusive

    public record DyeSlotMapping(int slotIndex, DyeColor dyeColor, int x, int y, int buttonX, int buttonY, float rotation) {}

    public static final DyeSlotMapping[] DYE_SLOTS = {
            new DyeSlotMapping(0,  DyeColor.WHITE,      93,   24,  96, 40, 270),
            new DyeSlotMapping(1,  DyeColor.ORANGE,     120,  157,  124, 149,90),
            new DyeSlotMapping(2,  DyeColor.MAGENTA,    39,  33,  43, 49,270),
            new DyeSlotMapping(3,  DyeColor.LIGHT_BLUE, 11,  108,  27, 111,180),
            new DyeSlotMapping(4,  DyeColor.YELLOW,     93,  166,  96, 158,90),
            new DyeSlotMapping(5,  DyeColor.LIME,       66,  166, 69, 158,90),
            new DyeSlotMapping(6,  DyeColor.PINK,       66, 24, 69, 40,270),
            new DyeSlotMapping(7,  DyeColor.GRAY,       143, 56, 135, 59,0),
            new DyeSlotMapping(8,  DyeColor.LIGHT_GRAY, 120, 33, 124, 49,270),
            new DyeSlotMapping(9,  DyeColor.CYAN,       17, 134, 33, 137,180),
            new DyeSlotMapping(10, DyeColor.PURPLE,     17, 56, 33, 59,180),
            new DyeSlotMapping(11, DyeColor.BLUE,       11, 82, 27, 85,180),
            new DyeSlotMapping(12, DyeColor.BROWN,      149, 108, 141, 111,0),
            new DyeSlotMapping(13, DyeColor.GREEN,      39, 157, 43, 149,90),
            new DyeSlotMapping(14, DyeColor.RED,        143, 134, 135, 137,0),
            new DyeSlotMapping(15, DyeColor.BLACK,      149, 82, 141, 85,0)
    };

    // Client constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, new SimpleContainer(18), new SimpleContainerData(0));
    }

    // Server constructor
    public DyeMixerMenu(int containerId, Inventory playerInventory,
                        Container container, ContainerData data) {
        super(ModMenuTypes.DYE_MIXER.get(), containerId);
        this.container = container;
        this.data = data;

        for (DyeSlotMapping mapping : DYE_SLOTS) {
            this.addSlot(new Slot(container, mapping.slotIndex(), mapping.x(), mapping.y()) {
                @Override
                public boolean mayPlace(ItemStack stack) {
                    if (stack.getItem() instanceof DyeItem dye) {
                        return dye.getDyeColor() == mapping.dyeColor();
                    }
                    return stack.is(ModItems.TITANIUM_DYE.get());
                }
            });
        }
        // Input slot (2)
        this.addSlot(new Slot(container, INPUT_SLOT, 53, 94) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return container.canPlaceItem(INPUT_SLOT, stack);
            }
        });

        // Result slot (3) - craft when taken
        this.addSlot(new Slot(container, RESULT_SLOT, 106, 94) {
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
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 204 + i * 18));
        for (int k = 0; k < 9; ++k)
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 262));

        this.addDataSlots(data);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack ret = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ret = stack.copy();

            if (index == RESULT_SLOT) {
                // result → player (inventory + hotbar)
                if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, LAST_HOTBAR_SLOT + 1, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, ret);
            } else if (index >= FIRST_INPUT_SLOT && index <= LAST_INPUT_SLOT) {
                // from dye inputs → player
                if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, LAST_HOTBAR_SLOT + 1, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index == INPUT_SLOT) {
                // from main input → player
                if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, LAST_HOTBAR_SLOT + 1, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // from player/hotbar → inputs
                if (stack.getItem() instanceof DyeItem dye) {
                    int dyeIndex = dye.getDyeColor().getId(); // 0–15
                    DyeSlotMapping mapping = DYE_SLOTS[dyeIndex];
                    if (!this.moveItemStackTo(stack, mapping.slotIndex(), mapping.slotIndex() + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (container.canPlaceItem(INPUT_SLOT, stack)) {
                    // nem dye → próbáljuk a fő input slotba
                    if (!this.moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= FIRST_PLAYER_SLOT && index <= LAST_PLAYER_SLOT) {
                    // player inventory → hotbar
                    if (!this.moveItemStackTo(stack, FIRST_HOTBAR_SLOT, LAST_HOTBAR_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= FIRST_HOTBAR_SLOT && index <= LAST_HOTBAR_SLOT) {
                    // hotbar → player inventory
                    if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, LAST_PLAYER_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == ret.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }
        return ret;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public boolean isSlotActive(int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        return slot != null && slot.hasItem();
    }
}