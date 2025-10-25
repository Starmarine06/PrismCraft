package net.starmarine06.prismcraft.client;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.block.ModBlocks;
import net.starmarine06.prismcraft.blockentity.PrismColoredBlockEntity;

@EventBusSubscriber(modid = PrismCraftMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class AxeStrippingHandler {

    @SubscribeEvent
    public static void onBlockToolModification(BlockEvent.BlockToolModificationEvent event) {
        BlockState state = event.getState();
        var level = event.getLevel(); // LevelAccessor
        BlockPos pos = event.getPos();

        // Check if tool action is axe stripping
        if (event.getItemAbility() == ItemAbilities.AXE_STRIP) {
            BlockState strippedState = getStrippedState(state);
            if (strippedState != null) {
                // Get color from old block entity
                int color = 0xFFFFFF;
                var be = level.getBlockEntity(pos);
                if (be instanceof PrismColoredBlockEntity tile) {
                    color = tile.getColor();
                }

                // Set the stripped block state
                event.setFinalState(strippedState);

                // Transfer color after block changes (on server side)
                int finalColor = color;
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.getServer().execute(() -> {
                        var newBe = serverLevel.getBlockEntity(pos);
                        if (newBe instanceof PrismColoredBlockEntity newTile) {
                            newTile.setColor(finalColor);
                        }
                    });
                }
            }
        }
    }

    private static BlockState getStrippedState(BlockState state) {
        // Prism
        if (state.is(ModBlocks.PRISM_LOG.get())) {
            return ModBlocks.PRISM_STRIPPED_LOG.get().defaultBlockState();
        }
        // add other logs if needed
        return null;
    }
}