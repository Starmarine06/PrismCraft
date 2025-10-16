package net.starmarine06.prismcraft.datagen;

import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModDataGeneration {

    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new ModRecipeProvider(packOutput, lookupProvider));
        generator.addProvider(true, new ModLootTableProvider(packOutput, lookupProvider));

        BlockTagsProvider blockTagProvider = new ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(true, blockTagProvider);
    }

    public static class ModRecipeProvider extends RecipeProvider {
        public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput recipeOutput) {
            // Dye Mixer Recipe
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DYE_MIXER.get())
                    .pattern("SPS")
                    .pattern("P P")
                    .pattern("SSS")
                    .define('S', Items.SMOOTH_STONE)
                    .define('P', Items.OAK_PLANKS)
                    .unlockedBy("has_smooth_stone", has(Items.SMOOTH_STONE))
                    .save(recipeOutput);

            // White Wood Recipe
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_WOOD.get(), 4)
                    .pattern("PP")
                    .pattern("PP")
                    .define('P', Items.OAK_LOG)
                    .unlockedBy("has_oak_log", has(Items.OAK_LOG))
                    .save(recipeOutput);

            // White Wood Planks Recipe
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_WOOD_PLANKS.get(), 4)
                    .pattern("W")
                    .define('W', ModBlocks.WHITE_WOOD.get())
                    .unlockedBy("has_white_wood", has(ModBlocks.WHITE_WOOD.get()))
                    .save(recipeOutput);

            // White Concrete Recipe
            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_CONCRETE.get(), 8)
                    .pattern("CCC")
                    .pattern("CWC")
                    .pattern("CCC")
                    .define('C', Items.WHITE_CONCRETE)
                    .define('W', Items.WATER_BUCKET)
                    .unlockedBy("has_white_concrete", has(Items.WHITE_CONCRETE))
                    .save(recipeOutput);
        }
    }

    public static class ModLootTableProvider extends LootTableProvider {
        public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, Set.of(), List.of(
                    new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
            ), registries);
        }
    }

    public static class ModBlockLootTables extends BlockLootSubProvider {
        public ModBlockLootTables(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        }

        @Override
        protected void generate() {
            this.dropSelf(ModBlocks.DYE_MIXER.get());
            this.dropSelf(ModBlocks.WHITE_WOOD.get());
            this.dropSelf(ModBlocks.WHITE_WOOD_PLANKS.get());
            this.dropSelf(ModBlocks.WHITE_WOOD_STAIRS.get());
            this.dropSelf(ModBlocks.WHITE_WOOD_SLAB.get());
            this.dropSelf(ModBlocks.WHITE_CONCRETE.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return List.of(
                    ModBlocks.DYE_MIXER.get(),
                    ModBlocks.WHITE_WOOD.get(),
                    ModBlocks.WHITE_WOOD_PLANKS.get(),
                    ModBlocks.WHITE_WOOD_STAIRS.get(),
                    ModBlocks.WHITE_WOOD_SLAB.get(),
                    ModBlocks.WHITE_CONCRETE.get()
            );
        }
    }

    public static class ModBlockTagProvider extends BlockTagsProvider {
        public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, PrismCraftMod.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            // Add block tags if needed
        }
    }
}