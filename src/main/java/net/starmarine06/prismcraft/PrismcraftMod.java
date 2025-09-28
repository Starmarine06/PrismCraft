package net.starmarine06.prismcraft;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.starmarine06.prismcraft.network.DyeMixerMixPayload;
import net.starmarine06.prismcraft.registry.ModRegistry;
import net.starmarine06.prismcraft.registry.ModBlockEntities;

@Mod(PrismcraftMod.MODID)
public class PrismcraftMod {
    public static final String MODID = "prismcraft";

    public PrismcraftMod() {
        var bus = net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();
        ModRegistry.register(bus);
        ModBlockEntities.register(bus);
    }

    @SubscribeEvent
    public static void onRegisterPayloads(final RegisterPayloadHandlersEvent event) {
        var registrar = event.registrar();
        registrar.play(
            DyeMixerMixPayload.ID,
            DyeMixerMixPayload::new,
            (payload, context) -> {
                context.enqueueWork(() -> {
                    var player = context.player();
                    if (player != null) {
                        var level = player.level();
                        var pos = payload.pos();
                        var be = level.getBlockEntity(pos);
                        if (be instanceof net.starmarine06.prismcraft.block.DyeMixerBlockEntity mixer) {
                            mixer.tryMix();
                        }
                    }
                });
            }
        );
    }
}
