package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikibropol.nbpvanillaplus.block.ModBlocks;
import net.nikibropol.nbpvanillaplus.block.custom.StrangeBeetrootCropBlock;
import net.nikibropol.nbpvanillaplus.item.ModItems;
import org.lwjgl.system.macosx.MacOSXLibraryDL;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {

    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {

        LootTableEvents.MODIFY.register((resourceKey, tableBuilder, source, registries) -> {
        if (source.isBuiltin() && resourceKey.identifier().equals(
                Identifier.withDefaultNamespace("gameplay/entities/sniffer_seeds"))) {
            tableBuilder.withPool(LootPool.lootPool()
                    .add(LootItem.lootTableItem(ModItems.STRANGE_BEETROOT_SEEDS))
                    .setRolls(ConstantValue.exactly(1))
            );
        }
    });
        dropSelf(ModBlocks.ECHO_BLOCK);
        dropSelf(ModBlocks.ECHO_MAGMA);
        dropSelf(ModBlocks.REINFORCED_OBSIDIAN);

        this.add(ModBlocks.STRANGE_BEETROOT_CROP, this.createCropDrops(ModBlocks.STRANGE_BEETROOT_CROP, ModItems.STRANGE_BEETROOT,
                ModItems.STRANGE_BEETROOT_SEEDS, LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRANGE_BEETROOT_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrangeBeetrootCropBlock.AGE, StrangeBeetrootCropBlock.MAX_AGE))));
    }

    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops){
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
