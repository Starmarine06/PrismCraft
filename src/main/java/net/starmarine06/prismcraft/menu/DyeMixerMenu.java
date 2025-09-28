package net.starmarine06.prismcraft.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.FriendlyByteBuf;

public class DyeMixerMenu extends AbstractContainerMenu {
    private final SimpleContainer handler;
    private final net.minecraft.core.BlockPos pos;

    public DyeMixerMenu(int id, Inventory playerInv, SimpleContainer handler, net.minecraft.core.BlockPos pos) {
        super(net.starmarine06.prismcraft.registry.ModRegistry.DYE_MIXER_MENU.get(), id);
        this.handler = handler;
        this.pos = pos;

        this.addSlot(new Slot(handler, 0, 44, 35));
        this.addSlot(new Slot(handler, 1, 62, 17));
        this.addSlot(new Slot(handler, 2, 80, 17));
        this.addSlot(new Slot(handler, 3, 116, 35) {
            @Override public boolean mayPlace(ItemStack stack) { return false; }
        });

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
        }
    }

    public static DyeMixerMenu fromNetwork(int id, Inventory inv, FriendlyByteBuf buf) {
        var pos = buf.readBlockPos();
        return new DyeMixerMenu(id, inv, new SimpleContainer(4), pos);
    }

    public SimpleContainer getInventory() { return handler; }

    @Override
    public boolean stillValid(net.minecraft.world.entity.player.Player player) {
        return true;
    }
}
