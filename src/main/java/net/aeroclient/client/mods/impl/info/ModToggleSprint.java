package net.aeroclient.client.mods.impl.info;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class ModToggleSprint implements IMod {
    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "Toggle Sprint";
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
    boolean sprint = false;
    int canChange = 0;
    @Override
    public void tick() {
        String state = "walking";
        if(Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode())) {
            state = "Sprint (Vanilla)";
            if(canChange == 0) {
                if (!sprint) {
                    sprint = true;
                } else {
                    sprint = false;
                }
                canChange = Minecraft.getDebugFPS() + Minecraft.getDebugFPS() / 2;
            }
        }
        if(canChange != 0)
            canChange--;
        if(sprint) {
            state = "Sprint (Toggled)";
        }
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(), sprint);

        renderString(state,
                (int) x, (int) y, -1,false, true);
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
        guiScreen.drawString(fontRendererObj, "Sprint (Toggled)", (int) x, (int) y, -1);
        guiScreen.drawHollowRect((int) x,
                (int) y,
                fontRendererObj.getStringWidth("Sprint (Toggled)"),
                fontRendererObj.FONT_HEIGHT,
                -1);
    }

    @Override
    public int getWidth() {
        return fontRendererObj.getStringWidth("Sprint (Toggled)");

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
