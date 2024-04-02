package com.github.yukkuritaku.unnamedmod.init;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, UnnamedMod.MOD_ID);


}
