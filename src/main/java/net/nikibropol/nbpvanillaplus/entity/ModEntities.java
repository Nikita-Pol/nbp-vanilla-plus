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
import net.nikibropol.nbpvanillaplus.entity.custom.EchoCreaking;

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
    public static final ResourceKey<EntityType<?>> ECHO_CREAKING_KEY = ResourceKey.create(
            Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "echo_creaking"));

    public static final EntityType<EchoCreaking> ECHO_CREAKING = Registry.register(BuiltInRegistries.ENTITY_TYPE, ECHO_CREAKING_KEY,
            EntityType.Builder.of(EchoCreaking::new, MobCategory.MONSTER).sized(0.9F, 2.7F)
                    .eyeHeight(2.3F).clientTrackingRange(8).build(ECHO_CREAKING_KEY));

    public static void registerEntities() {
        NBPVanillaPlus.LOGGER.info("Registering Entities for " + NBPVanillaPlus.MOD_ID);
    }
}