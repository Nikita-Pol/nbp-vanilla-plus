package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.client.renderer.SkyRenderer;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.nikibropol.nbpvanillaplus.client.PaleFogClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SkyRenderer.class)
public class SkyRendererMixin {
    @ModifyVariable(method = "renderSkyDisc", at = @At("HEAD"), argsOnly = true)
    private int nbp$greySky(int skyColor) {
        float g = PaleFogClient.greyAmount();
        if (g <= 0f) return skyColor;

        float r = ARGB.redFloat(skyColor);
        float gr = ARGB.greenFloat(skyColor);
        float b = ARGB.blueFloat(skyColor);
        float lum = r * 0.299f + gr * 0.587f + b * 0.114f;

        return ARGB.colorFromFloat(
                ARGB.alphaFloat(skyColor),
                Mth.lerp(g, r, lum),
                Mth.lerp(g, gr, lum),
                Mth.lerp(g, b, lum));
    }
}
