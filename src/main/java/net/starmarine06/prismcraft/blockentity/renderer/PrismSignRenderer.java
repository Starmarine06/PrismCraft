package net.starmarine06.prismcraft.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.starmarine06.prismcraft.block.PrismWallSignBlock;
import net.starmarine06.prismcraft.blockentity.PrismSignBlockEntity;
import net.starmarine06.prismcraft.block.PrismSignBlock;
import org.jetbrains.annotations.Nullable;

public class PrismSignRenderer implements BlockEntityRenderer<PrismSignBlockEntity, PrismSignRenderState> {

    public PrismSignRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public PrismSignRenderState createRenderState() {
        return new PrismSignRenderState();
    }



    @Override
    public void extractRenderState(PrismSignBlockEntity be, PrismSignRenderState state, float partialTick,
                                   Vec3 cameraPos, @Nullable net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay overlay) {
        state.blockState = be.getBlockState();

        // front & back text (from vanilla SignText API)
        state.frontText = be.getFrontText();
        state.backText = be.getBackText();
    }

    @Override
    public void submit(PrismSignRenderState state, PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector, CameraRenderState cameraState) {

        Minecraft mc = Minecraft.getInstance();
        BlockState blockState = state.blockState;
        boolean isWallSign = blockState.hasProperty(BlockStateProperties.FACING);

        poseStack.pushPose();
        poseStack.translate(0.5D, 0.5D, 0.5D);

        // Rotate correctly for standing vs wall sign
        if (isWallSign) {
            float yaw = -blockState.getValue(BlockStateProperties.FACING).toYRot();
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw));
            poseStack.translate(0.0D, -0.3125D, -0.4375D);
        } else if (blockState.hasProperty(BlockStateProperties.ROTATION_16)) {
            int rotation = blockState.getValue(BlockStateProperties.ROTATION_16);
            poseStack.mulPose(Axis.YP.rotationDegrees(-rotation * 22.5F));
            poseStack.translate(0.0D, -0.3125D, -0.4375D);
        }

        // Render both front and back
        renderSide(poseStack, state.frontText, mc);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        renderSide(poseStack, state.backText, mc);

        poseStack.popPose();
    }

    private void renderSide(PoseStack poseStack, SignText text, Minecraft mc) {
        if (text == null) return;

        Font font = Minecraft.getInstance().font;
        float scale = 0.010416667F * 2.0F; // matches vanilla
        poseStack.pushPose();
        poseStack.translate(0.0F, 0.33333334F, 0.046666667F);
        poseStack.scale(scale, -scale, scale);

        int color = text.getColor().getTextColor();

        for (int i = 0; i < 4; i++) {
            Component line = text.getMessage(i, false);
            if (line == null) continue;

            float width = font.width(line);
            float x = -width / 2.0F;
            float y = i * 10 - 20;
            font.drawInBatch(
                    line,
                    x,
                    y,
                    color,
                    false,
                    poseStack.last().pose(),
                    mc.renderBuffers().bufferSource(),
                    Font.DisplayMode.NORMAL,
                    0,
                    0xF000F0
            );
        }

        poseStack.popPose();
    }

    @Override
    public boolean shouldRenderOffScreen() {
        return true;
    }
}
