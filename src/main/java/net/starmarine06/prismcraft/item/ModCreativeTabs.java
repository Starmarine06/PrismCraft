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
                        // Wood blocks - use asItem() to get the BlockItem
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

                        // Concrete
                        output.accept(ModBlocks.PRISM_CONCRETE.get().asItem());

                        // Dye Mixer
                        output.accept(ModBlocks.DYE_MIXER.get().asItem());
                    })
                    .build());
}