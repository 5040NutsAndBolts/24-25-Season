package org.firstinspires.ftc.teamcode.HelperClasses;

import com.acmerobotics.roadrunner.Twist2d;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.ThreeDeadWheelLocalizer;

public class Odometry {
    private final ThreeDeadWheelLocalizer localizer;

    public Odometry(HardwareMap hardwareMap) {
        localizer = new ThreeDeadWheelLocalizer(hardwareMap, 340.6280288);
    }
    public Twist2d yas () {
        return localizer.update().value();
    }
}
