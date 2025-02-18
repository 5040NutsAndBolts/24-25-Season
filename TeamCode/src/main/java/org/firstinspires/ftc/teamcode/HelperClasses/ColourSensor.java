
package org.firstinspires.ftc.teamcode.HelperClasses;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotOpMode.teamColor;


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
    public teamColor getBest () {
         if (getBlue() > getRed() && getBlue() > 140)
            return teamColor.blue;
        else if (getRed() > getBlue() && getRed() > 105 && colorSensor.green() < 360)
            return teamColor.red;
        else return teamColor.noColor;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "R: "+getRed() + "\n" +
                "G: "+colorSensor.green() + "\n" +
                "B: "+getBlue()+ "\n" +
                "Y: "+getYellow()+ "\n" +
                "Best Fit: " + getBest();
    }

}