package net.aeroclient.client.mods.impl.render;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;

public class ModPackDisplay implements IMod {


    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "Pack Display";
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation("client/icons/mods/fps.png");
    }

    @Override
    public boolean StaffMod() {
        return false;
    }
    private static FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;

    @Override
    public void tick() {
        String pack = "";
        if(!Config.getResourcePackNames().equalsIgnoreCase("default"))
            pack = Config.getResourcePackNames().split(",")[Config.getResourcePacks().length -1];
        else
            pack = "default";
        renderString("Pack: " + pack, (int) x, (int) y, -1, false, false);

    }


    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean state() {
        return state;
    }

    @Override
    public void dummyRender(ModGUI guiScreen) {
        String pack = "";
        if(!Config.getResourcePackNames().equalsIgnoreCase("default"))
            pack = Config.getResourcePackNames().split(",")[Config.getResourcePacks().length -1];
        else
            pack = "default";
        guiScreen.drawString(fontRendererObj, "Pack: " + pack, (int) x, (int) y, -1);
        guiScreen.drawHollowRect((int)x,
                (int)y,
                fontRendererObj.getStringWidth("Pack: " + pack),
                fontRendererObj.FONT_HEIGHT,
                -1);
    }
    @Override
    public int getWidth() {
        String pack = "";
        if(!Config.getResourcePackNames().equalsIgnoreCase("default"))
            pack = Config.getResourcePackNames().split(",")[Config.getResourcePacks().length -1];
        else
            pack = "default";
        return fontRendererObj.getStringWidth("Pack: "  + pack);

    }

    @Override
    public int getHeight() {
        return fontRendererObj.FONT_HEIGHT;
    }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    @Override
    public boolean renderHUD() {
        return true;
    }

    @Override
    public boolean chroma() {
        return false;
    }

    @Override
    public void renderIcon(double x, double y) {

    }

}
