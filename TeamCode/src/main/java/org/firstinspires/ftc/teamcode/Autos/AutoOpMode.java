package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.HelperClasses.Position;
import org.firstinspires.ftc.teamcode.RobotOpMode;

import java.util.Random;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap, 5018.842708/2, 5018.842708/2, 1876.794122, 337.4011905);
    }

    protected void updateOdoTelemetry() {
        telemetry.addLine(odo.toString());
        telemetry.update();
    }
}