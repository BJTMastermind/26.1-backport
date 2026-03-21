package me.bjtmastermind.backport_261.tags;

import me.bjtmastermind.backport_261.Backport261;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> METAL_NUGGETS = registerTag("metal_nuggets");

    private static TagKey<Item> registerTag(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Backport261.MOD_ID, name);
        return TagKey.create(Registries.ITEM, id);
    }

    public static void registerItemTags() {}
}
