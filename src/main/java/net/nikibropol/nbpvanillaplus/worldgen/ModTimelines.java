package net.nikibropol.nbpvanillaplus.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.modifier.FloatModifier;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.timeline.Timeline;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

public class ModTimelines {
    public static final ResourceKey<Timeline> NIGHT_FOG = ResourceKey.create(Registries.TIMELINE,
            Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, "night_fog"));

    public static final float NIGHT_FOG_MULTIPLIER = 0.3F;

    public static void bootstrap(BootstrapContext<Timeline> context) {
        var clocks = context.lookup(Registries.WORLD_CLOCK);
        Holder.Reference<WorldClock> overworldClock = clocks.getOrThrow(WorldClocks.OVERWORLD);

        context.register(NIGHT_FOG,
                Timeline.builder(overworldClock)
                        .setPeriodTicks(24000)
                        .addModifierTrack(
                                EnvironmentAttributes.FOG_END_DISTANCE,
                                FloatModifier.MULTIPLY,
                                track -> track
                                        .addKeyframe(133, 1.0F)
                                        .addKeyframe(11867, 1.0F)
                                        .addKeyframe(13670, NIGHT_FOG_MULTIPLIER)
                                        .addKeyframe(22330, NIGHT_FOG_MULTIPLIER))
                        .build());
    }
}
