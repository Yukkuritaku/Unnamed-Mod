package com.github.yukkuritaku.unnamedmod.init;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.world.item.crafting.MoonTransformRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, UnnamedMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<MoonTransformRecipe>> MOON_TRANSFORM = RECIPE_SERIALIZERS.register("moon_transform", MoonTransformRecipe.Serializer::new);
}
