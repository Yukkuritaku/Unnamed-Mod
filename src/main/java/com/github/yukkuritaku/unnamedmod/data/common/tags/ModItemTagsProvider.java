package com.github.yukkuritaku.unnamedmod.data.common.tags;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModItems;
import com.github.yukkuritaku.unnamedmod.init.tags.ModBlockTags;
import com.github.yukkuritaku.unnamedmod.init.tags.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags,

                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, UnnamedMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // neoforge tags
        // for other modders
        copy(ModBlockTags.STORAGE_BLOCKS_MOON, ModItemTags.STORAGE_BLOCKS_MOON);
        // vanilla
        this.tag(ItemTags.BEACON_PAYMENT_ITEMS).add(ModItems.MOON_INGOT.get());

    }
}
