package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp (name = "2 Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
	@Override
	public void loop() {
		drivetrain.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
		drivetrain.toggleSlowMode(gamepad1.x);
		auto180(gamepad1.y);

		//wheel.updateTopMotor(gamepad2.left_stick_y);
		wheel.spin(gamepad2.right_trigger,gamepad2.left_trigger);

		scissor.updateScissor( gamepad2.right_stick_y);

		subWheel.spin(gamepad1.right_trigger, gamepad1.left_trigger);
		subWheel.tiltCarriage(gamepad2.left_bumper, gamepad2.right_bumper);

		telemetry.addLine("SLOWMODE: " + drivetrain.isSlow());
		telemetry.update();
	}
}