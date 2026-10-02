package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.worldgen.biome.ModBiomes;
import net.nikibropol.nbpvanillaplus.worldgen.structures.ModStructures;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends FabricTagsProvider<Biome> {

    public static final TagKey<Biome> DREAM_GREEN_LOGS = createTag("dream_green_logs");
    public static final TagKey<Biome> DREAM_PURPLE_LOGS = createTag("dream_purple_logs");
    public static final TagKey<Biome> DREAM_BLUE_LOGS = createTag("dream_blue_logs");
    public static final TagKey<Biome> DREAM_LIGHT_BLUE_LOGS = createTag("dream_light_blue_logs");

    private static TagKey<Biome> createTag(String name){
        return  TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name));
    }
    public ModBiomeTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModStructures.biomeTag("green"))
                .add(ModBiomes.DREAM_GREEN);
        tag(ModStructures.biomeTag("purple"))
                .add(ModBiomes.DREAM_PURPLE);
        tag(ModStructures.biomeTag("blue"))
                .add(ModBiomes.DREAM_BLUE);
        tag(ModStructures.biomeTag("light_blue"))
                .add(ModBiomes.DREAM_LIGHT_BLUE);
    }
}
