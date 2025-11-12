package net.starmarine06.prismcraft.client;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;
import net.starmarine06.prismcraft.blockentity.*;

import java.util.List;

@EventBusSubscriber(modid = PrismCraftMod.MOD_ID, value = Dist.CLIENT)
public class ModColorHandlers {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            List<DeferredBlock<?>> cutoutBlocks = List.of(
                    ModBlocks.PRISM_OAK_DOOR,
                    ModBlocks.PRISM_SPRUCE_DOOR,
                    ModBlocks.PRISM_BIRCH_DOOR,
                    ModBlocks.PRISM_JUNGLE_DOOR,
                    ModBlocks.PRISM_ACACIA_DOOR,
                    ModBlocks.PRISM_DARK_OAK_DOOR,
                    ModBlocks.PRISM_MANGROVE_DOOR,
                    ModBlocks.PRISM_CHERRY_DOOR,
                    ModBlocks.PRISM_PALE_OAK_DOOR,
                    ModBlocks.PRISM_BAMBOO_DOOR,
                    ModBlocks.PRISM_CRIMSON_DOOR,
                    ModBlocks.PRISM_WARPED_DOOR,
                    ModBlocks.PRISM_OAK_TRAPDOOR,
                    ModBlocks.PRISM_SPRUCE_TRAPDOOR,
                    ModBlocks.PRISM_BIRCH_TRAPDOOR,
                    ModBlocks.PRISM_JUNGLE_TRAPDOOR,
                    ModBlocks.PRISM_ACACIA_TRAPDOOR,
                    ModBlocks.PRISM_DARK_OAK_TRAPDOOR,
                    ModBlocks.PRISM_MANGROVE_TRAPDOOR,
                    ModBlocks.PRISM_CHERRY_TRAPDOOR,
                    ModBlocks.PRISM_PALE_OAK_TRAPDOOR,
                    ModBlocks.PRISM_BAMBOO_TRAPDOOR,
                    ModBlocks.PRISM_CRIMSON_TRAPDOOR,
                    ModBlocks.PRISM_WARPED_TRAPDOOR,

                    ModBlocks.PRISM_LADDER,
                    ModBlocks.DYE_MIXER,
                    ModBlocks.PRISM_COPPER_GRATE,
                    ModBlocks.PRISM_COPPER_DOOR,
                    ModBlocks.PRISM_COPPER_TRAPDOOR,

                    ModBlocks.PRISM_HANGING_SIGN,
                    ModBlocks.PRISM_WALL_HANGING_SIGN
            );

            for (DeferredBlock<?> block : cutoutBlocks) {
                ItemBlockRenderTypes.setRenderLayer(block.get(), ChunkSectionLayer.CUTOUT);
            }
        });
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        BlockColor blockColor = (state, level, pos, tintIndex) -> {
            if (level != null && pos != null) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof PrismColoredBlockEntity tile) {
                    int color = tile.getColor();
                    //System.out.println("Color handler called for " + state.getBlock() + " at " + pos + ": #" + String.format("%06X", color));
                    return color;
                }
                if (be instanceof PrismDecoratedPotBlockEntity dp) return dp.getColor();
                if (be instanceof PrismFlowerPotBlockEntity fp) return fp.getColor();
                if (be instanceof PrismSignBlockEntity sb) return sb.getColor();
                if (be instanceof PrismBarrelBlockEntity barrel) {
                    //System.out.println("Color handler called for " + state.getBlock() + " at " + pos + ": #" + String.format("%06X", barrel.getColor()));
                    return barrel.getColor();
                }
            }
            return 0xFFFFFF;
        };

        event.register(blockColor,
                ModBlocks.PRISM_LOG.get(),
                ModBlocks.PRISM_BARREL.get(),
                ModBlocks.PRISM_STRIPPED_LOG.get(),
                ModBlocks.PRISM_PLANKS.get(),
                ModBlocks.PRISM_SLAB.get(),
                ModBlocks.PRISM_STAIRS.get(),
                ModBlocks.PRISM_FENCE.get(),
                ModBlocks.PRISM_FENCE_GATE.get(),
                //ModBlocks.PRISM_DOOR.get(),
                //ModBlocks.PRISM_TRAPDOOR.get(),
                ModBlocks.PRISM_BUTTON.get(),
                ModBlocks.PRISM_PRESSURE_PLATE.get(),
                ModBlocks.PRISM_SIGN.get(),
                ModBlocks.PRISM_WALL_SIGN.get(),
                ModBlocks.PRISM_HANGING_SIGN.get(),
                ModBlocks.PRISM_WALL_HANGING_SIGN.get(),
                ModBlocks.PRISM_FLOWER_POT.get(),
                ModBlocks.PRISM_DECORATED_POT.get(),
                ModBlocks.PRISM_SAND.get(),
                ModBlocks.PRISM_CANDLE.get(),
                ModBlocks.PRISM_CARPET.get(),
                ModBlocks.PRISM_CONCRETE_POWDER.get(),
                ModBlocks.PRISM_LADDER.get(),
                ModBlocks.PRISM_SLIME_BLOCK.get(),
                ModBlocks.PRISM_WOOL.get(),
                ModBlocks.PRISM_CONCRETE.get(),
                ModBlocks.PRISM_CONCRETE_SLAB.get(),
                ModBlocks.PRISM_CONCRETE_STAIRS.get(),
                ModBlocks.PRISM_CONCRETE_WALL.get(),
                ModBlocks.PRISM_TERRACOTTA.get(),
                ModBlocks.PRISM_TERRACOTTA_SLAB.get(),
                ModBlocks.PRISM_TERRACOTTA_STAIRS.get(),
                ModBlocks.PRISM_TERRACOTTA_WALL.get(),
                ModBlocks.PRISM_STONE.get(),
                ModBlocks.PRISM_STONE_SLAB.get(),
                ModBlocks.PRISM_STONE_STAIRS.get(),
                ModBlocks.PRISM_STONE_WALL.get(),
                ModBlocks.PRISM_ANDESITE.get(),
                ModBlocks.PRISM_ANDESITE_SLAB.get(),
                ModBlocks.PRISM_ANDESITE_STAIRS.get(),
                ModBlocks.PRISM_ANDESITE_WALL.get(),
                ModBlocks.PRISM_COPPER_BLOCK.get(),
                ModBlocks.PRISM_CHISELED_COPPER.get(),
                ModBlocks.PRISM_COPPER_GRATE.get(),
                ModBlocks.PRISM_CUT_COPPER.get(),
                ModBlocks.PRISM_CUT_COPPER_SLAB.get(),
                ModBlocks.PRISM_CUT_COPPER_STAIRS.get(),
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
                ModBlocks.PRISM_WARPED_TRAPDOOR.get(),
                ModBlocks.PRISM_COPPER_TRAPDOOR.get(),
                ModBlocks.PRISM_COPPER_DOOR.get()
        );

        //System.out.println("=== COLORS REGISTERED SUCCESSFULLY ===");
    }
}