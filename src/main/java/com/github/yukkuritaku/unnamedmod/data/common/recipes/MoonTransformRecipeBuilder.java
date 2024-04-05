package com.github.yukkuritaku.unnamedmod.data.common.recipes;

import com.github.yukkuritaku.unnamedmod.world.item.crafting.MoonTransformRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;

public class MoonTransformRecipeBuilder {

    private final Ingredient transformIngredient;
    private final int transformTime;
    private final ItemStack result;
    private final RecipeCategory category;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public MoonTransformRecipeBuilder(Ingredient transformIngredient, int transformTime, ItemStack result, RecipeCategory category){
        this.transformIngredient = transformIngredient;
        this.transformTime = transformTime;
        this.result = result;
        this.category = category;
    }

    public static MoonTransformRecipeBuilder moonTransform(Ingredient transformIngredient, int transformTime, ItemStack result, RecipeCategory category){
        return new MoonTransformRecipeBuilder(transformIngredient, transformTime, result, category);
    }

    public MoonTransformRecipeBuilder unlocks(String key, Criterion<?> criterion) {
        this.criteria.put(key, criterion);
        return this;
    }


    public void save(RecipeOutput recipeOutput, String recipeId) {
        this.save(recipeOutput, new ResourceLocation(recipeId));
    }

    public void save(RecipeOutput recipeOutput, ResourceLocation recipeId) {
        this.ensureValid(recipeId);
        Advancement.Builder advancement = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId))
                .rewards(AdvancementRewards.Builder.recipe(recipeId))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement::addCriterion);
        MoonTransformRecipe recipe = new MoonTransformRecipe(this.transformIngredient, this.transformTime, this.result);
        recipeOutput.accept(recipeId, recipe,
                advancement.build(recipeId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + location);
        }
    }
}
