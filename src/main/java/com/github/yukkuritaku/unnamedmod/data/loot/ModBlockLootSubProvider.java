package com.github.yukkuritaku.unnamedmod.data.loot;

import com.github.yukkuritaku.unnamedmod.init.ModBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootSubProvider extends BlockLootSubProvider {

    private static final Set<Item> EXPLOSION_RESISTANT = Set.of();

    protected ModBlockLootSubProvider() {
        super(EXPLOSION_RESISTANT, FeatureFlags.DEFAULT_FLAGS);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(DeferredHolder::value).collect(Collectors.toList());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.MOON_BLOCK.get());
        /*this.add(Blocks.AMETHYST_CLUSTER, block ->
                createSilkTouchDispatchTable(block,
                        LootItem.lootTableItem(ModItems.AMETHYST_DUST)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4, 8)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                                .otherwise(
                                        this.applyExplosionDecay(block,
                                                LootItem.lootTableItem(ModItems.AMETHYST_DUST)
                                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0f)))))


                ));*/
    }
}
