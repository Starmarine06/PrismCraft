package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PrismSignBlockEntity extends SignBlockEntity {
    public PrismSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PRISM_SIGN.get(), pos, state);
    }
}
