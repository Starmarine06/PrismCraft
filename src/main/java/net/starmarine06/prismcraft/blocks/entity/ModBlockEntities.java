package net.starmarine06.prismcraft.blocks.entity;

import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.blocks.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, PrismCraftMod.MOD_ID);

    // Register the Dye Mixer block entity
    public static final Supplier<BlockEntityType<DyeMixerBlockEntity>> DYE_MIXER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dye_mixer_block_entity", () ->
                    BlockEntityType.Builder.of(DyeMixerBlockEntity::new, ModBlocks.DYE_MIXER.get()).build(null));

    // Register the Colorable block entity for all colorable blocks
    public static final Supplier<BlockEntityType<ColorableBlockEntity>> COLORABLE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("colorable_block_entity", () ->
                    BlockEntityType.Builder.of(
                            ColorableBlockEntity::new,
                            ModBlocks.WHITE_WOOD.get(),
                            ModBlocks.WHITE_WOOD_PLANKS.get(),
                            ModBlocks.WHITE_WOOD_STAIRS.get(),
                            ModBlocks.WHITE_WOOD_SLAB.get(),
                            ModBlocks.WHITE_CONCRETE.get()
                    ).build(null)
            );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}