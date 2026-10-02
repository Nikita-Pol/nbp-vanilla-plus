package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;
import net.nikibropol.nbpvanillaplus.sound.ModSounds;

import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {

    public ModSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

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

        exporter.add(ModSounds.THEY_STILL_WAIT_YOU, SoundTypeBuilder.of(ModSounds.THEY_STILL_WAIT_YOU.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "they_still_wait_you")).stream(true)));

        exporter.add(ModSounds.BREAK_TIME, SoundTypeBuilder.of(ModSounds.BREAK_TIME.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "break_time")).stream(true)));
        exporter.add(ModSounds.LETS_BE_HAPPY, SoundTypeBuilder.of(ModSounds.LETS_BE_HAPPY.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "lets_be_happy")).stream(true)));
        exporter.add(ModSounds.AFTER_ALL, SoundTypeBuilder.of(ModSounds.AFTER_ALL.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "after_all")).stream(true)));
        exporter.add(ModSounds.BREATHE_BLUR, SoundTypeBuilder.of(ModSounds.BREATHE_BLUR.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "breathe_blur")).stream(true)));
        exporter.add(ModSounds.EVENING, SoundTypeBuilder.of(ModSounds.EVENING.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "evening")).stream(true)));
        exporter.add(ModSounds.INFORMATED, SoundTypeBuilder.of(ModSounds.INFORMATED.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "informated")).stream(true)));
        exporter.add(ModSounds.MEMORIES, SoundTypeBuilder.of(ModSounds.MEMORIES.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "memories")).stream(true)));
        exporter.add(ModSounds.NEBULA, SoundTypeBuilder.of(ModSounds.NEBULA.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "nebula")).stream(true)));
        exporter.add(ModSounds.RESTART, SoundTypeBuilder.of(ModSounds.RESTART.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "restart")).stream(true)));
        exporter.add(ModSounds.STILL_THERE, SoundTypeBuilder.of(ModSounds.STILL_THERE.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "still_there")).stream(true)));
        exporter.add(ModSounds.TIMES_UP, SoundTypeBuilder.of(ModSounds.TIMES_UP.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "times_up")).stream(true)));
        exporter.add(ModSounds.AMBIENT_BROKEN_ETERNITY, SoundTypeBuilder.of(ModSounds.AMBIENT_BROKEN_ETERNITY.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "ambient_broken_eternity")).stream(true)));
        exporter.add(ModSounds.NAMELESS_ECHOES, SoundTypeBuilder.of(ModSounds.NAMELESS_ECHOES.value())
                .sound(SoundTypeBuilder.RegistrationBuilder
                        .ofFile(Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "nameless_echoes")).stream(true)));
    }

    @Override
    public String getName() {
        return "NBP Vanilla Plus Sounds";
    }
}
