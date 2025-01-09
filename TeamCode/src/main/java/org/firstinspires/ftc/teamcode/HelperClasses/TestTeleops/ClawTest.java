package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.Claw;
@TeleOp(name = "Claw Test", group = "Teleop")
public class ClawTest extends OpMode {

	Claw claw;

	@Override
	public void init() {
		claw = new Claw(hardwareMap);
	}

	@Override
	public void loop() {
		telemetry.addLine(claw.toString());
		telemetry.update();

		claw.liftSlides(gamepad1.left_stick_y);
		claw.rollClaw(gamepad1.left_bumper, gamepad1.right_bumper);
		claw.pinch(gamepad1.right_trigger > .05, gamepad1.left_trigger > .05);
	}
}