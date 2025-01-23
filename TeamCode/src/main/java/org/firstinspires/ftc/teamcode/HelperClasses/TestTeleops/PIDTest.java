package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;

@TeleOp(group = "Teleop", name = "PID Test")
public class PIDTest extends OpMode {
	WheelIntake wheel;
	@Override
	public void init() {
		wheel = new WheelIntake(hardwareMap);
	}

	@Override
	public void loop() {
		wheel.update(gamepad1.left_stick_x);
		telemetry.addLine(wheel.toString());
		telemetry.update();
	}
}