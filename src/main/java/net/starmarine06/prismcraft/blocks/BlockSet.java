package net.starmarine06.prismcraft.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.ArrayList;
import java.util.List;

import static net.starmarine06.prismcraft.Prismcraft.MOD_ID;
import static net.starmarine06.prismcraft.blocks.BlockRegistry.registerBlock;

public class BlockSet {
    public static List<BlockSet> blockSets = new ArrayList<>();

    private final DeferredBlock<Block> planksBlock;
    private final DeferredBlock<Block> stairsBlock;
    private final DeferredBlock<Block> slabBlock;
    private final DeferredBlock<Block> fenceBlock;
    private final DeferredBlock<ButtonBlock> buttonBlock;
    private final int rgb;
    private final String enUs;


    BlockSet(Builder builder){
        boolean requiresTool = builder.requiresTool;
        enUs = builder.enUs;
        rgb = builder.rgb;

        var name = builder.baseRegistryName;

        planksBlock = registerBlock(name+"_planks",properties -> new Block(blockProperties(requiresTool,name+"_planks")));

        stairsBlock = registerBlock(name+"_stairs", prop -> new StairBlock(planksBlock.get().defaultBlockState(),
                blockProperties(requiresTool,name+"_stairs")));

        slabBlock = registerBlock(name+"_slab", prop -> new SlabBlock(blockProperties(requiresTool,name+"_slab")));

        fenceBlock = registerBlock(name+"_fence", prop -> new FenceBlock(blockProperties(requiresTool,name+"_fence")));

        buttonBlock = registerBlock(name+"_button",prop -> new ButtonBlock(BlockSetType.OAK,0,blockProperties(requiresTool,name+"_button")));

        blockSets.add(this);
    }

    private static BlockBehaviour.Properties blockProperties(boolean requiresTool, String name ){
        var toReturn = BlockBehaviour.Properties.of()
                .strength(3,3)
                .sound(SoundType.WOOD)
                .setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MOD_ID,name)));
        if(requiresTool){
            toReturn.requiresCorrectToolForDrops();
        }
        return toReturn;
    }

    public Block getStairsBlock() {
        return stairsBlock.get();
    }

    public Block getSlabBlock() {
        return slabBlock.get();
    }

    public Block getPlanks(){
        return planksBlock.get();
    }

    public Block getFence(){
        return fenceBlock.get();
    }

    public Block getButton(){
        return buttonBlock.get();
    }

    public String getEnUs() {
        return enUs;
    }

    public static List<BlockSet> getBlockSets() {
        return blockSets;
    }

    public int getRgb(){
        return rgb;
    }

    public static class Builder{

        private String baseRegistryName;
        private boolean requiresTool = false;
        private int rgb = 0xF9FFFE;
        private String enUs = "Test";


        public Builder() {
        }

        public Builder rgb(int rgb){
            this.rgb = rgb;
            return this;
        }

        public Builder enUsLang(String enUs){
            this.enUs = enUs;
            return this;
        }

        public Builder baseRegistryName(String baseRegistryName){
            this.baseRegistryName = baseRegistryName;
            return this;
        }

        public Builder requiresTool(){
            this.requiresTool = true;
            return this;
        }

        public BlockSet build(){
            try {
                this.validateBuilder();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return new BlockSet(this);
        }

        private void validateBuilder() throws Exception {
            if(this.baseRegistryName == null) throw new Exception("Base Registry cannot be null");
        }
    }
}
