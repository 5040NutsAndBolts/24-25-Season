package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Claw;

@TeleOp(name = "Claw Test", group = "Teleop")
public class ClawTest extends OpMode {

	Claw claw;

	/**
	 * User-defined init method
	 * <p>
	 * This method will be called once, when the INIT button is pressed.
	 */
	@Override
	public void init() {
		claw = new Claw(hardwareMap);
	}

	/**
	 * User-defined loop method
	 * <p>
	 * This method will be called repeatedly during the period between when
	 * the play button is pressed and when the OpMode is stopped.
	 */
	@Override
	public void loop() {
		telemetry.addLine("Top Motor Position: " + claw.liftMotorTop.getCurrentPosition());
		telemetry.addLine("Bottom Motor Position: " + claw.liftMotorBottom.getCurrentPosition());
		telemetry.addLine("Average Slide Position: " + claw.getSlidePosition());
		telemetry.addLine("Roll Motor Position: " + claw.rollMotor.getCurrentPosition());
		telemetry.addLine("Pinch Servo Position: " + claw.pinchServo.getPosition());
		telemetry.update();

		claw.liftSlides(gamepad1.left_stick_y);
		claw.liftClaw(gamepad1.left_bumper, gamepad1.right_bumper);
		claw.pinch(gamepad1.right_trigger > .05, gamepad1.left_trigger > .05);
	}
}
