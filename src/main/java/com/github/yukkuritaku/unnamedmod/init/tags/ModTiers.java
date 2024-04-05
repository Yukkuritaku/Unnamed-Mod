package com.github.yukkuritaku.unnamedmod.init.tags;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.TierSortingRegistry;

import java.util.List;

public class ModTiers {

    public static final Tier MOON = TierSortingRegistry.registerTier(
            new SimpleTier(2, 763, 7.0f, 2.5f, 12,
                    ModBlockTags.NEEDS_MOON_TOOL, () -> Ingredient.of(ModItems.MOON_INGOT)),
            create("moon"),
            List.of(Tiers.IRON),
            List.of(Tiers.DIAMOND));

    private static ResourceLocation create(String name){
        return new ResourceLocation(UnnamedMod.MOD_ID, name);
    }

}
