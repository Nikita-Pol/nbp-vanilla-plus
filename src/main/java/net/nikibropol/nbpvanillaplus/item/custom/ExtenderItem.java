package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ExtenderItem extends Item {

    public ExtenderItem(Properties properties, double reachBonus) {
        super(withReachAttributes(properties, reachBonus));
    }

    private static Properties withReachAttributes(Properties properties, double reachBonus) {
        ItemAttributeModifiers modifiers = ItemAttributeModifiers.builder()
                .add(
                        Attributes.BLOCK_INTERACTION_RANGE,
                        new AttributeModifier(
                                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "extender_" + (int) reachBonus + "_block_reach"),
                                reachBonus,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.OFFHAND
                )
                .add(
                        Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(
                                Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "extender_" + (int) reachBonus + "_entity_reach"),
                                reachBonus,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.OFFHAND
                )
                .build();

        return properties.attributes(modifiers);
    }
}
