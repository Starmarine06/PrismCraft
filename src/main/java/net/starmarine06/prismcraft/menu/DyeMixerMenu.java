package net.starmarine06.prismcraft.menu;

import net.minecraft.core.BlockPos;
import net.neoforged.neoforge.network.PacketDistributor;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.starmarine06.prismcraft.blockentity.DyeMixerBlockEntity;
import net.starmarine06.prismcraft.item.ModItems;
import net.starmarine06.prismcraft.network.SelectionSyncPacket;

public class DyeMixerMenu extends AbstractContainerMenu {
	private final Container container;
	private final ContainerData data;
	private BlockPos blockPos = BlockPos.ZERO;

	private boolean hasSyncedSelection = false;

	private static final int NUM_DYE_SLOTS = 17;
	private static final int INPUT_SLOT = NUM_DYE_SLOTS;      // 17
	private static final int RESULT_SLOT = NUM_DYE_SLOTS + 1; // 18

	private static final int FIRST_INPUT_SLOT = 0;
	private static final int LAST_INPUT_SLOT = NUM_DYE_SLOTS - 1; // 16

	private static final int FIRST_PLAYER_SLOT = RESULT_SLOT + 1; // 19
	private static final int LAST_PLAYER_SLOT = FIRST_PLAYER_SLOT + 26; // 19..45
	private static final int FIRST_HOTBAR_SLOT = LAST_PLAYER_SLOT + 1; // 46
	private static final int LAST_HOTBAR_SLOT = FIRST_HOTBAR_SLOT + 8; // 46..54

	// selection state per dye slot (0–16)
	private final boolean[] selected = new boolean[NUM_DYE_SLOTS];

	public record DyeSlotMapping(int slotIndex, DyeColor dyeColor, int x, int y, int buttonX, int buttonY, float rotation) {}

	public static final DyeSlotMapping[] DYE_SLOTS = {
		new DyeSlotMapping(0,  DyeColor.WHITE,      93,   24,  96, 40, 270),
		new DyeSlotMapping(1,  DyeColor.ORANGE,     120, 157, 123, 147, 90),
		new DyeSlotMapping(2,  DyeColor.MAGENTA,    40,  33,  43, 49, 270),
		new DyeSlotMapping(3,  DyeColor.LIGHT_BLUE, 11, 108,  27, 111, 180),
		new DyeSlotMapping(4,  DyeColor.YELLOW,     93, 166,  96, 156, 90),
		new DyeSlotMapping(5,  DyeColor.LIME,       66, 166,  69, 156, 90),
		new DyeSlotMapping(6,  DyeColor.PINK,       66,  24,  69, 40, 270),
		new DyeSlotMapping(7,  DyeColor.GRAY,       143, 56, 133, 59, 0),
		new DyeSlotMapping(8,  DyeColor.LIGHT_GRAY, 120, 33, 123, 49, 270),
		new DyeSlotMapping(9,  DyeColor.CYAN,       17, 134,  33, 137, 180),
		new DyeSlotMapping(10, DyeColor.PURPLE,     17,  56,  33, 59, 180),
		new DyeSlotMapping(11, DyeColor.BLUE,       11,  82,  27, 85, 180),
		new DyeSlotMapping(12, DyeColor.BROWN,      149,108, 139,111, 0),
		new DyeSlotMapping(13, DyeColor.GREEN,      40, 157,  43,147, 90),
		new DyeSlotMapping(14, DyeColor.RED,        143,134, 133,137, 0),
		new DyeSlotMapping(15, DyeColor.BLACK,      149, 82, 139, 85, 0),
		// Titanium slot
		new DyeSlotMapping(16, null, 80, 63, 83, 79, 270)
	};

