package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.ArduCam;
import org.firstinspires.ftc.teamcode.Mechanisms.LineSensor;
import org.firstinspires.ftc.teamcode.RobotOpMode;

/**
 * @author Team 5040
 */

@Disabled
public class AutoMethods extends RobotOpMode {
    public enum AllianceColor {red, blue}
    protected ArduCam cam;
    protected LineSensor ls;
    public AutoMethods(HardwareMap hardwareMap) {
        super(hardwareMap);
        ls = new LineSensor(hardwareMap);
        cam = new ArduCam();
    }

    /**
     *
     */
}