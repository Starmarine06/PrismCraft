package net.starmarine06.prismcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class ColorableBlock extends Block implements EntityBlock {

    private final int baseColor;

    public ColorableBlock(BlockBehaviour.Properties properties, int baseColor) {
        super(properties);
        this.baseColor = baseColor;
    }

    public int getColor() {
        return baseColor;
    }

    /**
     * When this block is placed by an entity, read color from the ItemStack's NBT and set it on the block entity.
     */
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be != null && stack.hasTag() && stack.getTag().contains("prismcraft:color")) {
                int color = stack.getTag().getInt("prismcraft:color");
                // If your block entity has a setColor method (recommended)
                try {
                    be.getClass().getMethod("setColor", int.class).invoke(be, color);
                } catch (Exception ignored) {
                }
            }
        }
    }
}