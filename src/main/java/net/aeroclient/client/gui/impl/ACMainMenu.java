package net.aeroclient.client.gui.impl;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.font.ACFontManager;
import net.aeroclient.client.gui.object.TextButton;
import net.aeroclient.client.mods.ModManager;
import net.aeroclient.client.mods.object.IMod;
import net.aeroclient.client.render.ExtraDrawing;
import net.aeroclient.client.render.GuiButtonImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomPanorama;
import net.optifine.CustomPanoramaProperties;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ACMainMenu extends GuiScreen {
    private int panoramaTimer;
    private ResourceLocation backgroundTexture;
    private DynamicTexture viewportTexture;

    private static final ResourceLocation[] Panorama = new ResourceLocation[] {
            new ResourceLocation("client/panorama/0.png"),
            new ResourceLocation("client/panorama/1.png"),
            new ResourceLocation("client/panorama/2.png"),
            new ResourceLocation("client/panorama/3.png"), 
            new ResourceLocation("client/panorama/4.png"),
            new ResourceLocation("client/panorama/5.png")};

    @Override
    public void initGui() {
        this.viewportTexture = new DynamicTexture(256, 256);
        this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        super.initGui();
    }

    public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }
    private void drawHollowRect(int x, int y, int w, int h, int color) {
        this.drawHorizontalLine(x, x+w, y, color);
        this.drawHorizontalLine(x, x+w, y + h, color);

        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);


    }
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        GlStateManager.disableAlpha();
        this.renderSkybox(mouseX, mouseY, partialTicks);
        GlStateManager.enableAlpha();
        drawText(mouseX, mouseY);
        drawCustomButtons(mouseX, mouseY);
        drawMiscIcons(mouseX, mouseY);

    }
    public void drawCustomButtons(int mouseX, int mouseY) {




        String[] buttons = {"SINGLEPLAYER", "MULTIPLAYER"};
        int count = 0;
        double baseX = 427;
        double baseXMW = baseX - this.width;
        double xOfBox = this.width / 2 - 100;
        /*System.out.println("IN -> X: " + xOfBox + " BaseXMW: " + baseXMW);
        if(baseXMW != 0) {
            if(baseXMW > -0) {
               xOfBox = xOfBox - (baseXMW);
            } else {
               xOfBox = xOfBox + (baseXMW);

            }
            System.out.println("OUT -> X: " + xOfBox + " BaseXMW: " + baseXMW);
        } */
        for(String name : buttons) {
            boolean hovered = false;
            float x = 173;
            float y = this.height / 2 - 70 + (count * 30) + 27+ 60;
            int xBox = this.width / 2 - 75;

            if(mouseX >= xBox && mouseY >= y - 10 && mouseX < xBox + fontRendererObj.getStringWidth(name) && mouseY < y + fontRendererObj.FONT_HEIGHT + 5) {
                hovered = true;
            }else {
                hovered = false;
            }

            /*ExtraDrawing.drawRoundedRect(this.width / 2 - 50,
                    this.height / 2 - 20,
                    this.width / 2 + 40,
                    this.height / 2, 15, -1);*/
            double widthBox = 255 * 2;
            double heightBox =  y + 15;
            x += -10;
            y += -10;

            if(Minecraft.getMinecraft().getSession().getUsername().equalsIgnoreCase("PulseFX")) {
                drawHorizontalLine(100, 200, 200, -1);
                drawVerticalLine(100, 200, 50, -1);
            }
            if(!hovered) {
                Color color = new Color(33, 33, 33, 255);
                Color color1 =  new Color(33, 33, 33, 255);

                ExtraDrawing.drawRoundedRect(xBox,  y,
                        this.width / 2, heightBox,5, color.getRGB());
                ExtraDrawing.drawRoundedRect((int) (xBox +  (xBox / 7) - 30), (int) y, (int) (this.width / 2 + 80), (int) heightBox, 5,
                        color1.getRGB());
            } else{
                Color color = new Color(0, 0, 0, 255);
                ExtraDrawing.drawRoundedRect(xBox,  y,
                        this.width / 2, heightBox,5, color.getRGB());
                ExtraDrawing.drawRoundedRect((int) (xBox +  (xBox / 7) - 30), (int) y, (int) (this.width / 2 + 80), (int) heightBox, 5,
                        color.getRGB());
            }
            ACFontManager.playRegular22.drawCenteredString(name,(int)this.width / 2,(int) y + 10, Color.WHITE.getRGB());
            count ++;
        }


    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {


        this.playPressSound(mc.getSoundHandler());
        String[] buttons = {"SINGLEPLAYER", "MULTIPLAYER"};
        int count = 0;

        for(String name : buttons) {
            float x = 173;
            float y = this.height / 2 - 70 + (count * 30) + 27+ 60;
            int xBox = this.width / 2 - 75;


            if(mouseX >= xBox && mouseY >= y - 10 && mouseX < xBox + fontRendererObj.getStringWidth(name) && mouseY < y + fontRendererObj.FONT_HEIGHT + 5   ) {
                switch(name) {
                    case "SINGLEPLAYER":
                        mc.displayGuiScreen(new GuiSelectWorld(this));
                        break;
                    case "MULTIPLAYER":
                        mc.displayGuiScreen(new GuiMultiplayer(this));
                        break;

                }
            }
            count++;
        }


    }

    public void drawText(int mouseX, int mouseY) {
        String gitCommit = "(" + AeroClient.getInstance().getGitAbbrev() + "/" + AeroClient.getInstance().getGitBranch() + ")";
        ACFontManager.playRegular22.drawStringWithShadow("Aero Client Dev " + gitCommit , 3, this.height - 15, -1879048193);
        ACFontManager.playBold22.drawString("Aero Client", this.width / 20, 10, -1879048193);

        new TextButton(this.width / 20 + 9 + 105 + 16, 10, "Cosmetics", 96, -1879048193,true, true, -1, ACFontManager.playRegular22) {
            @Override
            public void mouseHandler(int mouseX, int mouseY) {
                //Cosmetic GUI Here
            }
        }.drawButton(Minecraft.getMinecraft(),mouseX, mouseY);
        new TextButton(this.width / 20 + 9 + 50 + 16, 10, "Options", 97, -1879048193,true, true, -1, ACFontManager.playRegular22) {
            @Override
            public void mouseHandler(int mouseX, int mouseY) {
                mc.displayGuiScreen(new GuiOptions(ACMainMenu.this, mc.gameSettings));
            }
        }.drawButton(Minecraft.getMinecraft(),mouseX, mouseY);
        new TextButton(this.width / 20 + 9 + 175 + 16, 10, "Updates", 96, -1879048193,true, true, -1, ACFontManager.playRegular22) {
            @Override
            public void mouseHandler(int mouseX, int mouseY) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop != null) {
                    try {
                        desktop.browse(new URL("https://aeroclient.net/updates").toURI());
                    } catch (IOException | URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.drawButton(Minecraft.getMinecraft(),mouseX, mouseY);
    }

    public void drawMiscIcons(int mouseX, int mouseY) {

        GlStateManager.pushMatrix();
        mc.getTextureManager().bindTexture(new ResourceLocation("client/logo/logo_white.png"));
        drawModalRectWithCustomSizedTexture(this.width / 2 - 55, this.height / 2 - 100, 0, 0, 100, 100, 100, 100);
        mc.getTextureManager().bindTexture(new ResourceLocation("client/icons/delete-64.png"));
        drawModalRectWithCustomSizedTexture(this.width - 30, 10, 0, 0, 10, 10, 10, 10);
        GlStateManager.popMatrix();
        Color color;
        boolean hovered = false;
        if(mouseX >= this.width - 35 && mouseY >= 5 - 10 && mouseX < this.width - 8 && mouseY < 25) {
            hovered = true;
            color = new Color(160, 0, 0);
        }else {
            color = new Color(71, 71, 71, 255);
        }
        ExtraDrawing.drawBorderedRoundedRect(this.width - 35, 5, this.width - 10, 25, 0.5F,5, Color.WHITE.getRGB(), color.getRGB());

    }
//Next 3 are vanilla mc main menu code
    private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.matrixMode(5889);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GlStateManager.matrixMode(5888);
        GlStateManager.pushMatrix();
        GlStateManager.loadIdentity();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableCull();
        GlStateManager.depthMask(false);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        int i = 8;
        int j = 64;
        CustomPanoramaProperties custompanoramaproperties = CustomPanorama.getCustomPanoramaProperties();

        if (custompanoramaproperties != null)
        {
            j = custompanoramaproperties.getBlur1();
        }

        for (int k = 0; k < j; ++k)
        {
            GlStateManager.pushMatrix();
            float f = ((float)(k % i) / (float)i - 0.5F) / 64.0F;
            float f1 = ((float)(k / i) / (float)i - 0.5F) / 64.0F;
            float f2 = 0.0F;
            GlStateManager.translate(f, f1, f2);
            GlStateManager.rotate(MathHelper.sin(((float)this.panoramaTimer + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-((float)this.panoramaTimer + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int l = 0; l < 6; ++l)
            {
                GlStateManager.pushMatrix();

                if (l == 1)
                {
                    GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 2)
                {
                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 3)
                {
                    GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 4)
                {
                    GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (l == 5)
                {
                    GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                ResourceLocation[] aresourcelocation = Panorama;

                if (custompanoramaproperties != null)
                {
                    aresourcelocation = custompanoramaproperties.getPanoramaLocations();
                }

                this.mc.getTextureManager().bindTexture(aresourcelocation[l]);
                worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
                int i1 = 255 / (k + 1);
                float f3 = 0.0F;
                worldrenderer.func_181662_b(-1.0D, -1.0D, 1.0D).func_181673_a(0.0D, 0.0D).func_181669_b(255, 255, 255, i1).func_181675_d();
                worldrenderer.func_181662_b(1.0D, -1.0D, 1.0D).func_181673_a(1.0D, 0.0D).func_181669_b(255, 255, 255, i1).func_181675_d();
                worldrenderer.func_181662_b(1.0D, 1.0D, 1.0D).func_181673_a(1.0D, 1.0D).func_181669_b(255, 255, 255, i1).func_181675_d();
                worldrenderer.func_181662_b(-1.0D, 1.0D, 1.0D).func_181673_a(0.0D, 1.0D).func_181669_b(255, 255, 255, i1).func_181675_d();
                tessellator.draw();
                GlStateManager.popMatrix();
            }

            GlStateManager.popMatrix();
            GlStateManager.colorMask(true, true, true, false);
        }

        worldrenderer.setTranslation(0.0D, 0.0D, 0.0D);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.matrixMode(5889);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.popMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
    }
    private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_)
    {
        this.mc.getFramebuffer().unbindFramebuffer();
        GlStateManager.viewport(0, 0, 256, 256);
        this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        int i = 3;
        CustomPanoramaProperties custompanoramaproperties = CustomPanorama.getCustomPanoramaProperties();

        if (custompanoramaproperties != null)
        {
            i = custompanoramaproperties.getBlur3();
        }

        for (int j = 0; j < i; ++j)
        {
            this.rotateAndBlurSkybox(p_73971_3_);
            this.rotateAndBlurSkybox(p_73971_3_);
        }

        this.mc.getFramebuffer().bindFramebuffer(true);
        GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        float f2 = this.width > this.height ? 120.0F / (float)this.width : 120.0F / (float)this.height;
        float f = (float)this.height * f2 / 256.0F;
        float f1 = (float)this.width * f2 / 256.0F;
        int k = this.width;
        int l = this.height;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        worldrenderer.func_181662_b(0.0D, (double)l, (double)this.zLevel).func_181673_a((double)(0.5F - f), (double)(0.5F + f1)).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
        worldrenderer.func_181662_b((double)k, (double)l, (double)this.zLevel).func_181673_a((double)(0.5F - f), (double)(0.5F - f1)).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
        worldrenderer.func_181662_b((double)k, 0.0D, (double)this.zLevel).func_181673_a((double)(0.5F + f), (double)(0.5F - f1)).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
        worldrenderer.func_181662_b(0.0D, 0.0D, (double)this.zLevel).func_181673_a((double)(0.5F + f), (double)(0.5F + f1)).func_181666_a(1.0F, 1.0F, 1.0F, 1.0F).func_181675_d();
        tessellator.draw();
    }

    private void rotateAndBlurSkybox(float p_73968_1_)
    {
        this.mc.getTextureManager().bindTexture(this.backgroundTexture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, 256, 256);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.colorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
        GlStateManager.disableAlpha();
        int i = 3;
        int j = 3;
        CustomPanoramaProperties custompanoramaproperties = CustomPanorama.getCustomPanoramaProperties();

        if (custompanoramaproperties != null)
        {
            j = custompanoramaproperties.getBlur2();
        }

        for (int k = 0; k < j; ++k)
        {
            float f = 1.0F / (float)(k + 1);
            int l = this.width;
            int i1 = this.height;
            float f1 = (float)(k - i / 2) / 256.0F;
            worldrenderer.func_181662_b((double)l, (double)i1, (double)this.zLevel).func_181673_a((double)(0.0F + f1), 1.0D).func_181666_a(1.0F, 1.0F, 1.0F, f).func_181675_d();
            worldrenderer.func_181662_b((double)l, 0.0D, (double)this.zLevel).func_181673_a((double)(1.0F + f1), 1.0D).func_181666_a(1.0F, 1.0F, 1.0F, f).func_181675_d();
            worldrenderer.func_181662_b(0.0D, 0.0D, (double)this.zLevel).func_181673_a((double)(1.0F + f1), 0.0D).func_181666_a(1.0F, 1.0F, 1.0F, f).func_181675_d();
            worldrenderer.func_181662_b(0.0D, (double)i1, (double)this.zLevel).func_181673_a((double)(0.0F + f1), 0.0D).func_181666_a(1.0F, 1.0F, 1.0F, f).func_181675_d();
        }

        tessellator.draw();
        GlStateManager.enableAlpha();
        GlStateManager.colorMask(true, true, true, true);
    }
    public void updateScreen()
    {
        ++this.panoramaTimer;

    }


}
