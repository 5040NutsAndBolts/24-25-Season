package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "Odo Test", group = "Teleop")
public class OdoTest extends OpMode {
    private Odometry odo = new Odometry(hardwareMap);

    @Override
    public void init() {
        odo.neutral();
    }

    @Override
    public void loop() {
        odo.update();
        telemetry.addLine("x"+odo.x);
        telemetry.addLine("y"+odo.y);
        telemetry.addLine("theta"+odo.theta);
    }
}