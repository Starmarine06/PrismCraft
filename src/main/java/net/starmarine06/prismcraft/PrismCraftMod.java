package net.starmarine06.prismcraft;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.starmarine06.prismcraft.block.ModBlocks;
import net.starmarine06.prismcraft.blockentity.ModBlockEntities;
import net.starmarine06.prismcraft.blockentity.renderer.PrismFlowerPotRenderer;
import net.starmarine06.prismcraft.item.ModCreativeTabs;
import net.starmarine06.prismcraft.item.ModItems;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;
import net.starmarine06.prismcraft.menu.ModMenuTypes;
import net.starmarine06.prismcraft.network.CraftDyeMixerPacket;
import net.starmarine06.prismcraft.network.DyeSelectionPacket;
import net.starmarine06.prismcraft.screen.DyeMixerScreen;
import net.starmarine06.prismcraft.screen.PrismBarrelScreen;
import net.starmarine06.prismcraft.screen.PrismChestScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Mod(PrismCraftMod.MOD_ID)
public class PrismCraftMod {
    public static final String MOD_ID = "prismcraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(PrismCraftMod.class);

    public PrismCraftMod(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing PrismCraft mod...");

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenuTypes.MENUS.register(modEventBus);

        modEventBus.addListener(this::registerScreens);
        //modEventBus.addListener(PrismCraftMod::registerPackets);
        ModCreativeTabs.register(modEventBus);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.DYE_MIXER.get(), DyeMixerScreen::new);
        event.register(ModMenuTypes.PRISM_BARREL.get(), PrismBarrelScreen::new);
        event.register(ModMenuTypes.PRISM_CHEST.get(), PrismChestScreen::new);
    }
}