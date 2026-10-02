package net.nikibropol.nbpvanillaplus.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;

public class ReinforceShulkerBoxBlockEntityRenderState extends BlockEntityRenderState {
    public Direction direction = Direction.UP;
    public DyeColor color;
    public float progress;
}