package net.nikibropol.nbpvanillaplus.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> STRANGE_BEETROOT_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "strange_beetroot_damage"));

    public static void bootstrap(BootstrapContext<DamageType> context){
        context.register(STRANGE_BEETROOT_DAMAGE, new DamageType("strange_beetroot_damage", 0.1f, DamageEffects.HURT));
    }

    public static DamageSource create(Level level, ResourceKey<DamageType> key){
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
