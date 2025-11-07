package net.starmarine06.prismcraft.item;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrismCraftMod.MOD_ID);

    private static void registerColoredBlockItem(String name, DeferredBlock<?> block) {
        ITEMS.register(name, registryName -> new BlockItem(block.get(),
                new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)))
        );
    }
    private static void registerBlockItem(String name, DeferredBlock<?> block) {
        ITEMS.register(name, registryName ->
                new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)))
        );
    }

    static {
        registerBlockItem("dye_mixer", ModBlocks.DYE_MIXER);
        registerColoredBlockItem("prism_log", ModBlocks.PRISM_LOG);
        registerColoredBlockItem("prism_stripped_log", ModBlocks.PRISM_STRIPPED_LOG);
        registerColoredBlockItem("prism_planks", ModBlocks.PRISM_PLANKS);
        registerColoredBlockItem("prism_slab", ModBlocks.PRISM_SLAB);
        registerColoredBlockItem("prism_stairs", ModBlocks.PRISM_STAIRS);
        registerColoredBlockItem("prism_fence", ModBlocks.PRISM_FENCE);
        registerColoredBlockItem("prism_fence_gate", ModBlocks.PRISM_FENCE_GATE);
        registerColoredBlockItem("prism_door", ModBlocks.PRISM_DOOR);
        registerColoredBlockItem("prism_trapdoor", ModBlocks.PRISM_TRAPDOOR);
        registerColoredBlockItem("prism_button", ModBlocks.PRISM_BUTTON);
        registerColoredBlockItem("prism_pressure_plate", ModBlocks.PRISM_PRESSURE_PLATE);
        registerColoredBlockItem("prism_standing_sign", ModBlocks.PRISM_STANDING_SIGN);
        registerColoredBlockItem("prism_wall_sign", ModBlocks.PRISM_WALL_SIGN);
        registerColoredBlockItem("prism_ceiling_hanging_sign", ModBlocks.PRISM_CEILING_HANGING_SIGN);
        registerColoredBlockItem("prism_wall_hanging_sign", ModBlocks.PRISM_WALL_HANGING_SIGN);
        registerColoredBlockItem("prism_flower_pot", ModBlocks.PRISM_FLOWER_POT);
        registerColoredBlockItem("prism_decorated_pot", ModBlocks.PRISM_DECORATED_POT);
        registerColoredBlockItem("prism_sand", ModBlocks.PRISM_SAND);
        registerColoredBlockItem("prism_candle", ModBlocks.PRISM_CANDLE);
        registerColoredBlockItem("prism_carpet", ModBlocks.PRISM_CARPET);
        registerColoredBlockItem("prism_ladder", ModBlocks.PRISM_LADDER);
        registerColoredBlockItem("prism_barrel", ModBlocks.PRISM_BARREL);
        registerColoredBlockItem("prism_slime_block", ModBlocks.PRISM_SLIME_BLOCK);
        registerColoredBlockItem("prism_wool", ModBlocks.PRISM_WOOL);
        registerColoredBlockItem("prism_concrete", ModBlocks.PRISM_CONCRETE);
        registerColoredBlockItem("prism_concrete_powder", ModBlocks.PRISM_CONCRETE_POWDER);
        registerColoredBlockItem("prism_concrete_slab", ModBlocks.PRISM_CONCRETE_SLAB);
        registerColoredBlockItem("prism_concrete_wall", ModBlocks.PRISM_CONCRETE_WALL);
        registerColoredBlockItem("prism_concrete_stairs", ModBlocks.PRISM_CONCRETE_STAIRS);
        registerColoredBlockItem("prism_stone", ModBlocks.PRISM_STONE);
        registerColoredBlockItem("prism_stone_slab", ModBlocks.PRISM_STONE_SLAB);
        registerColoredBlockItem("prism_stone_stairs", ModBlocks.PRISM_STONE_STAIRS);
        registerColoredBlockItem("prism_stone_wall", ModBlocks.PRISM_STONE_WALL);
        registerColoredBlockItem("prism_andesite", ModBlocks.PRISM_ANDESITE);
        registerColoredBlockItem("prism_andesite_slab", ModBlocks.PRISM_ANDESITE_SLAB);
        registerColoredBlockItem("prism_andesite_stairs", ModBlocks.PRISM_ANDESITE_STAIRS);
        registerColoredBlockItem("prism_andesite_wall", ModBlocks.PRISM_ANDESITE_WALL);
        registerColoredBlockItem("prism_terracotta", ModBlocks.PRISM_TERRACOTTA);
        registerColoredBlockItem("prism_terracotta_slab", ModBlocks.PRISM_TERRACOTTA_SLAB);
        registerColoredBlockItem("prism_terracotta_stairs", ModBlocks.PRISM_TERRACOTTA_STAIRS);
        registerColoredBlockItem("prism_terracotta_wall", ModBlocks.PRISM_TERRACOTTA_WALL);
        registerBlockItem("prism_oak_door", ModBlocks.PRISM_OAK_DOOR);
        registerBlockItem("prism_oak_trapdoor", ModBlocks.PRISM_OAK_TRAPDOOR);
        registerBlockItem("prism_spruce_door", ModBlocks.PRISM_SPRUCE_DOOR);
        registerBlockItem("prism_spruce_trapdoor", ModBlocks.PRISM_SPRUCE_TRAPDOOR);
        registerBlockItem("prism_birch_door", ModBlocks.PRISM_BIRCH_DOOR);
        registerBlockItem("prism_birch_trapdoor", ModBlocks.PRISM_BIRCH_TRAPDOOR);
        registerBlockItem("prism_jungle_door", ModBlocks.PRISM_JUNGLE_DOOR);
        registerBlockItem("prism_jungle_trapdoor", ModBlocks.PRISM_JUNGLE_TRAPDOOR);
        registerBlockItem("prism_acacia_door", ModBlocks.PRISM_ACACIA_DOOR);
        registerBlockItem("prism_acacia_trapdoor", ModBlocks.PRISM_ACACIA_TRAPDOOR);
        registerBlockItem("prism_dark_oak_door", ModBlocks.PRISM_DARK_OAK_DOOR);
        registerBlockItem("prism_dark_oak_trapdoor", ModBlocks.PRISM_DARK_OAK_TRAPDOOR);
        registerBlockItem("prism_mangrove_door", ModBlocks.PRISM_MANGROVE_DOOR);
        registerBlockItem("prism_mangrove_trapdoor", ModBlocks.PRISM_MANGROVE_TRAPDOOR);
        registerBlockItem("prism_cherry_door", ModBlocks.PRISM_CHERRY_DOOR);
        registerBlockItem("prism_cherry_trapdoor", ModBlocks.PRISM_CHERRY_TRAPDOOR);
        registerBlockItem("prism_pale_oak_door", ModBlocks.PRISM_PALE_OAK_DOOR);
        registerBlockItem("prism_pale_oak_trapdoor", ModBlocks.PRISM_PALE_OAK_TRAPDOOR);
        registerBlockItem("prism_bamboo_door", ModBlocks.PRISM_BAMBOO_DOOR);
        registerBlockItem("prism_bamboo_trapdoor", ModBlocks.PRISM_BAMBOO_TRAPDOOR);
        registerBlockItem("prism_crimson_door", ModBlocks.PRISM_CRIMSON_DOOR);
        registerBlockItem("prism_crimson_trapdoor", ModBlocks.PRISM_CRIMSON_TRAPDOOR);
        registerBlockItem("prism_warped_door", ModBlocks.PRISM_WARPED_DOOR);
        registerBlockItem("prism_warped_trapdoor", ModBlocks.PRISM_WARPED_TRAPDOOR);
    }

    public static final DeferredItem<Item> PRISMCRAFT_ICON = ITEMS.registerSimpleItem(
            "prismcraft_icon",
            new Item.Properties()
    );
}