package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class PrismColoredBlockEntity extends BlockEntity {
    private int color = 0xFFFFFF;

    public PrismColoredBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRISM_COLORED.get(), pos, state);
    }

    public void setColor(int color) {
        this.color = color;
        setChanged();

        // Sync to client immediately
        if (level != null && !level.isClientSide()) {
            BlockState state = getBlockState();
            level.sendBlockUpdated(getBlockPos(), state, state, 3);
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        // Force a block update when the BlockEntity loads on client
        if (level != null && level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", color);
    }

    // CLIENT SYNC - Send data to client
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

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getInt("Color").orElse(0xFFFFFF);
    }

    // CLIENT SYNC - Receive data on client
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}