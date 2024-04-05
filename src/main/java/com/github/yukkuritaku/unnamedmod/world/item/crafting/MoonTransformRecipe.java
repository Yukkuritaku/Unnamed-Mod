package com.github.yukkuritaku.unnamedmod.world.item.crafting;

import com.github.yukkuritaku.unnamedmod.init.ModRecipeSerializers;
import com.github.yukkuritaku.unnamedmod.init.ModRecipeTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record MoonTransformRecipe(Ingredient transformIngredient,
                                  int transformTime,
                                  ItemStack result) implements Recipe<Container> {

    @Override
    public boolean matches(Container container, Level level) {
        long dayTime = level.dayTime() % 24000L;
        return dayTime >= 13000L && dayTime <= 23000L &&
                this.transformIngredient.test(container.getItem(0));
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.MOON_TRANSFORM.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.MOON_TRANSFORM.get();
    }


    public static class Serializer implements RecipeSerializer<MoonTransformRecipe>{
        public static final Codec<MoonTransformRecipe> CODEC = RecordCodecBuilder.create(
                in -> in.group(Ingredient.CODEC_NONEMPTY.fieldOf("transform_ingredient").forGetter(getter -> getter.transformIngredient),
                                ExtraCodecs.POSITIVE_INT.fieldOf("transform_time").forGetter(getter -> getter.transformTime),
                                ItemStack.ITEM_WITH_COUNT_CODEC.fieldOf("result").forGetter(getter -> getter.result))
                        .apply(in, MoonTransformRecipe::new)
        );

        @Override
        public Codec<MoonTransformRecipe> codec() {
            return CODEC;
        }

        @Override
        public MoonTransformRecipe fromNetwork(FriendlyByteBuf buffer) {
            Ingredient transformIngredient = Ingredient.fromNetwork(buffer);
            int transformTime = buffer.readInt();
            ItemStack result = buffer.readItem();
            return new MoonTransformRecipe(transformIngredient, transformTime, result);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, MoonTransformRecipe recipe) {
            recipe.transformIngredient.toNetwork(buffer);
            buffer.writeInt(recipe.transformTime);
            buffer.writeItem(recipe.result);
        }
    }
}
