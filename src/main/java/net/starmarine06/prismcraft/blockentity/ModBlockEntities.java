package net.starmarine06.prismcraft.blockentity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;

import java.util.Set;
import java.util.function.Supplier;

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

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PrismSignBlockEntity>> PRISM_SIGN_ENTITY =
            BLOCK_ENTITIES.register("prism_sign",
                    () -> new BlockEntityType<>(
                            PrismSignBlockEntity::new,
                            ModBlocks.PRISM_SIGN.get(),
                            ModBlocks.PRISM_WALL_SIGN.get()
                    ));


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PrismHangingSignBlockEntity>> PRISM_HANGING_SIGN_ENTITY =
            BLOCK_ENTITIES.register("prism_hanging_sign", () ->
                    new BlockEntityType<>(
                            PrismHangingSignBlockEntity::new,
                            ModBlocks.PRISM_WALL_HANGING_SIGN.get(),
                            ModBlocks.PRISM_HANGING_SIGN.get()
                    )
            );
    public static final Supplier<BlockEntityType<PrismDecoratedPotBlockEntity>> PRISM_DECORATED_POT =
            BLOCK_ENTITIES.register("prism_decorated_pot", () ->
                    new BlockEntityType<>(
                            PrismDecoratedPotBlockEntity::new,
                            Set.of(ModBlocks.PRISM_DECORATED_POT.get())
                    )
            );


    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PrismFlowerPotBlockEntity>> PRISM_FLOWER_POT_ENTITY =
            BLOCK_ENTITIES.register("prism_flower_pot", () ->
                    new BlockEntityType<>(
                            PrismFlowerPotBlockEntity::new,
                            ModBlocks.PRISM_FLOWER_POT.get()
                    )
            );


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
                            ModBlocks.PRISM_STRIPPED_LOG.get(),
                            ModBlocks.PRISM_PLANKS.get(),
                            ModBlocks.PRISM_SLAB.get(),
                            ModBlocks.PRISM_STAIRS.get(),
                            ModBlocks.PRISM_FENCE.get(),
                            ModBlocks.PRISM_FENCE_GATE.get(),
                            ModBlocks.PRISM_WOOD_WALL.get(),
                            //ModBlocks.PRISM_DOOR.get(),
                            //ModBlocks.PRISM_TRAPDOOR.get(),
                            ModBlocks.PRISM_BUTTON.get(),
                            ModBlocks.PRISM_PRESSURE_PLATE.get(),
                            ModBlocks.PRISM_SIGN.get(),
                            ModBlocks.PRISM_WALL_SIGN.get(),
                            ModBlocks.PRISM_HANGING_SIGN.get(),
                            ModBlocks.PRISM_WALL_HANGING_SIGN.get(),
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
                            ModBlocks.PRISM_CUT_COPPER.get(),
                            ModBlocks.PRISM_COPPER_GRATE.get(),
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

                            ModBlocks.PRISM_COPPER_DOOR.get(),
                            ModBlocks.PRISM_COPPER_TRAPDOOR.get(),
                            ModBlocks.PRISM_COPPER_BULB.get()
                    ));
}