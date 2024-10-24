package org.firstinspires.ftc.teamcode.Teleop;

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
		odo.updatePositionRoadRunner();
		telemetry.addLine("l: "+odo.leftE);
		telemetry.addLine("r: "+odo.rightE);
		telemetry.addLine("c: "+odo.centerE);
		telemetry.update();
	}
}
