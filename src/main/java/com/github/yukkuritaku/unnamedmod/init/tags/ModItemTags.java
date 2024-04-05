package com.github.yukkuritaku.unnamedmod.init.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {

    public static final TagKey<Item> STORAGE_BLOCKS_MOON = forNeoForgeTag("storage_blocks/moon");


    private static TagKey<Item> forNeoForgeTag(String name) {
        return ItemTags.create(new ResourceLocation("forge", name));
    }
}
