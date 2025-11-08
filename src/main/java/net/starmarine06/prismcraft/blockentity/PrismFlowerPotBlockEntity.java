package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class PrismFlowerPotBlockEntity extends BlockEntity {
    private int color = 0xFFFFFF;

    public PrismFlowerPotBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRISM_FLOWER_POT_ENTITY.get(), pos, state);
    }

    public void setColor(int color) {
        this.color = color;
        setChanged();
    }
    public int getColor() { return color; }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("Color", this.color);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.color = input.getIntOr("Color", 0xFFFFFF);
    }
}