package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrismWallBlock extends WallBlock implements EntityBlock, IPrismColoredBlock {
    public PrismWallBlock(BlockBehaviour.Properties properties) {
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
        stack.set(net.minecraft.core.component.DataComponents.DYED_COLOR, new DyedItemColor(color, true));
    }

    public static int getColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(net.minecraft.core.component.DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        int color = getColor(stack);
        if (color != 0xFFFFFF) {
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = color & 0xFF;
            tooltip.add(Component.literal("RGB: " + r + ", " + g + ", " + b)
                    .withStyle(net.minecraft.ChatFormatting.GOLD));
        }
    }
}

