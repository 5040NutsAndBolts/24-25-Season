package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;

public class PIDTest extends OpMode {
	WheelIntake wheel;
	@Override
	public void init() {
		wheel = new WheelIntake(hardwareMap);
	}

	@Override
	public void loop() {
		wheel.spin(gamepad1.left_stick_y, gamepad1.right_stick_y);
		wheel.lift(gamepad1.left_stick_x);
		telemetry.addLine(wheel.toString());
		telemetry.update();

	}
}
