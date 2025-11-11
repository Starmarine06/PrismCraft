package net.starmarine06.prismcraft.client;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;
import net.starmarine06.prismcraft.blockentity.ModBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.starmarine06.prismcraft.blockentity.renderer.PrismFlowerPotRenderer;
import net.starmarine06.prismcraft.block.PrismDecoratedPotBlock;
import net.starmarine06.prismcraft.blockentity.renderer.PrismSignRenderer;

@EventBusSubscriber(modid = PrismCraftMod.MOD_ID, value = Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        System.out.println("Client: Registering Prism Flower Pot Renderer!");
        event.registerBlockEntityRenderer(ModBlockEntities.PRISM_FLOWER_POT_ENTITY.get(), PrismFlowerPotRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PRISM_SIGN.get(), PrismSignRenderer::new);
        Minecraft.getInstance().getBlockColors().register(
                (state, world, pos, tintIndex) -> state.getValue(PrismDecoratedPotBlock.COLOR),
                ModBlocks.PRISM_DECORATED_POT.get()
        );
    }

}
