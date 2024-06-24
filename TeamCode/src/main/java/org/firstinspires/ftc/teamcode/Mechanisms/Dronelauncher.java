package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Dronelauncher {
    //Drone Launcher Servo Declaration
    private final Servo droneLaunch;
    public Dronelauncher(HardwareMap hardwareMap){
        //Drone Launcher Config
        droneLaunch = hardwareMap.get(Servo.class, "Drone Launcher");
    }

    /**
     * <p>Pulls the servo back to launch the rubber band, thus the plane, forward.</p>
     */
    public void launch() {
        droneLaunch.setPosition(.5);
    }
}
