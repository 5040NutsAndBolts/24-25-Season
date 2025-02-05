
package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotOpMode.Color;


public class ColourSensor {
    private final ColorSensor colorSensor;

    public ColourSensor(HardwareMap hardwareMap, String name) {
        colorSensor = hardwareMap.get(ColorSensor.class,  name);
    }
    public double getRed(){
       return colorSensor.red();
    }
    public double getBlue(){
       return colorSensor.blue();
    }
    public double getYellow() {
        if (colorSensor.green() < 50 || colorSensor.red() < 50) //If either is very low, it's likely not yellow
            return 0;
        return (colorSensor.green() + colorSensor.red()) / 2.0;
    }
    public Color getBest () {
        if(getRed () > getYellow() && getRed() > getBlue())
            return Color.red;
        else if (getBlue() > getYellow() && getBlue() > getRed())
            return Color.blue;
        else if (getYellow() > getRed() && getYellow() > getBlue())
            return Color.yellow;
        else return Color.noColor;
    }
}