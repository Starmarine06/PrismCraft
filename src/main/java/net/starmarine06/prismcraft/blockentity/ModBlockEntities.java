package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.common.Mod;
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

    // In your ModBlockEntities class
    public static final DeferredHolder<BlockEntityType<?>,BlockEntityType<PrismBarrelBlockEntity>> PRISM_BARREL =
            BLOCK_ENTITIES.register("prism_barrel", () ->
                    new BlockEntityType<>(PrismBarrelBlockEntity::new, ModBlocks.PRISM_BARREL.get()));

    /*public static final Supplier<BlockEntityType<PrismSignBlockEntity>> PRISM_SIGN =
            BLOCK_ENTITY_TYPES.register("prism_sign",
                    () -> BlockEntityType.Builder.of(PrismSignBlockEntity::new,
                            ModBlocks.PRISM_STANDING_SIGN.get(),
                            ModBlocks.PRISM_WALL_SIGN.get()).build(null));*/

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
                            ModBlocks.PRISM_CONCRETE.get(),
                            ModBlocks.PRISM_SAND.get(),
                            ModBlocks.PRISM_CANDLE.get(),
                            ModBlocks.PRISM_CARPET.get(),
                            ModBlocks.PRISM_CONCRETE_POWDERED.get(),
                            ModBlocks.PRISM_LADDER.get()));
}