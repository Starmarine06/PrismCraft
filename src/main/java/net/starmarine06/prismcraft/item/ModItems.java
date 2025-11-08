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

    private static DeferredItem<Item> registerColoredBlockItem(String name, DeferredBlock<?> block) {
        return ITEMS.register(name, registryName -> new BlockItem(block.get(),
                new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)))
        );
    }
    private static DeferredItem<Item> registerBlockItem(String name, DeferredBlock<?> block) {
        return ITEMS.register(name, registryName ->
                new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName)))
        );
    }

    public static final DeferredItem<Item> DYE_MIXER = registerBlockItem("dye_mixer", ModBlocks.DYE_MIXER);

    public static final DeferredItem<Item> PRISM_LOG = registerColoredBlockItem("prism_log", ModBlocks.PRISM_LOG);
    public static final DeferredItem<Item> PRISM_STRIPPED_LOG = registerColoredBlockItem("prism_stripped_log", ModBlocks.PRISM_STRIPPED_LOG);
    public static final DeferredItem<Item> PRISM_PLANKS = registerColoredBlockItem("prism_planks", ModBlocks.PRISM_PLANKS);
    public static final DeferredItem<Item> PRISM_SLAB = registerColoredBlockItem("prism_slab", ModBlocks.PRISM_SLAB);
    public static final DeferredItem<Item> PRISM_STAIRS = registerColoredBlockItem("prism_stairs", ModBlocks.PRISM_STAIRS);
    public static final DeferredItem<Item> PRISM_FENCE = registerColoredBlockItem("prism_fence", ModBlocks.PRISM_FENCE);
    public static final DeferredItem<Item> PRISM_FENCE_GATE = registerColoredBlockItem("prism_fence_gate", ModBlocks.PRISM_FENCE_GATE);
    //public static final DeferredItem<Item> PRISM_DOOR = registerColoredBlockItem("prism_door", ModBlocks.PRISM_DOOR);
    //public static final DeferredItem<Item> PRISM_TRAPDOOR = registerColoredBlockItem("prism_trapdoor", ModBlocks.PRISM_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_BUTTON = registerColoredBlockItem("prism_button", ModBlocks.PRISM_BUTTON);
    public static final DeferredItem<Item> PRISM_PRESSURE_PLATE = registerColoredBlockItem("prism_pressure_plate", ModBlocks.PRISM_PRESSURE_PLATE);
    //public static final DeferredItem<Item> PRISM_STANDING_SIGN = registerColoredBlockItem("prism_standing_sign", ModBlocks.PRISM_STANDING_SIGN);
    //public static final DeferredItem<Item> PRISM_WALL_SIGN = registerColoredBlockItem("prism_wall_sign", ModBlocks.PRISM_WALL_SIGN);
    //public static final DeferredItem<Item> PRISM_CEILING_HANGING_SIGN = registerColoredBlockItem("prism_ceiling_hanging_sign", ModBlocks.PRISM_CEILING_HANGING_SIGN);
    //public static final DeferredItem<Item> PRISM_WALL_HANGING_SIGN = registerColoredBlockItem("prism_wall_hanging_sign", ModBlocks.PRISM_WALL_HANGING_SIGN);
    public static final DeferredItem<Item> PRISM_FLOWER_POT = registerColoredBlockItem("prism_flower_pot", ModBlocks.PRISM_FLOWER_POT);
    public static final DeferredItem<Item> PRISM_DECORATED_POT = registerColoredBlockItem("prism_decorated_pot", ModBlocks.PRISM_DECORATED_POT);
    public static final DeferredItem<Item> PRISM_SAND = registerColoredBlockItem("prism_sand", ModBlocks.PRISM_SAND);
    public static final DeferredItem<Item> PRISM_CANDLE = registerColoredBlockItem("prism_candle", ModBlocks.PRISM_CANDLE);
    public static final DeferredItem<Item> PRISM_CARPET = registerColoredBlockItem("prism_carpet", ModBlocks.PRISM_CARPET);
    public static final DeferredItem<Item> PRISM_LADDER = registerColoredBlockItem("prism_ladder", ModBlocks.PRISM_LADDER);
    public static final DeferredItem<Item> PRISM_BARREL = registerColoredBlockItem("prism_barrel", ModBlocks.PRISM_BARREL);
    public static final DeferredItem<Item> PRISM_SLIME_BLOCK = registerColoredBlockItem("prism_slime_block", ModBlocks.PRISM_SLIME_BLOCK);
    public static final DeferredItem<Item> PRISM_WOOL = registerColoredBlockItem("prism_wool", ModBlocks.PRISM_WOOL);
    public static final DeferredItem<Item> PRISM_CONCRETE = registerColoredBlockItem("prism_concrete", ModBlocks.PRISM_CONCRETE);
    public static final DeferredItem<Item> PRISM_CONCRETE_POWDER = registerColoredBlockItem("prism_concrete_powder", ModBlocks.PRISM_CONCRETE_POWDER);
    public static final DeferredItem<Item> PRISM_CONCRETE_SLAB = registerColoredBlockItem("prism_concrete_slab", ModBlocks.PRISM_CONCRETE_SLAB);
    public static final DeferredItem<Item> PRISM_CONCRETE_WALL = registerColoredBlockItem("prism_concrete_wall", ModBlocks.PRISM_CONCRETE_WALL);
    public static final DeferredItem<Item> PRISM_CONCRETE_STAIRS = registerColoredBlockItem("prism_concrete_stairs", ModBlocks.PRISM_CONCRETE_STAIRS);
    public static final DeferredItem<Item> PRISM_STONE = registerColoredBlockItem("prism_stone", ModBlocks.PRISM_STONE);
    public static final DeferredItem<Item> PRISM_STONE_SLAB = registerColoredBlockItem("prism_stone_slab", ModBlocks.PRISM_STONE_SLAB);
    public static final DeferredItem<Item> PRISM_STONE_STAIRS = registerColoredBlockItem("prism_stone_stairs", ModBlocks.PRISM_STONE_STAIRS);
    public static final DeferredItem<Item> PRISM_STONE_WALL = registerColoredBlockItem("prism_stone_wall", ModBlocks.PRISM_STONE_WALL);
    public static final DeferredItem<Item> PRISM_ANDESITE = registerColoredBlockItem("prism_andesite", ModBlocks.PRISM_ANDESITE);
    public static final DeferredItem<Item> PRISM_ANDESITE_SLAB = registerColoredBlockItem("prism_andesite_slab", ModBlocks.PRISM_ANDESITE_SLAB);
    public static final DeferredItem<Item> PRISM_ANDESITE_STAIRS = registerColoredBlockItem("prism_andesite_stairs", ModBlocks.PRISM_ANDESITE_STAIRS);
    public static final DeferredItem<Item> PRISM_ANDESITE_WALL = registerColoredBlockItem("prism_andesite_wall", ModBlocks.PRISM_ANDESITE_WALL);
    public static final DeferredItem<Item> PRISM_TERRACOTTA = registerColoredBlockItem("prism_terracotta", ModBlocks.PRISM_TERRACOTTA);
    public static final DeferredItem<Item> PRISM_TERRACOTTA_SLAB = registerColoredBlockItem("prism_terracotta_slab", ModBlocks.PRISM_TERRACOTTA_SLAB);
    public static final DeferredItem<Item> PRISM_TERRACOTTA_STAIRS = registerColoredBlockItem("prism_terracotta_stairs", ModBlocks.PRISM_TERRACOTTA_STAIRS);
    public static final DeferredItem<Item> PRISM_TERRACOTTA_WALL = registerColoredBlockItem("prism_terracotta_wall", ModBlocks.PRISM_TERRACOTTA_WALL);
    public static final DeferredItem<Item> PRISM_COPPER_BLOCK = registerColoredBlockItem("prism_copper_block", ModBlocks.PRISM_COPPER_BLOCK);
    public static final DeferredItem<Item> PRISM_CHISELED_COPPER_BLOCK = registerColoredBlockItem("prism_chiseled_copper_block", ModBlocks.PRISM_CHISELED_COPPER_BLOCK);
    public static final DeferredItem<Item> PRISM_CUT_COPPER_BLOCK = registerColoredBlockItem("prism_cut_copper_block", ModBlocks.PRISM_CUT_COPPER_BLOCK);
    public static final DeferredItem<Item> PRISM_COPPER_GRATE = registerColoredBlockItem("prism_copper_grate", ModBlocks.PRISM_COPPER_GRATE);
    public static final DeferredItem<Item> PRISM_COPPER_SLAB = registerColoredBlockItem("prism_copper_slab", ModBlocks.PRISM_COPPER_SLAB);
    public static final DeferredItem<Item> PRISM_COPPER_STAIRS = registerColoredBlockItem("prism_copper_stairs", ModBlocks.PRISM_COPPER_STAIRS);
    public static final DeferredItem<Item> PRISM_OAK_DOOR = registerColoredBlockItem("prism_oak_door", ModBlocks.PRISM_OAK_DOOR);
    public static final DeferredItem<Item> PRISM_OAK_TRAPDOOR = registerColoredBlockItem("prism_oak_trapdoor", ModBlocks.PRISM_OAK_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_SPRUCE_DOOR = registerColoredBlockItem("prism_spruce_door", ModBlocks.PRISM_SPRUCE_DOOR);
    public static final DeferredItem<Item> PRISM_SPRUCE_TRAPDOOR = registerColoredBlockItem("prism_spruce_trapdoor", ModBlocks.PRISM_SPRUCE_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_BIRCH_DOOR = registerColoredBlockItem("prism_birch_door", ModBlocks.PRISM_BIRCH_DOOR);
    public static final DeferredItem<Item> PRISM_BIRCH_TRAPDOOR = registerColoredBlockItem("prism_birch_trapdoor", ModBlocks.PRISM_BIRCH_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_JUNGLE_DOOR = registerColoredBlockItem("prism_jungle_door", ModBlocks.PRISM_JUNGLE_DOOR);
    public static final DeferredItem<Item> PRISM_JUNGLE_TRAPDOOR = registerColoredBlockItem("prism_jungle_trapdoor", ModBlocks.PRISM_JUNGLE_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_ACACIA_DOOR = registerColoredBlockItem("prism_acacia_door", ModBlocks.PRISM_ACACIA_DOOR);
    public static final DeferredItem<Item> PRISM_ACACIA_TRAPDOOR = registerColoredBlockItem("prism_acacia_trapdoor", ModBlocks.PRISM_ACACIA_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_DARK_OAK_DOOR = registerColoredBlockItem("prism_dark_oak_door", ModBlocks.PRISM_DARK_OAK_DOOR);
    public static final DeferredItem<Item> PRISM_DARK_OAK_TRAPDOOR = registerColoredBlockItem("prism_dark_oak_trapdoor", ModBlocks.PRISM_DARK_OAK_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_MANGROVE_DOOR = registerColoredBlockItem("prism_mangrove_door", ModBlocks.PRISM_MANGROVE_DOOR);
    public static final DeferredItem<Item> PRISM_MANGROVE_TRAPDOOR = registerColoredBlockItem("prism_mangrove_trapdoor", ModBlocks.PRISM_MANGROVE_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_CHERRY_DOOR = registerColoredBlockItem("prism_cherry_door", ModBlocks.PRISM_CHERRY_DOOR);
    public static final DeferredItem<Item> PRISM_CHERRY_TRAPDOOR = registerColoredBlockItem("prism_cherry_trapdoor", ModBlocks.PRISM_CHERRY_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_PALE_OAK_DOOR = registerColoredBlockItem("prism_pale_oak_door", ModBlocks.PRISM_PALE_OAK_DOOR);
    public static final DeferredItem<Item> PRISM_PALE_OAK_TRAPDOOR = registerColoredBlockItem("prism_pale_oak_trapdoor", ModBlocks.PRISM_PALE_OAK_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_BAMBOO_DOOR = registerColoredBlockItem("prism_bamboo_door", ModBlocks.PRISM_BAMBOO_DOOR);
    public static final DeferredItem<Item> PRISM_BAMBOO_TRAPDOOR = registerColoredBlockItem("prism_bamboo_trapdoor", ModBlocks.PRISM_BAMBOO_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_CRIMSON_DOOR = registerColoredBlockItem("prism_crimson_door", ModBlocks.PRISM_CRIMSON_DOOR);
    public static final DeferredItem<Item> PRISM_CRIMSON_TRAPDOOR = registerColoredBlockItem("prism_crimson_trapdoor", ModBlocks.PRISM_CRIMSON_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_WARPED_DOOR = registerColoredBlockItem("prism_warped_door", ModBlocks.PRISM_WARPED_DOOR);
    public static final DeferredItem<Item> PRISM_WARPED_TRAPDOOR = registerColoredBlockItem("prism_warped_trapdoor", ModBlocks.PRISM_WARPED_TRAPDOOR);
    public static final DeferredItem<Item> PRISM_COPPER_DOOR = registerColoredBlockItem("prism_copper_door", ModBlocks.PRISM_COPPER_DOOR);
    public static final DeferredItem<Item> PRISM_COPPPER_TRAPDOOR = registerColoredBlockItem("prism_copper_trapdoor", ModBlocks.PRISM_COPPER_TRAPDOOR);
    public static final DeferredItem<PrismSignItem> PRISM_SIGN_ITEM =
            ITEMS.register("prism_sign", registryName -> new PrismSignItem(
                    new Item.Properties()
                            .stacksTo(16)
                            .setId(ResourceKey.create(Registries.ITEM, registryName)),
                    ModBlocks.PRISM_STANDING_SIGN.get(),
                    ModBlocks.PRISM_WALL_SIGN.get()
            ));

    public static final DeferredItem<PrismHangingSignItem> PRISM_HANGING_SIGN_ITEM =
            ITEMS.register("prism_hanging_sign", registryName -> new PrismHangingSignItem(
                    ModBlocks.PRISM_CEILING_HANGING_SIGN.get(),
                    ModBlocks.PRISM_WALL_HANGING_SIGN.get(),
                    new Item.Properties()
                            .stacksTo(16)
                            .setId(ResourceKey.create(Registries.ITEM, registryName))
            ));


    public static final DeferredItem<Item> PRISMCRAFT_ICON = ITEMS.registerSimpleItem(
            "prismcraft_icon",
            new Item.Properties()
    );
}