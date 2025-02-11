package org.firstinspires.ftc.teamcode.HelperClasses.TestOpModes;

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
		odo.updateOdoPosition();
		telemetry.addLine(odo.toString());
		telemetry.update();
	}
}