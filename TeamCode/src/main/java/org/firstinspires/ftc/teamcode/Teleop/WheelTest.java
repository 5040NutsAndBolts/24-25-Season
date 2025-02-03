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
		wheel.update(gamepad2.left_stick_y);
		wheel.spin(gamepad2.right_trigger,gamepad2.left_trigger);
		telemetry.addLine(wheel.toString());
		telemetry.update();
	}
}