	// client ctor
	public DyeMixerMenu(int containerId, Inventory playerInventory, RegistryFriendlyByteBuf extraData) {
		super(ModMenuTypes.DYE_MIXER.get(), containerId);
		this.container = new SimpleContainer(NUM_DYE_SLOTS + 2);
		this.data = new SimpleContainerData(0);

		if (playerInventory.player.level().isClientSide()) {
			BlockPos playerPos = playerInventory.player.blockPosition();
			for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-10, -10, -10), playerPos.offset(10, 10, 10))) {
				BlockEntity be = playerInventory.player.level().getBlockEntity(pos);
				if (be instanceof DyeMixerBlockEntity) {
					this.blockPos = pos.immutable();
					break;
				}
			}
		}

		addAllSlots(playerInventory);
	}

	// server ctor
	public DyeMixerMenu(int containerId, Inventory playerInventory, Container container, ContainerData data) {
		super(ModMenuTypes.DYE_MIXER.get(), containerId);
		this.container = container;
		this.data = data;

		if (container instanceof DyeMixerBlockEntity be) {
			this.blockPos = be.getBlockPos();
			boolean[] beSelected = be.getSelectedSlots();
			System.arraycopy(beSelected, 0, selected, 0, NUM_DYE_SLOTS);
		}

		addAllSlots(playerInventory);
	}

	private void addAllSlots(Inventory playerInventory) {
		// dye slots
		for (DyeSlotMapping mapping : DYE_SLOTS) {
			this.addSlot(new Slot(container, mapping.slotIndex(), mapping.x(), mapping.y()) {
				@Override
				public boolean mayPlace(ItemStack stack) {
					if (mapping.dyeColor() != null && stack.getItem() instanceof DyeItem dye) {
						return dye.getDyeColor() == mapping.dyeColor();
					}
					if (mapping.dyeColor() == null) {
						return stack.is(ModItems.TITANIUM_DYE.get());
					}
					return false;
				}
			});
		}

		// input slot
		this.addSlot(new Slot(container, INPUT_SLOT, 53, 94) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return container.canPlaceItem(INPUT_SLOT, stack);
			}
		});

		// result slot
		this.addSlot(new Slot(container, RESULT_SLOT, 106, 94) {
			@Override public boolean mayPlace(ItemStack stack) { return false; }
			@Override public boolean mayPickup(Player player) { return this.hasItem(); }

			@Override
			public void onTake(Player player, ItemStack stack) {
				if (container instanceof DyeMixerBlockEntity be) {
					be.onResultTaken(player, stack);
				}
				super.onTake(player, stack);
			}
		});

		// player inventory
		for (int i = 0; i < 3; ++i)
			for (int j = 0; j < 9; ++j)
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 204 + i * 18));
		for (int k = 0; k < 9; ++k)
			this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 262));

		this.addDataSlots(data);
	}

	public void toggleSelected(int dyeSlot) {
		if (dyeSlot < 0 || dyeSlot >= NUM_DYE_SLOTS) return;
		selected[dyeSlot] = !selected[dyeSlot];

		if (container instanceof DyeMixerBlockEntity be) {
			be.setSelectedSlots(selected);
			be.updateResultPreview();
		}
	}

	public boolean isSlotSelected(int dyeSlot) {
		return dyeSlot >= 0 && dyeSlot < NUM_DYE_SLOTS && selected[dyeSlot];
	}

	public void setSelectedSlots(boolean[] arr) {
		if (arr.length != NUM_DYE_SLOTS) return;
		System.arraycopy(arr, 0, selected, 0, NUM_DYE_SLOTS);
	}

	// Reset all selections
	public void resetSelected() {
		for (int i = 0; i < NUM_DYE_SLOTS; i++) {
			selected[i] = false;
		}

		// Sync to block entity if available
		if (container instanceof DyeMixerBlockEntity be) {
			be.setSelectedSlots(selected);
			be.updateResultPreview();
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack ret = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);

		if (slot != null && slot.hasItem()) {
			ItemStack stack = slot.getItem();
			ret = stack.copy();

			if (index == RESULT_SLOT) {
				if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, LAST_HOTBAR_SLOT + 1, true)) {
					return ItemStack.EMPTY;
				}
				slot.onQuickCraft(stack, ret);
			} else if (index >= FIRST_INPUT_SLOT && index <= LAST_INPUT_SLOT) {
				if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, LAST_HOTBAR_SLOT + 1, true)) {
					return ItemStack.EMPTY;
				}
			} else if (index == INPUT_SLOT) {
				if (!this.moveItemStackTo(stack, FIRST_PLAYER_SLOT, LAST_HOTBAR_SLOT + 1, true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (stack.getItem() instanceof DyeItem dye) {
					int dyeIndex = dye.getDyeColor().getId();
					DyeSlotMapping mapping = DYE_SLOTS[dyeIndex];
					if (!this.moveItemStackTo(stack, mapping.slotIndex(), mapping.slotIndex() + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (stack.is(ModItems.TITANIUM_DYE.get())) {
					if (!this.moveItemStackTo(stack, 16, 17, false)) { // Titanium slot
						return ItemStack.EMPTY;
					}
				} else if (container.canPlaceItem(INPUT_SLOT, stack)) {
					if (!this.moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= FIRST_PLAYER_SLOT && index <= LAST_PLAYER_SLOT) {
					if (!this.moveItemStackTo(stack, FIRST_HOTBAR_SLOT, LAST_HOTBAR_SLOT + 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= FIRST_HOTBAR_SLOT && index <= LAST_HOTBAR_SLOT) {
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

	private int tickCount = 0;

	@Override
	public void broadcastChanges() {
		super.broadcastChanges();

		if (!hasSyncedSelection && container instanceof DyeMixerBlockEntity be) {
			tickCount++;
			if (tickCount >= 3) {
				if (be.getLevel() != null && !be.getLevel().isClientSide()) {
					for (Player player : be.getLevel().players()) {
						if (player instanceof ServerPlayer sp && player.containerMenu == this) {
							PacketDistributor.sendToPlayer(sp, new SelectionSyncPacket(be.getSelectedSlots()));
							hasSyncedSelection = true;
							break;
						}
					}
				}
			}
		}
	}

	public boolean isSlotActive(int slotIndex) {
		Slot slot = this.slots.get(slotIndex);
		return slot != null && slot.hasItem();
	}

	public void syncFromBlockEntity() {
		if (container instanceof DyeMixerBlockEntity be) {
			boolean[] beSelected = be.getSelectedSlots();
			System.arraycopy(beSelected, 0, selected, 0, NUM_DYE_SLOTS);
		}
	}

	public Container getContainer() {
		return container;
	}

	public BlockPos getBlockPos() {
		return blockPos;
	}
}