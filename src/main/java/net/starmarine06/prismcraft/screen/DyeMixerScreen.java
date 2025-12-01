package net.starmarine06.prismcraft.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.starmarine06.prismcraft.PrismCraftMod;
import net.starmarine06.prismcraft.blockentity.DyeMixerBlockEntity;
import net.starmarine06.prismcraft.menu.DyeMixerMenu;
import net.starmarine06.prismcraft.network.DyeSelectionPacket;
import org.joml.Matrix3x2fStack;

import static net.starmarine06.prismcraft.menu.DyeMixerMenu.DYE_SLOTS;

public class DyeMixerScreen extends AbstractContainerScreen<DyeMixerMenu> {
    private static final ResourceLocation MIXER_BG =
            ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "textures/gui/container/dye_mixer.png");
    private static final ResourceLocation MIXER_BUTTONS =
            ResourceLocation.fromNamespaceAndPath(PrismCraftMod.MOD_ID, "textures/gui/container/dye_mixer_button.png");

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
            boolean selected = menu.isSlotSelected(mapping.slotIndex());
            final int bx = this.leftPos + mapping.buttonX();
            final int by = this.topPos + mapping.buttonY();

            final boolean hovered = mouseX >= bx && mouseX < bx + 10 &&
                    mouseY >= by && mouseY < by + 10;

            int u = 0;
            int v = 0;

            if (!active) {
                u = 0; v = 0; // inactive
            } else if (hovered) {
                u = 0; v = 10; // hover
            } else if (selected) {
                u = 10; v = 10; // active
            } else {
                u = 10; v = 0; // button
            }

            final Matrix3x2fStack stack = graphics.pose();
            stack.pushMatrix();
            stack.translate(bx + 5, by + 5);
            stack.rotate((float) Math.toRadians(mapping.rotation()));
            stack.translate(-5, -5);

            graphics.blit(RenderPipelines.GUI_TEXTURED, MIXER_BUTTONS,
                    0, 0,
                    u, v,
                    10, 10,
                    20, 20
            );

            stack.popMatrix();
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean unused) {
        double mouseX = event.x();
        double mouseY = event.y();
        int button = event.button();

        //System.out.println("[Screen] mouseClicked called - mouseX: " + mouseX + ", mouseY: " + mouseY);

        for (DyeMixerMenu.DyeSlotMapping mapping : DYE_SLOTS) {
            int bx = this.leftPos + mapping.buttonX();
            int by = this.topPos + mapping.buttonY();
            Slot slot = this.menu.slots.get(mapping.slotIndex());

            if (!slot.hasItem()) {
                continue;
            }

            boolean hovered = mouseX >= bx && mouseX < bx + 10 &&
                    mouseY >= by && mouseY < by + 10;

            if (hovered) {
                //System.out.println("[Screen] Button CLICKED for slot " + mapping.slotIndex() + " color: " + mapping.dyeColor());

                // Toggle selection locally on client
                this.menu.toggleSelected(mapping.slotIndex());
                //System.out.println("[Screen] After toggle, selected: " + this.menu.isSlotSelected(mapping.slotIndex()));

                // Send packet using the BlockPos from menu
                BlockPos pos = this.menu.getBlockPos();
                //System.out.println("[Screen] BlockPos: " + pos);

                if (!pos.equals(BlockPos.ZERO)) {
                    boolean newState = this.menu.isSlotSelected(mapping.slotIndex());

                    //System.out.println("[Screen] Sending packet - pos: " + pos + ", slot: " + mapping.slotIndex() + ", state: " + newState);
                    ClientPacketDistributor.sendToServer(
                            new DyeSelectionPacket(pos, mapping.slotIndex(), newState)
                    );
                    //System.out.println("[Screen] Packet sent!");
                }

                return true;
            }
        }

        //System.out.println("[Screen] No button was clicked, calling super");
        return super.mouseClicked(event, unused);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
}