package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp (name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        dt.toggleSlowMode(gamepad1.x);

        wheel.rawLift (gamepad2.left_stick_y);
        wheel.spin(gamepad2.right_trigger,gamepad2.left_trigger);

        scissor.extend(Math.min(Math.max(-.7, gamepad2.right_stick_y), .7));
        scissor.spin(gamepad2.left_bumper, gamepad1.right_bumper);

        telemetry.addLine("SLOWMODE: " + dt.isSlow());
        telemetry.update();
    }
}