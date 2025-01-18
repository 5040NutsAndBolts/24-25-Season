package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotOpMode;

import java.util.ArrayList;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap);
    }

    protected void updateOdoTelemetry() {
        telemetry.addLine(odo.toString());
        telemetry.update();
    }
}