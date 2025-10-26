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
                ModBlocks.PRISM_STRIPPED_LOG.get(),
                ModBlocks.PRISM_PLANKS.get(),
                ModBlocks.PRISM_SLAB.get(),
                ModBlocks.PRISM_STAIRS.get(),
                ModBlocks.PRISM_FENCE.get(),
                ModBlocks.PRISM_FENCE_GATE.get(),
                ModBlocks.PRISM_DOOR.get(),
                ModBlocks.PRISM_TRAPDOOR.get(),
                ModBlocks.PRISM_BUTTON.get(),
                ModBlocks.PRISM_PRESSURE_PLATE.get(),
                ModBlocks.PRISM_CONCRETE.get(),
                ModBlocks.PRISM_SAND.get(),
                ModBlocks.PRISM_CANDLE.get(),
                ModBlocks.PRISM_CARPET.get(),
                ModBlocks.PRISM_CONCRETE_POWDER.get(),
                ModBlocks.PRISM_LADDER.get(),
                ModBlocks.PRISM_SLIME_BLOCK.get(),
                ModBlocks.PRISM_STONE.get(),
                ModBlocks.PRISM_ANDESITE.get(),
                ModBlocks.PRISM_WOOL.get(),
                ModBlocks.PRISM_TERRACOTTA.get(),
                //ORDER: Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry, Pale Oak, Bamboo, Crimson, Warped
                ModBlocks.PRISM_OAK_DOOR.get(),
                ModBlocks.PRISM_OAK_TRAPDOOR.get(),
                ModBlocks.PRISM_SPRUCE_DOOR.get(),
                ModBlocks.PRISM_SPRUCE_TRAPDOOR.get(),
                ModBlocks.PRISM_BIRCH_DOOR.get(),
                ModBlocks.PRISM_BIRCH_TRAPDOOR.get(),
                ModBlocks.PRISM_JUNGLE_DOOR.get(),
                ModBlocks.PRISM_JUNGLE_TRAPDOOR.get(),
                ModBlocks.PRISM_ACACIA_DOOR.get(),
                ModBlocks.PRISM_ACACIA_TRAPDOOR.get(),
                ModBlocks.PRISM_DARK_OAK_DOOR.get(),
                ModBlocks.PRISM_DARK_OAK_TRAPDOOR.get(),
                ModBlocks.PRISM_MANGROVE_DOOR.get(),
                ModBlocks.PRISM_MANGROVE_TRAPDOOR.get(),
                ModBlocks.PRISM_CHERRY_DOOR.get(),
                ModBlocks.PRISM_CHERRY_TRAPDOOR.get(),
                ModBlocks.PRISM_PALE_OAK_DOOR.get(),
                ModBlocks.PRISM_PALE_OAK_TRAPDOOR.get(),
                ModBlocks.PRISM_BAMBOO_DOOR.get(),
                ModBlocks.PRISM_BAMBOO_TRAPDOOR.get(),
                ModBlocks.PRISM_CRIMSON_DOOR.get(),
                ModBlocks.PRISM_CRIMSON_TRAPDOOR.get(),
                ModBlocks.PRISM_WARPED_DOOR.get(),
                ModBlocks.PRISM_WARPED_TRAPDOOR.get()
        );

        //System.out.println("=== COLORS REGISTERED SUCCESSFULLY ===");
    }
}