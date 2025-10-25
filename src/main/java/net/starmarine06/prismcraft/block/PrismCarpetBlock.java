package net.starmarine06.prismcraft.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrismCarpetBlock extends CarpetBlock implements EntityBlock, IPrismColoredBlock {
    public PrismCarpetBlock(Properties properties) {
        super(properties);
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
        if (!level.isClientSide) {
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

    public static void setColor(ItemStack stack, int color) {
        stack.set(DataComponents.DYED_COLOR, new DyedItemColor(color, true));
    }

    public static int getColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }
}