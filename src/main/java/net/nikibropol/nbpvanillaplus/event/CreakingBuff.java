package net.nikibropol.nbpvanillaplus.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.creaking.Creaking;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class CreakingBuff {
    private static final Identifier BONUS_ID =
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "creaking_damage_bonus");

    public static void register() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, level) -> {
            if (!(entity instanceof Creaking creaking)) return;
            var attribute = creaking.getAttribute(Attributes.ATTACK_DAMAGE);
            if (attribute != null && !attribute.hasModifier(BONUS_ID)) {
                attribute.addPermanentModifier(new AttributeModifier(
                        BONUS_ID, 8.0, AttributeModifier.Operation.ADD_VALUE));
            }
        });
    }
}
