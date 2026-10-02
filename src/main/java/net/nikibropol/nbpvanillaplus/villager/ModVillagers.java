package net.nikibropol.nbpvanillaplus.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.datagen.villager.ModTradeSets;

public class ModVillagers {

    public static final ResourceKey<PoiType> DREAM_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dream_poi"));

    public static final PoiType DREAM_POI = PoiHelper.register(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dream_poi"),
            1, 1, ModBlocks.DREAM_ECHO_STATUE);

    public static final VillagerProfession DREAMER = registerVillagerProfession("dreamer", "Dreamer", DREAM_POI_KEY,
            SoundEvents.AMETHYST_BLOCK_RESONATE, Int2ObjectMap.ofEntries(
                    Int2ObjectMap.entry(1, ModTradeSets.DREAMER_LEVEL_1),
                    Int2ObjectMap.entry(2, ModTradeSets.DREAMER_LEVEL_2),
                    Int2ObjectMap.entry(3, ModTradeSets.DREAMER_LEVEL_3),
                    Int2ObjectMap.entry(4, ModTradeSets.DREAMER_LEVEL_4),
                    Int2ObjectMap.entry(5, ModTradeSets.DREAMER_LEVEL_5)
            ));

    private static VillagerProfession registerVillagerProfession(String name, String title, ResourceKey<PoiType> poi,
                                                                 SoundEvent sound, Int2ObjectMap<ResourceKey<TradeSet>> map){
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                new VillagerProfession(Component.literal(title), holder -> holder.is(poi), holder -> holder.is(poi),
                        ImmutableSet.of(), ImmutableSet.of(), sound, map));
    }

    public static void register(){
        NBPVanillaPlus.LOGGER.info("Registering ModVillagers for " + NBPVanillaPlus.MOD_ID);
    }
}
