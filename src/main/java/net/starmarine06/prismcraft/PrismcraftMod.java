package net.starmarine06.prismcraft;

import net.starmarine06.prismcraft.blocks.ModBlocks;
import net.starmarine06.prismcraft.blocks.entity.ModBlockEntities;
import net.starmarine06.prismcraft.client.screens.DyeMixerScreen;
import net.starmarine06.prismcraft.datagen.ModDataGeneration;
import net.starmarine06.prismcraft.items.ModItems;
import net.starmarine06.prismcraft.recipes.ModRecipes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod(PrismCraftMod.MOD_ID)
public class PrismCraftMod {
    public static final String MOD_ID = "prismcraft";

    // Creative Mode Tab
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Supplier<CreativeModeTab> PRISMCRAFT_TAB = CREATIVE_MODE_TABS.register("prismcraft_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.prismcraft"))
                    .icon(() -> new ItemStack(ModBlocks.DYE_MIXER.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.DYE_MIXER.get());
                        output.accept(ModBlocks.WHITE_WOOD.get());
                        output.accept(ModBlocks.WHITE_WOOD_PLANKS.get());
                        output.accept(ModBlocks.WHITE_WOOD_STAIRS.get());
                        output.accept(ModBlocks.WHITE_WOOD_SLAB.get());
                        output.accept(ModBlocks.WHITE_CONCRETE.get());
                    })
                    .build());

    public PrismCraftMod(IEventBus modEventBus) {
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModRecipes.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                MenuScreens.register(ModRecipes.DYE_MIXER_MENU.get(), DyeMixerScreen::new);
                net.starmarine06.prismcraft.client.ClientSetup.registerColors();
            });
        }
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void gatherData(GatherDataEvent.Client event) {
            ModDataGeneration.gatherData(event);
        }
    }
}