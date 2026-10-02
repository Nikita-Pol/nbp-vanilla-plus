package net.nikibropol.nbpvanillaplus.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.custom.ReinforcedShulkerBoxBlock;
import net.nikibropol.nbpvanillaplus.block.entity.custom.ReinforcedShulkerBoxBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.Map;

public class ReinforceShulkerBoxBlockEntityRenderer implements BlockEntityRenderer<ReinforcedShulkerBoxBlockEntity, ReinforceShulkerBoxBlockEntityRenderState> {

    private static final Map<Direction, Transformation> TRANSFORMATIONS =
            net.minecraft.util.Util.makeEnumMap(Direction.class, ReinforceShulkerBoxBlockEntityRenderer::createModelTransform);

    private final SpriteGetter sprites;
    private final ShulkerBoxModel model;

    public ReinforceShulkerBoxBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.sprites = context.sprites();
        this.model = new ShulkerBoxModel(context.entityModelSet().bakeLayer(ModelLayers.SHULKER_BOX));
    }

    @Override
    public ReinforceShulkerBoxBlockEntityRenderState createRenderState() {
        return new ReinforceShulkerBoxBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(ReinforcedShulkerBoxBlockEntity blockEntity, ReinforceShulkerBoxBlockEntityRenderState state,
                                   float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.direction = blockEntity.getBlockState().getValueOrElse(ReinforcedShulkerBoxBlock.FACING, Direction.UP);
        state.color = blockEntity.getColor();
        state.progress = blockEntity.getProgress(partialTicks);BlockPos pos = blockEntity.getBlockPos();
        Level level = blockEntity.getLevel();
        int blockLight = level.getLightEngine().getLayerListener(LightLayer.BLOCK).getLightValue(pos);
        int skyLight = level.getLightEngine().getLayerListener(LightLayer.SKY).getLightValue(pos);
        state.lightCoords = (blockLight << 4) | (skyLight << 20);
    }

    @Override
    public void submit(ReinforceShulkerBoxBlockEntityRenderState state, PoseStack poseStack,
                       SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        DyeColor color = state.color;
        SpriteId sprite = getReinforcedSprite(state.color);

        poseStack.pushPose();
        poseStack.mulPose(TRANSFORMATIONS.get(state.direction).getMatrix());
        this.model.setupAnim(state.progress);
        submitNodeCollector.submitModel(this.model, state.progress, poseStack, state.lightCoords,
                OverlayTexture.NO_OVERLAY, -1, sprite, this.sprites, 0, state.breakProgress);
        poseStack.popPose();
    }

    private static Transformation createModelTransform(Direction direction) {
        return new Transformation(new Matrix4f()
                .translation(0.5F, 0.5F, 0.5F)
                .scale(0.9995F, 0.9995F, 0.9995F)
                .rotate(direction.getRotation())
                .scale(1.0F, -1.0F, -1.0F)
                .translate(0.0F, -1.0F, 0.0F));
    }

    private static class ShulkerBoxModel extends Model<Float> {
        private final ModelPart lid;

        public ShulkerBoxModel(ModelPart root) {
            super(root, RenderTypes::entityCutout);
            this.lid = root.getChild("lid");
        }

        public void setupAnim(Float progress) {
            super.setupAnim(progress);
            this.lid.setPos(0.0F, 24.0F - progress * 0.5F * 16.0F, 0.0F);
            this.lid.yRot = 270.0F * progress * (float) (Math.PI / 180.0);
        }
    }
    private static SpriteId getReinforcedSprite(@Nullable DyeColor color) {
        String path = "entity/shulker/" + (color != null ? "reinforced_shulker_" + color.getSerializedName() : "reinforced_shulker");
        return new SpriteId(Sheets.SHULKER_SHEET, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, path));
    }
}