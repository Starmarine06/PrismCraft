package net.starmarine06.prismcraft.blockentity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class PrismSignRenderState extends BlockEntityRenderState {
    public BlockState blockState;
    public SignText frontText;
    public SignText backText;

}
