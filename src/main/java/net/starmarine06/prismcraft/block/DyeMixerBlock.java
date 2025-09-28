package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DyeMixerBlock extends TintedBlock {
    public DyeMixerBlock(BlockBehaviour.Properties props) {
        super(props);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        var be = world.getBlockEntity(pos);
        if (be instanceof net.starmarine06.prismcraft.block.DyeMixerBlockEntity db) {
            if (stack.hasTag() && stack.getOrCreateTag().contains("CustomColor")) {
                // put color into BE inventory input slot 0 if desired - simpler: store in BE directly
                db.setChanged();
            }
        }
    }
}
