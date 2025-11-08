package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.starmarine06.prismcraft.blockentity.PrismFlowerPotBlockEntity;
import net.starmarine06.prismcraft.interfaces.IPrismColoredBlock;
import org.jetbrains.annotations.Nullable;

public class PrismFlowerPotBlock extends FlowerPotBlock implements EntityBlock, IPrismColoredBlock {
    public PrismFlowerPotBlock(BlockBehaviour.Properties properties) {
        // The vanilla constructor expects the empty pot for the plant argument.
        super(null, properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PrismFlowerPotBlockEntity(pos, state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PrismFlowerPotBlockEntity tile) {
                // Store your PrismCraft custom color.
                int color = getColor(stack);
                tile.setColor(color);
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    public static int getColor(ItemStack stack) {
        // Use your mod's dye/color system (just like other colored blocks).
        var dyedColor = stack.get(net.minecraft.core.component.DataComponents.DYED_COLOR);
        return dyedColor != null ? dyedColor.rgb() : 0xFFFFFF;
    }
    /*

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        // Check if the item is a flower that can be potted
        Block flower = FlowerPotBlock.getPlantForItem(stack.getItem());
        if (flower != null) {
            // Set the flower in your BlockEntity
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof PrismFlowerPotBlockEntity prismBE) {
                prismBE.setFlower(stack.getItem().getDescriptionId());
                // Optionally: remove item, update visuals, play sound, etc.
                if (!player.isCreative()) stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        // Fallback to vanilla logic
        return InteractionResult.PASS;
    }*/

}