package net.nikibropol.nbpvanillaplus.worldgen.biome;

import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;

public record FogProfile(float start, float end) {
    public static final FogProfile LIGHT = new FogProfile(0.0f, 96.0f);
    public static final FogProfile PALE  = new FogProfile(0.0f, 48.0f);
    public static final FogProfile THICK = new FogProfile(0.0f, 24.0f);

    public EnvironmentAttributeMap.Builder apply(EnvironmentAttributeMap.Builder b) {
        return b.set(EnvironmentAttributes.FOG_START_DISTANCE, start)
                .set(EnvironmentAttributes.FOG_END_DISTANCE, end);
    }
}