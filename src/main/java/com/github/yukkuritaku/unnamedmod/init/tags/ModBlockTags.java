package com.github.yukkuritaku.unnamedmod.init.tags;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {

    // neoforge
    // for other modders
    public static final TagKey<Block> STORAGE_BLOCKS_MOON = forNeoForgeTag("storage_blocks/moon");

    // vanilla
    public static final TagKey<Block> NEEDS_MOON_TOOL =
            BlockTags.create(new ResourceLocation(UnnamedMod.MOD_ID, "needs_moon_tool"));


    private static TagKey<Block> forNeoForgeTag(String name) {
        return BlockTags.create(new ResourceLocation("forge", name));
    }
}
