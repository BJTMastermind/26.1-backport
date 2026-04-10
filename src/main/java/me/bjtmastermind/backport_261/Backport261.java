package me.bjtmastermind.backport_261;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.datafixers.schemas.Schema;

import me.bjtmastermind.easy_data_fix.api.DataFixerAPI;
import me.bjtmastermind.easy_data_fix.api.DataFixerRegistry;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.util.datafix.fixes.BlockRenameFix;
import net.minecraft.util.datafix.fixes.ItemRenameFix;
import net.minecraft.util.datafix.schemas.V4771;

public class Backport261 implements PreLaunchEntrypoint {
    public static final String MOD_ID = "backport_261";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onPreLaunch() {
        DataFixerRegistry.addDataFix("Convert to vanilla", builder -> {
            Schema schema = builder.addSchema(4773, V4771::new);
            builder.addFixer(BlockRenameFix.create(schema, "Rename block", DataFixerAPI.createRenamer(MOD_ID+":golden_dandelion", "minecraft:golden_dandelion")));
            builder.addFixer(ItemRenameFix.create(schema, "Rename item", DataFixerAPI.createRenamer(MOD_ID+":golden_dandelion", "minecraft:golden_dandelion")));
        });
    }
}