package net.aeroclient.client.gui.buttons;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.gui.object.ButtonRender;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ModButton extends ButtonRender {

    public ModButton(int buttonId, int x, int y, int widthIn, int heightIn, int xTextPos, int yTextPos, String buttonText, ResourceLocation texture, int color, int hoveredColor, int textColor, int xMax, boolean useTexture) {
        super(buttonId, x, y, widthIn, heightIn, xTextPos, yTextPos, buttonText, texture, color, hoveredColor, textColor, xMax, useTexture);
    }




    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        AeroClient.getInstance().getGuiManager().shouldOpenModMenuGUI = true;
        super.mouseReleased(mouseX, mouseY);
    }
}

