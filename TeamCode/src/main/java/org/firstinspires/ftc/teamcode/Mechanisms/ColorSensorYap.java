
package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class ColorSensorYap {
    private final ColorSensor colorSensor;

    public ColorSensorYap(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "Color Sensor");
    }
    public double getRed(){
       return colorSensor.red();
    }
    public double getBlue(){
       return colorSensor.blue();
    }
    public double getYellow() {
        return (colorSensor.green() + colorSensor.red()) / 2.0;
    }
}
