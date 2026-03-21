package me.bjtmastermind.backport_261.datagen;

import java.util.concurrent.CompletableFuture;

import me.bjtmastermind.backport_261.block.ModBlocks;
import me.bjtmastermind.backport_261.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {
        valueLookupBuilder(ModItemTags.METAL_NUGGETS)
            .setReplace(false)
            .add(Items.COPPER_NUGGET)
            .add(Items.IRON_NUGGET)
            .add(Items.GOLD_NUGGET);

        valueLookupBuilder(ItemTags.PIGLIN_LOVED)
            .setReplace(false)
            .add(ModBlocks.GOLDEN_DANDELION.asItem());

        valueLookupBuilder(ItemTags.SMALL_FLOWERS)
            .setReplace(false)
            .add(ModBlocks.GOLDEN_DANDELION.asItem());
    }
}