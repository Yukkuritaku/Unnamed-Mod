package com.github.yukkuritaku.unnamedmod.data;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public abstract class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, UnnamedMod.MOD_ID, locale);
    }

    @Override
    protected abstract void addTranslations();

    @Override
    public @NotNull String getName() {
        return "かすみん:Languages: " + super.getName().replace("Languages: ", "");
    }

    protected void addTranslatable(Component component, String value) {
        add(component.getString(), value);
    }

    protected void addTab(Supplier<CreativeModeTab> tab, String value) {
        addTab(tab.get(), value);
    }

    protected void addTab(CreativeModeTab tab, String value) {
        add(tab.getDisplayName().getString(), value);
    }

    public static class En extends ModLanguageProvider {

        public En(PackOutput output) {
            super(output, "en_us");
        }

        @Override
        protected void addTranslations() {
            this.addItem(ModItems.AMETHYST_DUST, "Amethyst Dust");
            this.addItem(ModItems.MOON_SHARD, "Moon Shard");
            this.addItem(ModItems.MOON_INGOT, "Moon Ingot");
            this.addItem(ModItems.MOON_BLOCK, "Moon Block");
            this.addItem(ModItems.FRIED_TOFU, "Fried Tofu");
        }
    }

    public static class Ja extends ModLanguageProvider {

        public Ja(PackOutput output) {
            super(output, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            this.addItem(ModItems.AMETHYST_DUST, "アメジストの粉");
            this.addItem(ModItems.MOON_SHARD, "月の欠片");
            this.addItem(ModItems.MOON_INGOT, "月インゴット");
            this.addItem(ModItems.MOON_BLOCK, "月ブロック");
            this.addItem(ModItems.FRIED_TOFU, "油揚げ");
        }
    }
}