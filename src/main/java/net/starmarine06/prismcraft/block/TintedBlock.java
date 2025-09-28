package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;
import net.starmarine06.prismcraft.block.entity.TintedBlockEntity;

/**
 * Base class for blocks that support a CustomColor tag.
 * Uses NBT tag "CustomColor" on ItemStacks and persists color in BlockEntity.
 */
public class TintedBlock extends Block implements EntityBlock {
    public TintedBlock(Properties props) {
        super(props);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TintedBlockEntity(pos, state);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        ItemStack stack = new ItemStack(this);
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof TintedBlockEntity tbe) {
            stack.getOrCreateTag().putInt("CustomColor", tbe.getColor());
        }
        return stack;
    }

    @Override
    public void setPlacedBy(net.minecraft.world.level.Level world, BlockPos pos, BlockState state, @Nullable net.minecraft.world.entity.LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        BlockEntity be = world.getBlockEntity(pos);
        if (!(be instanceof TintedBlockEntity tbe)) return;
        if (stack.hasTag() && stack.getOrCreateTag().contains("CustomColor")) {
            tbe.setColor(stack.getOrCreateTag().getInt("CustomColor"));
            // Optional: remove migration if you prefer to keep NBT
            // stack.getOrCreateTag().remove("CustomColor");
        }
    }

    public static boolean itemHasColor(ItemStack stack) {
        return stack.hasTag() && stack.getOrCreateTag().contains("CustomColor");
    }

    public static int itemGetColor(ItemStack stack) {
        if (stack.hasTag() && stack.getOrCreateTag().contains("CustomColor")) {
            return stack.getOrCreateTag().getInt("CustomColor");
        }
        return 0xFFFFFF;
    }

    public static void itemSetColor(ItemStack stack, int color) {
        stack.getOrCreateTag().putInt("CustomColor", color);
    }
}