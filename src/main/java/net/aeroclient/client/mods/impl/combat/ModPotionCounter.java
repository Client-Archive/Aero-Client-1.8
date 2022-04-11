package net.aeroclient.client.mods.impl.combat;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModPotionCounter implements IMod {


    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "Potion Counter";
    }

    @Override
    public ResourceLocation icon() {
        return new ResourceLocation("client/icons/mods/fps.png");
    }

    @Override
    public boolean StaffMod() {
        return false;
    }
    private Minecraft mc = Minecraft.getMinecraft();
    private FontRenderer fontRendererObj = mc.fontRendererObj;

    @Override
    public void tick() {
        renderString("Pots: " + getPots(), (int) x, (int) y, -1, false, false);

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
        guiScreen.drawString(fontRendererObj, "Pots: 0", (int) x, (int) y, -1);
        guiScreen.drawHollowRect((int)x,
                (int)y,
                fontRendererObj.getStringWidth("Pots: 0"),
                fontRendererObj.FONT_HEIGHT,
                -1);
    }
    @Override
    public int getWidth() {
        return fontRendererObj.getStringWidth("FPS: 0");

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

    private int getPots() {
        if (mc.thePlayer.inventory != null && mc.thePlayer.inventory.getSizeInventory() != 0) {
            int pots = 0;
            for(int i =0; i < mc.thePlayer.inventory.getSizeInventory(); i++) {
                if(mc.thePlayer.inventory.getStackInSlot(i) != null) {
                    ItemStack item = mc.thePlayer.inventory.getStackInSlot(i);

                    if(Item.getIdFromItem(item.getItem()) == 373) {
                        if(item.getItemDamage() == 16421 || item.getItemDamage() == 16453) {
                            pots++;
                        }
                    }

                }
            }
            return pots;
        }
        return 0;
    }

}
