package net.starmarine06.prismcraft;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.starmarine06.prismcraft.block.ModBlocks;
import net.starmarine06.prismcraft.blockentity.ModBlockEntities;
import net.starmarine06.prismcraft.client.ModColorHandlers;
import net.starmarine06.prismcraft.item.ModCreativeTabs;
import net.starmarine06.prismcraft.item.ModItems;
import net.starmarine06.prismcraft.menu.ModMenuTypes;
import net.starmarine06.prismcraft.screen.DyeMixerScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(PrismCraftMod.MOD_ID)
public class PrismCraftMod {
    public static final String MOD_ID = "prismcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(PrismCraftMod.class);

    public PrismCraftMod(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing PrismCraft mod...");

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);

        modEventBus.addListener(this::registerScreens);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);


    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.DYE_MIXER.get(), DyeMixerScreen::new);
    }
}