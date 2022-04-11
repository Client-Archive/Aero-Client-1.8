package net.aeroclient.client.controller;

import com.studiohartman.jamepad.ControllerState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInput;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ACControllerManager {
    private static ACControllerManager instance;
    public ACControllerManager(GameSettings gameSettings) {
        instance = this;
        try {
            robot   = new Robot();
            this.gameSettings = gameSettings;
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    Robot robot;
    GameSettings gameSettings;
    public void updatePlayerMoveState(ControllerState currState) {
     //   System.out.println(currState.leftStickX);
     //   System.out.println(currState.leftStickY);
     //   System.out.println(currState.leftStickAngle);
        //     System.out.println(moveForward + " " + moveBackward);
        //     System.out.println(moveLeft + " " + moveRight);
        float leftStickX = currState.leftStickX;
        float leftStickY = currState.leftStickY;
        boolean moveForward = leftStickY <= 1.1 && leftStickY >= 0.7;
        boolean moveBackward = leftStickY >= -1.1 && leftStickY <= -0.7;
        boolean moveRight = leftStickX <= 1.1 && leftStickX >= 0.7;
        boolean moveLeft = leftStickX >= -1.1 && leftStickX <= -0.7;
        boolean jump = currState.a;

        if (moveForward) {
            System.out.println("Forward");
        } else if (moveBackward) {
            System.out.println("Backward");
        }
        if (moveRight) {
            System.out.println("Right");
        } else if (moveLeft) {
            System.out.println("Left");
        }
        if(jump) {
            robot.keyPress(gameSettings.keyBindJump.getKeyCode());
        } else {
            robot.keyRelease(gameSettings.keyBindJump.getKeyCode());
        }

    }
    
    public static ACControllerManager getInstance() {
        return instance;
    }
}
