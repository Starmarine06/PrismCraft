package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.starmarine06.prismcraft.blockentity.renderer.PrismFlowerPotRenderer;
import org.jetbrains.annotations.Nullable;

public class PrismFlowerPotBlockEntity extends BlockEntity {
    private int color = 0xFFFFFF;
    private Item flower = null;

    public PrismFlowerPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRISM_FLOWER_POT_ENTITY.get(), pos, state);
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

    public Item getFlower() {
        return flower;
    }

    public void setFlower(@Nullable Item flower) {
        this.flower = flower;
        System.out.println("Flower set to: " + (flower != null ? flower.toString() : "null") + " on " + (level != null && level.isClientSide() ? "CLIENT" : "SERVER"));
        setChanged();
        if (level != null && !level.isClientSide()) {
            BlockState state = getBlockState();
            level.sendBlockUpdated(getBlockPos(), state, state, 3);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", color);
        if (flower != null) output.putString("Flower", flower.getDescriptionId());
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getInt("Color").orElse(0xFFFFFF);
        this.flower = null;
        input.getString("Flower").ifPresent(s -> {
            // Resolve Item from registry by descriptionId (do your registry lookup here)
            this.flower = net.minecraft.core.registries.BuiltInRegistries.ITEM.stream()
                    .filter(item -> item.getDescriptionId().equals(s))
                    .findFirst().orElse(null);
        });
    }

    // Client sync
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        tag.putInt("Color", color);
        if (flower != null) tag.putString("Flower", flower.getDescriptionId());
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
        if (level != null) level.setBlocksDirty(getBlockPos(), getBlockState(), getBlockState());
    }

    @Override
    public void onDataPacket(Connection net, ValueInput valueInput) {
        handleUpdateTag(valueInput);
        int oldColor = this.color;
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

    @Override
    public void onLoad() {
        super.onLoad();
        // Force a block update when the BlockEntity loads on client
        if (level != null && level.isClientSide()) {
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    }
}