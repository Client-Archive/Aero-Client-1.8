package net.aeroclient.client.mods.impl.info;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class ModMemoryUsage implements IMod {
    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "Memory Usage";
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation("textures/items/apple.png");
    }

    @Override
    public boolean StaffMod() {
        return false;
    }
    private static FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;

    @Override
    public void tick() {
        long maxRam = Runtime.getRuntime().maxMemory();
        long totalRam = Runtime.getRuntime().totalMemory();
        long freeRam = Runtime.getRuntime().freeMemory();
        long var30 = totalRam - freeRam;
        long ram = var30 * 100L / maxRam;
        renderString("Ram: " + ram + "%",
                (int) x, (int) y, -1,false, false);

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
        guiScreen.drawString(fontRendererObj, "Ram: 0%", (int) x, (int) y, -1);
        guiScreen.drawHollowRect((int) x,
                (int) y,
                fontRendererObj.getStringWidth("Ram: 0%"),
                fontRendererObj.FONT_HEIGHT,
                -1);
    }

    @Override
    public int getWidth() {
        return fontRendererObj.getStringWidth("Ram: 0%");

    }

    @Override
    public int getHeight() {
        return font().FONT_HEIGHT;
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
