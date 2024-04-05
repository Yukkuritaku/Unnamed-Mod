package com.github.yukkuritaku.unnamedmod.init;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnnamedMod.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("unnamedmod_items",
            () -> CreativeModeTab.builder()
                    .icon(ModItems.MOON_INGOT.get()::getDefaultInstance)
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.AMETHYST_DUST);
                        output.accept(ModItems.MOON_SHARD);
                        output.accept(ModItems.MOON_INGOT);
                        output.accept(ModItems.MOON_BLOCK);
                        output.accept(ModItems.FRIED_TOFU);
                    }).build());
}
