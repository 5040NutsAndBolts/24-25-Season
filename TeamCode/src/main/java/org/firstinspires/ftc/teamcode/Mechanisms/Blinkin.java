package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Blinkin {
    private RevBlinkinLedDriver lights;

    public Blinkin(HardwareMap hardwareMap){
        lights = hardwareMap.get(RevBlinkinLedDriver.class, "lights");
    }
    public void turnRed(){
        lights.resetDeviceConfigurationForOpMode();
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
    }
    public void turnBlue(){
        lights.resetDeviceConfigurationForOpMode();
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
    }
    public void turnYellow(){
        lights.resetDeviceConfigurationForOpMode();
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.YELLOW);
    }

}

