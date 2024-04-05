package com.github.yukkuritaku.unnamedmod.data.common.glm;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModItems;
import com.github.yukkuritaku.unnamedmod.loot.AddItemLootModifier;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, UnnamedMod.MOD_ID);
    }

    @Override
    protected void start() {
        this.add("amethyst_dust_from_amethyst_cluster", new AddItemLootModifier(new LootItemCondition[]{
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.AMETHYST_CLUSTER).build(),
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)).build(),
        }, UniformGenerator.between(4.0f, 8.0f), ModItems.AMETHYST_DUST));
    }
}
