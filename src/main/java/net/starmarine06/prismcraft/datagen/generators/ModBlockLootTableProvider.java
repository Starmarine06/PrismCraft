package net.starmarine06.prismcraft.datagen.generators;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.starmarine06.prismcraft.blocks.BlockRegistry;
import net.starmarine06.prismcraft.blocks.BlockSet;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        BlockSet.getBlockSets().forEach(blockSet -> {
            dropSelf(blockSet.getSlabBlock());
            dropSelf(blockSet.getStairsBlock());
            dropSelf(blockSet.getPlanks());
            dropSelf(blockSet.getFence());
            dropSelf(blockSet.getButton());
        });
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}