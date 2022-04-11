package net.aeroclient.client.server;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.ResourceLocation;

public class ServerFeatured extends ServerData {
    public static final ResourceLocation STAR = new ResourceLocation("client/star.png");
    public static final ResourceLocation LOGO = new ResourceLocation("client/logo/logo_white.png");

    public ServerFeatured(String servername, String serverIP) {
        super(servername, serverIP, false);
    }

}
