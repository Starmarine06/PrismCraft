package net.starmarine06.prismcraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.starmarine06.prismcraft.blockentity.PrismFlowerPotBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

public class PrismFlowerPotBlock extends BaseEntityBlock implements IPrismColoredBlock {
    private static final VoxelShape SHAPE = Shapes.or(
            // Pot base (thinner)
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 7.0D, 10.0D),
            // Pot outer walls (taller + thinner)
            Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D) // Pot outer walls
    );
    public static final MapCodec<PrismFlowerPotBlock> CODEC = simpleCodec(PrismFlowerPotBlock::new);

    public PrismFlowerPotBlock(Properties props) {
        super(props);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext p_60575_) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrismFlowerPotBlockEntity(pos, state);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof PrismFlowerPotBlockEntity flowerPot) {
            // Check if the item is pottable using the item tag instead
            if (stack.is(ItemTags.SMALL_FLOWERS) ||
                    stack.is(ItemTags.FLOWERS) ||
                    stack.is(ItemTags.SAPLINGS)) {

                // If pot is empty and player has a flower, place it
                if (flowerPot.getFlower() == null && !stack.isEmpty()) {
                    flowerPot.setFlower(stack.getItem());
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    level.playSound(player, pos, SoundEvents.CROP_PLANTED, SoundSource.BLOCKS, 1f, 1f);
                    return InteractionResult.SUCCESS;
                }
            } else if (stack.isEmpty() && flowerPot.getFlower() != null) {
                // If player is empty-handed and pot has a flower, remove it
                ItemStack flowerStack = new ItemStack(flowerPot.getFlower());
                player.setItemInHand(InteractionHand.MAIN_HAND, flowerStack);
                flowerPot.setFlower(null);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof PrismFlowerPotBlockEntity te) {
            // Set custom color from stack, if your items are dyed
            int color = getColor(stack);
            te.setColor(color);
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    public static int getColor(ItemStack stack) {
        var dyedColor = stack.get(net.minecraft.core.component.DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof PrismFlowerPotBlockEntity te && te.getFlower() != null) {
            if (!player.isCreative()) {
                popResource(level, pos, new ItemStack(te.getFlower()));
            }
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }
}