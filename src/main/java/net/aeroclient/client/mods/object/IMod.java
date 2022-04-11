package net.aeroclient.client.mods.object;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.render.object.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public interface IMod {
    String label();
    ResourceLocation icon();
    boolean StaffMod();
    void tick();
    void setX(int x);
    void setY(int y);
    void setState(boolean state);
    boolean state();
    default FontRenderer font() {
        return Minecraft.getMinecraft().getRenderManager().getFontRenderer();
    }
    void dummyRender(ModGUI guiScreen);
    default void renderString(String text, int x, int y, int color, boolean shadow, boolean chroma) {
        AeroClient.getInstance().getRenderManager().addToRender(new Render(text, x, y, color, shadow, chroma));
    }
    int getWidth();
    int getHeight();
    int getX();
    int getY();
    boolean renderHUD();
    boolean chroma();
    default double scale() { return 1.0;}
    void renderIcon(double x, double y);

}
