package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;

@TeleOp(group = "Teleop", name = "WheelTest")
public class WheelTest extends OpMode {
	WheelIntake wheel;
	@Override
	public void init() {
		wheel = new WheelIntake(hardwareMap);
	}

	@Override
	public void loop() {
		wheel.update(gamepad1.left_stick_y);
		telemetry.addLine(wheel.toString());
		telemetry.update();
	}
}
