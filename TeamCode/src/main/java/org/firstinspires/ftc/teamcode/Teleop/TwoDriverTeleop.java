package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp (name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        drivetrain.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        drivetrain.toggleSlowMode (gamepad1.x || wheel.getPosition() > 1100);

        wheel.update(gamepad1.right_stick_y);
        wheel.spin (gamepad2.right_trigger,gamepad2.left_trigger);

        telemetry.addLine(drivetrain.toString());
        telemetry.addLine(wheel.toString());
        telemetry.update();
    }
}