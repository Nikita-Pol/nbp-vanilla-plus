package net.nikibropol.nbpvanillaplus.block.entity.custom;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.nikibropol.nbpvanillaplus.block.custom.ReinforcedShulkerBoxBlock;
import net.nikibropol.nbpvanillaplus.block.entity.ModBlockEntities;
import net.nikibropol.nbpvanillaplus.menu.custom.ReinforcedShulkerBoxMenu;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class ReinforcedShulkerBoxBlockEntity extends BlockEntity implements Container, ExtendedMenuProvider<BlockPos> {

    public static final int SLOT_COUNT = 54;
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
    private final @Nullable DyeColor color;
    private float progress;
    private float progressOld;
    private @Nullable Component customName;

    public ReinforcedShulkerBoxBlockEntity(BlockPos worldPosition, BlockState blockState, @Nullable DyeColor color) {
        super(ModBlockEntities.REINFORCED_SHULKER_BOX_BE, worldPosition, blockState);
        this.color = color;
    }

    public @Nullable DyeColor getColor() {
        return color;
    }

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(inventory, slot, amount);
        if (!result.isEmpty()) setChanged();
        return result;
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(inventory, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) stack.setCount(getMaxStackSize());
        setChanged();
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        Block block = Block.byItem(stack.getItem());
        return !(block instanceof ShulkerBoxBlock) && !(block instanceof ReinforcedShulkerBoxBlock);
    }
    @Override
    public boolean stillValid(Player player) {
        if (level == null || level.getBlockEntity(worldPosition) != this) return false;
        return player.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clearContent() {
        inventory.clear();
    }

    public boolean isClosed() {
        return animationStatus == AnimationStatus.CLOSED;
    }

    public enum AnimationStatus { CLOSED, OPENING, OPENED, CLOSING }

    private int openCount;
    private AnimationStatus animationStatus = AnimationStatus.CLOSED;

    private static void doNeighborUpdates(Level level, BlockPos pos, BlockState state) {
        state.updateNeighbourShapes(level, pos, 3);
        level.updateNeighborsAt(pos, state.getBlock());
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ReinforcedShulkerBoxBlockEntity entity) {
        entity.progressOld = entity.progress;
        switch (entity.animationStatus) {
            case CLOSED -> entity.progress = 0.0F;
            case OPENING -> {
                entity.progress += 0.1F;
                if (entity.progress >= 1.0F) {
                    entity.animationStatus = AnimationStatus.OPENED;
                    entity.progress = 1.0F;
                }
                entity.moveCollidedEntities(level, pos, state);
            }
            case OPENED -> entity.progress = 1.0F;
            case CLOSING -> {
                entity.progress -= 0.1F;
                if (entity.progressOld == 1.0F) {
                    doNeighborUpdates(level, pos, state);
                }
                if (entity.progress <= 0.0F) {
                    entity.animationStatus = AnimationStatus.CLOSED;
                    entity.progress = 0.0F;
                    doNeighborUpdates(level, pos, state);
                }
            }
        }
    }

    @Override
    public void startOpen(ContainerUser containerUser) {
        if (!remove && !containerUser.getLivingEntity().isSpectator()) {
            if (openCount < 0) openCount = 0;
            openCount++;
            level.blockEvent(worldPosition, getBlockState().getBlock(), 1, openCount);
            //level.getChunkSource().getLightEngine().checkBlock(worldPosition);
            if (openCount == 1) {
                level.gameEvent(containerUser.getLivingEntity(), GameEvent.CONTAINER_OPEN, worldPosition);
                level.playSound(null, worldPosition, SoundEvents.SHULKER_BOX_OPEN,
                        SoundSource.BLOCKS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    @Override
    public void stopOpen(ContainerUser containerUser) {
        if (!remove && !containerUser.getLivingEntity().isSpectator()) {
            openCount--;
            level.blockEvent(worldPosition, getBlockState().getBlock(), 1, openCount);
            //level.getChunkSource().getLightEngine().checkBlock(worldPosition);
            if (openCount <= 0) {
                level.gameEvent(containerUser.getLivingEntity(), GameEvent.CONTAINER_CLOSE, worldPosition);
                level.playSound(null, worldPosition, SoundEvents.SHULKER_BOX_CLOSE,
                        SoundSource.BLOCKS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
            }
        }
    }

    @Override
    public boolean triggerEvent(int id, int data) {
        if (id == 1) {
            openCount = data;
            if (data == 0) animationStatus = AnimationStatus.CLOSING;
            if (data == 1) animationStatus = AnimationStatus.OPENING;
            return true;
        }
        return super.triggerEvent(id, data);
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {

    }
    public void setCustomName(@Nullable Component name) {
        this.customName = name;
    }

    @Override
    public Component getDisplayName() {
        return customName != null ? customName : Component.translatable("block.nbp-vanilla-plus.reinforced_shulker_box");
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.inventory));
        if (customName != null) {
            components.set(DataComponents.CUSTOM_NAME, customName);
        }
    }

    @Override
    protected void applyImplicitComponents(DataComponentGetter components) {
        super.applyImplicitComponents(components);
        ItemContainerContents contents = components.get(DataComponents.CONTAINER);
        if (contents != null) {
            contents.copyInto(this.inventory);
        }
        this.customName = components.get(DataComponents.CUSTOM_NAME);
    }
    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.inventory);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.inventory);
    }

    public AABB getBoundingBox(BlockState state) {
        Vec3 bottomCenter = new Vec3(0.5, 0.0, 0.5);
        return Shulker.getProgressAabb(1.0F, state.getValue(ReinforcedShulkerBoxBlock.FACING), 0.5F * getProgress(1.0F), bottomCenter);
    }

    private void moveCollidedEntities(final Level level, final BlockPos pos, final BlockState state) {
        if (state.getBlock() instanceof ReinforcedShulkerBoxBlock) {
            Direction direction = state.getValue(ShulkerBoxBlock.FACING);
            AABB aabb = Shulker.getProgressDeltaAabb(1.0F, direction, this.progressOld, this.progress, Vec3.atBottomCenterOf(pos));
            List<Entity> entities = level.getEntities(null, aabb);
            if (!entities.isEmpty()) {
                for (Entity entity : entities) {
                    if (entity.getPistonPushReaction() != PushReaction.IGNORE) {
                        entity.move(
                                MoverType.SHULKER_BOX,
                                new Vec3(
                                        (aabb.getXsize() + 0.01) * direction.getStepX(), (aabb.getYsize() + 0.01) * direction.getStepY(), (aabb.getZsize() + 0.01) * direction.getStepZ()
                                )
                        );
                    }
                }
            }
        }
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ReinforcedShulkerBoxMenu(containerId, inventory, this);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return this.worldPosition;
    }

    public float getProgress(final float a) {
        return Mth.lerp(a, this.progressOld, this.progress);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }


}