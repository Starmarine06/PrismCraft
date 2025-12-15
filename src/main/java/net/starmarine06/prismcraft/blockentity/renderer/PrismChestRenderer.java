package net.starmarine06.prismcraft.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.ChestModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.OrderedSubmitNodeCollector;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.blockentity.PrismChestBlockEntity;

import javax.annotation.Nullable;

public class PrismChestRenderer
        implements BlockEntityRenderer<PrismChestBlockEntity, ChestRenderState> {

    private final MaterialSet materials;
    private final ChestModel single;
    private final ChestModel left;
    private final ChestModel right;

    public PrismChestRenderer(BlockEntityRendererProvider.Context ctx) {
        this.materials = ctx.materials();
        this.single = new ChestModel(ctx.bakeLayer(ModelLayers.CHEST));
        this.left = new ChestModel(ctx.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT));
        this.right = new ChestModel(ctx.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT));
    }

    @Override
    public ChestRenderState createRenderState() {
        return new ChestRenderState();
    }

    @Override
    public void extractRenderState(
            PrismChestBlockEntity be,
            ChestRenderState state,
            float partialTick,
            Vec3 cameraPos,
            @Nullable ModelFeatureRenderer.CrumblingOverlay crumbling
    ) {
        boolean hasLevel = be.getLevel() != null;
        BlockState blockState = hasLevel
                ? be.getBlockState()
                : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);

        state.type = blockState.getValue(ChestBlock.TYPE);
        state.angle = blockState.getValue(ChestBlock.FACING).toYRot();
        state.material = ChestRenderState.ChestMaterialType.REGULAR;

        // --- choose texture BASED ON COLOR ---
        state.customMaterial = new Material(
                Sheets.CHEST_SHEET,
                ResourceLocation.fromNamespaceAndPath(
                        PrismCraftMod.MOD_ID,
                        "entity/chest/prism_chest_" + be.getColor()
                )
        );

        @SuppressWarnings("unchecked")
        DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> combine =
                hasLevel && blockState.getBlock() instanceof ChestBlock chest
                        ? (DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity>)
                        chest.combine(blockState, be.getLevel(), be.getBlockPos(), true)
                        : DoubleBlockCombiner.Combiner::acceptNone;

        state.open = combine.apply(ChestBlock.opennessCombiner(be)).get(partialTick);

        if (state.type != ChestType.SINGLE) {
            state.lightCoords =
                    ((it.unimi.dsi.fastutil.ints.Int2IntFunction)
                            combine.apply(new BrightnessCombiner()))
                            .applyAsInt(state.lightCoords);
        }
    }

    @Override
    public void submit(
            ChestRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState cameraState
    ) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(-state.angle));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        Material material = state.customMaterial != null
                ? state.customMaterial
                : Sheets.chooseMaterial(state.material, state.type);

        RenderType renderType = material.renderType(RenderType::entityCutout);
        TextureAtlasSprite sprite = this.materials.get(material);

        ChestModel model =
                state.type == ChestType.LEFT ? this.left :
                        state.type == ChestType.RIGHT ? this.right :
                                this.single;

        // ✅ EXACT 8-ARGUMENT OVERLOAD YOU SHOWED
        /*collector.submitModel(
                model,                      // Model<? super S>
                state,                      // S = ChestRenderState
                poseStack,
                renderType,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                -1,
                null
        );*/

        poseStack.popPose();
    }





}