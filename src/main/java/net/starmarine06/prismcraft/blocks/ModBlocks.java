package net.starmarine06.prismcraft.blocks;

import net.starmarine06.prismcraft.PrismCraftMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PrismCraftMod.MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PrismCraftMod.MOD_ID);

    // Dye Mixer Block
    public static final DeferredBlock<Block> DYE_MIXER = registerBlock("dye_mixer",
            () -> new DyeMixerBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0f)
                    .sound(SoundType.WOOD)));

    // White Wood Blocks (will be colorable)
    public static final DeferredBlock<Block> WHITE_WOOD = registerBlock("white_wood",
            () -> new ColorableBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0f)
                    .sound(SoundType.WOOD), 0xFFFFFF));

    public static final DeferredBlock<Block> WHITE_WOOD_PLANKS = registerBlock("white_wood_planks",
            () -> new ColorableBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0f, 3.0f)
                    .sound(SoundType.WOOD), 0xFFFFFF));

    public static final DeferredBlock<StairBlock> WHITE_WOOD_STAIRS = registerBlock("white_wood_stairs",
            () -> new ColorableStairBlock(WHITE_WOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(WHITE_WOOD_PLANKS.get()), 0xFFFFFF));

    public static final DeferredBlock<SlabBlock> WHITE_WOOD_SLAB = registerBlock("white_wood_slab",
            () -> new ColorableSlabBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_WOOD_PLANKS.get()), 0xFFFFFF));

    // White Concrete (will be colorable)
    public static final DeferredBlock<Block> WHITE_CONCRETE = registerBlock("white_concrete",
            () -> new ColorableBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.QUARTZ)
                    .strength(1.8f)
                    .sound(SoundType.STONE), 0xFFFFFF));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItem(String name, DeferredBlock<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

    // Helper method to check if a block is colorable
    public static boolean isColorableBlock(BlockState state) {
        Block block = state.getBlock();
        return block instanceof ColorableBlock ||
                block instanceof ColorableStairBlock ||
                block instanceof ColorableSlabBlock;
    }

    // Helper method to get block color
    public static int getBlockColor(BlockState state, BlockGetter level, BlockPos pos, int tintIndex) {
        Block block = state.getBlock();
        if (block instanceof ColorableBlock colorable) {
            return colorable.getColor();
        } else if (block instanceof ColorableStairBlock colorable) {
            return colorable.getColor();
        } else if (block instanceof ColorableSlabBlock colorable) {
            return colorable.getColor();
        }
        return 0xFFFFFF;
    }
}