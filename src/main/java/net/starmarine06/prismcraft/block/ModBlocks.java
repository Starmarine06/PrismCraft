package net.starmarine06.prismcraft.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(PrismCraftMod.MOD_ID);
    private static final BlockBehaviour.Properties SIGN_PROPS =
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(1.0F)
                    .noOcclusion();

    public static final BlockBehaviour.Properties WOOD_PROPS =
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(1.0F);

    // Dye Mixer
    public static final DeferredBlock<DyeMixerBlock> DYE_MIXER = BLOCKS.register(
            "dye_mixer",
            registryName -> new DyeMixerBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(3.5F)
                    .noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    // Pale Oak (no suffix)
    public static final DeferredBlock<PrismWoodBlock> PRISM_LOG = BLOCKS.register(
            "prism_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_STRIPPED_LOG = BLOCKS.register(
            "prism_stripped_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_PLANKS = BLOCKS.register(
            "prism_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_SLAB = BLOCKS.register(
            "prism_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_STAIRS = BLOCKS.register(
            "prism_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_FENCE = BLOCKS.register(
            "prism_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_FENCE_GATE = BLOCKS.register(
            "prism_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    //    public static final DeferredBlock<PrismDoorBlock> PRISM_DOOR = BLOCKS.register(
//            "prism_door",
//            registryName -> new PrismDoorBlock(BlockSetType.OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
//    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_TRAPDOOR = BLOCKS.register(
//            "prism_trapdoor",
//            registryName -> new PrismTrapdoorBlock(BlockSetType.OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismButtonBlock> PRISM_BUTTON = BLOCKS.register(
            "prism_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollision().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_PRESSURE_PLATE = BLOCKS.register(
            "prism_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollision().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSignBlock> PRISM_SIGN =
            BLOCKS.register("prism_sign", registryName ->
                    new PrismSignBlock(
                            WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    public static final DeferredBlock<PrismWallSignBlock> PRISM_WALL_SIGN =
            BLOCKS.register("prism_wall_sign", registryName ->
                    new PrismWallSignBlock(
                            WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );


    public static final DeferredBlock<PrismHangingSignBlock> PRISM_HANGING_SIGN =
            BLOCKS.register("prism_hanging_sign", registryName ->
                    new PrismHangingSignBlock(
                            WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    public static final DeferredBlock<PrismWallHangingSignBlock> PRISM_WALL_HANGING_SIGN =
            BLOCKS.register("prism_wall_hanging_sign", registryName ->
                    new PrismWallHangingSignBlock(
                            WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    public static final DeferredBlock<PrismWallBlock> PRISM_WOOD_WALL = BLOCKS.register(
            "prism_wood_wall",
            registryName -> new PrismWallBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD)
                            .strength(1.0F)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismFlowerPotBlock> PRISM_FLOWER_POT =
            BLOCKS.register("prism_flower_pot", registryName ->
                    new PrismFlowerPotBlock(BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD)
                            .strength(0F)
                            .sound(SoundType.DECORATED_POT)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismDecoratedPotBlock> PRISM_DECORATED_POT = BLOCKS.register(
            "prism_decorated_pot",
            registryName -> new PrismDecoratedPotBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(0F)
                    .sound(SoundType.DECORATED_POT)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismCopperBulbBlock> PRISM_COPPER_BULB = BLOCKS.register(
            "prism_copper_bulb",
            registryName -> new PrismCopperBulbBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));


    //*********************************************************************Other Blocks
    //Concrete Vars
    public static final DeferredBlock<PrismConcreteBlock> PRISM_CONCRETE = BLOCKS.register(
            "prism_concrete",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_CONCRETE_SLAB = BLOCKS.register(
            "prism_concrete_slab",
            registryName -> new PrismSlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_CONCRETE_STAIRS = BLOCKS.register(
            "prism_concrete_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWallBlock> PRISM_CONCRETE_WALL = BLOCKS.register(
            "prism_concrete_wall",
            registryName -> new PrismWallBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.8F)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName)))
    );
    //Stone and Variants
    public static final DeferredBlock<PrismConcreteBlock> PRISM_STONE = BLOCKS.register(
            "prism_stone",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_STONE_SLAB = BLOCKS.register(
            "prism_stone_slab",
            registryName -> new PrismSlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_STONE_STAIRS = BLOCKS.register(
            "prism_stone_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWallBlock> PRISM_STONE_WALL = BLOCKS.register(
            "prism_stone_wall",
            registryName -> new PrismWallBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.8F)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    //Andesite and Vars
    public static final DeferredBlock<PrismConcreteBlock> PRISM_ANDESITE = BLOCKS.register(
            "prism_andesite",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_ANDESITE_SLAB = BLOCKS.register(
            "prism_andesite_slab",
            registryName -> new PrismSlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_ANDESITE_STAIRS = BLOCKS.register(
            "prism_andesite_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWallBlock> PRISM_ANDESITE_WALL = BLOCKS.register(
            "prism_andesite_wall",
            registryName -> new PrismWallBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.8F)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_WOOL = BLOCKS.register(
            "prism_wool",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOL).strength(0.5F).sound(SoundType.WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    //Terracotta Vars
    public static final DeferredBlock<PrismConcreteBlock> PRISM_TERRACOTTA = BLOCKS.register(
            "prism_terracotta",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_TERRACOTTA_SLAB = BLOCKS.register(
            "prism_terracotta_slab",
            registryName -> new PrismSlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_TERRACOTTA_STAIRS = BLOCKS.register(
            "prism_terracotta_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWallBlock> PRISM_TERRACOTTA_WALL = BLOCKS.register(
            "prism_terracotta_wall",
            registryName -> new PrismWallBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(1.8F)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismCandleBlock> PRISM_CANDLE = BLOCKS.register(
            "prism_candle",
            registryName -> new PrismCandleBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIAMOND).strength(0.1F).sound(SoundType.CANDLE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismCarpetBlock> PRISM_CARPET = BLOCKS.register(
            "prism_carpet",
            registryName -> new PrismCarpetBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DIAMOND).strength(0.1F).sound(SoundType.MOSS_CARPET)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismLadderBlock> PRISM_LADDER = BLOCKS.register(
            "prism_ladder",
            registryName -> new PrismLadderBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.0F)
                    .noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSand> PRISM_SAND = BLOCKS.register(
            "prism_sand",
            registryName -> new PrismSand(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcretePowder> PRISM_CONCRETE_POWDER = BLOCKS.register(
            "prism_concrete_powder",
            registryName -> new PrismConcretePowder(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlimeBlock> PRISM_SLIME_BLOCK = BLOCKS.register(
            "prism_slime_block",
            registryName -> new PrismSlimeBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GRASS).sound(SoundType.SLIME_BLOCK)
                    .friction(0.8F)
                    .strength(0.0F)
                    .noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismBarrelBlock> PRISM_BARREL = BLOCKS.register(
            "prism_barrel",
            registryName -> new PrismBarrelBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_COPPER_BLOCK = BLOCKS.register(
            "prism_copper_block",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_CHISELED_COPPER = BLOCKS.register(
            "prism_chiseled_copper",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_CUT_COPPER = BLOCKS.register(
            "prism_cut_copper",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_COPPER_GRATE = BLOCKS.register(
            "prism_copper_grate",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_CUT_COPPER_SLAB = BLOCKS.register(
            "prism_cut_copper_slab",
            registryName -> new PrismSlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_CUT_COPPER_STAIRS = BLOCKS.register(
            "prism_cut_copper_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.STONE).strength(1.8F).requiresCorrectToolForDrops()
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));


    //ORDER: Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry, Pale Oak, Bamboo, Crimson, Warped
    // Oak
    public static final DeferredBlock<Block> PRISM_OAK_DOOR =
            BLOCKS.register("prism_oak_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_OAK_TRAPDOOR =
            BLOCKS.register("prism_oak_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Spruce
    public static final DeferredBlock<Block> PRISM_SPRUCE_DOOR =
            BLOCKS.register("prism_spruce_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_SPRUCE_TRAPDOOR =
            BLOCKS.register("prism_spruce_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Birch
    public static final DeferredBlock<Block> PRISM_BIRCH_DOOR =
            BLOCKS.register("prism_birch_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_BIRCH_TRAPDOOR =
            BLOCKS.register("prism_birch_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Jungle
    public static final DeferredBlock<Block> PRISM_JUNGLE_DOOR =
            BLOCKS.register("prism_jungle_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_JUNGLE_TRAPDOOR =
            BLOCKS.register("prism_jungle_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Acacia
    public static final DeferredBlock<Block> PRISM_ACACIA_DOOR =
            BLOCKS.register("prism_acacia_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_ACACIA_TRAPDOOR =
            BLOCKS.register("prism_acacia_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Dark Oak
    public static final DeferredBlock<Block> PRISM_DARK_OAK_DOOR =
            BLOCKS.register("prism_dark_oak_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_DARK_OAK_TRAPDOOR =
            BLOCKS.register("prism_dark_oak_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Mangrove
    public static final DeferredBlock<Block> PRISM_MANGROVE_DOOR =
            BLOCKS.register("prism_mangrove_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_MANGROVE_TRAPDOOR =
            BLOCKS.register("prism_mangrove_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Cherry
    public static final DeferredBlock<Block> PRISM_CHERRY_DOOR =
            BLOCKS.register("prism_cherry_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_CHERRY_TRAPDOOR =
            BLOCKS.register("prism_cherry_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Pale Oak
    public static final DeferredBlock<Block> PRISM_PALE_OAK_DOOR =
            BLOCKS.register("prism_pale_oak_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_PALE_OAK_TRAPDOOR =
            BLOCKS.register("prism_pale_oak_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Bamboo
    public static final DeferredBlock<Block> PRISM_BAMBOO_DOOR =
            BLOCKS.register("prism_bamboo_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_BAMBOO_TRAPDOOR =
            BLOCKS.register("prism_bamboo_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Crimson
    public static final DeferredBlock<Block> PRISM_CRIMSON_DOOR =
            BLOCKS.register("prism_crimson_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_CRIMSON_TRAPDOOR =
            BLOCKS.register("prism_crimson_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );

    // Warped
    public static final DeferredBlock<Block> PRISM_WARPED_DOOR =
            BLOCKS.register("prism_warped_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_WARPED_TRAPDOOR =
            BLOCKS.register("prism_warped_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    // Copper
    public static final DeferredBlock<Block> PRISM_COPPER_DOOR =
            BLOCKS.register("prism_copper_door",
                    registryName -> new PrismDoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
    public static final DeferredBlock<Block> PRISM_COPPER_TRAPDOOR =
            BLOCKS.register("prism_copper_trapdoor",
                    registryName -> new PrismTrapdoorBlock(
                            BlockBehaviour.Properties.of()
                                    .mapColor(MapColor.WOOD).strength(1.0F).noOcclusion()
                                    .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    )
            );
}
