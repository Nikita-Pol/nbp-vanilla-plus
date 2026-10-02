package net.nikibropol.nbpvanillaplus.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.nikibropol.nbpvanillaplus.block.entity.ModBlockEntities;
import net.nikibropol.nbpvanillaplus.block.entity.custom.ReinforcedShulkerBoxBlockEntity;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ReinforcedShulkerBoxBlock extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = DirectionalBlock.FACING;
    public static final Map<Direction, VoxelShape> SHAPES_OPEN_SUPPORT = Shapes.rotateAll(Block.boxZ(16.0, 0.0, 1.0));
    private static final MapCodec<ReinforcedShulkerBoxBlock> CODEC =
            com.mojang.serialization.codecs.RecordCodecBuilder.mapCodec(instance -> instance.group(
                    DyeColor.CODEC.optionalFieldOf("color").forGetter(b -> java.util.Optional.ofNullable(b.color)),
                    propertiesCodec()
            ).apply(instance, (color, properties) -> new ReinforcedShulkerBoxBlock(color.orElse(null), properties)));

    private final @Nullable DyeColor color;
    public static final Identifier CONTENTS = Identifier.withDefaultNamespace("contents");

    public ReinforcedShulkerBoxBlock(@Nullable DyeColor color, Properties properties) {
        super(properties);
        this.color = color;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    public @Nullable DyeColor getColor() {
        return color;
    }

    @Override
    public MapCodec<ReinforcedShulkerBoxBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return new ReinforcedShulkerBoxBlockEntity(worldPosition, blockState, color);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof ReinforcedShulkerBoxBlockEntity be && !level.isClientSide()) {
            ItemStack itemStack = new ItemStack(state.getBlock());
            itemStack.applyComponents(blockEntity.collectComponents());
            ItemEntity entity = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, itemStack);
            entity.setDefaultPickUpDelay();
            level.addFreshEntity(entity);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }
    @Override
    protected List<ItemStack> getDrops(final BlockState state, LootParams.Builder params) {
        BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
        if (blockEntity instanceof ShulkerBoxBlockEntity shulkerBoxBlockEntity) {
            params = params.withDynamicDrop(CONTENTS, output -> {
                for (int i = 0; i < shulkerBoxBlockEntity.getContainerSize(); i++) {
                    output.accept(shulkerBoxBlockEntity.getItem(i));
                }
            });
        }

        return super.getDrops(state, params);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof ReinforcedShulkerBoxBlockEntity be) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(be);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.REINFORCED_SHULKER_BOX_BE, ReinforcedShulkerBoxBlockEntity::tick);
    }

    @Override
    protected boolean propagatesSkylightDown(final BlockState state) {
        return false;
    }
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return level.getBlockEntity(pos) instanceof ReinforcedShulkerBoxBlockEntity be
                ? Shapes.create(be.getBoundingBox(state))
                : Shapes.block();
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return level.getBlockEntity(pos) instanceof ReinforcedShulkerBoxBlockEntity be && !be.isClosed()
                ? SHAPES_OPEN_SUPPORT.get(state.getValue(FACING).getOpposite())
                : Shapes.block();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace());
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}