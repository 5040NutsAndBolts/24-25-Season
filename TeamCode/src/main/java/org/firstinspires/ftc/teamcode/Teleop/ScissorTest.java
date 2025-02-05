package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.ScissorMech;
import org.firstinspires.ftc.teamcode.RobotOpMode.Color;

@TeleOp( name = "ScissorTest")
public class ScissorTest extends OpMode {
	ScissorMech scissor;
	@Override
	public void init() {
		scissor = new ScissorMech(hardwareMap);
		scissor.color = Color.red;
	}

	@Override
	public void loop() {
		scissor.updateScissor(gamepad1.right_stick_y);

		telemetry.addLine(scissor.toString());
		telemetry.update();
	}
}