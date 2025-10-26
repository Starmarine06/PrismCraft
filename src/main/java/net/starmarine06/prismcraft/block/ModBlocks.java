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

    public static final BlockBehaviour.Properties WOOD_PROPS =
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F);

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
    public static final DeferredBlock<PrismDoorBlock> PRISM_DOOR = BLOCKS.register(
            "prism_door",
            registryName -> new PrismDoorBlock(BlockSetType.OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_TRAPDOOR = BLOCKS.register(
            "prism_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismButtonBlock> PRISM_BUTTON = BLOCKS.register(
            "prism_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_PRESSURE_PLATE = BLOCKS.register(
            "prism_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));

    //Other Blocks
    public static final DeferredBlock<PrismConcreteBlock> PRISM_CONCRETE = BLOCKS.register(
            "prism_concrete",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_STONE = BLOCKS.register(
            "prism_stone",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_ANDESITE = BLOCKS.register(
            "prism_andesite",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F).sound(SoundType.STONE)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_WOOL = BLOCKS.register(
            "prism_wool",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOL).strength(0.8F).sound(SoundType.WOOL)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismConcreteBlock> PRISM_TERRACOTTA = BLOCKS.register(
            "prism_terracotta",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F)
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
                    .mapColor(MapColor.STONE).strength(1.8F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSand> PRISM_SAND = BLOCKS.register(
            "prism_sand",
            registryName -> new PrismSand(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.SAND).strength(0.5F).sound(SoundType.SAND)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSand> PRISM_CONCRETE_POWDER = BLOCKS.register(
            "prism_concrete_powder",
            registryName -> new PrismSand(BlockBehaviour.Properties.of()
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


    //ORDER: Oak, Spruce, Birch, Jungle, Acacia, Dark Oak, Mangrove, Cherry, Pale Oak, Bamboo, Crimson, Warped

    //Wood types (variants)
    //Oak
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_OAK_LOG = BLOCKS.register(
            "prism_oak_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_OAK_PLANKS = BLOCKS.register(
            "prism_oak_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_OAK_SLAB = BLOCKS.register(
            "prism_oak_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_OAK_STAIRS = BLOCKS.register(
            "prism_oak_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_OAK_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_OAK_FENCE = BLOCKS.register(
            "prism_oak_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_OAK_FENCE_GATE = BLOCKS.register(
            "prism_oak_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_OAK_DOOR = BLOCKS.register(
            "prism_oak_door",
            registryName -> new PrismDoorBlock(BlockSetType.OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_OAK_TRAPDOOR = BLOCKS.register(
            "prism_oak_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));

    /*public static final DeferredBlock<PrismButtonBlock> PRISM_OAK_BUTTON = BLOCKS.register(
            "prism_oak_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_OAK_PRESSURE_PLATE = BLOCKS.register(
            "prism_oak_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    //Spruce
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_SPRUCE_LOG = BLOCKS.register(
            "prism_spruce_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_SPRUCE_PLANKS = BLOCKS.register(
            "prism_spruce_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_SPRUCE_SLAB = BLOCKS.register(
            "prism_spruce_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_SPRUCE_STAIRS = BLOCKS.register(
            "prism_spruce_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_SPRUCE_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_SPRUCE_FENCE = BLOCKS.register(
            "prism_spruce_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_SPRUCE_FENCE_GATE = BLOCKS.register(
            "prism_spruce_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_SPRUCE_DOOR = BLOCKS.register(
            "prism_spruce_door",
            registryName -> new PrismDoorBlock(BlockSetType.SPRUCE, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_SPRUCE_TRAPDOOR = BLOCKS.register(
            "prism_spruce_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.SPRUCE, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_SPRUCE_BUTTON = BLOCKS.register(
            "prism_spruce_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_SPRUCE_PRESSURE_PLATE = BLOCKS.register(
            "prism_spruce_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Birch
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_BIRCH_LOG = BLOCKS.register(
            "prism_birch_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_BIRCH_PLANKS = BLOCKS.register(
            "prism_birch_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_BIRCH_SLAB = BLOCKS.register(
            "prism_birch_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_BIRCH_STAIRS = BLOCKS.register(
            "prism_birch_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_BIRCH_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_BIRCH_FENCE = BLOCKS.register(
            "prism_birch_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_BIRCH_FENCE_GATE = BLOCKS.register(
            "prism_birch_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_BIRCH_DOOR = BLOCKS.register(
            "prism_birch_door",
            registryName -> new PrismDoorBlock(BlockSetType.BIRCH, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_BIRCH_TRAPDOOR = BLOCKS.register(
            "prism_birch_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.BIRCH, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_BIRCH_BUTTON = BLOCKS.register(
            "prism_birch_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_BIRCH_PRESSURE_PLATE = BLOCKS.register(
            "prism_birch_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Jungle
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_JUNGLE_LOG = BLOCKS.register(
            "prism_jungle_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_JUNGLE_PLANKS = BLOCKS.register(
            "prism_jungle_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_JUNGLE_SLAB = BLOCKS.register(
            "prism_jungle_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_JUNGLE_STAIRS = BLOCKS.register(
            "prism_jungle_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_JUNGLE_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_JUNGLE_FENCE = BLOCKS.register(
            "prism_jungle_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_JUNGLE_FENCE_GATE = BLOCKS.register(
            "prism_jungle_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_JUNGLE_DOOR = BLOCKS.register(
            "prism_jungle_door",
            registryName -> new PrismDoorBlock(BlockSetType.JUNGLE, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_JUNGLE_TRAPDOOR = BLOCKS.register(
            "prism_jungle_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.JUNGLE, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_JUNGLE_BUTTON = BLOCKS.register(
            "prism_jungle_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_JUNGLE_PRESSURE_PLATE = BLOCKS.register(
            "prism_jungle_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Acacia
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_ACACIA_LOG = BLOCKS.register(
            "prism_acacia_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_ACACIA_PLANKS = BLOCKS.register(
            "prism_acacia_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_ACACIA_SLAB = BLOCKS.register(
            "prism_acacia_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_ACACIA_STAIRS = BLOCKS.register(
            "prism_acacia_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_ACACIA_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_ACACIA_FENCE = BLOCKS.register(
            "prism_acacia_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_ACACIA_FENCE_GATE = BLOCKS.register(
            "prism_acacia_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_ACACIA_DOOR = BLOCKS.register(
            "prism_acacia_door",
            registryName -> new PrismDoorBlock(BlockSetType.ACACIA, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_ACACIA_TRAPDOOR = BLOCKS.register(
            "prism_acacia_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.ACACIA, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_ACACIA_BUTTON = BLOCKS.register(
            "prism_acacia_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_ACACIA_PRESSURE_PLATE = BLOCKS.register(
            "prism_acacia_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Dark Oak
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_DARK_OAK_LOG = BLOCKS.register(
            "prism_dark_oak_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_DARK_OAK_PLANKS = BLOCKS.register(
            "prism_dark_oak_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_DARK_OAK_SLAB = BLOCKS.register(
            "prism_dark_oak_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_DARK_OAK_STAIRS = BLOCKS.register(
            "prism_dark_oak_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_DARK_OAK_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_DARK_OAK_FENCE = BLOCKS.register(
            "prism_dark_oak_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_DARK_OAK_FENCE_GATE = BLOCKS.register(
            "prism_dark_oak_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_DARK_OAK_DOOR = BLOCKS.register(
            "prism_dark_oak_door",
            registryName -> new PrismDoorBlock(BlockSetType.DARK_OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_DARK_OAK_TRAPDOOR = BLOCKS.register(
            "prism_dark_oak_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.DARK_OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_DARK_OAK_BUTTON = BLOCKS.register(
            "prism_dark_oak_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_DARK_OAK_PRESSURE_PLATE = BLOCKS.register(
            "prism_dark_oak_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Mangrove
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_MANGROVE_LOG = BLOCKS.register(
            "prism_mangrove_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_MANGROVE_PLANKS = BLOCKS.register(
            "prism_mangrove_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_MANGROVE_SLAB = BLOCKS.register(
            "prism_mangrove_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_MANGROVE_STAIRS = BLOCKS.register(
            "prism_mangrove_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_MANGROVE_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_MANGROVE_FENCE = BLOCKS.register(
            "prism_mangrove_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_MANGROVE_FENCE_GATE = BLOCKS.register(
            "prism_mangrove_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_MANGROVE_DOOR = BLOCKS.register(
            "prism_mangrove_door",
            registryName -> new PrismDoorBlock(BlockSetType.MANGROVE, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_MANGROVE_TRAPDOOR = BLOCKS.register(
            "prism_mangrove_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.MANGROVE, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_MANGROVE_BUTTON = BLOCKS.register(
            "prism_mangrove_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_MANGROVE_PRESSURE_PLATE = BLOCKS.register(
            "prism_mangrove_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Cherry
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_CHERRY_LOG = BLOCKS.register(
            "prism_cherry_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_CHERRY_PLANKS = BLOCKS.register(
            "prism_cherry_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_CHERRY_SLAB = BLOCKS.register(
            "prism_cherry_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_CHERRY_STAIRS = BLOCKS.register(
            "prism_cherry_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_CHERRY_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_CHERRY_FENCE = BLOCKS.register(
            "prism_cherry_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_CHERRY_FENCE_GATE = BLOCKS.register(
            "prism_cherry_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_CHERRY_DOOR = BLOCKS.register(
            "prism_cherry_door",
            registryName -> new PrismDoorBlock(BlockSetType.CHERRY, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_CHERRY_TRAPDOOR = BLOCKS.register(
            "prism_cherry_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.CHERRY, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_CHERRY_BUTTON = BLOCKS.register(
            "prism_cherry_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_CHERRY_PRESSURE_PLATE = BLOCKS.register(
            "prism_cherry_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    //Pale Oak
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_PALE_OAK_LOG = BLOCKS.register(
            "prism_pale_oak_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_PALE_OAK_PLANKS = BLOCKS.register(
            "prism_pale_oak_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_PALE_OAK_SLAB = BLOCKS.register(
            "prism_pale_oak_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_PALE_OAK_STAIRS = BLOCKS.register(
            "prism_pale_oak_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PALE_OAK_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_PALE_OAK_FENCE = BLOCKS.register(
            "prism_pale_oak_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_PALE_OAK_FENCE_GATE = BLOCKS.register(
            "prism_pale_oak_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_PALE_OAK_DOOR = BLOCKS.register(
            "prism_pale_oak_door",
            registryName -> new PrismDoorBlock(BlockSetType.PALE_OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_PALE_OAK_TRAPDOOR = BLOCKS.register(
            "prism_pale_oak_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.PALE_OAK, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_PALE_OAK_BUTTON = BLOCKS.register(
            "prism_pale_oak_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_PALE_OAK_PRESSURE_PLATE = BLOCKS.register(
            "prism_pale_oak_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Bamboo
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_BAMBOO_LOG = BLOCKS.register(
            "prism_bamboo_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_BAMBOO_PLANKS = BLOCKS.register(
            "prism_bamboo_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_BAMBOO_SLAB = BLOCKS.register(
            "prism_bamboo_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_BAMBOO_STAIRS = BLOCKS.register(
            "prism_bamboo_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_BAMBOO_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_BAMBOO_FENCE = BLOCKS.register(
            "prism_bamboo_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_BAMBOO_FENCE_GATE = BLOCKS.register(
            "prism_bamboo_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_BAMBOO_DOOR = BLOCKS.register(
            "prism_bamboo_door",
            registryName -> new PrismDoorBlock(BlockSetType.BAMBOO, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_BAMBOO_TRAPDOOR = BLOCKS.register(
            "prism_bamboo_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.BAMBOO, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_BAMBOO_BUTTON = BLOCKS.register(
            "prism_bamboo_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_BAMBOO_PRESSURE_PLATE = BLOCKS.register(
            "prism_bamboo_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Crimson
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_CRIMSON_LOG = BLOCKS.register(
            "prism_crimson_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_CRIMSON_PLANKS = BLOCKS.register(
            "prism_crimson_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_CRIMSON_SLAB = BLOCKS.register(
            "prism_crimson_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_CRIMSON_STAIRS = BLOCKS.register(
            "prism_crimson_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_CRIMSON_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_CRIMSON_FENCE = BLOCKS.register(
            "prism_crimson_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_CRIMSON_FENCE_GATE = BLOCKS.register(
            "prism_crimson_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_CRIMSON_DOOR = BLOCKS.register(
            "prism_crimson_door",
            registryName -> new PrismDoorBlock(BlockSetType.CRIMSON, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_CRIMSON_TRAPDOOR = BLOCKS.register(
            "prism_crimson_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.CRIMSON, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_CRIMSON_BUTTON = BLOCKS.register(
            "prism_crimson_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_CRIMSON_PRESSURE_PLATE = BLOCKS.register(
            "prism_crimson_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/

    // Warped
    /*public static final DeferredBlock<PrismWoodBlock> PRISM_WARPED_LOG = BLOCKS.register(
            "prism_warped_log",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismWoodBlock> PRISM_WARPED_PLANKS = BLOCKS.register(
            "prism_warped_planks",
            registryName -> new PrismWoodBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismSlabBlock> PRISM_WARPED_SLAB = BLOCKS.register(
            "prism_warped_slab",
            registryName -> new PrismSlabBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismStairsBlock> PRISM_WARPED_STAIRS = BLOCKS.register(
            "prism_warped_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_WARPED_PLANKS.get().defaultBlockState(),
                    WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceBlock> PRISM_WARPED_FENCE = BLOCKS.register(
            "prism_warped_fence",
            registryName -> new PrismFenceBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismFenceGateBlock> PRISM_WARPED_FENCE_GATE = BLOCKS.register(
            "prism_warped_fence_gate",
            registryName -> new PrismFenceGateBlock(WOOD_PROPS.setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
    public static final DeferredBlock<PrismDoorBlock> PRISM_WARPED_DOOR = BLOCKS.register(
            "prism_warped_door",
            registryName -> new PrismDoorBlock(BlockSetType.WARPED, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_WARPED_TRAPDOOR = BLOCKS.register(
            "prism_warped_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockSetType.WARPED, WOOD_PROPS.noOcclusion().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    /*public static final DeferredBlock<PrismButtonBlock> PRISM_WARPED_BUTTON = BLOCKS.register(
            "prism_warped_button",
            registryName -> new PrismButtonBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_WARPED_PRESSURE_PLATE = BLOCKS.register(
            "prism_warped_pressure_plate",
            registryName -> new PrismPressurePlateBlock(WOOD_PROPS.noCollission().setId(ResourceKey.create(Registries.BLOCK, registryName))));*/
}
