package com.github.yukkuritaku.unnamedmod.data.common.tags;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModBlocks;
import com.github.yukkuritaku.unnamedmod.init.tags.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, UnnamedMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // neoforge
        // for other modders
        // noinspection unchecked <- addTags is unchecked method
        this.tag(Tags.Blocks.STORAGE_BLOCKS).addTags(ModBlockTags.STORAGE_BLOCKS_MOON);
        this.tag(ModBlockTags.STORAGE_BLOCKS_MOON).add(ModBlocks.MOON_BLOCK.get());

        // vanilla
        this.tag(BlockTags.BEACON_BASE_BLOCKS).add(ModBlocks.MOON_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.MOON_BLOCK.get()//,
        );
        this.tag(ModBlockTags.NEEDS_MOON_TOOL).add(
                ModBlocks.MOON_BLOCK.get()//,
        );
    }
}
