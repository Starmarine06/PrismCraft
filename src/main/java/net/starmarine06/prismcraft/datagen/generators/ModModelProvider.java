package net.starmarine06.prismcraft.datagen.generators;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.starmarine06.prismcraft.blocks.BlockRegistry;
import net.starmarine06.prismcraft.blocks.BlockSet;
import net.starmarine06.prismcraft.items.ItemRegistry;

import java.util.stream.Stream;

import static net.starmarine06.prismcraft.Prismcraft.MOD_ID;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        BlockSet.getBlockSets().forEach(blockSet -> {

            blockModels.family(blockSet.getPlanks())
                    .stairs(blockSet.getStairsBlock())
                    .fence(blockSet.getFence())
                    .slab(blockSet.getSlabBlock())
                    .button(blockSet.getButton());


        });

    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream();
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        return ItemRegistry.ITEMS.getEntries().stream();
    }
}
