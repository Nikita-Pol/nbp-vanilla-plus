package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "canBeAffected", at = @At("HEAD"), cancellable = true)
    private void blockDarknessWithEchoChestplate(MobEffectInstance effectInstance, CallbackInfoReturnable<Boolean> callbackInfo) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (effectInstance.is(MobEffects.DARKNESS)
                && livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.ECHO_CHESTPLATE)) {
            callbackInfo.setReturnValue(false);
        }
    }
}
