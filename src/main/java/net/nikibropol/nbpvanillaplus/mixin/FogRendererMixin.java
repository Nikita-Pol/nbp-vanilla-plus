package net.nikibropol.nbpvanillaplus.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.Std140Builder;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.util.Mth;
import net.nikibropol.nbpvanillaplus.client.PaleFogClient;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/buffers/Std140SizeCalculator;get()I"))
    private static int nbp$size(int original) {
        return original + 4;
    }
    @Inject(method = "computeFogColor", at = @At("RETURN"))
    private void nbp$greyFog(Camera camera, float partialTicks, ClientLevel level, int renderDistance,
                             float darkenWorldAmount, Vector4f dest, CallbackInfo ci) {
        float g = PaleFogClient.greyAmount();
        if (g <= 0f) return;
        float lum = dest.x * 0.299f + dest.y * 0.587f + dest.z * 0.114f;
        dest.set(Mth.lerp(g, dest.x, lum),
                Mth.lerp(g, dest.y, lum),
                Mth.lerp(g, dest.z, lum),
                1.0f - 0.01f * g);
    }
    @WrapOperation(method = "updateBuffer(Ljava/nio/ByteBuffer;ILorg/joml/Vector4f;FFFFFF)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/buffers/Std140Builder;putFloat(F)Lcom/mojang/blaze3d/buffers/Std140Builder;", ordinal = 5))
    private Std140Builder nbp$grey(Std140Builder builder, float value, Operation<Std140Builder> op) {
        return op.call(builder, value).putFloat(PaleFogClient.greyAmount());
    }
}
