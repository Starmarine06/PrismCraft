package net.starmarine06.prismcraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.ItemStack;
import net.starmarine06.prismcraft.block.PrismStandingSignBlock;
import net.starmarine06.prismcraft.block.PrismWallSignBlock; // If you have this, else vanilla WallSignBlock
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;

public class PrismSignItem extends SignItem {
    public PrismSignItem(Item.Properties properties, Block standing, Block wall) {
        super(standing, wall, properties);
    }

    // No override for useOn needed if blocks implement setPlacedBy with color!
    // Only include the following if you want to make sure color is propagated
    @Override
    public InteractionResult useOn(UseOnContext context) {
        InteractionResult result = super.useOn(context);
        // If you ever need to add special syncing/logging, you could do it here
        return result;
    }
}
