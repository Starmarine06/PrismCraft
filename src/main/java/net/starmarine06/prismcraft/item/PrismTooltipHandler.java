package net.starmarine06.prismcraft.item;

import com.mojang.datafixers.util.Either;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;
import net.starmarine06.prismcraft.PrismCraftMod;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = PrismCraftMod.MOD_ID, value = Dist.CLIENT)
public class PrismTooltipHandler {
    @SubscribeEvent
    public static void onTooltip(RenderTooltipEvent.GatherComponents event) {
        ItemStack stack = event.getItemStack();
        List<Either<FormattedText, TooltipComponent>> tooltip = event.getTooltipElements();

        // Shift check that works on all Mojang/NeoForge mappings:
        boolean shiftDown = isShiftDown();

        if (!stack.isEmpty() && stack.has(DataComponents.CUSTOM_DATA)) {
            if (shiftDown) {
                CustomData data = stack.get(DataComponents.CUSTOM_DATA);
                CompoundTag dyeData = data != null ? data.copyTag().asCompound().orElse(null) : null;
                if (dyeData != null && dyeData.contains("prismcraft:dye_ingredients")) {
                    ListTag dyeList = dyeData.getListOrEmpty("prismcraft:dye_ingredients");
                    if (!dyeList.isEmpty()) {
                        tooltip.add(Either.left(Component.literal("Used Dyes:").withStyle(ChatFormatting.GRAY)));
                        for (int i = 0; i < dyeList.size(); i++) {
                            CompoundTag dyeTag = dyeList.getCompoundOrEmpty(i);
                            String idString = dyeTag.getStringOr("id","");
                            int count = dyeTag.getIntOr("count",1);
                            ResourceLocation dyeLoc = ResourceLocation.tryParse(idString);
                            String displayName = idString;
                            if (dyeLoc != null) {
                                Optional<Item> itemOpt = BuiltInRegistries.ITEM.getOptional(dyeLoc);
                                if (itemOpt.isPresent()) {
                                    displayName = Component.translatable(itemOpt.get().getDescriptionId()).getString();
                                }
                            }
                            tooltip.add(Either.left(Component.literal("• " + displayName + " x" + count).withStyle(ChatFormatting.AQUA)));
                        }
                    }
                }
            } else {
                tooltip.add(Either.left(Component.literal("Hold SHIFT to view dyes").withStyle(ChatFormatting.DARK_GRAY)));
            }
        }
    }

    private static boolean isShiftDown() {
        long window = Minecraft.getInstance().getWindow().handle();
        return GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS
                || GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
    }
}