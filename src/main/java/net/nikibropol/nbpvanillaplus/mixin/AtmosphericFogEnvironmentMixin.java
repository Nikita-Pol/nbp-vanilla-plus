package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.AtmosphericFogEnvironment;
import net.minecraft.util.Mth;
import net.nikibropol.nbpvanillaplus.client.PaleFogClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AtmosphericFogEnvironment.class)
public class AtmosphericFogEnvironmentMixin {

    private static final float PALE_FOG_END = 48.0F;

    @Inject(method = "setupFog", at = @At("TAIL"))
    private void nbp$paleFog(FogData fog, Camera camera, ClientLevel level,
                             float renderDistance, DeltaTracker deltaTracker, CallbackInfo ci) {
        float k = PaleFogClient.intensity();
        if (k <= 0.001F) return;

        fog.environmentalEnd = Mth.lerp(k, fog.environmentalEnd, Math.min(fog.environmentalEnd, PALE_FOG_END));
    }
}
