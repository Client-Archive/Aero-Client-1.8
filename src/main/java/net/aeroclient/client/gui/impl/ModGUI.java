package net.aeroclient.client.gui.impl;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.gui.buttons.ModButton;
import net.aeroclient.client.gui.buttons.SettingsButton;
import net.aeroclient.client.gui.impl.modMenu.SettingsGui;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;

import java.awt.*;
import java.util.Optional;
import java.util.function.Predicate;

public class ModGUI extends GuiScreen {
    private int prevX, prevY;
    private Optional<IMod> selectedMod = Optional.empty();
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawHollowRect(0, 0, this.width -1, this.height -1, -1);
        this.drawEnabledMods();
        this.drawModsButton(mouseX, mouseY);
    }

    @Override
    public void initGui() {
        super.initGui();
        AeroClient.getInstance().getGuiManager().hasModGUIOpen = true;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        AeroClient.getInstance().getGuiManager().hasModGUIOpen = false;

    }

    public void drawHollowRect(int x, int y, int w, int h, int color) {
        this.drawHorizontalLine(x, x + w, y, color);
        this.drawHorizontalLine(x, x + w, y + h, color);
        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);
    }

    private void drawEnabledMods() { // Make this draw those that are enabled, not every last one of them
        for(IMod mod : AeroClient.getInstance().getModManager().getMods()) {
            if(mod.state()) {
                mod.dummyRender(this);
            }
        }
    }
    private void drawModsButton(int mouseX, int mouseY) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());


        if(!AeroClient.getInstance().getGuiManager().hideModsButton) {

            new ModButton(99,  resolution.getScaledWidth() / 2 - 60,
                    resolution.getScaledHeight() / 2 - 20,
                    resolution.getScaledWidth() / 2 + 60,
                    resolution.getScaledHeight() / 2 + 20,
                    resolution.getScaledWidth() / 2,
                    resolution.getScaledHeight() / 2 - 2,
                    "Mod", new ResourceLocation("client/test.png"),
                    Color.BLACK.getRGB(),
                    Color.BLUE.getRGB(), -1, (resolution.getScaledWidth() / 2 - 60) + (resolution.getScaledWidth() / 2 + 60) / 3 + 28, false)
                    .drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
            new SettingsButton(98, resolution.getScaledWidth() / 2 + 80,
                    resolution.getScaledHeight() / 2 - 20,
                    resolution.getScaledWidth() / 2 + 120,
                    resolution.getScaledHeight() / 2 + 20,
                    resolution.getScaledWidth() / 2 + 100,
                    resolution.getScaledHeight() / 2 - 2,
                    "S", new ResourceLocation("client/test.png"),
                    Color.BLACK.getRGB(),
                    Color.GRAY.getRGB(), -1,  (resolution.getScaledWidth() / 2 + 80) + (resolution.getScaledWidth() / 2 + 120) / 2 - 126, false)
                    .drawButton(Minecraft.getMinecraft(), mouseX, mouseY);

          /*  drawRect(resolution.getScaledWidth() / 2 - 60,
                    resolution.getScaledHeight() / 2 - 20,
                    resolution.getScaledWidth() / 2 + 60,
                    resolution.getScaledHeight() / 2 + 20,
                    color);
            drawCenteredString(fontRendererObj, "Mods",
                    resolution.getScaledWidth() / 2,
                    resolution.getScaledHeight() / 2 - 2, Color.gray.getRGB());*/
        }

    }



    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {
        if(selectedMod.isPresent()) {
            moveSelectModBy(x - prevX, y - prevY);

        }
        this.prevX = x;
        this.prevY = y;
    }




    @Override
    protected void mouseClicked(int x, int y, int mobuttonuseButton) {
        prevX = x;
        prevY = y;
        selectedMod = AeroClient.getInstance().getModManager().getMods().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IMod> {
            private int mouseX, mouseY;
        public MouseOverFinder(int x, int y) {
            mouseX = x;
            mouseY = y;
        }
        @Override
        public boolean test(IMod mod) {

            int absoluteX = mod.getX();
            int absoluteY = mod.getY();

            if(mouseX >= absoluteX && mouseX <= absoluteX + mod.getWidth()){
                if(mouseY >= absoluteY && mouseY <= absoluteY + mod.getHeight()){
                    return true;
                }
            }

            return false;
        }
    }
    private void moveSelectModBy(int offsetX, int offsetY) {
        IMod mod = selectedMod.get();
        int x = mod.getX();
        int y = mod.getY();

        int newX = x + offsetX;
        int newY = y + offsetY;
  //      System.out.println("Moving mod: " + mod.label() + " X: " + x + " Y: " + y + " New X: " + newX + " New Y: " + newY + " Offset X: " + offsetX + " Offset Y: " + offsetY);
        mod.setX(newX);
        mod.setY(newY);
    }







}
