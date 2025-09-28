package net.starmarine06.prismcraft.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DyeMixerMixPayload(BlockPos pos) implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation("prismcraft", "dye_mixer_mix");

    public DyeMixerMixPayload(FriendlyByteBuf buf) {
        this(buf.readBlockPos());
    }

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }
}
