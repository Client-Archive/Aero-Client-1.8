package net.aeroclient.client;


import net.aeroclient.client.controller.ControllerThread;
import net.aeroclient.client.gui.GuiManager;
import net.aeroclient.client.mods.ModManager;
import net.aeroclient.client.network.ACNetworkManager;
import net.aeroclient.client.network.websocket.ACWebSocketClient;
import net.aeroclient.client.render.RenderManager;
import net.aeroclient.client.font.ACFontManager;
import net.aeroclient.client.utils.SessionChanger;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import zsawyer.mods.mumblelink.MumbleLinkBase;
import zsawyer.mods.mumblelink.MumbleLinkImpl;
import zsawyer.mods.mumblelink.api.MumbleLink;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AeroClient {
    private static AeroClient instance;
    private String gitCommit;
    private String gitBranch;
    private String gitAbbrev;
    private final DiscordRichPresence presence;
    private final ModManager modManager;
    private final RenderManager renderManager;
    private final ACNetworkManager networkManager;
    private final GuiManager guiManager;
    private final ACFontManager fontManager;
    private MumbleLinkBase mumble;



    private ACWebSocketClient webSocketClient;
    private ClientBranch branch;

    public AeroClient() {
        instance = this;
        loadGitInfo();
        String windowtitle = "Aero Client 1.8.9 (" + getGitAbbrev() + "/" + getGitBranch() + ")";
        Display.setTitle(windowtitle);
        System.out.println("[AC] Starting");
        this.presence = new DiscordRichPresence();
        DiscordRPC.discordInitialize("702822890487414784", (DiscordEventHandlers) null, true, (String) null);
        this.presence.startTimestamp = System.currentTimeMillis();
        this.presence.state = "(" + getGitAbbrev() + "/" + getGitBranch() + ")";
        this.presence.details = "In The Menus";
        this.presence.largeImageKey = "logo";
        this.presence.smallImageKey = "logo_small";
        this.presence.smallImageText = "Aero Client Dev 1.8";
        this.presence.largeImageText = "Aero Client Dev 1.8";
        DiscordRPC.discordUpdatePresence(presence);
        new ControllerThread().start();
        this.modManager = new ModManager();
        this.renderManager = new RenderManager();
        this.networkManager = new ACNetworkManager();
        this.guiManager = new GuiManager();
        this.fontManager = new ACFontManager();
        Runtime.getRuntime().addShutdownHook(new Shutdown());
        setMumble(new MumbleLinkBase());
        this.branch = ClientBranch.DEV;
        try {
            connectToWebsocket();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mumble.load();

    }

    public static AeroClient getInstance() {
        return instance;
    }
    public ModManager getModManager() {
        return modManager;
    }
    public RenderManager getRenderManager() {
        return renderManager;
    }
    public ACNetworkManager getNetworkManager() {
        return networkManager;
    }
    public GuiManager getGuiManager() {
        return guiManager;
    }
    public ACFontManager getFontManager() {
        return fontManager;
    }
    public ACWebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    public void setMumble(MumbleLinkBase mumble) {
        this.mumble = mumble;
    }

    public MumbleLinkBase getMumble() {
        return mumble;
    }
    public String getGitCommit() {
        return gitCommit;
    }
    public String getGitBranch() {
        return gitBranch;
    }
    public String getGitAbbrev() {
        return gitAbbrev;
    }
    public ClientBranch getBranch() {
        return branch;
    }

    public void updateRPC(String server) {

        if (server.toLowerCase().contains("lunar.gg")) {
            this.presence.details = "Playing Lunar Network";
        } else if (server.toLowerCase().contains("vipermc.net")) {
            this.presence.details = "Playing ViperMC";
        } else if (server.toLowerCase().contains("faithfulmc.com")) {
            this.presence.details = "Playing FaithfulMC";
        } else if (server.toLowerCase().contains("cavepvp.org")) {
            this.presence.details = "Playing CavePvP";
        } else if (server.toLowerCase().contains("protocol.rip")) {
            this.presence.details = "Playing Protocol";
        } else if (server.toLowerCase().contains("certix.cc")) {
            this.presence.details = "Playing Certix";
        } else if (server.toLowerCase().contains("minemen.club")) {
            this.presence.details = "Playing Minemen Club";
        } else if (server.toLowerCase().contains("pvp.land")) {
            this.presence.details = "Playing PvP Land";
        } else if (server.toLowerCase().contains("veltpvp.com")) {
            this.presence.details = "Playing VeltPvP";
        } else if (server.toLowerCase().contains("potion.land")) {
            this.presence.details = "Playing Potion Land";
        } else if (server.toLowerCase().contains("hivemc.com")) {
            this.presence.details = "Playing HiveMc";
        }else if (server.toLowerCase().contains("hypixel.net")) {
            this.presence.details = "Playing Hypixel";
        }else if (validate(server.toLowerCase().split(":")[0])) {
            this.presence.details = "Private Server";
        }else {
            this.presence.details = server;
        }
        DiscordRPC.discordUpdatePresence(presence);
    }
    private static final String PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    public static boolean validate(final String ip){

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }
    private void loadGitInfo() {
        try {
            ResourceLocation var1 = new ResourceLocation("client/app.properties");
            Properties var2 = new Properties();
            InputStream var3 = Minecraft.getMinecraft().getResourceManager().getResource(var1).getInputStream();
            if (var3 == null) {
                return;
            }
            var2.load(var3);
            var3.close();
            this.gitAbbrev = var2.getProperty("git.commit.id.abbrev");
            this.gitCommit = var2.getProperty("git.commit.id");
            this.gitBranch = var2.getProperty("git.branch");
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }


    public void connectToWebsocket() throws URISyntaxException {
        HashMap<String, String> var2 = new HashMap();
        var2 .put("username", Minecraft.getMinecraft().session.getUsername());
        var2 .put("playerId", Minecraft.getMinecraft().session.getProfile().getId().toString());
        var2.put("version", "1.7.10");

        this.webSocketClient = new ACWebSocketClient(new URI("ws://aero-client-websockets.herokuapp.com:80"), var2);
        System.out.println("[AC] Connecting to websocket");
        this.webSocketClient.connect();


    }

    private static class Shutdown extends Thread {
        @Override
        public void run() {
            AeroClient.getInstance().getModManager().save();
        }
    }
    public boolean isWhitelisted(){
        String playerId = Minecraft.getMinecraft().session.getProfile().getId().toString();
        String[] whitelisted = new String[]{"a6b34d61-c122-4349-a97d-d16dd6c22be3", "adf667a8-2c23-4a67-9c64-4c23fd83f437",  "3da31a3c-8439-4041-b2da-e579576c99af", "d90df151-0966-4922-8d0c-a58e1fc5f3fb", "42cb2d10-567e-4d4a-b33d-680ed3a7e4fb", "b58d2633-c86e-4579-8265-11116304b3c9", "285c25e3-74f6-47e0-81a6-4e74ceb54ed3", "58025126-17c2-4fe3-bbc9-81519341f7d6", "12e0d63d-9e50-49a9-b5fe-4229fba667f2", "26bc0d30-1adf-4d1a-9742-da5e0cd9922a", "a9d05c56-d1f6-4522-8b72-10f031774582", "4b986a1f-25e6-4f22-9200-dbed8397c15d", "ca624816-b6a6-403e-9c48-455077baa272", "dd64c677-32c4-459f-877e-4c71244b7061"};
        for(String s : whitelisted) {
            if(s.equalsIgnoreCase(playerId) || s.replace("-", "").equalsIgnoreCase(playerId))
                return true;
        }
        return false;
    }




}
