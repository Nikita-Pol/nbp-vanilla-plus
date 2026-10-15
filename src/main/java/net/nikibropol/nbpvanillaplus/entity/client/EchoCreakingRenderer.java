package net.nikibropol.nbpvanillaplus.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.monster.creaking.CreakingModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.entity.state.CreakingRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.entity.custom.EchoCreaking;

@Environment(EnvType.CLIENT)
public class EchoCreakingRenderer extends MobRenderer<EchoCreaking, CreakingRenderState, CreakingModel> {
    private static final Identifier TEXTURE_LOCATION =
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "textures/entity/echo_creaking/echo_creaking.png");
    private static final Identifier EYES_TEXTURE_LOCATION =
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "textures/entity/echo_creaking/echo_creaking_eyes.png");

    public EchoCreakingRenderer(final EntityRendererProvider.Context context) {
        super(context, new CreakingModel(context.bakeLayer(ModelLayers.CREAKING)), 0.6F);
        this.addLayer(
                new LivingEntityEmissiveLayer<>(
                        this,
                        renderState -> EYES_TEXTURE_LOCATION,
                        (state, ageInTicks) -> state.eyesGlowing ? 1.0F : 0.0F,
                        new CreakingModel(context.bakeLayer(ModelLayers.CREAKING_EYES)),
                        RenderTypes::eyes,
                        true
                )
        );
    }

    @Override
    public Identifier getTextureLocation(final CreakingRenderState state) {
        return TEXTURE_LOCATION;
    }

    @Override
    public CreakingRenderState createRenderState() {
        return new CreakingRenderState();
    }

    @Override
    public void extractRenderState(final EchoCreaking entity, final CreakingRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
        state.invulnerabilityAnimationState.copyFrom(entity.invulnerabilityAnimationState);
        state.deathAnimationState.copyFrom(entity.deathAnimationState);
        if (entity.isTearingDown()) {
            state.deathTime = 0.0F;
            state.hasRedOverlay = false;
            state.eyesGlowing = entity.hasGlowingEyes();
        } else {
            state.eyesGlowing = entity.canMove();
        }

        state.canMove = entity.canMove();
    }
}
