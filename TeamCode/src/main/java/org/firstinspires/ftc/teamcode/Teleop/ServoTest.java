package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ServoOp")
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
		CRServo servo = hardwareMap.get(CRServo.class, "crServo");

		waitForStart();

		while (opModeIsActive()) {
			servo.setPower(gamepad1.right_trigger);
			telemetry.addLine("sp " + servo.getPower());
			telemetry.update();
		}
	}
}
