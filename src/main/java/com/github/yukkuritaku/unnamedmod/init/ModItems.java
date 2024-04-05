package com.github.yukkuritaku.unnamedmod.init;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(UnnamedMod.MOD_ID);

    public static final DeferredItem<Item> AMETHYST_DUST = ITEMS.registerSimpleItem("amethyst_dust");

    public static final DeferredItem<Item> MOON_SHARD = ITEMS.registerSimpleItem("moon_shard",
            new Item.Properties().rarity(Rarity.UNCOMMON));

    public static final DeferredItem<Item> MOON_INGOT = ITEMS.registerSimpleItem("moon_ingot",
            new Item.Properties().rarity(Rarity.UNCOMMON));

    public static final DeferredItem<BlockItem> MOON_BLOCK = ITEMS.registerSimpleBlockItem(ModBlocks.MOON_BLOCK);

    public static final DeferredItem<Item> FRIED_TOFU = ITEMS.registerSimpleItem("fried_tofu",
            new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationMod(2.5f)
                    .meat()
                    .fast()
                    .build())
    );

}
