package net.starmarine06.prismcraft.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.item.ModItems;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(PrismCraftMod.MOD_ID);

    public static final BlockBehaviour.Properties WOOD_PROPS =
            BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F);

    // Wood blocks
    public static final DeferredBlock<PrismWoodBlock> PRISM_LOG = BLOCKS.register(
            "prism_log",
            registryName -> new PrismWoodBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismWoodBlock> PRISM_PLANKS = BLOCKS.register(
            "prism_planks",
            registryName -> new PrismWoodBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismSlabBlock> PRISM_SLAB = BLOCKS.register(
            "prism_slab",
            registryName -> new PrismSlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismStairsBlock> PRISM_STAIRS = BLOCKS.register(
            "prism_stairs",
            registryName -> new PrismStairsBlock(
                    PRISM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD).strength(2.0F, 3.0F)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismFenceBlock> PRISM_FENCE = BLOCKS.register(
            "prism_fence",
            registryName -> new PrismFenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismFenceGateBlock> PRISM_FENCE_GATE = BLOCKS.register(
            "prism_fence_gate",
            registryName -> new PrismFenceGateBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.WOOD).strength(2.0F, 3.0F)
                            .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismDoorBlock> PRISM_DOOR = BLOCKS.register(
            "prism_door",
            registryName -> new PrismDoorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F).noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    // FIXED: Use PrismTrapdoorBlock instead of SlabBlock
    public static final DeferredBlock<PrismTrapdoorBlock> PRISM_TRAPDOOR = BLOCKS.register(
            "prism_trapdoor",
            registryName -> new PrismTrapdoorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F).noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    // FIXED: Use PrismButtonBlock instead of SlabBlock
    public static final DeferredBlock<PrismButtonBlock> PRISM_BUTTON = BLOCKS.register(
            "prism_button",
            registryName -> new PrismButtonBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F).noCollission()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    // FIXED: Use PrismPressurePlateBlock instead of SlabBlock
    public static final DeferredBlock<PrismPressurePlateBlock> PRISM_PRESSURE_PLATE = BLOCKS.register(
            "prism_pressure_plate",
            registryName -> new PrismPressurePlateBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD).strength(2.0F, 3.0F).noCollission()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<PrismConcreteBlock> PRISM_CONCRETE = BLOCKS.register(
            "prism_concrete",
            registryName -> new PrismConcreteBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(1.8F)
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    public static final DeferredBlock<DyeMixerBlock> DYE_MIXER = BLOCKS.register(
            "dye_mixer",
            registryName -> new DyeMixerBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE).strength(3.5F)
                    .noOcclusion()
                    .setId(ResourceKey.create(Registries.BLOCK, registryName))));

    // Register block items
    static {
        registerBlockItem("prism_log", PRISM_LOG);
        registerBlockItem("prism_planks", PRISM_PLANKS);
        registerBlockItem("prism_slab", PRISM_SLAB);
        registerBlockItem("prism_stairs", PRISM_STAIRS);
        registerBlockItem("prism_fence", PRISM_FENCE);
        registerBlockItem("prism_fence_gate", PRISM_FENCE_GATE);
        registerBlockItem("prism_door", PRISM_DOOR);
        registerBlockItem("prism_trapdoor", PRISM_TRAPDOOR);
        registerBlockItem("prism_button", PRISM_BUTTON);
        registerBlockItem("prism_pressure_plate", PRISM_PRESSURE_PLATE);
        registerBlockItem("prism_concrete", PRISM_CONCRETE);
        registerBlockItem("dye_mixer", DYE_MIXER);
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, registryName ->
                new BlockItem(block.get(), new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, registryName))));
    }
}