package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

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
		scissor.teamColour = Color.red;
	}

	@Override
	public void loop() {
		//scissor.updateScissor(gamepad1.right_stick_y);
		scissor.spin(gamepad1.left_bumper, gamepad1.right_bumper);
		scissor.tiltCarriage(gamepad1.dpad_up, gamepad1.dpad_down);

		telemetry.addLine(scissor.toString());
		telemetry.update();
	}
}