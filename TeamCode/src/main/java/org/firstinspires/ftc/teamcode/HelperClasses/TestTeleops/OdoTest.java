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
		odo = new Odometry(hardwareMap, 5018.842708/2, 5018.842708/2, 1876.794122);
	}

	@Override
	public void loop() {
		odo.update();
		telemetry.addLine(odo.toString());
		telemetry.update();
	}
}
