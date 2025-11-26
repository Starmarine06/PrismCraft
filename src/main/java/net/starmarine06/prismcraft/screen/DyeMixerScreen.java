package net.starmarine06.prismcraft.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;
import net.starmarine06.prismcraft.network.CraftDyeMixerPacket;

public class DyeMixerScreen extends AbstractContainerScreen<DyeMixerMenu> {
    private static final ResourceLocation BACKGROUND_LOCATION =
            ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "textures/gui/container/dye_mixer2.png");

    private Button craftButton;

    public DyeMixerScreen(DyeMixerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();

        // Center the craft button horizontally and vertically
        int buttonWidth = 50;
        int buttonHeight = 20;
        int buttonX = (this.width - buttonWidth) / 2;
        int buttonY = ((this.height - buttonHeight) / 2)-20;

        this.craftButton = Button.builder(
                        Component.literal("Craft"),
                        button -> onCraftButtonPressed()
                )
                .bounds(buttonX, buttonY, buttonWidth, buttonHeight)
                .build();

        this.addRenderableWidget(craftButton);
    }

    private void onCraftButtonPressed() {
        if (this.menu.canCraft()) {
            minecraft.getConnection().send(new CraftDyeMixerPacket());
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        // Update button state every tick
        if (this.craftButton != null) {
            this.craftButton.active = this.menu.canCraft();
        }
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_LOCATION, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}