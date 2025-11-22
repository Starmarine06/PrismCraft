package net.starmarine06.prismcraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.storage.ValueOutput;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

public class PrismConcretePowder extends BaseEntityBlock implements IPrismColoredBlock {
    public static final MapCodec<PrismConcretePowder> CODEC = simpleCodec(PrismConcretePowder::new);

    public PrismConcretePowder(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrismColoredBlockEntity(pos, state);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PrismColoredBlockEntity tile) {
                int color = getColor(stack);
                tile.setColor(color);

                // Force block update to sync immediately to client
                BlockState currentState = level.getBlockState(pos);
                level.sendBlockUpdated(pos, currentState, currentState, 3);

                // Mark chunk for saving
                level.blockEntityChanged(pos);
            }
        }
    }



    public static void setColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
    }

    public static int getColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }

    @Override
    public void neighborChanged(
            BlockState state,
            Level level,
            BlockPos pos,
            Block neighborBlock,
            @Nullable Orientation fromDirection,
            boolean moved
    ) {
        super.neighborChanged(state, level, pos, neighborBlock, fromDirection, moved);

        if (!level.isClientSide()) {
            for (Direction direction : Direction.values()) {
                BlockPos adjacentPos = pos.relative(direction);
                BlockState adjacentState = level.getBlockState(adjacentPos);
                if (adjacentState.getFluidState().is(FluidTags.WATER)) {
                    // 1. Get the color from old block entity
                    int color = 0xFFFFFF;
                    BlockEntity oldBe = level.getBlockEntity(pos);
                    if (oldBe instanceof PrismColoredBlockEntity entity) {
                        color = entity.getColor();
                    }

                    // 2. Replace block with concrete
                    BlockState newConcreteState = ModBlocks.PRISM_CONCRETE.get().defaultBlockState();
                    level.setBlock(pos, newConcreteState, Block.UPDATE_ALL);

                    // 3. Set color on new block entity
                    BlockEntity newBe = level.getBlockEntity(pos);
                    if (newBe instanceof PrismColoredBlockEntity entity) {
                        entity.setColor(color);
                    }
                    break;
                }
            }
        }
    }

}