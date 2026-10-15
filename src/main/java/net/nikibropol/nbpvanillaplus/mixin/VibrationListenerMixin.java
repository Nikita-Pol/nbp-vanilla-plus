package net.nikibropol.nbpvanillaplus.mixin;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.GameEventTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import net.minecraft.world.phys.Vec3;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VibrationSystem.Listener.class)
public abstract class VibrationListenerMixin {

    @Inject(method = "handleGameEvent", at = @At("HEAD"), cancellable = true)
    private void ignoreMovementWithEchoLeggings(ServerLevel level, Holder<GameEvent> gameEvent,
                                                GameEvent.Context context, Vec3 position,
                                                CallbackInfoReturnable<Boolean> callbackInfo) {
        Entity sourceEntity = context.sourceEntity();
        if (sourceEntity instanceof LivingEntity livingEntity
                && gameEvent.is(GameEventTags.IGNORE_VIBRATIONS_SNEAKING)
                && livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.ECHO_LEGGINGS)) {
            callbackInfo.setReturnValue(false);
        }
    }
}
