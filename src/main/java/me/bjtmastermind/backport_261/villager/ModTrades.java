package me.bjtmastermind.backport_261.villager;

import java.util.ArrayList;
import java.util.List;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import me.bjtmastermind.backport_261.Backport261;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.entity.npc.villager.VillagerTrades.ItemListing;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

public class ModTrades {
    private static final List<Item> REMOVE_LIBRARIAN_TRADED_ITEMS = new ArrayList<>() {{
        add(Items.NAME_TAG);
    }};

    private static void removeTradesFromProfession(ResourceKey<VillagerProfession> profession, int villagerLevel, List<Item> itemsToRemove) {
        Int2ObjectMap<ItemListing[]> trades = VillagerTrades.TRADES.get(profession);
        ItemListing[] levelTrades = trades.get(villagerLevel);

        List<ItemListing> filtered = new ArrayList<>();
        for (ItemListing trade : levelTrades) {
            MerchantOffer offer = trade.getOffer(null, null, RandomSource.create());

            if (!itemsToRemove.contains(offer.getResult().getItem())) {
                filtered.add(trade);
            }
        }

        trades.put(villagerLevel, filtered.toArray(new ItemListing[0]));
    }

    public static void registerModTrades() {
        Backport261.LOGGER.info("Registering Mod Trades for " + Backport261.MOD_ID);

        removeTradesFromProfession(VillagerProfession.LIBRARIAN, 5, REMOVE_LIBRARIAN_TRADED_ITEMS);

        // Villager Trades
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 5, factories -> {
            factories.add((world, entity, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 3),
                new ItemStack(Items.RED_CANDLE, 1), 12, 5, 0.05f
            ));

            factories.add((world, entity, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 3),
                new ItemStack(Items.YELLOW_CANDLE, 1), 12, 5, 0.05f
            ));
        });

        // Wandering Trader Trades
        TradeOfferHelper.registerWanderingTraderOffers(factories -> {
            factories.addAll(
                Identifier.fromNamespaceAndPath(Backport261.MOD_ID, "emerald_for_name_tag"),
                (world, entity, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(Items.NAME_TAG, 1), 5, 1, 0.05f
                )
            );
        });
    }
}
