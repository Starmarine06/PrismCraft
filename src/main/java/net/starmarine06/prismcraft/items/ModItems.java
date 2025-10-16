package net.starmarine06.prismcraft.items;

import net.starmarine06.prismcraft.PrismCraftMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrismCraftMod.MOD_ID);

    // Currently no custom items, block items are handled in ModBlocks

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}