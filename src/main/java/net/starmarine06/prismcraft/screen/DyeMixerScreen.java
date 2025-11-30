package net.starmarine06.prismcraft.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;
import org.joml.Matrix3x2fStack;

public class DyeMixerScreen extends AbstractContainerScreen<DyeMixerMenu> {
    private static final ResourceLocation MIXER_BG =
            ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "textures/gui/container/dye_mixer_wheel.png");
    private static final ResourceLocation MIXER_BUTTON_ACTIVE =
            ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "textures/gui/container/dye_mixer_button_active.png");
    private static final ResourceLocation MIXER_BUTTON_INACTIVE =
            ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "textures/gui/container/dye_mixer_button_inactive.png");
    static final ResourceLocation MIXER_BUTTON_HOVER =
            ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "textures/gui/container/dye_mixer_button_hover.png");

    public DyeMixerScreen(DyeMixerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 286;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, MIXER_BG, x, y, 0, 0, imageWidth, imageHeight, 176, 286);

        for (DyeMixerMenu.DyeSlotMapping mapping : DyeMixerMenu.DYE_SLOTS) {
            boolean active = menu.isSlotActive(mapping.slotIndex());

            final int bx = this.leftPos + mapping.buttonX();
            final int by = this.topPos + mapping.buttonY();

            ResourceLocation icon = active ? MIXER_BUTTON_ACTIVE : MIXER_BUTTON_INACTIVE;

            // 8×8 hover area
            final boolean hovered = mouseX >= bx && mouseX < bx + 8 &&
                    mouseY >= by && mouseY < by + 8;
            if (active && hovered) {
                icon = MIXER_BUTTON_HOVER;
            }

            final Matrix3x2fStack stack = graphics.pose();
            stack.pushMatrix();

            // 1) Pivot a gomb közepére (képernyő koordináta → lokális)
            stack.translate(bx + 4, by + 4);

            // 2) Forgatás fokból radiánba
            stack.rotate((float) Math.toRadians(mapping.rotation()));

            // 3) Vissza a bal felső sarokra lokálisan
            stack.translate(-4, -4);

            // 4) Blit lokális (0,0)-ból! NEM bx,by!
            graphics.blit(RenderPipelines.GUI_TEXTURED, icon,
                    0, 0,  // local origin after transform
                    0, 0,
                    8, 8,
                    8, 8);

            stack.popMatrix();
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean unused) {
        double mouseX = event.x();
        double mouseY = event.y();
        int button = event.button();

        for (DyeMixerMenu.DyeSlotMapping mapping : DyeMixerMenu.DYE_SLOTS) {
            int bx = this.leftPos + mapping.buttonX();
            int by = this.topPos + mapping.buttonY();

            // csak akkor hover, ha van item
            Slot slot = this.menu.slots.get(mapping.slotIndex());
            if (!slot.hasItem()) {
                continue; // üres → nem kattintható
            }

            boolean hovered = mouseX >= bx && mouseX < bx + 8 &&
                    mouseY >= by && mouseY < by + 8;

            if (hovered) {
                System.out.println("Clicked button for " + mapping.dyeColor());

                // send action to server
                return true;
            }
        }
        return super.mouseClicked(event, unused);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}