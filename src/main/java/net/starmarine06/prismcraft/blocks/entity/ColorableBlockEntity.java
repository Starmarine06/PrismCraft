package net.starmarine06.prismcraft.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ColorableBlockEntity extends BlockEntity {
    private int color = 0xFFFFFF;

    public ColorableBlockEntity(BlockPos pos, BlockState state) {
        // Replace with your actual block entity type reference (below is a placeholder)
        super(ModBlockEntities.COLORABLE_BLOCK_ENTITY.get(), pos, state);
    }

    public void setColor(int color) {
        this.color = color;
        setChanged();
    }

    public int getColor() {
        return this.color;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
        super.saveAdditional(tag, lookup);
        tag.putInt("prismcraft:color", this.color);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider lookup) {
        super.loadAdditional(tag, lookup);
        if (tag.contains("prismcraft:color")) {
            this.color = tag.getInt("prismcraft:color");
        } else {
            this.color = 0xFFFFFF;
        }
    }
}