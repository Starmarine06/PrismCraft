package net.starmarine06.prismcraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;

public record SelectionSyncPacket(boolean[] selectedSlots) implements CustomPacketPayload {

    public static final Type<SelectionSyncPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "selection_sync"));

    public static final StreamCodec<ByteBuf, SelectionSyncPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL.apply(ByteBufCodecs.list(16)),
            packet -> {
                java.util.List<Boolean> list = new java.util.ArrayList<>();
                for (boolean b : packet.selectedSlots) list.add(b);
                return list;
            },
            list -> {
                boolean[] array = new boolean[16];
                for (int i = 0; i < 16 && i < list.size(); i++) {
                    array[i] = list.get(i);
                }
                return new SelectionSyncPacket(array);
            }
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SelectionSyncPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            System.out.println("[SelectionSyncPacket] Received on client");

            if (context.player().containerMenu instanceof DyeMixerMenu menu) {
                menu.setSelectedSlots(packet.selectedSlots());

                // Print which slots are selected
                for (int i = 0; i < 16; i++) {
                    if (packet.selectedSlots()[i]) {
                        System.out.println("[SelectionSyncPacket] Slot " + i + " is selected");
                    }
                }
            } else {
                System.out.println("[SelectionSyncPacket] ERROR: Container is not DyeMixerMenu");
            }
        });
    }

}
