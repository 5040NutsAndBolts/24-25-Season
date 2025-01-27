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

		scissor.extend(Math.min(Math.max(-.7, gamepad2.right_stick_y), .7));
		scissor.spin(gamepad2.left_trigger, gamepad1.right_trigger);
		scissor.tiltCarriage(gamepad2.left_bumper, gamepad2.right_bumper);

		telemetry.addLine(scissor.toString());
		telemetry.addLine(""+gamepad2.left_trigger);
		telemetry.addLine(""+gamepad2.right_trigger);
		telemetry.update();
	}
}
