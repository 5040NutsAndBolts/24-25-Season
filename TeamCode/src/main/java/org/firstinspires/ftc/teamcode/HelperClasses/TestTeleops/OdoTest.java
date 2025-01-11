package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "odotest", group = "Teleop")
public class OdoTest extends RobotOpMode {
	private Odometry odo;
	@Override
	public void init() {
		super.init();
		odo = new Odometry(hardwareMap);
		dt.neutral();
	}

	@Override
	public void loop() {
		odo.update();
		telemetry.addLine("l: "+odo.toString());
		telemetry.update();
	}
}
