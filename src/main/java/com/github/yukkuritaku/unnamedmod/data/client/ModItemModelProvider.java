package com.github.yukkuritaku.unnamedmod.data.client;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, UnnamedMod.MOD_ID, existingFileHelper);
    }

    private ItemModelBuilder basicItem(ItemLike item){
        return this.basicItem(item.asItem());
    }

    @Override
    protected void registerModels() {
        this.basicItem(ModItems.AMETHYST_DUST);
        this.basicItem(ModItems.MOON_SHARD);
        this.basicItem(ModItems.MOON_INGOT);
        this.basicItem(ModItems.FRIED_TOFU);
    }

    @Override
    public String getName() {
        return "かすみん:ItemModels";
    }
}
