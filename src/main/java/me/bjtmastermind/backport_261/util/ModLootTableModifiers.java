package me.bjtmastermind.backport_261.util;

import java.util.List;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTableModifiers {
    private static final List<ResourceKey<LootTable>> LOOT_TABLES = List.of(
        BuiltInLootTables.ANCIENT_CITY,
        BuiltInLootTables.WOODLAND_MANSION
    );

    public static void modifyLootTables() {
        LootTableEvents.MODIFY_DROPS.register((entry, context, stacks) -> {
            for (ResourceKey<LootTable> lootTable : LOOT_TABLES) {
                if (entry.is(lootTable)) {
                    stacks.removeIf(stack -> stack.is(Items.NAME_TAG));
                }
            }
        });
    }
}
