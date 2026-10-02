package net.nikibropol.nbpvanillaplus.stat;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ModStats {
    public static final Stat<?> COBBLER_USED_STAT = makeCustomStat("cobbler_used");
    public static final Stat<?> NOTED_COORDINATES_IN_RECOVERY_SHARD = makeCustomStat("noted_coordinates_in_recovery_shard");
    public static final Stat<?> CLICKED_WITH_EXTENDER = makeCustomStat("clicked_with_extender");

    private static Stat<?> makeCustomStat(String key){
        Identifier identifier = Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, key);
        Identifier newStat = Registry.register(BuiltInRegistries.CUSTOM_STAT, key, identifier);

        return Stats.CUSTOM.get(newStat, StatFormatter.DEFAULT);
    }

    public static void registerStats(){
        NBPVanillaPlus.LOGGER.info("Registering Stats for " + NBPVanillaPlus.MOD_ID);
    }
}
