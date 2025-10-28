package net.starmarine06.prismcraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.core.component.DataComponents;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;

import java.util.List;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrismCraftMod.MOD_ID);

    /** Registers a colored block item with dye info tooltip. */
    private static void registerColoredBlockItem(String name, DeferredBlock<?> block) {
        ITEMS.register(name, registryName -> new BlockItem(block.get(),
                new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName))) {
            @Override
            public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
                var data = stack.get(DataComponents.CUSTOM_DATA);
                if (Screen.hasShiftDown()) {
                    if (data != null) {
                        CompoundTag dyeData = data.copyTag();
                        if (dyeData.contains("prismcraft:dye_ingredients", Tag.TAG_LIST)) {
                            ListTag dyeList = dyeData.getList("prismcraft:dye_ingredients", Tag.TAG_COMPOUND);
                            if (!dyeList.isEmpty()) {
                                tooltip.add(Component.literal("Used Dyes:").withStyle(ChatFormatting.GRAY));
                                for (int i = 0; i < dyeList.size(); i++) {
                                    CompoundTag dyeTag = dyeList.getCompound(i);
                                    String idString = dyeTag.getString("id");
                                    int count = dyeTag.getInt("count");
                                    ResourceLocation dyeLoc = ResourceLocation.tryParse(idString);
                                    Item dyeItem = dyeLoc != null ? BuiltInRegistries.ITEM.getValue(dyeLoc) : null;
                                    String displayName = dyeItem != null ? Component.translatable(dyeItem.getDescriptionId()).getString() : idString;
                                    tooltip.add(Component.literal("• " + displayName + " x" + count).withStyle(ChatFormatting.AQUA));
                                }
                            }
                        }
                    }
                } else {
                    tooltip.add(Component.literal("Hold SHIFT to view dyes").withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        });
    }

    /** Registers a regular block item. */
    private static void registerBlockItem(String name, DeferredBlock<?> block) {
        ITEMS.register(name, registryName -> new BlockItem(block.get(),
                new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName))));
    }

    static {

        // Dye Mixer
        registerBlockItem("dye_mixer", ModBlocks.DYE_MIXER);
        // Prism
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
        registerColoredBlockItem("prism_pressure_plate",ModBlocks.PRISM_STANDING_SIGN);
        registerColoredBlockItem("prism_wall_sign", ModBlocks.PRISM_WALL_SIGN);
        registerColoredBlockItem("prism_ceiling_hanging_sign", ModBlocks.PRISM_CEILING_HANGING_SIGN);
        registerColoredBlockItem("prism_wall_hanging_sign", ModBlocks.PRISM_WALL_HANGING_SIGN);
        registerColoredBlockItem("prism_flower_pot", ModBlocks.PRISM_FLOWER_POT);
        registerColoredBlockItem("prism_decorated_pot", ModBlocks.PRISM_DECORATED_POT);
        //Other Items
        registerColoredBlockItem("prism_sand", ModBlocks.PRISM_SAND);
        registerColoredBlockItem("prism_candle", ModBlocks.PRISM_CANDLE);
        registerColoredBlockItem("prism_carpet", ModBlocks.PRISM_CARPET);
        registerColoredBlockItem("prism_ladder", ModBlocks.PRISM_LADDER);
        registerColoredBlockItem("prism_barrel", ModBlocks.PRISM_BARREL);
        registerColoredBlockItem("prism_slime_block", ModBlocks.PRISM_SLIME_BLOCK);
        registerColoredBlockItem("prism_wool", ModBlocks.PRISM_WOOL);
        //Concrete
        registerColoredBlockItem("prism_concrete", ModBlocks.PRISM_CONCRETE);
        registerColoredBlockItem("prism_concrete_powder", ModBlocks.PRISM_CONCRETE_POWDER);
        registerColoredBlockItem("prism_concrete_slab", ModBlocks.PRISM_CONCRETE_SLAB);
        registerColoredBlockItem("prism_concrete_wall", ModBlocks.PRISM_CONCRETE_WALL);
        registerColoredBlockItem("prism_concrete_stairs", ModBlocks.PRISM_CONCRETE_STAIRS);
        //Stone
        registerColoredBlockItem("prism_stone", ModBlocks.PRISM_STONE);
        registerColoredBlockItem("prism_stone_slab", ModBlocks.PRISM_STONE_SLAB);
        registerColoredBlockItem("prism_stone_stairs", ModBlocks.PRISM_STONE_STAIRS);
        registerColoredBlockItem("prism_stone_wall", ModBlocks.PRISM_STONE_WALL);
        //Andesite
        registerColoredBlockItem("prism_andesite", ModBlocks.PRISM_ANDESITE);
        registerColoredBlockItem("prism_andesite_slab", ModBlocks.PRISM_ANDESITE_SLAB);
        registerColoredBlockItem("prism_andesite_stairs", ModBlocks.PRISM_ANDESITE_STAIRS);
        registerColoredBlockItem("prism_andesite_wall", ModBlocks.PRISM_ANDESITE_WALL);
        //Terracotta
        registerColoredBlockItem("prism_terracotta", ModBlocks.PRISM_TERRACOTTA);
        registerColoredBlockItem("prism_terracotta_slab", ModBlocks.PRISM_TERRACOTTA_SLAB);
        registerColoredBlockItem("prism_terracotta_stairs", ModBlocks.PRISM_TERRACOTTA_STAIRS);
        registerColoredBlockItem("prism_terracotta_wall", ModBlocks.PRISM_TERRACOTTA_WALL);

        // Oak
        /*registerColoredBlockItem("prism_oak_log", ModBlocks.PRISM_OAK_LOG);
        registerColoredBlockItem("prism_oak_planks", ModBlocks.PRISM_OAK_PLANKS);
        registerColoredBlockItem("prism_oak_slab", ModBlocks.PRISM_OAK_SLAB);
        registerColoredBlockItem("prism_oak_stairs", ModBlocks.PRISM_OAK_STAIRS);
        registerColoredBlockItem("prism_oak_fence", ModBlocks.PRISM_OAK_FENCE);
        registerColoredBlockItem("prism_oak_fence_gate", ModBlocks.PRISM_OAK_FENCE_GATE);*/
        registerBlockItem("prism_oak_door", ModBlocks.PRISM_OAK_DOOR);
        registerBlockItem("prism_oak_trapdoor", ModBlocks.PRISM_OAK_TRAPDOOR);
        /*registerBlockItem("prism_oak_button", ModBlocks.PRISM_OAK_BUTTON);
        registerBlockItem("prism_oak_pressure_plate", ModBlocks.PRISM_OAK_PRESSURE_PLATE);*/

        // Spruce
        /*registerColoredBlockItem("prism_spruce_log", ModBlocks.PRISM_SPRUCE_LOG);
        registerColoredBlockItem("prism_spruce_planks", ModBlocks.PRISM_SPRUCE_PLANKS);
        registerColoredBlockItem("prism_spruce_slab", ModBlocks.PRISM_SPRUCE_SLAB);
        registerColoredBlockItem("prism_spruce_stairs", ModBlocks.PRISM_SPRUCE_STAIRS);
        registerColoredBlockItem("prism_spruce_fence", ModBlocks.PRISM_SPRUCE_FENCE);
        registerColoredBlockItem("prism_spruce_fence_gate", ModBlocks.PRISM_SPRUCE_FENCE_GATE);*/
        registerBlockItem("prism_spruce_door", ModBlocks.PRISM_SPRUCE_DOOR);
        registerBlockItem("prism_spruce_trapdoor", ModBlocks.PRISM_SPRUCE_TRAPDOOR);
        /*registerBlockItem("prism_spruce_button", ModBlocks.PRISM_SPRUCE_BUTTON);
        registerBlockItem("prism_spruce_pressure_plate", ModBlocks.PRISM_SPRUCE_PRESSURE_PLATE);*/

        // Birch
        /*registerColoredBlockItem("prism_birch_log", ModBlocks.PRISM_BIRCH_LOG);
        registerColoredBlockItem("prism_birch_planks", ModBlocks.PRISM_BIRCH_PLANKS);
        registerColoredBlockItem("prism_birch_slab", ModBlocks.PRISM_BIRCH_SLAB);
        registerColoredBlockItem("prism_birch_stairs", ModBlocks.PRISM_BIRCH_STAIRS);
        registerColoredBlockItem("prism_birch_fence", ModBlocks.PRISM_BIRCH_FENCE);
        registerColoredBlockItem("prism_birch_fence_gate", ModBlocks.PRISM_BIRCH_FENCE_GATE);*/
        registerBlockItem("prism_birch_door", ModBlocks.PRISM_BIRCH_DOOR);
        registerBlockItem("prism_birch_trapdoor", ModBlocks.PRISM_BIRCH_TRAPDOOR);
        /*registerBlockItem("prism_birch_button", ModBlocks.PRISM_BIRCH_BUTTON);
        registerBlockItem("prism_birch_pressure_plate", ModBlocks.PRISM_BIRCH_PRESSURE_PLATE);*/

        // Jungle
        /*registerColoredBlockItem("prism_jungle_log", ModBlocks.PRISM_JUNGLE_LOG);
        registerColoredBlockItem("prism_jungle_planks", ModBlocks.PRISM_JUNGLE_PLANKS);
        registerColoredBlockItem("prism_jungle_slab", ModBlocks.PRISM_JUNGLE_SLAB);
        registerColoredBlockItem("prism_jungle_stairs", ModBlocks.PRISM_JUNGLE_STAIRS);
        registerColoredBlockItem("prism_jungle_fence", ModBlocks.PRISM_JUNGLE_FENCE);
        registerColoredBlockItem("prism_jungle_fence_gate", ModBlocks.PRISM_JUNGLE_FENCE_GATE);*/
        registerBlockItem("prism_jungle_door", ModBlocks.PRISM_JUNGLE_DOOR);
        registerBlockItem("prism_jungle_trapdoor", ModBlocks.PRISM_JUNGLE_TRAPDOOR);
        /*registerBlockItem("prism_jungle_button", ModBlocks.PRISM_JUNGLE_BUTTON);
        registerBlockItem("prism_jungle_pressure_plate", ModBlocks.PRISM_JUNGLE_PRESSURE_PLATE);*/

        // Acacia
        /*registerColoredBlockItem("prism_acacia_log", ModBlocks.PRISM_ACACIA_LOG);
        registerColoredBlockItem("prism_acacia_planks", ModBlocks.PRISM_ACACIA_PLANKS);
        registerColoredBlockItem("prism_acacia_slab", ModBlocks.PRISM_ACACIA_SLAB);
        registerColoredBlockItem("prism_acacia_stairs", ModBlocks.PRISM_ACACIA_STAIRS);
        registerColoredBlockItem("prism_acacia_fence", ModBlocks.PRISM_ACACIA_FENCE);
        registerColoredBlockItem("prism_acacia_fence_gate", ModBlocks.PRISM_ACACIA_FENCE_GATE);*/
        registerBlockItem("prism_acacia_door", ModBlocks.PRISM_ACACIA_DOOR);
        registerBlockItem("prism_acacia_trapdoor", ModBlocks.PRISM_ACACIA_TRAPDOOR);
        /*registerBlockItem("prism_acacia_button", ModBlocks.PRISM_ACACIA_BUTTON);
        registerBlockItem("prism_acacia_pressure_plate", ModBlocks.PRISM_ACACIA_PRESSURE_PLATE);*/

        // Dark Oak
        /*registerColoredBlockItem("prism_dark_oak_log", ModBlocks.PRISM_DARK_OAK_LOG);
        registerColoredBlockItem("prism_dark_oak_planks", ModBlocks.PRISM_DARK_OAK_PLANKS);
        registerColoredBlockItem("prism_dark_oak_slab", ModBlocks.PRISM_DARK_OAK_SLAB);
        registerColoredBlockItem("prism_dark_oak_stairs", ModBlocks.PRISM_DARK_OAK_STAIRS);
        registerColoredBlockItem("prism_dark_oak_fence", ModBlocks.PRISM_DARK_OAK_FENCE);
        registerColoredBlockItem("prism_dark_oak_fence_gate", ModBlocks.PRISM_DARK_OAK_FENCE_GATE);*/
        registerBlockItem("prism_dark_oak_door", ModBlocks.PRISM_DARK_OAK_DOOR);
        registerBlockItem("prism_dark_oak_trapdoor", ModBlocks.PRISM_DARK_OAK_TRAPDOOR);
        /*registerBlockItem("prism_dark_oak_button", ModBlocks.PRISM_DARK_OAK_BUTTON);
        registerBlockItem("prism_dark_oak_pressure_plate", ModBlocks.PRISM_DARK_OAK_PRESSURE_PLATE);*/

        // Mangrove
        /*registerColoredBlockItem("prism_mangrove_log", ModBlocks.PRISM_MANGROVE_LOG);
        registerColoredBlockItem("prism_mangrove_planks", ModBlocks.PRISM_MANGROVE_PLANKS);
        registerColoredBlockItem("prism_mangrove_slab", ModBlocks.PRISM_MANGROVE_SLAB);
        registerColoredBlockItem("prism_mangrove_stairs", ModBlocks.PRISM_MANGROVE_STAIRS);
        registerColoredBlockItem("prism_mangrove_fence", ModBlocks.PRISM_MANGROVE_FENCE);
        registerColoredBlockItem("prism_mangrove_fence_gate", ModBlocks.PRISM_MANGROVE_FENCE_GATE);*/
        registerBlockItem("prism_mangrove_door", ModBlocks.PRISM_MANGROVE_DOOR);
        registerBlockItem("prism_mangrove_trapdoor", ModBlocks.PRISM_MANGROVE_TRAPDOOR);
        /*registerBlockItem("prism_mangrove_button", ModBlocks.PRISM_MANGROVE_BUTTON);
        registerBlockItem("prism_mangrove_pressure_plate", ModBlocks.PRISM_MANGROVE_PRESSURE_PLATE);*/

        // Cherry
        /*registerColoredBlockItem("prism_cherry_log", ModBlocks.PRISM_CHERRY_LOG);
        registerColoredBlockItem("prism_cherry_planks", ModBlocks.PRISM_CHERRY_PLANKS);
        registerColoredBlockItem("prism_cherry_slab", ModBlocks.PRISM_CHERRY_SLAB);
        registerColoredBlockItem("prism_cherry_stairs", ModBlocks.PRISM_CHERRY_STAIRS);
        registerColoredBlockItem("prism_cherry_fence", ModBlocks.PRISM_CHERRY_FENCE);
        registerColoredBlockItem("prism_cherry_fence_gate", ModBlocks.PRISM_CHERRY_FENCE_GATE);*/
        registerBlockItem("prism_cherry_door", ModBlocks.PRISM_CHERRY_DOOR);
        registerBlockItem("prism_cherry_trapdoor", ModBlocks.PRISM_CHERRY_TRAPDOOR);
        /*registerBlockItem("prism_cherry_button", ModBlocks.PRISM_CHERRY_BUTTON);
        registerBlockItem("prism_cherry_pressure_plate", ModBlocks.PRISM_CHERRY_PRESSURE_PLATE);*/

        // Pale Oak
        /*registerColoredBlockItem("prism_pale_oak_log", ModBlocks.PRISM_PALE_OAK_LOG);
        registerColoredBlockItem("prism_pale_oak_planks", ModBlocks.PRISM_PALE_OAK_PLANKS);
        registerColoredBlockItem("prism_pale_oak_slab", ModBlocks.PRISM_PALE_OAK_SLAB);
        registerColoredBlockItem("prism_pale_oak_stairs", ModBlocks.PRISM_PALE_OAK_STAIRS);
        registerColoredBlockItem("prism_pale_oak_fence", ModBlocks.PRISM_PALE_OAK_FENCE);
        registerColoredBlockItem("prism_pale_oak_fence_gate", ModBlocks.PRISM_PALE_OAK_FENCE_GATE);*/
        registerBlockItem("prism_pale_oak_door", ModBlocks.PRISM_PALE_OAK_DOOR);
        registerBlockItem("prism_pale_oak_trapdoor", ModBlocks.PRISM_PALE_OAK_TRAPDOOR);
        /*registerBlockItem("prism_pale_oak_button", ModBlocks.PRISM_PALE_OAK_BUTTON);
        registerBlockItem("prism_pale_oak_pressure_plate", ModBlocks.PRISM_PALE_OAK_PRESSURE_PLATE);*/

        // Bamboo
        /*registerColoredBlockItem("prism_bamboo_log", ModBlocks.PRISM_BAMBOO_LOG);
        registerColoredBlockItem("prism_bamboo_planks", ModBlocks.PRISM_BAMBOO_PLANKS);
        registerColoredBlockItem("prism_bamboo_slab", ModBlocks.PRISM_BAMBOO_SLAB);
        registerColoredBlockItem("prism_bamboo_stairs", ModBlocks.PRISM_BAMBOO_STAIRS);
        registerColoredBlockItem("prism_bamboo_fence", ModBlocks.PRISM_BAMBOO_FENCE);
        registerColoredBlockItem("prism_bamboo_fence_gate", ModBlocks.PRISM_BAMBOO_FENCE_GATE);*/
        registerBlockItem("prism_bamboo_door", ModBlocks.PRISM_BAMBOO_DOOR);
        registerBlockItem("prism_bamboo_trapdoor", ModBlocks.PRISM_BAMBOO_TRAPDOOR);
        /*registerBlockItem("prism_bamboo_button", ModBlocks.PRISM_BAMBOO_BUTTON);
        registerBlockItem("prism_bamboo_pressure_plate", ModBlocks.PRISM_BAMBOO_PRESSURE_PLATE);*/

        // Crimson
        /*registerColoredBlockItem("prism_crimson_log", ModBlocks.PRISM_CRIMSON_LOG);
        registerColoredBlockItem("prism_crimson_planks", ModBlocks.PRISM_CRIMSON_PLANKS);
        registerColoredBlockItem("prism_crimson_slab", ModBlocks.PRISM_CRIMSON_SLAB);
        registerColoredBlockItem("prism_crimson_stairs", ModBlocks.PRISM_CRIMSON_STAIRS);
        registerColoredBlockItem("prism_crimson_fence", ModBlocks.PRISM_CRIMSON_FENCE);
        registerColoredBlockItem("prism_crimson_fence_gate", ModBlocks.PRISM_CRIMSON_FENCE_GATE);*/
        registerBlockItem("prism_crimson_door", ModBlocks.PRISM_CRIMSON_DOOR);
        registerBlockItem("prism_crimson_trapdoor", ModBlocks.PRISM_CRIMSON_TRAPDOOR);
        /*registerBlockItem("prism_crimson_button", ModBlocks.PRISM_CRIMSON_BUTTON);
        registerBlockItem("prism_crimson_pressure_plate", ModBlocks.PRISM_CRIMSON_PRESSURE_PLATE);*/

        // Warped
        /*registerColoredBlockItem("prism_warped_log", ModBlocks.PRISM_WARPED_LOG);
        registerColoredBlockItem("prism_warped_planks", ModBlocks.PRISM_WARPED_PLANKS);
        registerColoredBlockItem("prism_warped_slab", ModBlocks.PRISM_WARPED_SLAB);
        registerColoredBlockItem("prism_warped_stairs", ModBlocks.PRISM_WARPED_STAIRS);
        registerColoredBlockItem("prism_warped_fence", ModBlocks.PRISM_WARPED_FENCE);
        registerColoredBlockItem("prism_warped_fence_gate", ModBlocks.PRISM_WARPED_FENCE_GATE);*/
        registerBlockItem("prism_warped_door", ModBlocks.PRISM_WARPED_DOOR);
        registerBlockItem("prism_warped_trapdoor", ModBlocks.PRISM_WARPED_TRAPDOOR);
        /*registerBlockItem("prism_warped_button", ModBlocks.PRISM_WARPED_BUTTON);
        registerBlockItem("prism_warped_pressure_plate", ModBlocks.PRISM_WARPED_PRESSURE_PLATE);*/
    }

    public static final DeferredItem<Item> PRISMCRAFT_ICON = ITEMS.registerSimpleItem(
            "prismcraft_icon",
            new Item.Properties()
    );
}
