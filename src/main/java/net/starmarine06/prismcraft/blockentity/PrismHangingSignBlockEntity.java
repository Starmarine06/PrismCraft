package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;

public class PrismHangingSignBlockEntity extends HangingSignBlockEntity{
    private int color = 0xFFFFFF;

    public PrismHangingSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public void setColor(int color) {
        this.color = color;
        setChanged();
    }

    public int getColor() {
        return color;
    }

    // ValueInput/Output serialization:
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