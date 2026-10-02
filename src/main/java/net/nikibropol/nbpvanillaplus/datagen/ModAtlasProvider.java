package net.nikibropol.nbpvanillaplus.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

import java.util.concurrent.CompletableFuture;

public class ModAtlasProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public ModAtlasProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "atlases");
    }

    @Override
    public CompletableFuture<?> run(final CachedOutput cache) {
        JsonArray sources = new JsonArray();
        JsonObject nullEntry = new JsonObject();
        nullEntry.addProperty("type", "single");
        nullEntry.addProperty("resource", NBPVanillaPlus.MOD_ID + ":entity/shulker/reinforced_shulker");
        sources.add(nullEntry);
        JsonObject root = new JsonObject();
        root.add("sources", sources);
        for (DyeColor color : DyeColor.values()) {
            JsonObject entry = new JsonObject();
            entry.addProperty("type", "single");
            entry.addProperty("resource", NBPVanillaPlus.MOD_ID + ":entity/shulker/reinforced_shulker_" + color.getSerializedName());
            sources.add(entry);

        }

        return DataProvider.saveStable(cache, root, this.pathProvider.file(Identifier.withDefaultNamespace("shulker_boxes"), "json"));
    }

    @Override
    public String getName() {
        return "NBP Vanilla Plus Atlas Provider";
    }
}