package net.starmarine06.prismcraft.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.blockentity.DyeMixerBlockEntity;

public record DyeSelectionPacket(BlockPos pos, int slotIndex, boolean selected) implements CustomPacketPayload {

    public static final Type<DyeSelectionPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "dye_selection"));

    public static final StreamCodec<FriendlyByteBuf, DyeSelectionPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC,
                    DyeSelectionPacket::pos,
                    ByteBufCodecs.INT,
                    DyeSelectionPacket::slotIndex,
                    ByteBufCodecs.BOOL,
                    DyeSelectionPacket::selected,
                    DyeSelectionPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

	public static void handle(DyeSelectionPacket packet, IPayloadContext context) {
		context.enqueueWork(() -> {
			if (context.player() instanceof ServerPlayer serverPlayer) {
				BlockEntity be = serverPlayer.level().getBlockEntity(packet.pos());
				if (be instanceof DyeMixerBlockEntity mixer) {
					boolean[] selectedSlots = mixer.getSelectedSlots();

					// Normal toggle: 0–16 dye slot
					if (packet.slotIndex() >= 0 && packet.slotIndex() <= 16) {
						selectedSlots[packet.slotIndex()] = packet.selected();
						mixer.setSelectedSlots(selectedSlots);
						mixer.updateResultPreview();
					}

					// Reset if slotIndex == -1
					else if (packet.slotIndex() == -1) {
						boolean[] reset = new boolean[selectedSlots.length];
						mixer.setSelectedSlots(reset);
						mixer.updateResultPreview();
					}
				}
			}
		});
	}
}