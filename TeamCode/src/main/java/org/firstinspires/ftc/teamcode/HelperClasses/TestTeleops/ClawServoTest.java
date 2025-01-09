package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Claw;

@TeleOp(name="ServoOp")
public class ClawServoTest extends LinearOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		Claw servo = new Claw(hardwareMap);

		waitForStart();

		while (opModeIsActive()) {
			telemetry.addLine("claw");
			servo.pinch(gamepad1.right_trigger > .05, gamepad1.left_trigger > .05);
			telemetry.update();
		}
	}
}