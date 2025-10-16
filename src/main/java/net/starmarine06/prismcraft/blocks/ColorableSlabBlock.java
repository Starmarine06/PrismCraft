package net.starmarine06.prismcraft.blocks;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ColorableSlabBlock extends SlabBlock {
    private final int baseColor;

    public ColorableSlabBlock(BlockBehaviour.Properties properties, int baseColor) {
        super(properties);
        this.baseColor = baseColor;
    }

    public int getColor() {
        return baseColor;
    }
}
