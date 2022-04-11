package net.aeroclient.client.mods.impl.info;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ModKeystrokes implements IMod {


    float x;
    float y;
    boolean state;
    public static enum KeystrokesMode {



        WASD(Key.W, Key.A, Key.S, Key.D),
        WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
        WASD_SPRINT(Key.W, Key.A, Key.S, Key.D, new Key("Sprint", Minecraft.getMinecraft().gameSettings.keyBindSprint, 1, 41, 58, 18)),
        WASD_SPRINT_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB)
        ;

        private final Key[] keys;
        private int width = 0;
        private int height= 0;

        private KeystrokesMode(Key... keysIn) {
            this.keys = keysIn;

            for(Key key : keys) {
                this.width = Math.max(this.width, key.getX() + key.getWidth());
                this.height = Math.max(this.height, key.getY() + key.getHeight());
            }
        }

        public int getHeight() {
            return height;
        }
        public int getWidth() {
            return width;
        }
        public Key[] getKeys() {
            return keys;
        }

    }
    private static class Key {



        private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
        private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
        private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
        private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);

        private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28,
                18);
        private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28,
                18);

        private static final Key LMBCPS = new Key("CPS", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 61,
                28, 18);
        private static final Key RMBCPS = new Key("CPS", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 61,
                28, 18);



        private final String name;
        private final KeyBinding keyBind;
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
            this.name = name;
            this.keyBind = keyBind;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean isDown() {
            return keyBind.isKeyDown();

        }

        public int getHeight() {
            return height;
        }

        public String getName() {
            return name;
        }

        public int getWidth() {
            return width;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }




    private KeystrokesMode mode = KeystrokesMode.WASD_SPRINT_MOUSE;


    public void setMode(KeystrokesMode mode) {
        this.mode = mode;
    }

    @Override
    public String label() {
        return "Keystrokes";
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
        GL11.glPushMatrix();

        boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);



        for(Key key : mode.getKeys()) {

            int textWidth = fontRendererObj.getStringWidth(key.getName());

            Gui.drawRect((int)x + key.getX(), (int)y + key.getY(),
                    (int)x + key.getX() + key.getWidth(),
                    (int)y + key.getY() + key.getHeight(),
                    key.isDown() ? new Color(255, 255, 255, 102).getRGB() :
                            new Color(0, 0, 0, 102).getRGB());

            renderString(key.getName(), (int)x + key.getX() + key.getWidth() / 2 - textWidth / 2,
                    (int)y + key.getY() + key.getHeight() / 2 - 4,
                    key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB(), false, true );


        }

        if(blend) {
            GL11.glEnable(GL11.GL_BLEND);
        }

        GL11.glPopMatrix();
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
        GL11.glPushMatrix();

        boolean blend = GL11.glIsEnabled(GL11.GL_BLEND);



        for(Key key : mode.getKeys()) {

            int textWidth = font().getStringWidth(key.getName());
            Gui.drawRect((int)x + key.getX(), (int)y + key.getY(),
                    (int)x + key.getX() + key.getWidth(),
                    (int)y + key.getY() + key.getHeight(),
                    key.isDown() ? new Color(255, 255, 255, 102).getRGB() :
                            new Color(0, 0, 0, 102).getRGB());

            font().drawString(key.getName(), (int)x + key.getX() + key.getWidth() / 2 - textWidth / 2,
                    (int)y + key.getY() + key.getHeight() / 2 - 4,
                    key.isDown() ? Color.BLACK.getRGB() : Color.WHITE.getRGB());


        }

        if(blend) {
            GL11.glEnable(GL11.GL_BLEND);
        }

        GL11.glPopMatrix();
    }

    @Override
    public int getWidth() {
        return mode.getWidth();
    }

    @Override
    public int getHeight() {
        return mode.getHeight();
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
