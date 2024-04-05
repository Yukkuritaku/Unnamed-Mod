package com.github.yukkuritaku.unnamedmod.loot;

import com.github.yukkuritaku.unnamedmod.init.ModGlobalLootModifierSerializers;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.slf4j.Logger;

public class AddItemLootModifier extends LootModifier {
    public static final Codec<AddItemLootModifier> CODEC = RecordCodecBuilder.create(in -> in.group(
            IGlobalLootModifier.LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(glm -> glm.conditions),
            NumberProviders.CODEC.fieldOf("provider").forGetter(addItemLootModifier -> addItemLootModifier.numberProvider),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(addItemLootModifier -> addItemLootModifier.item)
    ).apply(in, AddItemLootModifier::new));

    private final NumberProvider numberProvider;
    private final Item item;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public AddItemLootModifier(LootItemCondition[] conditionsIn, NumberProvider numberProvider, ItemLike item) {
        super(conditionsIn);
        this.numberProvider = numberProvider;
        this.item = item.asItem();
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (var condition : this.conditions){
            if (!condition.test(context)){
                return generatedLoot;
            }
        }
        generatedLoot.add(new ItemStack(this.item, this.numberProvider.getInt(context)));
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return ModGlobalLootModifierSerializers.ADD_ITEM_LOOT_MODIFIER_TYPE.get();
    }
}
