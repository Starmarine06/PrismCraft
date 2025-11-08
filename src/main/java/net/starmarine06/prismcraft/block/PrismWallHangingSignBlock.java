package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.starmarine06.prismcraft.blockentity.ModBlockEntities;
import net.starmarine06.prismcraft.blockentity.PrismHangingSignBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

public class PrismWallHangingSignBlock extends WallHangingSignBlock implements IPrismColoredBlock {
    public PrismWallHangingSignBlock(BlockBehaviour.Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.PRISM_HANGING_SIGN.get().create(pos, state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PrismHangingSignBlockEntity tile) {
                int color = getColor(stack);
                tile.setColor(color);
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    public static int getColor(ItemStack stack) {
        DyedItemColor dyedColor = stack.get(DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }
}