package net.starmarine06.prismcraft.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.world.phys.Vec3;
import net.starmarine06.prismcraft.blockentity.PrismHangingSignBlockEntity;
import net.starmarine06.prismcraft.blockentity.PrismSignBlockEntity;
import org.jetbrains.annotations.Nullable;

public class PrismHangingSignRenderer implements BlockEntityRenderer<PrismHangingSignBlockEntity, SignRenderState> {
    public PrismHangingSignRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public SignRenderState createRenderState() {
        return new SignRenderState();
    }


    @Override
    public void extractRenderState(PrismHangingSignBlockEntity be, SignRenderState state, float partialTick, Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        //state.copyFrom(be); // This pulls vanilla sign text, glowing, etc.
        //state.setWoodColor(be.getColor()); // Custom extension you add
    }

    @Override
    public void submit(SignRenderState state, PoseStack stack, SubmitNodeCollector submitNodeCollector, net.minecraft.client.renderer.state.CameraRenderState cameraState) {
        // Vanilla sign renderer will use state.getWoodColor() when drawing the sign boards.
        Minecraft.getInstance().getBlockEntityRenderDispatcher().submit(state, stack, submitNodeCollector, cameraState);
    }
}
