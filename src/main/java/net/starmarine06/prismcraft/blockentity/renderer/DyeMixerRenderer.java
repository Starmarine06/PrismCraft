package net.starmarine06.prismcraft.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import net.starmarine06.prismcraft.blockentity.DyeMixerBlockEntity;

public class DyeMixerRenderer implements BlockEntityRenderer<DyeMixerBlockEntity, DyeMixerRenderState> {
    private final ItemModelResolver itemModelResolver;

    public DyeMixerRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public DyeMixerRenderState createRenderState() {
        return new DyeMixerRenderState();
    }

    @Override
    public void extractRenderState(DyeMixerBlockEntity blockEntity,
                                   DyeMixerRenderState renderState,
                                   float partialTick,
                                   Vec3 cameraPosition,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);

        renderState.lightPosition = blockEntity.getBlockPos();
        renderState.blockEntityLevel = blockEntity.getLevel();

        // rotation animation (same approach as Pedestal)
        if (blockEntity.getLevel() != null) {
            long gameTime = blockEntity.getLevel().getGameTime();
            renderState.rotation = (float) ((gameTime + partialTick) * 4.0 % 360.0);
        } else {
            renderState.rotation = 0f;
        }

        // prepare the ItemStackRenderState from the result slot
        ItemStack result = blockEntity.getItem(DyeMixerBlockEntity.getResultSlotIndex());
        if (result == null || result.isEmpty()) {
            renderState.itemStackRenderState.clear();
            return;
        }

        ItemStack stackForRender = result.copyWithCount(1);
        itemModelResolver.updateForTopItem(renderState.itemStackRenderState,
                stackForRender, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
    }

    @Override
    public void submit(DyeMixerRenderState renderState,
                       PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector,
                       CameraRenderState cameraRenderState) {

        if (renderState.itemStackRenderState.isEmpty()) return;

        poseStack.pushPose();

        // position + scale above the mixer (tweak to taste)
        poseStack.translate(0.5f, 1.05f, 0.5f);
        poseStack.scale(0.6f, 0.6f, 0.6f);

        // rotation
        poseStack.mulPose(Axis.YP.rotationDegrees(renderState.rotation));

        // compute lighting
        int packedLight = 0xF000F0;
        if (renderState.blockEntityLevel != null && renderState.lightPosition != null) {
            packedLight = getLightLevel(renderState.blockEntityLevel, renderState.lightPosition);
        }

        // submit prepared item render state
        renderState.itemStackRenderState.submit(poseStack, submitNodeCollector, packedLight, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}