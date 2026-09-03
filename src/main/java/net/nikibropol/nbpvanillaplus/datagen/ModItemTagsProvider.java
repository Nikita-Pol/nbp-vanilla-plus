package net.nikibropol.nbpvanillaplus.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.nikibropol.nbpvanillaplus.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ItemTags.SWORDS).add(ModItems.getRK(ModItems.ECHO_SWORD));
        tag(ItemTags.PICKAXES).add(ModItems.getRK(ModItems.ECHO_PICKAXE));
        tag(ItemTags.AXES).add(ModItems.getRK(ModItems.ECHO_AXE));
        tag(ItemTags.SHOVELS).add(ModItems.getRK(ModItems.ECHO_SHOVEL));
        tag(ItemTags.HOES).add(ModItems.getRK(ModItems.ECHO_HOE));
        tag(ItemTags.SPEARS).add(ModItems.getRK(ModItems.ECHO_SPEAR));

        tag(ItemTags.HEAD_ARMOR).add(ModItems.getRK(ModItems.ECHO_HELMET));
        tag(ItemTags.CHEST_ARMOR).add(ModItems.getRK(ModItems.ECHO_CHESTPLATE));
        tag(ItemTags.LEG_ARMOR).add(ModItems.getRK(ModItems.ECHO_LEGGINGS));
        tag(ItemTags.FOOT_ARMOR).add(ModItems.getRK(ModItems.ECHO_BOOTS));

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.getRK(ModItems.ECHO_HORSE_ARMOR))
                .add(ModItems.getRK(ModItems.ECHO_NAUTILUS_ARMOR));
    }
}
