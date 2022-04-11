package net.aeroclient.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
//This is from 1.12.2
public class GuiButtonImage extends Gui
{
    private ResourceLocation resource;
    private int textureU, textureV;
    private int textureWidth, textureHeight;
    private boolean visible;
    private int x, y;

    public GuiButtonImage(int x, int y, int textureU, int textureV, int textureWidth, int textureHeight, ResourceLocation resource)
    {
        this.resource = resource;
        this.textureU = textureU;
        this.textureV = textureV;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.visible = true;
        this.x = x; this.y = y;
    }




    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if(!this.visible)
            return;
        this.zLevel = 100.0F;
        mc.getTextureManager().bindTexture(resource);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(x + 3, y + 3, textureU, textureV, textureWidth, textureHeight);

        this.zLevel = 0.0F;
    }
}
