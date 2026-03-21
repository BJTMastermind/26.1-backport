package me.bjtmastermind.backport_261.datagen;

import java.util.concurrent.CompletableFuture;

import me.bjtmastermind.backport_261.tags.ModEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public ModEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {
        valueLookupBuilder(ModEntityTypeTags.CANNOT_BE_AGE_LOCKED)
            .setReplace(false)
            .add(EntityType.ZOMBIE_HORSE)
            .add(EntityType.SKELETON_HORSE)
            .add(EntityType.VILLAGER);
    }
}
