package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.nikibropol.nbpvanillaplus.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "dampensVibrations", at = @At("HEAD"), cancellable = true)
    private void addSilenceEffect(CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity)(Object)this;
        if (self instanceof LivingEntity living && living.hasEffect(ModEffects.SILENCE)) {
            cir.setReturnValue(true);
        }
    }
}
