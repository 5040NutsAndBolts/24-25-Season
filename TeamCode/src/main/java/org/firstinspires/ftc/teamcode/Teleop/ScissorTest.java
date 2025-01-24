package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.ScissorMech;

@TeleOp(group = "Teleop", name = "ScissorTest")
public class ScissorTest extends OpMode {
	ScissorMech scissor;
	@Override
	public void init() {
		scissor = new ScissorMech(hardwareMap);
	}

	@Override
	public void loop() {
		scissor.extend(gamepad1.left_stick_y);
		scissor.spin(gamepad1.left_bumper, gamepad1.right_bumper);
		telemetry.addLine(scissor.toString());
		telemetry.update();
	}
}
