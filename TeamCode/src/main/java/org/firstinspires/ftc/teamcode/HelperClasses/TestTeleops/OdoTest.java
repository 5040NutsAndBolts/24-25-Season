package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;

@TeleOp(name = "odotest", group = "Teleop")
public class OdoTest extends OpMode {
	private Odometry odo;
	private Drivetrain drivetrain;
	@Override
	public void init() {
		odo = new Odometry(hardwareMap);
		drivetrain = new Drivetrain(hardwareMap);
		drivetrain.neutral();
	}

	@Override
	public void loop() {
		drivetrain.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
		odo.updateOdoPosition();
		telemetry.addLine("l: "+odo.leftE);
		telemetry.addLine("r: "+odo.rightE);
		telemetry.addLine("c: "+odo.centerE);
		telemetry.update();
	}
}
//jack is a bit stupid and doesnt know how to work pid and broke the axon servo progromer christian is the goat
