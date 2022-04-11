package net.aeroclient.client.gui.object;

import net.aeroclient.client.font.ACFontManager;
import net.aeroclient.client.font.ACFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class TextButton extends Gui {

    public int xPosition;

    public int yPosition;

    public String displayString;
    public int id;
    public int color;


    public boolean enabled;

    public boolean visible;
    protected boolean hovered;
    protected int hoveredColor;
    protected ACFontRenderer fontRenderer;

    public TextButton(int xPosition, int yPosition, String text, int id, int color, boolean enabled, boolean visible, int hoveredColor, ACFontRenderer fontRenderer) {

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.displayString = text;
        this.id =id;
        this.color = color;
        this.enabled = enabled;
        this.visible = visible;
        this.hoveredColor = hoveredColor;
        this.fontRenderer = fontRenderer;
    }


    protected int getHoverState(boolean mouseOver)
    {
        int i = 1;

        if (!this.enabled)
        {
            i = 0;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }

    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        boolean yCheck =  mouseY >= this.yPosition && mouseY < this.yPosition + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
        boolean xCheck =  mouseX >= this.xPosition && mouseX < mc.fontRendererObj.getStringWidth(this.displayString);;
        return this.enabled && xCheck && yCheck;
    }

    public boolean isMouseOver()
    {
        return this.hovered;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {

        if (this.visible) {

            FontRenderer fontrenderer = mc.fontRendererObj;
            //  mc.getTextureManager().bindTexture(texture );
            //    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            boolean yCheck = mouseY >= this.yPosition && mouseY < this.yPosition + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
            boolean xCheck = mouseX >= this.xPosition && mouseX < this.xPosition + fontrenderer.getStringWidth(this.displayString);
            this.hovered = xCheck && yCheck;
            if(hovered) {
                fontRenderer.drawString(displayString, this.xPosition, this.yPosition,  hoveredColor);
                if(Mouse.isButtonDown(0)) {
                    mouseHandler(mouseX, mouseY);
                }
            } else {
                fontRenderer.drawString(displayString, this.xPosition, this.yPosition,  color);
            }
        }
    }

    public void mouseHandler(int mouseX, int mouseY) {
    }
}
