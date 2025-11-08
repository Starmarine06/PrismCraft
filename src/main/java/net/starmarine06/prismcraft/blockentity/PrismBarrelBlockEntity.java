package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import net.starmarine06.prismcraft.menu.PrismBarrelMenu;
import org.jetbrains.annotations.Nullable;

public class PrismBarrelBlockEntity extends BlockEntity implements MenuProvider, Container, IPrismColoredBlock {
    private int color = 0xFFFFFF;
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    public PrismBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRISM_BARREL.get(), pos, state);
    }

    public int getColor() {
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

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) return false;
        }
        return true;
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
    public Component getDisplayName() {
        return Component.translatable("container.prismcraft.prism_barrel");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new PrismBarrelMenu(containerId, playerInventory, this);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (level != null && level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, items, true);
        output.putInt("Color", color);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, items);
        this.color = input.getInt("Color").orElse(0xFFFFFF);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putInt("Color", color);
        return tag;
    }

    @Override
    public void handleUpdateTag(ValueInput input) {
        super.handleUpdateTag(input);
        int oldColor = this.color;
        loadAdditional(input);

        // If color changed, request re-render
        if (oldColor != this.color && level != null) {
            level.setBlocksDirty(getBlockPos(), getBlockState(), getBlockState());
            // Force model data refresh
            requestModelDataUpdate();
        }
    }

    @Override
    public void onDataPacket(Connection net, ValueInput valueInput) {
        int oldColor = this.color;
        handleUpdateTag(valueInput);

        // Force immediate render update if color changed
        if (oldColor != this.color && level != null && level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public void drops() {
        for (int i = 0; i < getContainerSize(); i++) {
            Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), getItem(i));
        }
    }

    private int viewers = 0;
    public float openness;
    public float prevOpenness;

    public static void clientTick(Level level, BlockPos pos, BlockState state, PrismBarrelBlockEntity tile) {
        tile.prevOpenness = tile.openness;
        if (state.getValue(net.minecraft.world.level.block.BarrelBlock.OPEN)) {
            tile.openness = Math.min(tile.openness + 0.1F, 1.0F);
        } else {
            tile.openness = Math.max(tile.openness - 0.1F, 0.0F);
        }
    }

    public void startOpen(Player player) {
        if (!hasLevel()) return;
        viewers++;
        if (viewers == 1 && !getBlockState().getValue(net.minecraft.world.level.block.BarrelBlock.OPEN)) {
            level.setBlock(worldPosition, getBlockState().setValue(net.minecraft.world.level.block.BarrelBlock.OPEN, true), 3);
            level.playSound(null, worldPosition, net.minecraft.sounds.SoundEvents.BARREL_OPEN, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
        } else if (viewers > 1 && getBlockState().getValue(net.minecraft.world.level.block.BarrelBlock.OPEN)) {
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