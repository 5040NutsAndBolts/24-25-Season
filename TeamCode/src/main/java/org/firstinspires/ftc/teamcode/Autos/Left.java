package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Left NC", group = "Autonomous")
public class Left extends AutoOpMode {
	@Override
	public void loop() {
		odo.resetOdometry();
		while(odo.centerE < 5000) {
			drivetrain.drive(0, .6, 0);
			odo.updateOdoPosition();
			telemetry.addLine("LOOP 1");
			updateTelemetry();
		}
		ElapsedTime e = new ElapsedTime();
		wheel.setTopTarget(1500);
		while((odo.rightE + odo.leftE) / 2 < 9300 && e.seconds() < 4) {
			drivetrain.drive(.5,0 , 0);
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("LOOP 2");
			updateTelemetry();
		}
		e.reset();
		while(wheel.getTopPosition() < 1550 && e.seconds() < 1) {
			drivetrain.drive(0,0 , 0);
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("LOOP 3");
			updateTelemetry();
		}
		wheel.setTopTarget(0);
		wheel.setBottomTarget(300);
		while(wheel.getBottomPosition() < 300) {
			wheel.spin(.5,0);
			wheel.updateBottomMotor();
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("LOOP 4");
			updateTelemetry();
		}
		wheel.setBottomTarget(0);
		while(wheel.getBottomPosition() > 120) {
			wheel.spin(1,0);
			drivetrain.drive(-.1,0,0);
			wheel.updateBottomMotor();
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("LOOP 4");
			updateTelemetry();
		}
		wheel.setBottomTarget(0);
		if(parkToggle) {
			while ((odo.rightE + odo.leftE) / 2 > 0) {
				drivetrain.drive(-.5, 0, 0);
				wheel.updateTopMotor();
				wheel.updateBottomMotor();
				odo.updateOdoPosition();
				telemetry.addLine("LOOP 2");
				updateTelemetry();
			}
			while(odo.centerE > -14000) {
				drivetrain.drive(-.20, -.6, 0);
				wheel.updateTopMotor();
				wheel.updateBottomMotor();
				odo.updateOdoPosition();
				telemetry.addLine("LOOP 1");
				updateTelemetry();
			}
		}
		stop();
	}
}