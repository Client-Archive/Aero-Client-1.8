package net.aeroclient.client.font;

import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class ACFontManager {


    public static ACFontRenderer playBold22;
    public static ACFontRenderer playRegular22;
    public static ACFontRenderer ubuntuM16;
    public static ACFontRenderer playBold18;
    public static ACFontRenderer robotoRegular24;
    public static ACFontRenderer playRegular18;
    public static ACFontRenderer playRegular14;
    public static ACFontRenderer playRegular16;
    public static ACFontRenderer robotoRegular13;
    public static ACFontRenderer robotoBold14;
    public static ACFontRenderer playRegular12;

    public ACFontManager() {

        this.playBold22 = new ACFontRenderer(new ResourceLocation("client/font/Play-Bold.ttf"), 22.0f);
        this.playRegular22 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Regular.ttf"), 22.0f);
        this.playRegular18 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Regular.ttf"), 18.0f);
        this.playRegular14 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Regular.ttf"), 14.0f);
        this.playRegular12 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Regular.ttf"), 12.0f);
        this.playRegular16 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Regular.ttf"), 16.0f);
        this.playBold18 = new ACFontRenderer(new ResourceLocation("client/font/Play-Bold.ttf"), 18.0f);
        this.ubuntuM16 = new ACFontRenderer(new ResourceLocation("client/font/Ubuntu-M.ttf"), 16.0f);
        this.robotoRegular13 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Regular.ttf"), 13.0f);
        this.robotoBold14 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Bold.ttf"), 14.0f);
        this.robotoRegular24 = new ACFontRenderer(new ResourceLocation("client/font/Roboto-Regular.ttf"), 24.0f);
    }
}
