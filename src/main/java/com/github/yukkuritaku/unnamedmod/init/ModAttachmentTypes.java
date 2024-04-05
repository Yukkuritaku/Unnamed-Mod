package com.github.yukkuritaku.unnamedmod.init;

import com.github.yukkuritaku.unnamedmod.UnnamedMod;
import com.mojang.serialization.Codec;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachmentTypes {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, UnnamedMod.MOD_ID);
    public static final Supplier<AttachmentType<Integer>> TRANSFORM_TICK_COUNT =
            ATTACHMENT_TYPES.register("transform_tick_count",
                    () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());
    public static final Supplier<AttachmentType<Boolean>> TRANSFORMED = ATTACHMENT_TYPES.register("transformed",
            () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).build());
}
