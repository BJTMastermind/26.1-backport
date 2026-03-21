package me.bjtmastermind.backport_261;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.bjtmastermind.backport_261.block.ModBlocks;
import me.bjtmastermind.backport_261.particle.ModParticles;
import me.bjtmastermind.backport_261.sound_events.ModSoundEvents;
import me.bjtmastermind.backport_261.tags.ModTags;
import me.bjtmastermind.backport_261.util.ModLootTableModifiers;
import me.bjtmastermind.backport_261.villager.ModTrades;

public class Backport261 implements ModInitializer {
    public static final String MOD_ID = "backport_261";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.registerModBlocks();

        ModParticles.registerModParticles();
        ModSoundEvents.registerModSoundEvents();

        ModTags.registerModTags();

        ModTrades.registerModTrades();
        ModLootTableModifiers.modifyLootTables();
    }
}