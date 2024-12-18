package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Autos.AutoOpMode;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "odotest", group = "Teleop")
public class OdoTest extends OpMode {
	private Odometry odo;
	private Drivetrain dt;
	@Override
	public void init() {
		dt = new Drivetrain(hardwareMap);
		odo = new Odometry(hardwareMap);
		dt.neutral();
		dt.backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
	}

	@Override
	public void loop() {
		odo.update();
		telemetry.addLine(odo.toString());
		telemetry.update();
		//strafe left
		if(gamepad1.a){
			while(odo.curC < 7000) {
				dt.drive(.1,-.3,0);
				odo.update();
				telemetry.addLine(odo.toString());
				telemetry.update();
			}
			dt.drive(.1,0,0);
			stop();
		}
	}

	@Override
	public void stop() {
		super.stop();
		terminateOpModeNow();
	}
}
