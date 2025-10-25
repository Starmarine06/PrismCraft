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
                        // Pale Oak
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
                        output.accept(ModBlocks.PRISM_CONCRETE.get().asItem());
                        output.accept(ModBlocks.PRISM_STRIPPED_LOG.get().asItem());

                        // Oak
                        /*output.accept(ModBlocks.PRISM_OAK_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_OAK_TRAPDOOR.get().asItem());

                        // Birch
                        output.accept(ModBlocks.PRISM_BIRCH_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_BIRCH_TRAPDOOR.get().asItem());

                        // Acacia
                        output.accept(ModBlocks.PRISM_ACACIA_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_ACACIA_TRAPDOOR.get().asItem());

                        // Dark Oak
                        output.accept(ModBlocks.PRISM_DARK_OAK_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_DARK_OAK_TRAPDOOR.get().asItem());

                        // Jungle
                        output.accept(ModBlocks.PRISM_JUNGLE_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_JUNGLE_TRAPDOOR.get().asItem());

                        // Mangrove
                        output.accept(ModBlocks.PRISM_MANGROVE_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_MANGROVE_TRAPDOOR.get().asItem());

                        // Cherry
                        output.accept(ModBlocks.PRISM_CHERRY_LOG.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_PLANKS.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_SLAB.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_STAIRS.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_FENCE.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_FENCE_GATE.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_BUTTON.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_PRESSURE_PLATE.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_DOOR.get().asItem());
                        output.accept(ModBlocks.PRISM_CHERRY_TRAPDOOR.get().asItem());*/

                        // Dye Mixer
                        output.accept(ModBlocks.DYE_MIXER.get().asItem());
                        output.accept(ModBlocks.PRISM_BARREL.get().asItem());
                        output.accept(ModBlocks.PRISM_SAND.get().asItem());
                        output.accept(ModBlocks.PRISM_CONCRETE_POWDERED.get().asItem());
                        output.accept(ModBlocks.PRISM_CANDLE.get().asItem());
                        output.accept(ModBlocks.PRISM_CARPET.get().asItem());
                        output.accept(ModBlocks.PRISM_TERRACOTTA.get().asItem());
                        output.accept(ModBlocks.PRISM_SLIME_BLOCK.get().asItem());
                        output.accept(ModBlocks.PRISM_STONE.get().asItem());
                        output.accept(ModBlocks.PRISM_ANDESITE.get().asItem());
                        output.accept(ModBlocks.PRISM_WOOL.get().asItem());

                    })
                    .build());
}