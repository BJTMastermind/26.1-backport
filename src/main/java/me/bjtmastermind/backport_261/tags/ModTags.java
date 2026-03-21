package me.bjtmastermind.backport_261.tags;

import me.bjtmastermind.backport_261.Backport261;

public class ModTags {

    public static void registerModTags() {
        Backport261.LOGGER.info("Registering Mod Tags for " + Backport261.MOD_ID);

        ModEntityTypeTags.registerEntityTypeTags();
        ModItemTags.registerItemTags();
    }
}
