package net.starmarine06.prismcraft.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;
import net.starmarine06.prismcraft.PrismcraftMod;
import net.starmarine06.prismcraft.block.DyeMixerBlock;
import net.starmarine06.prismcraft.block.DyeMixerBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, PrismcraftMod.MODID);

    public static final Supplier<BlockEntityType<DyeMixerBlockEntity>> DYE_MIXER_BE =
            BLOCK_ENTITIES.register("dye_mixer", () -> BlockEntityType.Builder.of(DyeMixerBlockEntity::new, DyeMixerBlock::new).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
