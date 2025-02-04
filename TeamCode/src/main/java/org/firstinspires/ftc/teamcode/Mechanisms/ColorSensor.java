
package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.RobotOpMode.Color;


public class ColorSensor {

    private final com.qualcomm.robotcore.hardware.ColorSensor colorSensor;

    public ColorSensor(HardwareMap hardwareMap, String name) {
        colorSensor = hardwareMap.get(com.qualcomm.robotcore.hardware.ColorSensor.class,  name);
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
    public Color getBest () {
        if(getRed () > getYellow() && getRed() > getBlue())
            return Color.red;
        else if (getBlue() > getYellow() && getBlue() > getRed())
            return Color.blue;
        else if (getYellow() > getRed() && getYellow() > getBlue())
            return Color.yellow;
        else return Color.other;
    }
}
