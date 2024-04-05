package com.github.yukkuritaku.unnamedmod.init;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.world.item.crafting.MoonTransformRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTypes {

    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, UnnamedMod.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<MoonTransformRecipe>> MOON_TRANSFORM = RECIPE_TYPES.register("moon_transform",
            () -> RecipeType.simple(new ResourceLocation(UnnamedMod.MOD_ID, "moon_transform")));
}
