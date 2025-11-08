package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class PrismSignBlockEntity extends SignBlockEntity {
    private int color = 0xFFFFFF;

    public PrismSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRISM_SIGN.get(), pos, state);
    }

    public void setColor(int color) {
        this.color = color;
        setChanged();
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
}