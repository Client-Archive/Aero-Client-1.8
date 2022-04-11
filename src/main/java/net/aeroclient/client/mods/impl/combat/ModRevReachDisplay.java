package net.aeroclient.client.mods.impl.combat;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.text.DecimalFormat;

public class ModRevReachDisplay implements IMod {
    private long lastAttack;
    private int decimals;
    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "Reach";
    }
    String reach = "";
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

        renderString(reach, (int) x, (int) y, -1, false, false);
        if (System.nanoTime() - this.lastAttack >= 2.0E9 && this.state)
        {
            reach = "Rev: Hasn't attacked";
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
        guiScreen.drawString(fontRendererObj, "Rev Blocks: 0", (int) x, (int) y, -1);
        guiScreen.drawHollowRect((int)x,
                (int)y,
                fontRendererObj.getStringWidth("Rev Blocks: 0"),
                fontRendererObj.FONT_HEIGHT,
                -1);
    }
    @Override
    public int getWidth() {
        return fontRendererObj.getStringWidth("Rev Block: 0");

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

    public void attack(Entity entity)
    {
        if(entity != null) {
            double hitRange = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(entity);

            reach = this.getFormatter().format(hitRange) + " blocks";
            this.lastAttack = System.nanoTime();
        }
    }

    private DecimalFormat getFormatter()
    {
        StringBuilder format = new StringBuilder("0");
        for (int i = 0; this.decimals > i; i++)
        {
            format.append('0');
        }
        return new DecimalFormat(format.toString());
    }


}