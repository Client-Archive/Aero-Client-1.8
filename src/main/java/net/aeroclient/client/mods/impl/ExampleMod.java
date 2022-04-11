package net.aeroclient.client.mods.impl;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ExampleMod implements IMod  {

    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "Example";
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation("textures/items/apple.png");
    }

    @Override
    public boolean StaffMod() {
        return false;
    }

    @Override
    public void tick() {

        if( Minecraft.getMinecraft().theWorld != null  && font() != null ) {
            Minecraft.getMinecraft().ingameGUI.drawString(Minecraft.getMinecraft().fontRendererObj,"FPS", 200, 400, -1);
         }
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

    }

    @Override
    public int getWidth() {
        return 0;

    }

    @Override
    public int getHeight() {
        return 0;
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
