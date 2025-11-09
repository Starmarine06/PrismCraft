package net.starmarine06.prismcraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;


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
