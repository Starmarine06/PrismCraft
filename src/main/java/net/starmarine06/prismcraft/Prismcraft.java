package net.starmarine06.prismcraft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.starmarine06.prismcraft.blocks.BlockRegistry;
import net.starmarine06.prismcraft.items.ItemRegistry;

@Mod(Prismcraft.MOD_ID)
public class Prismcraft {
    public static final String MOD_ID = "prismcraft";

    public Prismcraft(IEventBus modEventBus, ModContainer modContainer) {

        BlockRegistry.BLOCKS.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);

    }
}
