package net.aeroclient.client.mods;

import net.aeroclient.client.AeroClient;
import net.aeroclient.client.mods.impl.combat.ModPotionCounter;
import net.aeroclient.client.mods.impl.combat.ModReachDisplay;
import net.aeroclient.client.mods.impl.combat.ModRevReachDisplay;
import net.aeroclient.client.mods.impl.info.*;
import net.aeroclient.client.mods.impl.render.ModPackDisplay;
import net.aeroclient.client.mods.impl.utils.ModFreelook;
import net.aeroclient.client.mods.impl.utils.ModServerAddress;
import net.aeroclient.client.mods.impl.utils.ModTimeDisplay;
import net.aeroclient.client.mods.object.IMod;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import zsawyer.mods.mumblelink.UpdateTicker;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class ModManager {
    private ArrayList<IMod> mods = new ArrayList<>();
    private UpdateTicker updateTicker;
    int a = 0;
    World lastWorld;
    public ModManager() {
        getMods().add(new ModFPS());
        getMods().add(new ModPingDisplay());
        getMods().add(new ModCoordinates());
        getMods().add(new ModMemoryUsage());
        getMods().add(new ModPotionCounter());
        getMods().add(new ModReachDisplay());
        getMods().add(new ModPackDisplay());
        getMods().add(new ModServerAddress());
        getMods().add(new ModRevReachDisplay());
        getMods().add(new ModToggleSprint());
        getMods().add(new ModTimeDisplay());
        getMods().add(new ModKeystrokes());
        getMods().get(0).setState(true);
        getMods().get(1).setState(true);
        getMods().get(2).setState(true);
        getMods().get(3).setState(true);
        getMods().get(4).setState(true);
        getMods().get(5).setState(true);
        getMods().get(6).setState(true);
        getMods().get(7).setState(true);
        getMods().get(8).setState(true);
        getMods().get(9).setState(true);
        getMods().get(10).setState(true);
        getMods().get(11).setState(true);
        updateTicker = new UpdateTicker();
    }






    public void tick() {

        AeroClient.getInstance().getGuiManager().tick();
        if(Minecraft.getMinecraft().gameSettings.openModGUI.isKeyDown()) {
            return;
        }
        if(AeroClient.getInstance().getGuiManager().hasModGUIOpen)
            return;
        for(IMod mod : mods) {
            if(mod.state())
                mod.tick();
        }
        if(AeroClient.getInstance().getWebSocketClient().isClosed()) {

                if(a == 0) {
                    try {
                        AeroClient.getInstance().connectToWebsocket();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    a = Minecraft.getDebugFPS() * 30;
                } else {
                    a--;
                }

        }
        AeroClient.getInstance().getMumble().tryUpdateMumble(Minecraft.getMinecraft());


    }

    public ArrayList<IMod> getMods() {
        return mods;
    }

    public void save() {
        saveProfiles();
        saveSettings();
    }
    public void load() {
        loadSettings();
        loadProfiles();
    }
    private void loadSettings() {
        File globalSettings = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace("config\\.", "config") +
                File.separator + "config" + File.separator + "aeroclient-1.8" + File.separator + "global.cfg");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(globalSettings));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Object object;
                String[] arrstring;
                if (line.startsWith("#") || line.length() == 0 || (arrstring = line.split("=", 2)).length != 2) continue;
                if (arrstring[0].equalsIgnoreCase("ActiveProfile")) {
                    object = arrstring[1].split(",");

                    continue;
                }
                if (arrstring[0].equalsIgnoreCase("ProfileIndexes")) {
                    object = arrstring[1].split("]\\[");

                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[AC] Loaded Settings");
    }
    private void loadProfiles() {
        File profiles = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace("config\\.", "config") + File.separator + "config" + File.separator + "aeroclient-1.8" + File.separator + "profiles");
        int i = 0;
        if (profiles.exists()) {
            File[] var2 = profiles.listFiles();
            int var3 = var2.length;
            for (File file2 : profiles.listFiles()) {
                if (!file2.getName().endsWith(".cfg")) continue;
                Properties configFile = new Properties();
                try {
                    i++;
                    configFile.load(new FileInputStream(file2));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        System.out.println("[AC] Loaded " + i + " Profiles");

    }
    private void saveSettings() {
        File globalSettings = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace("config\\.", "config") +
                File.separator + "config" + File.separator + "aeroclient-1.8" + File.separator + "global.cfg");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(globalSettings));
            bufferedWriter.write("################################");
            bufferedWriter.newLine();
            bufferedWriter.write("# MC_Client: GLOBAL SETTINGS");
            bufferedWriter.newLine();
            bufferedWriter.write("################################");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write("ActiveProfile=" + "default");
            bufferedWriter.newLine();
            bufferedWriter.write("ProfileIndexes=[default,0]");

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[AC] Saved Settings");


    }
    private void saveProfiles() {
        File profiles = new File(Minecraft.getMinecraft().mcDataDir.getAbsolutePath().replace("config\\.", "config") + File.separator + "config" + File.separator + "aeroclient-1.8" + File.separator + "profiles" + File.separator + "default.cfg");

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(profiles));
            bufferedWriter.write("################################");
            bufferedWriter.newLine();
            bufferedWriter.write("# MC_Client: MODULE SETTINGS");
            bufferedWriter.newLine();
            bufferedWriter.write("################################");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            for (IMod mod : AeroClient.getInstance().getModManager().getMods()) {
                bufferedWriter.write("[" + mod.label() + "]");
                bufferedWriter.newLine();
                bufferedWriter.write("-State=" + mod.state());
                bufferedWriter.newLine();
                bufferedWriter.write("-xTranslation=" + mod.getX());
                bufferedWriter.newLine();
                bufferedWriter.write("-yTranslation=" + mod.getY());
                bufferedWriter.newLine();
                bufferedWriter.write("-RenderHUD=" + mod.renderHUD());
                bufferedWriter.newLine();
                bufferedWriter.write("-chroma=" + mod.chroma());
                bufferedWriter.newLine();
                bufferedWriter.write("-scale=" + mod.scale());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[AC] Saved Profiles");

    }


}
