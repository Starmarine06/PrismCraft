package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class PrismDecoratedPotBlockEntity extends DecoratedPotBlockEntity {
    private int color = 0xFFFFFF;

    public PrismDecoratedPotBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.PRISM_DECORATED_POT.get();
    }

    public void setColor(int color) {
        this.color = color;
        setChanged();

        // Sync to client
        if (level != null && !level.isClientSide()) {
            BlockState state = getBlockState();
            level.sendBlockUpdated(getBlockPos(), state, state, 3);
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getIntOr("Color", 0xFFFFFF);
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", this.color);
    }

    // CLIENT SYNC
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putInt("Color", color);
        return tag;
    }
}