package net.aeroclient.client.controller;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MovementInput;
import uk.co.electronstudio.sdl2gdx.SDL2Controller;

public class ControllerThread extends Thread {

    ACControllerManager controllerManager;
    public ControllerThread() {
        super("Control Support");
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[AC] Attempting to enable Controller Support");
        controllerManager = new ACControllerManager(Minecraft.getMinecraft().gameSettings);
        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad();
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ControllerState currState = controllers.getState(0);
            if(currState.isConnected) {
                controllerManager.updatePlayerMoveState(currState);
                //  System.out.println(currState.leftStickAngle);

            }
        }
    }
}
