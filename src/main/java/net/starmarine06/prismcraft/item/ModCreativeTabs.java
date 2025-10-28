package net.starmarine06.prismcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrismCraftMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PRISMCRAFT_TAB =
            CREATIVE_MODE_TABS.register("prismcraft_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.prismcraft.prismcraft_tab"))
                    .icon(() -> new ItemStack(ModItems.PRISMCRAFT_ICON.get()))
                    .displayItems((parameters, output) -> {

                        // Dye Mixer
                        output.accept(ModBlocks.DYE_MIXER.get().asItem());

                        // Prism
                        output.accept(ModBlocks.PRISM_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_TRAPDOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_STRIPPED_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_STANDING_SIGN.get().asItem());
                        output.accept(ModBlocks.PRISM_WALL_SIGN.get().asItem());
                        output.accept(ModBlocks.PRISM_CEILING_HANGING_SIGN.get().asItem());
                        output.accept(ModBlocks.PRISM_WALL_HANGING_SIGN.get().asItem());
                        output.accept(ModBlocks.PRISM_FLOWER_POT.get().asItem());
                        output.accept(ModBlocks.PRISM_DECORATED_POT.get().asItem());

                        output.accept(ModBlocks.PRISM_BARREL.get().asItem());
                        output.accept(ModBlocks.PRISM_LADDER.get().asItem());
                        output.accept(ModBlocks.PRISM_SAND.get().asItem());
                        output.accept(ModBlocks.PRISM_CANDLE.get().asItem());
                        output.accept(ModBlocks.PRISM_CARPET.get().asItem());
                        output.accept(ModBlocks.PRISM_SLIME_BLOCK.get().asItem());
                        output.accept(ModBlocks.PRISM_WOOL.get().asItem());

                        output.accept(ModBlocks.PRISM_CONCRETE.get().asItem());
                        output.accept(ModBlocks.PRISM_CONCRETE_POWDER.get().asItem());
                        output.accept(ModBlocks.PRISM_CONCRETE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_CONCRETE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_CONCRETE_WALL.get().asItem());
                        output.accept(ModBlocks.PRISM_TERRACOTTA.get().asItem());
                        output.accept(ModBlocks.PRISM_TERRACOTTA_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_TERRACOTTA_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_TERRACOTTA_WALL.get().asItem());
                        output.accept(ModBlocks.PRISM_STONE.get().asItem());
                        output.accept(ModBlocks.PRISM_STONE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_STONE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_STONE_WALL.get().asItem());
                        output.accept(ModBlocks.PRISM_ANDESITE.get().asItem());
                        output.accept(ModBlocks.PRISM_ANDESITE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_ANDESITE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_ANDESITE_WALL.get().asItem());
                        output.accept(ModBlocks.PRISM_COPPER_BLOCK.get().asItem());
                        output.accept(ModBlocks.PRISM_CHISELED_COPPER_BLOCK.get().asItem());
                        output.accept(ModBlocks.PRISM_CUT_COPPER_BLOCK.get().asItem());

                        // Oak
                        /*output.accept(ModBlocks.PRISM_OAK_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_OAK_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_TRAPDOOR.get().asItem());

                        // Spruce
                        /*output.accept(ModBlocks.PRISM_SPRUCE_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_SPRUCE_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_SPRUCE_TRAPDOOR.get().asItem());

                        // Birch
                        /*output.accept(ModBlocks.PRISM_BIRCH_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_BIRCH_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_TRAPDOOR.get().asItem());

                        // Jungle
                        /*output.accept(ModBlocks.PRISM_JUNGLE_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_JUNGLE_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_TRAPDOOR.get().asItem());

                        // Acacia
                        /*output.accept(ModBlocks.PRISM_ACACIA_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_ACACIA_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_TRAPDOOR.get().asItem());

                        // Dark Oak
                        /*output.accept(ModBlocks.PRISM_DARK_OAK_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_DARK_OAK_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_TRAPDOOR.get().asItem());


                        // Mangrove
                        /*output.accept(ModBlocks.PRISM_MANGROVE_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_PRESSURE_PLATE.get().asItem())*/
                        output.accept(ModBlocks.PRISM_MANGROVE_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_TRAPDOOR.get().asItem());

                        // Cherry
                        /*output.accept(ModBlocks.PRISM_CHERRY_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_CHERRY_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_TRAPDOOR.get().asItem());

                        // Pale Oak
                        /*output.accept(ModBlocks.PRISM_PALE_OAK_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_PALE_OAK_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_PALE_OAK_TRAPDOOR.get().asItem());

                        // Bamboo
                        /*output.accept(ModBlocks.PRISM_BAMBOO_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_BAMBOO_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_BAMBOO_TRAPDOOR.get().asItem());

                        // Crimson
                        /*output.accept(ModBlocks.PRISM_CRIMSON_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_CRIMSON_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_CRIMSON_TRAPDOOR.get().asItem());

                        // Warped
                        /*output.accept(ModBlocks.PRISM_WARPED_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_PRESSURE_PLATE.get().asItem());*/
                        output.accept(ModBlocks.PRISM_WARPED_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_WARPED_TRAPDOOR.get().asItem());
                    })
                    .build());
}