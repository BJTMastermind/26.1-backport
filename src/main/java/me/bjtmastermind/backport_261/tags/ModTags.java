package me.bjtmastermind.backport_261.tags;

import me.bjtmastermind.backport_261.Backport261;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class ModTags {
    public static final TagKey<EntityType<?>> CANNOT_BE_AGE_LOCKED = registerTag("cannot_be_age_locked");

    private static TagKey<EntityType<?>> registerTag(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(Backport261.MOD_ID, name);
        return TagKey.create(Registries.ENTITY_TYPE, id);
    }

    public static void registerModTags() {
        Backport261.LOGGER.info("Registering Mod Tags for " + Backport261.MOD_ID);
    }
}
