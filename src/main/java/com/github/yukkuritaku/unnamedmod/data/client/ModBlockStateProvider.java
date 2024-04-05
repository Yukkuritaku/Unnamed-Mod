package com.github.yukkuritaku.unnamedmod.data.client;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.github.yukkuritaku.unnamedmod.init.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, UnnamedMod.MOD_ID, exFileHelper);
    }

    private void simpleBlockWithItem(Supplier<Block> block) {
        ModelFile model = cubeAll(block.get());
        simpleBlock(block.get(), model);
        simpleBlockItem(block.get(), model);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlockWithItem(ModBlocks.MOON_BLOCK);
    }
}
