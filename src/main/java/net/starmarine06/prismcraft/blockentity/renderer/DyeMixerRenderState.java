package net.starmarine06.prismcraft.blockentity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

/**
 * Render state for the Dye Mixer block entity.
 * Mirrors the Pedestal render state style: holds an ItemStackRenderState,
 * level & pos for lighting, and a rotation value for simple animation.
 */
public class DyeMixerRenderState extends BlockEntityRenderState {
    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();

    // Lighting & level context
    public Level blockEntityLevel = null;
    public BlockPos lightPosition = null;

    // Simple rotation (degrees)
    public float rotation = 0f;
}
