package net.nikibropol.nbpvanillaplus.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record DeathRecord(GlobalPos pos, String playerName) {
    public static final Codec<DeathRecord> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            GlobalPos.CODEC.fieldOf("pos").forGetter(DeathRecord::pos),
            Codec.STRING.fieldOf("player_name").forGetter(DeathRecord::playerName)
    ).apply(instance, DeathRecord::new));

    public static final StreamCodec<ByteBuf, DeathRecord> STREAM_CODEC = StreamCodec.composite(
            GlobalPos.STREAM_CODEC, DeathRecord::pos,
            ByteBufCodecs.STRING_UTF8, DeathRecord::playerName,
            DeathRecord::new
    );
}