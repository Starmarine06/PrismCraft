package net.starmarine06.prismcraft.block;

import com.mojang.serialization.MapCodec;
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
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;
import net.starmarine06.prismcraft.client.ModColorHandlers;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrismWoodBlock extends RotatedPillarBlock implements EntityBlock, IPrismColoredBlock {
    public static final MapCodec<PrismWoodBlock> CODEC = simpleCodec(PrismWoodBlock::new);

    public PrismWoodBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Nullable
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

        if (!level.isClientSide) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PrismColoredBlockEntity tile) {
                int color = getColor(stack);
                tile.setColor(color);

                // Force block update to sync immediately to client
                BlockState currentState = level.getBlockState(pos);
                level.sendBlockUpdated(pos, currentState, currentState, 3);
                //System.out.println("Color handler called for " + state.getBlock() + " at " + pos + ": #" + String.format("%06X", color));



                // Mark chunk for saving
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

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);

        int color = getColor(stack);

        // Only show RGB if block is dyed (not white)
        if (color != 0xFFFFFF) {
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = color & 0xFF;

            // Golden colored text
            tooltip.add(Component.literal("RGB: " + r + ", " + g + ", " + b)
                    .withStyle(ChatFormatting.GOLD));
        }
    }
}