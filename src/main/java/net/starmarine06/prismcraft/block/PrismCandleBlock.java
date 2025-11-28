package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

public class PrismCandleBlock extends CandleBlock implements EntityBlock, IPrismColoredBlock {
    public PrismCandleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(BlockStateProperties.LIT, false)); // alapból nem ég
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrismColoredBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
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
                BlockState currentState = level.getBlockState(pos);
                level.sendBlockUpdated(pos, currentState, currentState, 3);
                level.blockEntityChanged(pos);
            }
        }
    }

    @Override
    public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
                                       BlockPos pos, Player player, InteractionHand hand,
                                       BlockHitResult hit) {
        if (stack.is(Items.FLINT_AND_STEEL) || stack.is(Items.FIRE_CHARGE)) {
            if (!state.getValue(BlockStateProperties.LIT)) {
                level.setBlock(pos, state.setValue(BlockStateProperties.LIT, true), 3);
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE,
                        SoundSource.BLOCKS, 1.0F, level.random.nextFloat() * 0.4F + 0.8F);

                stack.hurtAndBreak(1, player, hand);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public static void setColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color));
    }

    public static int getColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }
}
