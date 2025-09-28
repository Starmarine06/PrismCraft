package net.starmarine06.prismcraft.util;

import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.DyeColor;

public class ColorUtil {
    public static int getColorFromDye(DyeItem dye) {
        return dyeColorToRGB(dye.getDyeColor());
    }

    public static int dyeColorToRGB(DyeColor c) {
        return switch (c) {
            case WHITE -> 0xF9FFFE;
            case ORANGE -> 0xF9801D;
            case MAGENTA -> 0xC74EBD;
            case LIGHT_BLUE -> 0x3AB3DA;
            case YELLOW -> 0xFED83D;
            case LIME -> 0x80C71F;
            case PINK -> 0xF38BAA;
            case GRAY -> 0x474F52;
            case LIGHT_GRAY -> 0x9D9D97;
            case CYAN -> 0x169C9C;
            case PURPLE -> 0x8932B8;
            case BLUE -> 0x3C44AA;
            case BROWN -> 0x835432;
            case GREEN -> 0x5E7C16;
            case RED -> 0xB02E26;
            case BLACK -> 0x1D1D21;
        };
    }

    public static int blendAverage(int rgb1, int rgb2) {
        int r1 = (rgb1 >> 16) & 0xFF;
        int g1 = (rgb1 >> 8) & 0xFF;
        int b1 = rgb1 & 0xFF;
        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = rgb2 & 0xFF;
        int r = (r1 + r2) / 2;
        int g = (g1 + g2) / 2;
        int b = (b1 + b2) / 2;
        return (r << 16) | (g << 8) | b;
    }

    public static int blendWeighted(int oldRGB, int newRGB, float weightNew) {
        float weightOld = 1.0f - weightNew;
        int rOld = (oldRGB >> 16) & 0xFF;
        int gOld = (oldRGB >> 8) & 0xFF;
        int bOld = oldRGB & 0xFF;
        int rNew = (newRGB >> 16) & 0xFF;
        int gNew = (newRGB >> 8) & 0xFF;
        int bNew = newRGB & 0xFF;
        int r = Math.round(rOld * weightOld + rNew * weightNew);
        int g = Math.round(gOld * weightOld + gNew * weightNew);
        int b = Math.round(bOld * weightOld + bNew * weightNew);
        return (r << 16) | (g << 8) | b;
    }
}
