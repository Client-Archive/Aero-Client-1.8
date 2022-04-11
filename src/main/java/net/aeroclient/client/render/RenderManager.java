package net.aeroclient.client.render;

import net.aeroclient.client.render.object.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class RenderManager {
    private final ArrayList<Render> toRender = new ArrayList<>();

    public void addToRender(Render render) {
        toRender.add(render);
    }

    public ArrayList<Render> getToRender() {
        return toRender;
    }

    public void renderNameTag(String username, double x, double y , double z) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("client/logo/icon-16x16"));


    }
}
