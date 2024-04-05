package com.github.yukkuritaku.unnamedmod.mixin;

import com.github.yukkuritaku.unnamedmod.init.ModAttachmentTypes;
import com.github.yukkuritaku.unnamedmod.init.ModRecipeTypes;
import com.github.yukkuritaku.unnamedmod.world.item.crafting.MoonTransformRecipe;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    private ItemEntityMixin(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Shadow public abstract ItemStack getItem();

    @Shadow public abstract void setItem(ItemStack pStack);

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTailTick(CallbackInfo ci){
        if (!this.getItem().isEmpty()){
            SimpleContainer container = new SimpleContainer(this.getItem());
            Optional<RecipeHolder<MoonTransformRecipe>> transformRecipe =
                    this.level().getRecipeManager().getRecipeFor(ModRecipeTypes.MOON_TRANSFORM.get(), container, this.level());
            if (transformRecipe.isPresent()){
                MoonTransformRecipe recipe = transformRecipe.get().value();
                List<ItemEntity> itemEntities = this.level().getEntitiesOfClass(ItemEntity.class,
                        this.getBoundingBox().inflate(2.0));
                Optional<ItemEntity> glowStone =
                        itemEntities.stream().filter(item -> item.getItem().is(Tags.Items.DUSTS_GLOWSTONE)).findFirst();
                if (recipe.matches(container, this.level()) && glowStone.isPresent()){
                    int transformTick = this.getData(ModAttachmentTypes.TRANSFORM_TICK_COUNT);
                    boolean transformed = this.getData(ModAttachmentTypes.TRANSFORMED);
                    if (transformTick > recipe.transformTime() && !transformed) {
                        this.playSound(SoundEvents.AMETHYST_BLOCK_BREAK);
                        if (this.level().isClientSide) {
                            for(int i = 0; i < 15; ++i) {
                                double d0 = this.random.nextGaussian() * 0.02;
                                double d1 = this.random.nextGaussian() * 0.02;
                                double d2 = this.random.nextGaussian() * 0.02;
                                this.level().addParticle(ParticleTypes.GLOW, this.getRandomX(1.0), this.getRandomY(), this.getRandomZ(1.0), d0, d1, d2);
                            }
                        }
                        int count = Math.min(this.getItem().getCount(), glowStone.get().getItem().getCount());
                        ItemStack stack = recipe.assemble(container, this.level().registryAccess());
                        stack.setCount(count * recipe.result().getCount());
                        this.getItem().shrink(count);
                        glowStone.get().getItem().shrink(count);
                        ItemEntity assembledEntity = new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(),
                                stack,
                                0, 0.3, 0);
                        if (!this.level().isClientSide) {
                            this.level().addFreshEntity(assembledEntity);
                        }

                        this.setData(ModAttachmentTypes.TRANSFORMED, true);
                        this.setDeltaMovement(new Vec3(0, 0.2, 0));
                    }
                    this.setData(ModAttachmentTypes.TRANSFORM_TICK_COUNT, transformTick + 1);
                }
            }
        }
    }
}
