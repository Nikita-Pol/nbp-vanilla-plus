package net.nikibropol.nbpvanillaplus.client.render;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.entity.custom.EchoArrow;

public class EchoArrowRenderer extends ArrowRenderer<EchoArrow, ArrowRenderState> {
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "textures/entity/projectiles/echo_arrow.png");

    public EchoArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    protected Identifier getTextureLocation(ArrowRenderState state) {
        return TEXTURE;
    }
}