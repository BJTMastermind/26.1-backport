package me.bjtmastermind.backport_261;

import me.bjtmastermind.backport_261.datagen.ModEntityTypeTagProvider;
import me.bjtmastermind.backport_261.datagen.ModItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class Backport261DataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModEntityTypeTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
    }
}
