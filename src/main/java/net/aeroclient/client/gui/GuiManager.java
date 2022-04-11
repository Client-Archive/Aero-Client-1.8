package net.aeroclient.client.gui;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.gui.impl.modMenu.ModMenuGUI;
import net.aeroclient.client.gui.impl.modMenu.SettingsGui;
import net.minecraft.client.Minecraft;

public class GuiManager {
    public boolean hasModGUIOpen = false;
    public boolean shouldOpenModMenuGUI = false;
    public boolean shouldOpenSettingsGUI = false;
    public boolean hideModsButton = false;


    public void tick() {
        if(Minecraft.getMinecraft().gameSettings.openModGUI.isKeyDown()) {
            Minecraft.getMinecraft().displayGuiScreen(new ModGUI());
        } else if(shouldOpenModMenuGUI) {
            new ModMenuGUI(Minecraft.getMinecraft()).render();
        }else if(shouldOpenModMenuGUI) {
            new SettingsGui(Minecraft.getMinecraft()).render();
        }
    }

  
}
