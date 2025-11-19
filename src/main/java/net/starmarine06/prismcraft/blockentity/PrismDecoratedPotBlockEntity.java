package net.starmarine06.prismcraft.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

public class PrismDecoratedPotBlockEntity extends DecoratedPotBlockEntity implements IPrismColoredBlock {
    private int color = 0xFFFFFF;
    public final AnimationState potAnim = new AnimationState();

    public PrismDecoratedPotBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.PRISM_DECORATED_POT.get();
    }


    public void playPotAnimation() {
        if (level != null) {
            potAnim.start((int) level.getGameTime());
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public void setColor(int color) {
        this.color = color;

        setChanged();
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
    public void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getInt("Color").orElse(0xFFFFFF);
    }

    @Override
    public void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", color);
    }

    // CLIENT SYNC
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
}