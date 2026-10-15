package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.sound.ModAmbientSounds;
import net.nikibropol.nbpvanillaplus.sound.ModSounds;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {

    public ModSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static final int MOD_TRACK_WEIGHT = 5;

    @Override
    protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
        exporter.add(ModSounds.COBBLER_USE, SoundTypeBuilder.of(ModSounds.COBBLER_USE).subtitle("sounds.nbp-vanilla-plus.cobbler_use")
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "cobbler_use"))));

        exporter.add(ModSounds.ONLY_ONCE_MORE, SoundTypeBuilder.of(ModSounds.ONLY_ONCE_MORE.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "only_once_more")).stream(true)));
        exporter.add(ModSounds.DUSK_TO_DAWN, SoundTypeBuilder.of(ModSounds.DUSK_TO_DAWN.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dusk_to_dawn")).stream(true)));
        exporter.add(ModSounds.BROKEN_ETERNITY, SoundTypeBuilder.of(ModSounds.BROKEN_ETERNITY.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "broken_eternity")).stream(true)));
        exporter.add(ModSounds.SLEEPING_FOREST, SoundTypeBuilder.of(ModSounds.SLEEPING_FOREST.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "sleeping_forest")).stream(true)));
        exporter.add(ModSounds.DREAMS, SoundTypeBuilder.of(ModSounds.DREAMS.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "dreams")).stream(true)));
        exporter.add(ModSounds.INTO_SILENCE, SoundTypeBuilder.of(ModSounds.INTO_SILENCE.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "into_silence")).stream(true)));
        exporter.add(ModSounds.JUST_A_MEMORY, SoundTypeBuilder.of(ModSounds.JUST_A_MEMORY.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "just_a_memory")).stream(true)));

        ModAmbientSounds.playlists().forEach(playlist ->
                playlist(exporter, playlist.event(), playlist.vanilla(), playlist.files().toArray(String[]::new)));
    }

    private static void playlist(SoundExporter exporter, Holder.Reference<SoundEvent> event,
                                 List<String> vanillaEvents, String... files) {
        SoundTypeBuilder builder = SoundTypeBuilder.of(event.value())
                .subtitle(null);
        for (String file : files) {
            builder.sound(SoundTypeBuilder.RegistrationBuilder
                    .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, file))
                    .stream(true)
                    .weight(MOD_TRACK_WEIGHT));
        }
        for (String vanilla : vanillaEvents) {
            builder.sound(SoundTypeBuilder.RegistrationBuilder
                    .ofEvent(SoundEvent.createVariableRangeEvent(Identifier.withDefaultNamespace(vanilla))));
        }
        exporter.add(event, builder);
    }

    @Override
    public String getName() {
        return "NBP Vanilla Plus Sounds";
    }
}
