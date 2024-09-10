package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;

public class OdometryTestOpMode extends OpMode {
    private Odometry odo;

    @Override
    public void init() {
        odo = new Odometry(hardwareMap);
        odo.neutral();
    }

    @Override
    public void loop() {
        odo.update();
        telemetry.addLine(odo.toString());
    }
}