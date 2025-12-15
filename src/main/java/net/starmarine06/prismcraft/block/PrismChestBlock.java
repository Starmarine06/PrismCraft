package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.starmarine06.prismcraft.blockentity.ModBlockEntities;
import net.starmarine06.prismcraft.blockentity.PrismChestBlockEntity;

public class PrismChestBlock extends ChestBlock implements EntityBlock {

    public PrismChestBlock(Properties properties) {
        super(() -> ModBlockEntities.PRISM_CHEST.get(), SoundEvents.CHEST_OPEN, SoundEvents.CHEST_CLOSE, properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrismChestBlockEntity(pos, state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state,
                            LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (!level.isClientSide()
                && level.getBlockEntity(pos) instanceof PrismChestBlockEntity chest) {

            DyedItemColor dyed = stack.get(DataComponents.DYED_COLOR);
            chest.setColor(dyed != null ? dyed.rgb() : 0xFFFFFF);
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos,
                        BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (level.isClientSide()) return;
        if (state.getValue(TYPE) == ChestType.SINGLE) return;

        BlockPos otherPos = pos.relative(getConnectedDirection(state));

        if (level.getBlockEntity(pos) instanceof PrismChestBlockEntity a
                && level.getBlockEntity(otherPos) instanceof PrismChestBlockEntity b) {

            int avg = averageColor(a.getColor(), b.getColor());
            a.setColor(avg);
            b.setColor(avg);
        }
    }

    private static int averageColor(int c1, int c2) {
        int r = (((c1 >> 16) & 0xFF) + ((c2 >> 16) & 0xFF)) / 2;
        int g = (((c1 >> 8) & 0xFF) + ((c2 >> 8) & 0xFF)) / 2;
        int b = ((c1 & 0xFF) + (c2 & 0xFF)) / 2;
        return (r << 16) | (g << 8) | b;
    }
}
