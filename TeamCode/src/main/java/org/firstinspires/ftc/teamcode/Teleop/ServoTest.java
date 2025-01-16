package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Claw;

@TeleOp (name = "servotest", group = "Teleop")
public class ServoTest extends LinearOpMode {

	/**
	 * Override this method and place your code here.
	 * <p>
	 * Please do not catch {@link InterruptedException}s that are thrown in your OpMode
	 * unless you are doing it to perform some brief cleanup, in which case you must exit
	 * immediately afterward. Once the OpMode has been told to stop, your ability to
	 * control hardware will be limited.
	 *
	 * @throws InterruptedException When the OpMode is stopped while calling a method
	 *                              that can throw {@link InterruptedException}
	 */
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