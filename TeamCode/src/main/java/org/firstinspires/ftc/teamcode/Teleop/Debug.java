package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "Debug", group = "Teleop")
public class Debug extends RobotOpMode {
	@Override
	public void loop() {
		drivetrain.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
		drivetrain.toggleSlowMode(gamepad1.x);

		wheel.updateSlides(gamepad1.dpad_up ? 1 : gamepad1.dpad_down ? -1 : 0);
		wheel.spin(gamepad1.right_bumper, gamepad1.left_bumper);

		scissor.updateScissor(gamepad1.dpad_right ? 1 : gamepad1.dpad_left ? -1 : 0);
		scissor.spin(gamepad1.right_trigger, gamepad1.left_trigger);
		scissor.tiltCarriage(gamepad1.a, gamepad1.b);

		telemetry.addLine(drivetrain.toString());
		telemetry.addLine(wheel.toString());
		telemetry.addLine(scissor.toString());
		telemetry.addLine("SLOWMODE: " + drivetrain.isSlow());
		telemetry.update();
	}
}
