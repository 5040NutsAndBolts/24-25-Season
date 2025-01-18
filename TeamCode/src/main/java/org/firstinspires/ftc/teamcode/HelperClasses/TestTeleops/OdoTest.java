package org.firstinspires.ftc.teamcode.HelperClasses.TestTeleops;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "odotest", group = "Teleop")
public class OdoTest extends OpMode {
	private Odometry odo;
	@Override
	public void init() {
		odo = new Odometry(hardwareMap);
		odo.neutral();
	}

	@Override
	public void loop() {
		odo.update();
		telemetry.addLine(odo.toString());
		telemetry.update();
	}
}
