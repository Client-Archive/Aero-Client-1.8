package net.aeroclient.client.gui.impl.modMenu;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.render.ExtraDrawing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class SettingsGui extends Gui {
    private final Minecraft mc;
    private final FontRenderer fontRenderer;

    public SettingsGui(Minecraft mc) {
        this.mc = mc;
        this.fontRenderer = mc.fontRendererObj;
    }

    public void render() {
        AeroClient.getInstance().getGuiManager().hideModsButton = true;
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        ExtraDrawing.drawBorderedRoundedRect(resolution.getScaledWidth() / 2 - 200,
                resolution.getScaledHeight() / 2 - 90,
                resolution.getScaledWidth() / 2 + 200,
                resolution.getScaledHeight() / 2 + 90, 15, -1, Color.RED.getRGB() );
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            AeroClient.getInstance().getGuiManager().shouldOpenModMenuGUI = false;
            mc.thePlayer.closeScreen();
            new Thread(() -> {
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(mc.currentScreen != null)
                    mc.thePlayer.closeScreen();
            }).start();
            AeroClient.getInstance().getGuiManager().hideModsButton = false;
        }
    }
}
