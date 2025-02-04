package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Right NC", group = "Autonomous")
public class Right extends AutoOpMode {
	@Override
	public void loop() {
		drivetrain.drive(0, 0, 0);
		odo.updateOdoPosition();
		//MOVING OUT FROM BEHIND SUBMERSIBLE
		while(odo.centerE < 6200) {
			telemetry.addLine("MOVE OUT FROM BEHIND SUBMERSIBLE");
			drivetrain.drive(0, -.4, 0);
			odo.updateOdoPosition();
			updateTelemetry();
		}if(odo.centerE > 7200) { //ERROR CORRECTION
			drivetrain.drive(0, .2, 0);
			odo.updateOdoPosition();
			updateTelemetry();
		}
		drivetrain.drive(0, 0, 0);

		//MOVING FORWARD IN FRONT OF BRICK LINE
		while(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) < 16300) {
			telemetry.addLine("MOVING FORWARD IN FRONT OF BRICK LINE");
			drivetrain.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateTelemetry();
		}if(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) > 16300) { //ERROR CORRECTION
			drivetrain.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateTelemetry();
		}
		drivetrain.drive(0, 0, 0);

		//MOVING IN FRONT OF SPECIMEN
		while(odo.centerE < 9600) {
			telemetry.addLine("MOVING IN FRONT OF SPECIMEN");
			drivetrain.drive(0, -.4, 0);
			odo.updateOdoPosition();
			updateTelemetry();
		}if(odo.centerE > 10500) { //ERROR CORRECTION
			drivetrain.drive(0, .2, 0);
			odo.updateOdoPosition();
			updateTelemetry();
		}
		drivetrain.drive(0, 0, 0);

		//MOVE BACKWARDS INTO PARK ZONE
		ElapsedTime parkTimer = new ElapsedTime();
		while(parkTimer.seconds() < 4) {
			telemetry.addLine("MOVE BACKWARDS INTO PARK ZONE");
			drivetrain.drive(-.3, 0, 0);
			odo.updateOdoPosition();
			updateTelemetry();
		}
		terminateOpModeNow();
	}
}
