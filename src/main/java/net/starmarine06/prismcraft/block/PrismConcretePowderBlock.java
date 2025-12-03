package net.starmarine06.prismcraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.Orientation;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;
import net.starmarine06.prismcraft.blockentity.PrismDecoratedPotBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
public class PrismConcretePowderBlock extends BaseEntityBlock implements IPrismColoredBlock {
    public static final MapCodec<PrismConcretePowderBlock> CODEC = simpleCodec(PrismConcretePowderBlock::new);
    public PrismConcretePowderBlock(Properties properties) { super(properties); }

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
                level.sendBlockUpdated(pos, state, state, 3);
                level.blockEntityChanged(pos);
            }
        }
    }

    public static int getColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        ItemStack stack = new ItemStack(this.asItem());
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof PrismColoredBlockEntity tile) {
            int color = tile.getColor();
            stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
        }
        return stack;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation fromDirection, boolean moved) {
        super.neighborChanged(state, level, pos, neighborBlock, fromDirection, moved);

        if (!level.isClientSide()) {
            for (Direction direction : Direction.values()) {
                BlockPos adjacentPos = pos.relative(direction);
                BlockState adjacentState = level.getBlockState(adjacentPos);
                if (adjacentState.getFluidState().is(FluidTags.WATER)) {
                    int color = 0xFFFFFF;
                    BlockEntity oldBe = level.getBlockEntity(pos);
                    if (oldBe instanceof PrismColoredBlockEntity entity) {
                        color = entity.getColor();
                    }

                    level.setBlock(pos, ModBlocks.PRISM_CONCRETE.get().defaultBlockState(), Block.UPDATE_ALL);
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