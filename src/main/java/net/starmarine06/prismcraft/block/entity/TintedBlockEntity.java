package net.starmarine06.prismcraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.starmarine06.prismcraft.registry.ModBlockEntities;

public class TintedBlockEntity extends BlockEntity {
    private int color = 0xFFFFFF;

    public TintedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DYE_MIXER_BE.get(), pos, state);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int newColor) {
        this.color = newColor;
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("CustomColor", color);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("CustomColor")) {
            this.color = tag.getInt("CustomColor");
        }
    }
}
