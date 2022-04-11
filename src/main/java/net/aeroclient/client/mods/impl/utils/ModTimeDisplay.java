package net.aeroclient.client.mods.impl.utils;

import net.aeroclient.client.gui.impl.ModGUI;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModTimeDisplay implements IMod {


    float x;
    float y;
    boolean state;
    @Override
    public String label() {
        return "FPS";
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

        renderString("Time: " + getTime(), (int) x, (int) y, -1, false, true);

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

        guiScreen.drawString(fontRendererObj, "Time: " + getTime(), (int) x, (int) y, -1);
        guiScreen.drawHollowRect((int)x,
                (int)y,
                fontRendererObj.getStringWidth("Time: " + getTime()),
                fontRendererObj.FONT_HEIGHT,
                -1);
    }
    @Override
    public int getWidth() {


        return fontRendererObj.getStringWidth("Time: " + getTime());

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


    private String getTime() {
        DateFormat dtf = new SimpleDateFormat("HH:mm");
        String time =  dtf.format(new Date()).toString();
        DateFormat df = new SimpleDateFormat("HH:mm");
        //Date/time pattern of desired output date
        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        String output = null;
        try{
            //Conversion of input String to date
            date= df.parse(time);
            //old date format to new date format
            output = outputformat.format(date);
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        return output;
    }

}
