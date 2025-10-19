package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PrismCraftMod.MOD_ID);

    // Dye Mixer Block Entity
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DyeMixerBlockEntity>> DYE_MIXER =
            BLOCK_ENTITIES.register("dye_mixer", () ->
                    new BlockEntityType<>(DyeMixerBlockEntity::new, ModBlocks.DYE_MIXER.get()));

    // Prism Colored Block Entity (for wood and concrete)
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PrismColoredBlockEntity>> PRISM_COLORED =
            BLOCK_ENTITIES.register("prism_colored", () ->
                    new BlockEntityType<>(PrismColoredBlockEntity::new,
                            ModBlocks.PRISM_LOG.get(),
                            ModBlocks.PRISM_PLANKS.get(),
                            ModBlocks.PRISM_SLAB.get(),
                            ModBlocks.PRISM_STAIRS.get(),
                            ModBlocks.PRISM_FENCE.get(),
                            ModBlocks.PRISM_FENCE_GATE.get(),
                            ModBlocks.PRISM_DOOR.get(),
                            ModBlocks.PRISM_TRAPDOOR.get(),
                            ModBlocks.PRISM_BUTTON.get(),
                            ModBlocks.PRISM_PRESSURE_PLATE.get(),
                            ModBlocks.PRISM_CONCRETE.get()));
}