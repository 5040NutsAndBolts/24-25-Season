package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;

@TeleOp(name="Odometry Test", group="Teleop")
public class OdometryTestOpMode extends OpMode {
    private Odometry odo;

    @Override
    public void init() {
        odo = new Odometry(hardwareMap, telemetry);
        odo.neutral();
    }

    @Override
    public void loop() {
        odo.update();
        odo.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}