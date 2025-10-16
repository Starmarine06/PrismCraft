package net.starmarine06.prismcraft.blocks;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ColorableStairBlock extends StairBlock {
    private final int baseColor;

    public ColorableStairBlock(BlockState baseBlockState, BlockBehaviour.Properties properties, int baseColor) {
        super(baseBlockState, properties);
        this.baseColor = baseColor;
    }

    public int getColor() {
        return baseColor;
    }
}