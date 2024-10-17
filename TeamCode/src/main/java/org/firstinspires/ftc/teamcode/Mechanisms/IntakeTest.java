package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;


public class IntakeTest {
    public ColorSensorYap colorTrueTest;
    private final Blinkin blinkOn;

    public IntakeTest(HardwareMap hardwareMap){
        colorTrueTest = new ColorSensorYap(hardwareMap);
        blinkOn = new Blinkin(hardwareMap);
    }
    public double[] getSensorData(){
        return new double[] {colorTrueTest.getRed(), colorTrueTest.getBlue(), colorTrueTest.getYellow()};
    }
    public void update() {

        if (colorTrueTest.getRed() > 200)
            blinkOn.turnRed();
        else if (colorTrueTest.getBlue() > 200)
            blinkOn.turnBlue();
        else if (colorTrueTest.getYellow() > 200) {
            blinkOn.turnYellow();
        }
    }
}
