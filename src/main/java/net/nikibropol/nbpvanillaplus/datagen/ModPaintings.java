package net.nikibropol.nbpvanillaplus.datagen;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

import java.util.Optional;

public class ModPaintings {

    public static final ResourceKey<PaintingVariant> BROKEN_ETERNITY_KEY = create("broken_eternity");
    public static final ResourceKey<PaintingVariant> DUSK_TO_DAWN_KEY = create("dusk_to_dawn");
    public static final ResourceKey<PaintingVariant> NAMELESS_ECHOES_KEY = create("nameless_echoes");
    public static final ResourceKey<PaintingVariant> AMBIENTS_KEY = create("ambients");

    public static void bootstrap(BootstrapContext<PaintingVariant> context){
        register(context, BROKEN_ETERNITY_KEY, 2,2, true);
        register(context, DUSK_TO_DAWN_KEY, 2,2, true);
        register(context, NAMELESS_ECHOES_KEY, 2,2, true);
        register(context, AMBIENTS_KEY, 4,4, true);
    }

    private static ResourceKey<PaintingVariant> create(final String id){
        return ResourceKey.create(Registries.PAINTING_VARIANT, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, id));
    }

    private static void  register(final BootstrapContext<PaintingVariant> context, final ResourceKey<PaintingVariant> key,
                                  final int width, final int height, final boolean hasAuthor){
        context.register(key, new PaintingVariant(width, height, key.identifier(),
                Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)),
                hasAuthor ? Optional.of(Component.translatable(key.identifier()
                        .toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY)) : Optional.empty()));
    }
}
