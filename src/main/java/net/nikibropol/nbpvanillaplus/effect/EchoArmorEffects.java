package net.nikibropol.nbpvanillaplus.effect;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.nikibropol.nbpvanillaplus.item.ModItems;

public class EchoArmorEffects {

    private static final int EFFECT_DURATION = 40;
    private static final int REFRESH_THRESHOLD = 20;

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                updateEffects(player);
            }
        });
    }

    public static boolean isWearingFullSet(LivingEntity livingEntity) {
        return livingEntity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.ECHO_HELMET)
                && livingEntity.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.ECHO_CHESTPLATE)
                && livingEntity.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.ECHO_LEGGINGS)
                && livingEntity.getItemBySlot(EquipmentSlot.FEET).is(ModItems.ECHO_BOOTS);
    }

    private static void updateEffects(ServerPlayer player) {
        if (player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.ECHO_CHESTPLATE)
                && player.hasEffect(MobEffects.DARKNESS)) {
            player.removeEffect(MobEffects.DARKNESS);
        }

        if (!isWearingFullSet(player)) {
            return;
        }
        MobEffectInstance current = player.getEffect(ModEffects.SILENCE);
        if (current == null || current.getDuration() < REFRESH_THRESHOLD) {
            player.addEffect(new MobEffectInstance(ModEffects.SILENCE, EFFECT_DURATION, 0, true, false, true));
        }
    }
}