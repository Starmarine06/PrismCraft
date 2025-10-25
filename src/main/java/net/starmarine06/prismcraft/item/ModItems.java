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
        // Pale Oak
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

        // Oak
        /*registerColoredBlockItem("prism_oak_log", ModBlocks.PRISM_OAK_LOG);
        registerColoredBlockItem("prism_oak_planks", ModBlocks.PRISM_OAK_PLANKS);
        registerColoredBlockItem("prism_oak_slab", ModBlocks.PRISM_OAK_SLAB);
        registerColoredBlockItem("prism_oak_stairs", ModBlocks.PRISM_OAK_STAIRS);
        registerColoredBlockItem("prism_oak_fence", ModBlocks.PRISM_OAK_FENCE);
        registerColoredBlockItem("prism_oak_fence_gate", ModBlocks.PRISM_OAK_FENCE_GATE);*/
        registerColoredBlockItem("prism_oak_door", ModBlocks.PRISM_OAK_DOOR);
        registerColoredBlockItem("prism_oak_trapdoor", ModBlocks.PRISM_OAK_TRAPDOOR);
        /*registerColoredBlockItem("prism_oak_button", ModBlocks.PRISM_OAK_BUTTON);
        registerColoredBlockItem("prism_oak_pressure_plate", ModBlocks.PRISM_OAK_PRESSURE_PLATE);

        // Birch
        registerColoredBlockItem("prism_birch_log", ModBlocks.PRISM_BIRCH_LOG);
        registerColoredBlockItem("prism_birch_planks", ModBlocks.PRISM_BIRCH_PLANKS);
        registerColoredBlockItem("prism_birch_slab", ModBlocks.PRISM_BIRCH_SLAB);
        registerColoredBlockItem("prism_birch_stairs", ModBlocks.PRISM_BIRCH_STAIRS);
        registerColoredBlockItem("prism_birch_fence", ModBlocks.PRISM_BIRCH_FENCE);
        registerColoredBlockItem("prism_birch_fence_gate", ModBlocks.PRISM_BIRCH_FENCE_GATE);*/
        registerColoredBlockItem("prism_birch_door", ModBlocks.PRISM_BIRCH_DOOR);
        registerColoredBlockItem("prism_birch_trapdoor", ModBlocks.PRISM_BIRCH_TRAPDOOR);/*
        registerColoredBlockItem("prism_birch_button", ModBlocks.PRISM_BIRCH_BUTTON);
        registerColoredBlockItem("prism_birch_pressure_plate", ModBlocks.PRISM_BIRCH_PRESSURE_PLATE);

        // Acacia
        registerColoredBlockItem("prism_acacia_log", ModBlocks.PRISM_ACACIA_LOG);
        registerColoredBlockItem("prism_acacia_planks", ModBlocks.PRISM_ACACIA_PLANKS);
        registerColoredBlockItem("prism_acacia_slab", ModBlocks.PRISM_ACACIA_SLAB);
        registerColoredBlockItem("prism_acacia_stairs", ModBlocks.PRISM_ACACIA_STAIRS);
        registerColoredBlockItem("prism_acacia_fence", ModBlocks.PRISM_ACACIA_FENCE);
        registerColoredBlockItem("prism_acacia_fence_gate", ModBlocks.PRISM_ACACIA_FENCE_GATE);*/
        registerColoredBlockItem("prism_acacia_door", ModBlocks.PRISM_ACACIA_DOOR);
        registerColoredBlockItem("prism_acacia_trapdoor", ModBlocks.PRISM_ACACIA_TRAPDOOR);/*
        registerColoredBlockItem("prism_acacia_button", ModBlocks.PRISM_ACACIA_BUTTON);
        registerColoredBlockItem("prism_acacia_pressure_plate", ModBlocks.PRISM_ACACIA_PRESSURE_PLATE);

        // Dark Oak
        registerColoredBlockItem("prism_dark_oak_log", ModBlocks.PRISM_DARK_OAK_LOG);
        registerColoredBlockItem("prism_dark_oak_planks", ModBlocks.PRISM_DARK_OAK_PLANKS);
        registerColoredBlockItem("prism_dark_oak_slab", ModBlocks.PRISM_DARK_OAK_SLAB);
        registerColoredBlockItem("prism_dark_oak_stairs", ModBlocks.PRISM_DARK_OAK_STAIRS);
        registerColoredBlockItem("prism_dark_oak_fence", ModBlocks.PRISM_DARK_OAK_FENCE);
        registerColoredBlockItem("prism_dark_oak_fence_gate", ModBlocks.PRISM_DARK_OAK_FENCE_GATE);*/
        registerColoredBlockItem("prism_dark_oak_door", ModBlocks.PRISM_DARK_OAK_DOOR);
        registerColoredBlockItem("prism_dark_oak_trapdoor", ModBlocks.PRISM_DARK_OAK_TRAPDOOR);/*
        registerColoredBlockItem("prism_dark_oak_button", ModBlocks.PRISM_DARK_OAK_BUTTON);
        registerColoredBlockItem("prism_dark_oak_pressure_plate", ModBlocks.PRISM_DARK_OAK_PRESSURE_PLATE);

        // Jungle
        registerColoredBlockItem("prism_jungle_log", ModBlocks.PRISM_JUNGLE_LOG);
        registerColoredBlockItem("prism_jungle_planks", ModBlocks.PRISM_JUNGLE_PLANKS);
        registerColoredBlockItem("prism_jungle_slab", ModBlocks.PRISM_JUNGLE_SLAB);
        registerColoredBlockItem("prism_jungle_stairs", ModBlocks.PRISM_JUNGLE_STAIRS);
        registerColoredBlockItem("prism_jungle_fence", ModBlocks.PRISM_JUNGLE_FENCE);
        registerColoredBlockItem("prism_jungle_fence_gate", ModBlocks.PRISM_JUNGLE_FENCE_GATE);*/
        registerColoredBlockItem("prism_jungle_door", ModBlocks.PRISM_JUNGLE_DOOR);
        registerColoredBlockItem("prism_jungle_trapdoor", ModBlocks.PRISM_JUNGLE_TRAPDOOR);/*
        registerColoredBlockItem("prism_jungle_button", ModBlocks.PRISM_JUNGLE_BUTTON);
        registerColoredBlockItem("prism_jungle_pressure_plate", ModBlocks.PRISM_JUNGLE_PRESSURE_PLATE);

        // Mangrove
        registerColoredBlockItem("prism_mangrove_log", ModBlocks.PRISM_MANGROVE_LOG);
        registerColoredBlockItem("prism_mangrove_planks", ModBlocks.PRISM_MANGROVE_PLANKS);
        registerColoredBlockItem("prism_mangrove_slab", ModBlocks.PRISM_MANGROVE_SLAB);
        registerColoredBlockItem("prism_mangrove_stairs", ModBlocks.PRISM_MANGROVE_STAIRS);
        registerColoredBlockItem("prism_mangrove_fence", ModBlocks.PRISM_MANGROVE_FENCE);
        registerColoredBlockItem("prism_mangrove_fence_gate", ModBlocks.PRISM_MANGROVE_FENCE_GATE);*/
        registerColoredBlockItem("prism_mangrove_door", ModBlocks.PRISM_MANGROVE_DOOR);
        registerColoredBlockItem("prism_mangrove_trapdoor", ModBlocks.PRISM_MANGROVE_TRAPDOOR);/*
        registerColoredBlockItem("prism_mangrove_button", ModBlocks.PRISM_MANGROVE_BUTTON);
        registerColoredBlockItem("prism_mangrove_pressure_plate", ModBlocks.PRISM_MANGROVE_PRESSURE_PLATE);

        // Cherry
        registerColoredBlockItem("prism_cherry_log", ModBlocks.PRISM_CHERRY_LOG);
        registerColoredBlockItem("prism_cherry_planks", ModBlocks.PRISM_CHERRY_PLANKS);
        registerColoredBlockItem("prism_cherry_slab", ModBlocks.PRISM_CHERRY_SLAB);
        registerColoredBlockItem("prism_cherry_stairs", ModBlocks.PRISM_CHERRY_STAIRS);
        registerColoredBlockItem("prism_cherry_fence", ModBlocks.PRISM_CHERRY_FENCE);
        registerColoredBlockItem("prism_cherry_fence_gate", ModBlocks.PRISM_CHERRY_FENCE_GATE);*/
        registerColoredBlockItem("prism_cherry_door", ModBlocks.PRISM_CHERRY_DOOR);
        registerColoredBlockItem("prism_cherry_trapdoor", ModBlocks.PRISM_CHERRY_TRAPDOOR);/*
        registerColoredBlockItem("prism_cherry_button", ModBlocks.PRISM_CHERRY_BUTTON);
        registerColoredBlockItem("prism_cherry_pressure_plate", ModBlocks.PRISM_CHERRY_PRESSURE_PLATE);*/

        // Dye Mixer
        registerBlockItem("dye_mixer", ModBlocks.DYE_MIXER);
        //Other Items
        registerColoredBlockItem("prism_concrete", ModBlocks.PRISM_CONCRETE);
        registerColoredBlockItem("prism_concrete_powdered", ModBlocks.PRISM_CONCRETE_POWDERED);
        registerColoredBlockItem("prism_sand", ModBlocks.PRISM_SAND);
        registerColoredBlockItem("prism_candle", ModBlocks.PRISM_CANDLE);
        registerColoredBlockItem("prism_carpet", ModBlocks.PRISM_CARPET);
        registerColoredBlockItem("prism_ladder", ModBlocks.PRISM_LADDER);
        registerColoredBlockItem("prism_barrel", ModBlocks.PRISM_BARREL);
        registerColoredBlockItem("prism_slime_block", ModBlocks.PRISM_SLIME_BLOCK);
        registerColoredBlockItem("prism_stone", ModBlocks.PRISM_STONE);
        registerColoredBlockItem("prism_andesite", ModBlocks.PRISM_ANDESITE);
        registerColoredBlockItem("prism_wool", ModBlocks.PRISM_WOOL);
        registerColoredBlockItem("prism_terracotta", ModBlocks.PRISM_TERRACOTTA);
    }

    public static final DeferredItem<Item> PRISMCRAFT_ICON = ITEMS.registerSimpleItem(
            "prismcraft_icon",
            new Item.Properties()
    );
}
