package net.starmarine06.prismcraft.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;

@EventBusSubscriber(modid = PrismCraftMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModColorHandlers {

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        //System.out.println("=== REGISTERING BLOCK COLORSasdaadawdwdadawd ===");

        BlockColor blockColor = (state, level, pos, tintIndex) -> {
            if (level != null && pos != null) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof PrismColoredBlockEntity tile) {
                    int color = tile.getColor();
                    //System.out.println("Color handler called for " + state.getBlock() + " at " + pos + ": #" + String.format("%06X", color));
                    return color;
                }
            }
            return 0xFFFFFF;
        };

        event.register(blockColor,
                ModBlocks.PRISM_LOG.get(),
                ModBlocks.PRISM_PLANKS.get(),
                ModBlocks.PRISM_SLAB.get(),
                ModBlocks.PRISM_STAIRS.get(),
                ModBlocks.PRISM_FENCE.get(),
                ModBlocks.PRISM_FENCE_GATE.get(),
                ModBlocks.PRISM_BUTTON.get(),
                ModBlocks.PRISM_PRESSURE_PLATE.get(),
                ModBlocks.PRISM_DOOR.get(),
                ModBlocks.PRISM_TRAPDOOR.get(),
                ModBlocks.PRISM_CONCRETE.get()
        );

        //System.out.println("=== COLORS REGISTERED SUCCESSFULLY ===");
    }
}