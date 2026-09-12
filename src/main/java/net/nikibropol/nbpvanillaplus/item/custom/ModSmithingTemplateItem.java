package net.nikibropol.nbpvanillaplus.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ModSmithingTemplateItem extends SmithingTemplateItem {

    public ModSmithingTemplateItem(Component appliesTo, Component ingredients, Component baseSlotDescription, Component additionsSlotDescription, List<Identifier> baseSlotEmptyIcons, List<Identifier> additionalSlotEmptyIcons, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription, baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
    }
    public static SmithingTemplateItem create(

            String translationKeyPrefix,
            List<Identifier> baseSlotEmptyIcons,
            List<Identifier> additionalSlotEmptyIcons,
            Properties properties
    ) {
        return new SmithingTemplateItem(
                Component.translatable(translationKeyPrefix + ".applies_to"),
                Component.translatable(translationKeyPrefix + ".ingredients"),
                Component.translatable(translationKeyPrefix + ".base_slot_description"),
                Component.translatable(translationKeyPrefix + ".additions_slot_description"),
                baseSlotEmptyIcons,
                additionalSlotEmptyIcons,
                properties
        );
    }

    public static List<Identifier> createEchoUpgradeIconList() {
        return List.of(
                Identifier.withDefaultNamespace("container/slot/helmet"),
                Identifier.withDefaultNamespace("container/slot/sword"),
                Identifier.withDefaultNamespace("container/slot/chestplate"),
                Identifier.withDefaultNamespace("container/slot/pickaxe"),
                Identifier.withDefaultNamespace("container/slot/leggings"),
                Identifier.withDefaultNamespace("container/slot/axe"),
                Identifier.withDefaultNamespace("container/slot/boots"),
                Identifier.withDefaultNamespace("container/slot/hoe"),
                Identifier.withDefaultNamespace("container/slot/shovel")
        );
    }
    public  static List<Identifier> createEchoUpgradeMaterialList() {
        return List.of(Identifier.withDefaultNamespace("container/slot/ingot"));
    }
}
