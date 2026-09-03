package net.nikibropol.nbpvanillaplus.item;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.tags.ModTags;

public class ModArmorMaterials {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY =
            ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> ECHO_KEY =
            ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "echo"));

    public static final ArmorMaterial ECHO_ARMOR_MATERIAL = new ArmorMaterial(100,
            ArmorMaterials.makeDefense(5, 8, 12, 7, 25),
            28, SoundEvents.ARMOR_EQUIP_NETHERITE, 5, 0.25f,
            ModTags.Items.ECHO_REPAIR, ECHO_KEY);


}
