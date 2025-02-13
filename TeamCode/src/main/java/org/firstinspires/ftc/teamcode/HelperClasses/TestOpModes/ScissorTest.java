package org.firstinspires.ftc.teamcode.HelperClasses.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;
import org.firstinspires.ftc.teamcode.RobotOpMode.teamColor;

@TeleOp( name = "ScissorTest")
public class ScissorTest extends OpMode {
	WheelIntake scissor;
	@Override
	public void init() {
		scissor = new WheelIntake(hardwareMap);
		scissor.setTeamColour(teamColor.red);
	}

	@Override
	public void loop() {
		//subWheel.updateScissor(gamepad1.right_stick_y);
		scissor.spin(gamepad1.left_bumper, gamepad1.right_bumper);
		scissor.tiltCarriage(gamepad1.dpad_up, gamepad1.dpad_down);

		telemetry.addLine(scissor.toString());
		telemetry.update();
	}
}