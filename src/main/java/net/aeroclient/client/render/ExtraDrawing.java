package net.aeroclient.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ExtraDrawing {

    private static Minecraft mc = Minecraft.getMinecraft();

    public void drawPinnedIcon(int x, int y, boolean b, ResourceLocation imageLocation) {

        this.mc.getTextureManager().bindTexture(imageLocation);
        Gui.drawModalRectWithCustomSizedTexture(x - 16, b ? y - 16 : y, 0.0F, 0.0F, 16, 16, 16, 16);

    }
    public static void drawRect(float g, float h, float i, float j, Color c) {
        Gui.drawRect((int) g, (int) h, (int) i, (int) j, c.getRGB());
    }

    public static void drawRect(float g, float h, float i, float j, int col1) {
        Gui.drawRect((int) g, (int) h, (int) i, (int) j, col1);
    }

    public static void drawChromaString(String string, int x, int y, boolean shadow) {
        Minecraft mc = Minecraft.getMinecraft();

        int xTmp = x;
        for (char textChar : string.toCharArray()) {
            long l = System.currentTimeMillis() - (xTmp * 10 - y * 10);
            int i = Color.HSBtoRGB(l % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
            String tmp = String.valueOf(textChar);
            mc.fontRendererObj.drawString(tmp, xTmp, y, i, shadow);
            xTmp += mc.fontRendererObj.getCharWidth(textChar);
        }
    }

    protected void drawArc(int x, int y, int radius, int startAngle, int endAngle, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255, (float) color.getAlpha() / 255);
        WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
        worldrenderer.func_181662_b(x, y, 0).func_181675_d();
        for (int i = (int) (startAngle / 360.0 * 100); i <= (int) (endAngle / 360.0 * 100); i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            worldrenderer.func_181662_b(x + Math.sin(angle) * radius, y + Math.cos(angle) * radius, 0).func_181675_d();
        }
        Tessellator.getInstance().draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    public static void drawRoundedRect(double x, double y, double width, double height, final double radius,
                                       final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2.0;
        y *= 2.0;
        width *= 2.0;
        height *= 2.0;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * (radius * -1.0),
                    y + radius + Math.cos(i * 3.141592653589793 / 180.0) * (radius * -1.0));
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * (radius * -1.0),
                    height - radius + Math.cos(i * 3.141592653589793 / 180.0) * (radius * -1.0));
        }
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(width - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius,
                    height - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(width - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius,
                    y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
    }
    public static void drawBorderedRoundedRect(final float x, final float y, final float width, final float height, final float borderSize, final double radius, final int borderC, final int insideC) {
        drawRoundedRect(x, y, width, height, radius, borderC);
        drawRoundedRect(x + borderSize, y + borderSize, width - borderSize, height - borderSize, radius, insideC);
    }
    public static void drawBorderedRoundedRect(final float x, final float y, final float width, final float height, final float borderSize, final int borderC, final int insideC) {
        drawRoundedRect(x, y, width, height, borderSize, borderC);
        drawRoundedRect(x + 0.9f, y + 0.9f, width - 0.9f, height - 0.9f, borderSize, insideC);
    }
    /*
    public static void drawOutlinedRect(int left, int top, int right, int bottom, int rectColor, int outlineColor) {
        drawRect(left + 1, top, right - 1, bottom, rectColor);
        drawHorizontalLine(left, right - 1, top, outlineColor);
        drawHorizontalLine(left, right - 1, bottom, outlineColor);
        drawVerticalLine(left, top, bottom, outlineColor);
        drawVerticalLine(right - 1, top, bottom, outlineColor);
    } */
    public static void glScissor(double x, double y, double width, double height) {
        y += height;
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        Minecraft mc = Minecraft.getMinecraft();
        GL11.glScissor((int) ((x * mc.displayWidth) / scaledResolution.getScaledWidth()), (int) (((scaledResolution.getScaledHeight() - y) * mc.displayHeight) / scaledResolution.getScaledHeight()), (int) (width * mc.displayWidth / scaledResolution.getScaledWidth()), (int) (height * mc.displayHeight / scaledResolution.getScaledHeight()));
    }
    public static void drawCircle(int xPosition, int yPosition, int radius, Color color) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GlStateManager.color(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        worldRenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldRenderer.func_181662_b(xPosition, yPosition, 0).func_181675_d();
        for (int i = 0; i <= 100; i++) {
            double angle = (Math.PI * 2 * i / 100) + Math.toRadians(180);
            worldRenderer.func_181662_b(xPosition + Math.sin(angle) * radius, yPosition + Math.cos(angle) * radius, 0).func_181675_d();
        }
        Tessellator.getInstance().draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.bindTexture(0);
    }

}
