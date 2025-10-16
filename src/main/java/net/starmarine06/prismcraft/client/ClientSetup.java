package net.starmarine06.prismcraft.client;

import net.starmarine06.prismcraft.blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ClientSetup {
    public static void registerColors() {
        var blockColors = Minecraft.getInstance().getBlockColors();
        var itemColors = Minecraft.getInstance().getItemColors();

        // Block color: use color from block entity NBT, default to white
        BlockColor colorableBlockColor = (state, world, pos, tintIndex) -> {
            if (tintIndex != 0) return 0xFFFFFF;
            if (world != null && pos != null) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be != null && be.getClass().getSimpleName().endsWith("BlockEntity")) {
                    try {
                        var m = be.getClass().getMethod("getColor");
                        return (int) m.invoke(be);
                    } catch (Exception ignored) {}
                }
            }
            return 0xFFFFFF;
        };

        blockColors.register(colorableBlockColor,
                ModBlocks.WHITE_WOOD.get(),
                ModBlocks.WHITE_WOOD_PLANKS.get(),
                ModBlocks.WHITE_WOOD_STAIRS.get(),
                ModBlocks.WHITE_WOOD_SLAB.get(),
                ModBlocks.WHITE_CONCRETE.get()
        );

        // Item color: read the color from item NBT if present
        ItemColor colorableItemColor = (stack, tintIndex) -> {
            if (tintIndex != 0) return 0xFFFFFF;
            if (stack.hasTag() && stack.getTag().contains("prismcraft:color")) {
                return stack.getTag().getInt("prismcraft:color");
            }
            return 0xFFFFFF;
        };

        itemColors.register(colorableItemColor,
                ModBlocks.WHITE_WOOD.get().asItem(),
                ModBlocks.WHITE_WOOD_PLANKS.get().asItem(),
                ModBlocks.WHITE_WOOD_STAIRS.get().asItem(),
                ModBlocks.WHITE_WOOD_SLAB.get().asItem(),
                ModBlocks.WHITE_CONCRETE.get().asItem()
        );
    }
}