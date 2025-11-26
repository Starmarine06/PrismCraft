package net.starmarine06.prismcraft.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CraftDyeMixerPacket() implements CustomPacketPayload {

    public static final Type<CraftDyeMixerPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("prismcraft", "craft_dye_mixer"));

    public static final StreamCodec<ByteBuf, CraftDyeMixerPacket> CODEC =
            StreamCodec.unit(new CraftDyeMixerPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}