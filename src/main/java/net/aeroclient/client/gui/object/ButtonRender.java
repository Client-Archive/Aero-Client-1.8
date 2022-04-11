package net.aeroclient.client.gui.object;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonRender extends Gui {
    protected int width;

    protected int height;

    public int xPosition;

    public int yPosition;

    public String displayString;
    public int id;
    public int color;


    public boolean enabled;

    public boolean visible;
    protected boolean hovered;
    protected ResourceLocation texture;
    protected int hoveredColor;
    protected int textColor;

    private int xTextPos;
    private int yTextPos;
    private int xMax;

    public boolean useTexture;

    public ButtonRender(int buttonId, int x, int y, int xTextPos, int yTextPos, String buttonText,
                        ResourceLocation texture, int color, int hoveredColor, int textColor, int xMax, boolean useTexture)
    {
        this(buttonId, x, y, 200, 20, xTextPos, yTextPos, buttonText, texture, color, hoveredColor, textColor, xMax, useTexture);
    }

    public ButtonRender(int buttonId, int x, int y, int widthIn, int heightIn, int xTextPos, int yTextPos,
                        String buttonText, ResourceLocation texture, int color, int hoveredColor, int textColor, int xMax, boolean useTexture)
    {
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
        this.texture = texture;
        this.color = color;
        this.hoveredColor = hoveredColor;
        this.textColor = textColor;
        this.xTextPos = xTextPos;
        this.yTextPos = yTextPos;
        this.xMax = xMax;
        this.useTexture = useTexture;
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
        boolean yCheck =  mouseY >= this.yPosition && mouseY < this.yPosition + this.height / 4 + 4;
        boolean xCheck =  mouseX >= this.xPosition && mouseX < xMax;
        return this.enabled && xCheck && yCheck;
    }

    public boolean isMouseOver()
    {
        return this.hovered;
    }
    public int getButtonWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {

        if (this.visible)
        {

            FontRenderer fontrenderer = mc.fontRendererObj;
            //  mc.getTextureManager().bindTexture(texture );
            //    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            boolean yCheck =  mouseY >= this.yPosition && mouseY < this.yPosition + this.height / 4 + 4;
            boolean xCheck =  mouseX >= this.xPosition && mouseX < xMax;
            //            System.out.println((mouseY > this.yPosition + this.height) + " " + xCheck + " " + yCheck);
            this.hovered = xCheck && yCheck;
            int i = this.getHoverState(this.hovered);
            //     GlStateManager.enableBlend();
            //     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            //         GlStateManager.blendFunc(770, 771);
            if(hovered) {
                drawRect(this.xPosition, this.yPosition, this.width, this.height, hoveredColor);
                if(Mouse.isButtonDown(0)) {
                    if (mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)) {
                        mouseReleased(mouseX, mouseY);
                    }
                }
            } else {
                drawRect(this.xPosition, this.yPosition, this.width, this.height, color);
            }

            this.mouseDragged(mc, mouseX, mouseY);

            this.drawCenteredString(fontrenderer, this.displayString, this.xTextPos, this.yTextPos, textColor);
        }
    }
    public void mouseReleased(int mouseX, int mouseY)
    {
    }
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
    }






}
