package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import net.starmarine06.prismcraft.menu.PrismBarrelMenu;
import org.jetbrains.annotations.Nullable;

public class PrismBarrelBlockEntity extends BlockEntity implements MenuProvider, IPrismColoredBlock {
    private int color = 0xFFFFFF;
    private final ItemStackHandler itemHandler = new ItemStackHandler(27) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    public PrismBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRISM_BARREL.get(), pos, state);
    }

    public int getColor() {
        System.out.println("[BARREL COLOR DEBUG] getColor() called at " + worldPosition + ", color=" + Integer.toHexString(color));
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        setChanged();
        if (level != null && !level.isClientSide()) {
            BlockState state = getBlockState();
            level.sendBlockUpdated(getBlockPos(), state, state, 3);
        }
    }

    // THIS IS THE METHOD THAT WAS MISSING
    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.prismcraft.prism_barrel");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new PrismBarrelMenu(containerId, playerInventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("Color", color);
        tag.put("inventory", itemHandler.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        color = tag.getInt("Color");
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putInt("Color", color);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        loadAdditional(tag, registries);
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void drops() {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(),
                    worldPosition.getZ(), itemHandler.getStackInSlot(i));
        }
    }

    private int viewers = 0;
    public float openness;
    public float prevOpenness;

    // Animation ticking (client side)
    public static void clientTick(Level level, BlockPos pos, BlockState state, PrismBarrelBlockEntity tile) {
        tile.prevOpenness = tile.openness;
        if (state.getValue(net.minecraft.world.level.block.BarrelBlock.OPEN)) {
            tile.openness = Math.min(tile.openness + 0.1F, 1.0F);
        } else {
            tile.openness = Math.max(tile.openness - 0.1F, 0.0F);
        }
    }

    // Call when open/close (from your menu container logic)
    public void startOpen(Player player) {
        if (!hasLevel()) return;
        viewers++;
        if (viewers == 1 && !getBlockState().getValue(net.minecraft.world.level.block.BarrelBlock.OPEN)) {
            // First opener: set open state and play sound
            level.setBlock(worldPosition, getBlockState().setValue(net.minecraft.world.level.block.BarrelBlock.OPEN, true), 3);
            level.playSound(null, worldPosition, net.minecraft.sounds.SoundEvents.BARREL_OPEN, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
        } else if (viewers > 1 && getBlockState().getValue(net.minecraft.world.level.block.BarrelBlock.OPEN)) {
            // Additional opener: just play sound
            level.playSound(null, worldPosition, net.minecraft.sounds.SoundEvents.BARREL_OPEN, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
    public void stopOpen(Player player) {
        if (!hasLevel()) return;
        viewers = Math.max(0, viewers - 1);
        if (viewers == 0 && getBlockState().getValue(net.minecraft.world.level.block.BarrelBlock.OPEN)) {
            level.setBlock(worldPosition, getBlockState().setValue(net.minecraft.world.level.block.BarrelBlock.OPEN, false), 3);
            level.playSound(null, worldPosition, net.minecraft.sounds.SoundEvents.BARREL_CLOSE, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

}