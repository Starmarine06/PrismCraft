package net.starmarine06.prismcraft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;

import java.util.function.Consumer;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrismCraftMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PRISMCRAFT_TAB =
            CREATIVE_MODE_TABS.register("prismcraft_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.prismcraft.prismcraft_tab"))
                    .icon(() -> new ItemStack(ModItems.PRISMCRAFT_ICON.get()))
                    .displayItems((parameters, output) -> {
                        // Dye Mixer
                        output.accept(new ItemStack(ModItems.DYE_MIXER.get()));

                        // Prism
                        output.accept(new ItemStack(ModItems.PRISM_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PRESSURE_PLATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_TRAPDOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_STRIPPED_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_STANDING_SIGN.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WALL_SIGN.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CEILING_HANGING_SIGN.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WALL_HANGING_SIGN.get()));
                        output.accept(new ItemStack(ModItems.PRISM_FLOWER_POT.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DECORATED_POT.get()));

                        output.accept(new ItemStack(ModItems.PRISM_BARREL.get()));
                        output.accept(new ItemStack(ModItems.PRISM_LADDER.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SAND.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CANDLE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CARPET.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SLIME_BLOCK.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WOOL.get()));

                        output.accept(new ItemStack(ModItems.PRISM_CONCRETE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CONCRETE_POWDER.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CONCRETE_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CONCRETE_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CONCRETE_WALL.get()));
                        output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA.get()));
                        output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_TERRACOTTA_WALL.get()));
                        output.accept(new ItemStack(ModItems.PRISM_STONE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_STONE_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_STONE_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_STONE_WALL.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ANDESITE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ANDESITE_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ANDESITE_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ANDESITE_WALL.get()));
                        output.accept(new ItemStack(ModItems.PRISM_COPPER_BLOCK.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHISELED_COPPER_BLOCK.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CUT_COPPER_BLOCK.get()));

                        // Oak
                        /*output.accept(new ItemStack(ModItems.PRISM_OAK_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_OAK_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_OAK_TRAPDOOR.get()));

                        // Spruce
                        /*output.accept(new ItemStack(ModItems.PRISM_SPRUCE_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_SPRUCE_TRAPDOOR.get()));

                        // Birch
                        /*output.accept(new ItemStack(ModItems.PRISM_BIRCH_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BIRCH_TRAPDOOR.get()));

                        // Jungle
                        /*output.accept(new ItemStack(ModItems.PRISM_JUNGLE_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_JUNGLE_TRAPDOOR.get()));

                        // Acacia
                        /*output.accept(new ItemStack(ModItems.PRISM_ACACIA_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_ACACIA_TRAPDOOR.get()));

                        // Dark Oak
                        /*output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_DARK_OAK_TRAPDOOR.get()));


                        // Mangrove
                        /*output.accept(new ItemStack(ModItems.PRISM_MANGROVE_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_PRESSURE_PLATE.get()))*/
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_MANGROVE_TRAPDOOR.get()));

                        // Cherry
                        /*output.accept(new ItemStack(ModItems.PRISM_CHERRY_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CHERRY_TRAPDOOR.get()));

                        // Pale Oak
                        /*output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_PALE_OAK_TRAPDOOR.get()));

                        // Bamboo
                        /*output.accept(new ItemStack(ModItems.PRISM_BAMBOO_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_BAMBOO_TRAPDOOR.get()));

                        // Crimson
                        /*output.accept(new ItemStack(ModItems.PRISM_CRIMSON_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_CRIMSON_TRAPDOOR.get()));

                        // Warped
                        /*output.accept(new ItemStack(ModItems.PRISM_WARPED_LOG.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_PLANKS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_SLAB.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_STAIRS.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_FENCE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_FENCE_GATE.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_BUTTON.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_PRESSURE_PLATE.get()));*/
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_DOOR.get()));
                        output.accept(new ItemStack(ModItems.PRISM_WARPED_TRAPDOOR.get()));
                        //Items Registration
                        
                    })
                    .build());
}