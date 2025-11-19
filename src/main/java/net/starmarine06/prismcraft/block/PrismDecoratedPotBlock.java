package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DecoratedPotBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.starmarine06.prismcraft.blockentity.ModBlockEntities;
import net.starmarine06.prismcraft.blockentity.PrismDecoratedPotBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

public class PrismDecoratedPotBlock extends DecoratedPotBlock implements IPrismColoredBlock {
    public PrismDecoratedPotBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrismDecoratedPotBlockEntity(pos, state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PrismDecoratedPotBlockEntity tile) {
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

    @Override
    protected InteractionResult useItemOn(ItemStack p_316569_, BlockState p_316562_, Level p_316177_, BlockPos p_316898_, Player p_316632_, InteractionHand p_316424_, BlockHitResult p_316345_) {
        return super.useItemOn(p_316569_, p_316562_, p_316177_, p_316898_, p_316632_, p_316424_, p_316345_);
    }
}