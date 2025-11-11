package net.starmarine06.prismcraft.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.starmarine06.prismcraft.blockentity.PrismFlowerPotBlockEntity;
import net.starmarine06.prismcraft.blockentity.renderer.PrismFlowerPotRenderState;
import org.jetbrains.annotations.Nullable;

public class PrismFlowerPotRenderer implements BlockEntityRenderer<PrismFlowerPotBlockEntity, PrismFlowerPotRenderState> {
    private final ItemModelResolver itemModelResolver;

    public PrismFlowerPotRenderer(BlockEntityRendererProvider.Context context) {
        System.out.println("PrismFlowerPotRenderer constructed");
        itemModelResolver = context.itemModelResolver();
    }

    @Override
    public PrismFlowerPotRenderState createRenderState() {
        return new PrismFlowerPotRenderState();
    }

    @Override
    public void extractRenderState(PrismFlowerPotBlockEntity blockEntity, PrismFlowerPotRenderState renderState, float partialTick,
                                   Vec3 cameraPosition, @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, cameraPosition, breakProgress);

        renderState.lightPosition = blockEntity.getBlockPos();
        renderState.blockEntityLevel = blockEntity.getLevel();
        renderState.flowerItem = blockEntity.getFlower();

        // Get the flower item and update the render state
        ItemStack flowerStack = blockEntity.getFlower() != null ? new ItemStack(blockEntity.getFlower()) : ItemStack.EMPTY;

        // Debug logging
        System.out.println("Renderer extracting flower: " + (blockEntity.getFlower() != null ? blockEntity.getFlower().toString() : "null"));
        System.out.println("FlowerStack: " + flowerStack);

        itemModelResolver.updateForTopItem(renderState.itemStackRenderState,
                flowerStack, ItemDisplayContext.FIXED, blockEntity.getLevel(), null, 0);
    }

    @Override
    public void submit(PrismFlowerPotRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        // Debug logging
        System.out.println("Renderer submit called!");  // type: Item

        poseStack.pushPose();

        // Center above the pot -- adjust height as needed
        poseStack.translate(0.15, 0.15, 0.15);
        // Bigger flower: scale from 0.5 (default) to, eg., 0.8 for 1.6x
        poseStack.scale(0.8F, 0.8F, 0.8F);

        if (renderState.blockEntityLevel != null && renderState.flowerItem != null) {
            Block flowerBlock = Block.byItem(renderState.flowerItem);
            if (flowerBlock != null) {
                BlockState flowerState = flowerBlock.defaultBlockState();
                Minecraft.getInstance().getBlockRenderer().renderSingleBlock(
                        flowerState, poseStack,
                        Minecraft.getInstance().renderBuffers().bufferSource(), // Fix: use the buffer source here!
                        0xF000F0, OverlayTexture.NO_OVERLAY
);

            }
        }

        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}