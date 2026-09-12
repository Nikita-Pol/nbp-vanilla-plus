package net.nikibropol.nbpvanillaplus.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.nikibropol.nbpvanillaplus.NBPVanillaPlus;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<DeathRecord> LAST_DEATH_COORDINATES = register("last_death_coordinates",
            builder -> builder.persistent(DeathRecord.CODEC).networkSynchronized(DeathRecord.STREAM_CODEC));
    public static final DataComponentType<Boolean> IS_ACTUAL = register("is_actual",
            builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    private static <T>DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator){
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, Identifier.fromNamespaceAndPath(NBPVanillaPlus.MOD_ID, name),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponents(){
        NBPVanillaPlus.LOGGER.info("Registering Data Components for " + NBPVanillaPlus.MOD_ID);
    }
}
