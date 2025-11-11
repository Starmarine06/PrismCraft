package net.starmarine06.prismcraft.blockentity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PrismFlowerPotRenderState extends BlockEntityRenderState {
    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
    public BlockPos lightPosition;
    public Level blockEntityLevel;
    public Item flowerItem;
}