package net.aeroclient.client.gui.impl;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.font.ACFontManager;
import net.aeroclient.client.render.ExtraDrawing;
import net.aeroclient.client.render.object.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class OverlayGUI extends Gui {
    private final Minecraft mc;
    private final FontRenderer fontRenderer;

    public OverlayGUI(Minecraft mc) {
        this.mc = mc;
        this.fontRenderer = mc.fontRendererObj;
    }


    public void render() {
        int removed = 0;
        if (AeroClient.getInstance().getGuiManager().hasModGUIOpen) {
            //Nope
        } else {

            for (int i = 0; i < AeroClient.getInstance().getRenderManager().getToRender().size() - 1; i++) {
                Render render = AeroClient.getInstance().getRenderManager().getToRender().get(i - removed);
                String text = render.getText();
                int x = render.getX();
                int y = render.getY();
                int color = render.getColor();
                boolean chroma = render.isChroma();
                boolean shadow = render.isShadow();

                int j = this.fontRenderer.FONT_HEIGHT;
                int k = this.fontRenderer.getStringWidth(text);
                int l = new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() - 2 - k;

                int i1 = 2 + j;
                if(!chroma)
                    fontRenderer.drawString(text, x, y, color, shadow);
                else
                    ExtraDrawing.drawChromaString(text, x, y, shadow);
                AeroClient.getInstance().getRenderManager().getToRender().remove(i - removed);
                removed++;
            }
        }
    }
}
