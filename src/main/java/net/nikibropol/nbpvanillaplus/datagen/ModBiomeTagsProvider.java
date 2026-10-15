package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.worldgen.biome.ModBiomes;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends FabricTagsProvider<Biome> {

    public static final Map<String, TagKey<Biome>> HOUSE_TAGS = new LinkedHashMap<>();
    public static final Map<String, TagKey<Biome>> LAKE_TAGS = new LinkedHashMap<>();
    public static final Map<String, ResourceKey<Biome>> DREAM_BIOMES = new LinkedHashMap<>();
    public static final Map<String, TagKey<Biome>> VILLAGE_BIOME_TAGS = new LinkedHashMap<>();
    public static final Map<String, TagKey<Biome>> SCULKED_HOUSE_TAGS = new LinkedHashMap<>();
    public static final TagKey<Biome> HAS_PALE_GARDEN_TOWER = createStructureTag("pale_garden_tower");
    public static final TagKey<Biome> HAS_PALE_GARDEN_HOUSE = createStructureTag("pale_garden_house");

    static {
        DREAM_BIOMES.put("green", ModBiomes.DREAM_GREEN);
        DREAM_BIOMES.put("purple", ModBiomes.DREAM_PURPLE);
        DREAM_BIOMES.put("blue", ModBiomes.DREAM_BLUE);
        DREAM_BIOMES.put("light_blue", ModBiomes.DREAM_LIGHT_BLUE);

        for (String color : DREAM_BIOMES.keySet()) {
            HOUSE_TAGS.put(color, createStructureTag("dream_" + color + "_house"));
            LAKE_TAGS.put(color, createStructureTag("dream_" + color + "_lake"));
        }
        VILLAGE_BIOME_TAGS.put("plains", BiomeTags.HAS_VILLAGE_PLAINS);
        VILLAGE_BIOME_TAGS.put("desert", BiomeTags.HAS_VILLAGE_DESERT);
        VILLAGE_BIOME_TAGS.put("savanna", BiomeTags.HAS_VILLAGE_SAVANNA);
        VILLAGE_BIOME_TAGS.put("snowy", BiomeTags.HAS_VILLAGE_SNOWY);
        VILLAGE_BIOME_TAGS.put("taiga", BiomeTags.HAS_VILLAGE_TAIGA);

        for (String biomeType : VILLAGE_BIOME_TAGS.keySet()) {
            SCULKED_HOUSE_TAGS.put(biomeType, createStructureTag(biomeType + "_sculked_house"));
        }
    }

    private static TagKey<Biome> createTag(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
    }

    private static TagKey<Biome> createStructureTag(String structureName) {
        return createTag("has_structure/" + structureName);
    }

    public ModBiomeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        for (Map.Entry<String, ResourceKey<Biome>> entry : DREAM_BIOMES.entrySet()) {
            String color = entry.getKey();
            tag(HOUSE_TAGS.get(color)).add(entry.getValue());
            tag(LAKE_TAGS.get(color)).add(entry.getValue());
        }
        for (Map.Entry<String, TagKey<Biome>> entry : VILLAGE_BIOME_TAGS.entrySet()) {
            tag(SCULKED_HOUSE_TAGS.get(entry.getKey())).forceAddTag(entry.getValue());
        }
        tag(HAS_PALE_GARDEN_TOWER).add(Biomes.PALE_GARDEN);
        tag(HAS_PALE_GARDEN_HOUSE).add(Biomes.PALE_GARDEN);

    }
}