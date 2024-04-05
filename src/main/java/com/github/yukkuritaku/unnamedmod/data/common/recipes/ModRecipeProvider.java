package com.github.yukkuritaku.unnamedmod.data.common.recipes;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    protected static void moonIngot(RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MOON_INGOT)
                .define('M', ModItems.MOON_SHARD)
                .define('I', Tags.Items.INGOTS_IRON)
                .pattern("MMM")
                .pattern("MIM")
                .pattern("MMM")
                .unlockedBy("has_moon_shard", has(ModItems.MOON_SHARD))
                .save(recipeOutput);
    }/*
    protected static void moonSword(RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOON_SWORD)
                .define('M', ModItems.MOON_INGOT)
                .define('R', Tags.Items.RODS_WOODEN)
                .pattern("M")
                .pattern("M")
                .pattern("R")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
    }

    protected static void moonShovel(RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOON_SHOVEL)
                .define('M', ModItems.MOON_INGOT)
                .define('R', Tags.Items.RODS_WOODEN)
                .pattern("M")
                .pattern("R")
                .pattern("R")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
    }

    protected static void moonPickaxe(RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOON_PICKAXE)
                .define('M', ModItems.MOON_INGOT)
                .define('R', Tags.Items.RODS_WOODEN)
                .pattern("MMM")
                .pattern(" R ")
                .pattern(" R ")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
    }

    protected static void moonAxe(RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOON_AXE)
                .define('M', ModItems.MOON_INGOT)
                .define('R', Tags.Items.RODS_WOODEN)
                .pattern("MM")
                .pattern("MR")
                .pattern(" R")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
    }

    protected static void moonHoe(RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MOON_HOE)
                .define('M', ModItems.MOON_INGOT)
                .define('R', Tags.Items.RODS_WOODEN)
                .pattern("MM")
                .pattern(" R")
                .pattern(" R")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
    }*/

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        MoonTransformRecipeBuilder.moonTransform(Ingredient.of(ModItems.AMETHYST_DUST),
                100,
                new ItemStack(ModItems.MOON_SHARD.get()), RecipeCategory.MISC)
                .unlocks("has_amethyst_dust", has(ModItems.AMETHYST_DUST))
                .save(recipeOutput, new ResourceLocation(UnnamedMod.MOD_ID,
                        getItemName(ModItems.AMETHYST_DUST) + "_transform"));
        moonIngot(recipeOutput);
        nineBlockStorageRecipesRecipesWithCustomUnpacking(
                recipeOutput, RecipeCategory.MISC, ModItems.MOON_INGOT, RecipeCategory.BUILDING_BLOCKS, ModItems.MOON_BLOCK, "moon_ingot_from_moon_block", "moon_ingot"
        );
        /*moonSword(recipeOutput);
        moonShovel(recipeOutput);
        moonPickaxe(recipeOutput);
        moonAxe(recipeOutput);
        moonHoe(recipeOutput);*/

        /*ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MOON_HELMET)
                .define('X', ModItems.MOON_INGOT)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MOON_CHESTPLATE)
                .define('X', ModItems.MOON_INGOT)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MOON_LEGGINGS)
                .define('X', ModItems.MOON_INGOT)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.MOON_BOOTS)
                .define('X', ModItems.MOON_INGOT)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_moon_ingot", has(ModItems.MOON_INGOT))
                .save(recipeOutput);
*/
    }
}
