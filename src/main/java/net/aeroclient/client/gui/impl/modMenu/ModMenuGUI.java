package net.aeroclient.client.gui.impl.modMenu;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.font.ACFont;
import net.aeroclient.client.font.ACFontManager;
import net.aeroclient.client.mods.ModManager;
import net.aeroclient.client.mods.object.IMod;
import net.aeroclient.client.render.ExtraDrawing;
import net.aeroclient.client.utils.ColorCodes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.Random;

public class ModMenuGUI extends Gui {
    private final Minecraft mc;
    private final FontRenderer fontRenderer;

    public ModMenuGUI(Minecraft mc) {
        this.mc = mc;
        this.fontRenderer = mc.fontRendererObj;
    }

    public void render() {
        AeroClient.getInstance().getGuiManager().hideModsButton = true;
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        ExtraDrawing.drawBorderedRoundedRect(resolution.getScaledWidth() / 2 - 200,
                resolution.getScaledHeight() / 2 - 90,
                resolution.getScaledWidth() / 2 + 200,
                resolution.getScaledHeight() / 2 + 90, 15, -1, Color.BLACK.getRGB() );
        ExtraDrawing.drawRoundedRect(resolution.getScaledWidth() / 2 - 150,
                resolution.getScaledHeight() / 2 - 20,
                resolution.getScaledWidth() / 2 + 150,
                resolution.getScaledHeight() / 2 + 20, 15, -1);


        int mBoxWidth = 64, mBoxHeight = 64, size_of_box = resolution.getScaledWidth() / 2 + 150,
                middle_of_boxX = resolution.getScaledWidth() / 2 - 150, middle_of_boxY = resolution.getScaledHeight() / 2 - 20;

        //Close Code
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
    public void renderMod(IMod mod, double x, double y) {
        ExtraDrawing.drawRoundedRect(x, y, 120, 120, 5, -1);

        Color color = new Color(0x45FF01);
        Color red = new Color(-5756117);
        if(!mod.state())
            color =red;
        ExtraDrawing.drawRoundedRect(x, y + 80, 100, 120, 5, color.getRGB());

        ACFontManager.playRegular22.drawCenteredString((mod.state() ? "Enabled" : "Disabled"), (int) x + 50, (int) y + 85, Color.GRAY.getRGB() );
        mod.renderIcon((int) x + 30, (int)y + 10);

    }

}
