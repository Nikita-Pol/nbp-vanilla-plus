package net.nikibropol.nbpvanillaplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.CreakingHeartBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class EchoCreakingHeartBlock extends CreakingHeartBlock {
    public static final MapCodec<EchoCreakingHeartBlock> ECHO_CODEC = simpleCodec(EchoCreakingHeartBlock::new);

    public EchoCreakingHeartBlock(final BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    @SuppressWarnings("unchecked")
    public MapCodec<CreakingHeartBlock> codec() {
        return (MapCodec<CreakingHeartBlock>) (MapCodec<?>) ECHO_CODEC;
    }
}
