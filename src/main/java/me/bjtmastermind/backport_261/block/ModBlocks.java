package me.bjtmastermind.backport_261.block;

import java.util.function.Function;

import me.bjtmastermind.backport_261.Backport261;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
    public static final Block GOLDEN_DANDELION = registerBlock(
        "golden_dandelion",
        properties -> new FlowerBlock(
            MobEffects.SATURATION,
            0.35F,
            properties
        ),
        Properties.of()
            .mapColor(MapColor.PLANT)
            .noCollision()
            .instabreak()
            .sound(SoundType.GRASS)
            .offsetType(OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY),
        true
    );

    public static final Block POTTED_GOLDEN_DANDELION = registerBlock(
        "potted_golden_dandelion",
        properties -> new FlowerPotBlock(
            GOLDEN_DANDELION,
            properties
        ),
        Properties.of()
            .instabreak()
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY),
        false
    );

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties properties, boolean hasBlockItem) {
        Identifier id = Identifier.fromNamespaceAndPath(Backport261.MOD_ID, name);

        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        Block block = function.apply(properties.setId(blockKey));

        if (hasBlockItem) {
            ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    public static void registerModBlocks() {
        Backport261.LOGGER.info("Registering Mod Blocks for " + Backport261.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.addAfter(Items.BEETROOT, GOLDEN_DANDELION);
        });
    }
}
