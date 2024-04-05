package com.github.yukkuritaku.unnamedmod;

import com.github.yukkuritaku.unnamedmod.data.ModLanguageProvider;
import com.github.yukkuritaku.unnamedmod.data.client.ModBlockStateProvider;
import com.github.yukkuritaku.unnamedmod.data.client.ModItemModelProvider;
import com.github.yukkuritaku.unnamedmod.data.common.glm.ModGlobalLootModifierProvider;
import com.github.yukkuritaku.unnamedmod.data.common.recipes.ModRecipeProvider;
import com.github.yukkuritaku.unnamedmod.data.common.tags.ModBlockTagsProvider;
import com.github.yukkuritaku.unnamedmod.data.common.tags.ModItemTagsProvider;
import com.github.yukkuritaku.unnamedmod.data.loot.ModLootTableProvider;
import com.github.yukkuritaku.unnamedmod.init.*;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(UnnamedMod.MOD_ID)
public class UnnamedMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "unnamedmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public UnnamedMod(IEventBus modEventBus) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModAttachmentTypes.ATTACHMENT_TYPES.register(modEventBus);

        ModGlobalLootModifierSerializers.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::gatherData);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void gatherData(final GatherDataEvent event){
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        var efh = event.getExistingFileHelper();
        var lookup = event.getLookupProvider();
        gen.addProvider(event.includeClient(), new ModItemModelProvider(output, efh));
        gen.addProvider(event.includeClient(), new ModBlockStateProvider(output, efh));
        ModBlockTagsProvider blockTagsProvider = new ModBlockTagsProvider(output, lookup, efh);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(output, lookup, blockTagsProvider.contentsGetter(), efh));

        gen.addProvider(event.includeServer(), new ModLanguageProvider.Ja(output));
        gen.addProvider(event.includeServer(), new ModLanguageProvider.En(output));
        gen.addProvider(event.includeServer(), new ModRecipeProvider(output));
        gen.addProvider(event.includeServer(), new ModLootTableProvider(output));
        gen.addProvider(event.includeServer(), new ModGlobalLootModifierProvider(output));


    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        /*if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);*/
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
