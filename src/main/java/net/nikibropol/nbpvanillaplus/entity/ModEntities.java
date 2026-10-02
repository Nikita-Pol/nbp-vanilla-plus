package net.nikibropol.nbpvanillaplus.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.entity.custom.EchoArrow;

public class ModEntities {
    public static final EntityType<EchoArrow> ECHO_ARROW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "echo_arrow"),
            EntityType.Builder.<EchoArrow>of(EchoArrow::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "echo_arrow")))
    );

    public static void registerEntities() {
        NBPVanillaPlus.LOGGER.info("Registering Entities for " + NBPVanillaPlus.MOD_ID);
    }
}