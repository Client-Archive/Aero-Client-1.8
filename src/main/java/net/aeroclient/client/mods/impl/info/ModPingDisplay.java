package net.aeroclient.client.mods.impl.info;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class ModPingDisplay implements IMod {


    public ModPingDisplay() {
        x = 200;
        y = 400;
    }
    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "Ping";
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation("client/icons/mods/ping.png");
    }

    @Override
    public boolean StaffMod() {
        return false;
    }
    private static FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;

    @Override
    public void tick() {

            String string = "";

            if(Minecraft.getMinecraft().isSingleplayer())
                string = "Singleplayer";
            else {
                if(Minecraft.getMinecraft().getNetHandler().getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()) != null) {

                    string = String.valueOf(Minecraft.getMinecraft().thePlayer.sendQueue.getPlayerInfo(Minecraft.getMinecraft().thePlayer.getUniqueID()).getResponseTime());
                }
            }
            renderString("Ping: " + string, (int)x, (int)y, -1, false, true);


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
        guiScreen.drawString(fontRendererObj, "Ping: 0", (int) x,(int) y, -1);
        guiScreen.drawHollowRect((int)x,
                (int)y,
                fontRendererObj.getStringWidth("Ping: 0"),
                fontRendererObj.FONT_HEIGHT,
                -1);
    }

    @Override
    public int getWidth() {
        return fontRendererObj.getStringWidth("Ping: 0");
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
