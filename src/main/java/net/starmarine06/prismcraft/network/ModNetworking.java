package net.starmarine06.prismcraft.network;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.starmarine06.prismcraft.PrismCraftMod;

@EventBusSubscriber(modid = PrismCraftMod.MOD_ID)
public class ModNetworking {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        //System.out.println("[ModNetworking] Registering packets!");
        PayloadRegistrar registrar = event.registrar("1");

        // Client -> Server
        registrar.playToServer(
                DyeSelectionPacket.TYPE,
                DyeSelectionPacket.STREAM_CODEC,
                DyeSelectionPacket::handle
        );

        // Server -> Client
        registrar.playToClient(
                SelectionSyncPacket.TYPE,
                SelectionSyncPacket.STREAM_CODEC,
                SelectionSyncPacket::handle
        );

        //System.out.println("[ModNetworking] Packets registered!");
    }

}