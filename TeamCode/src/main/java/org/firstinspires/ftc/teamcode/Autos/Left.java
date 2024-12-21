package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Left NC", group = "Autonomous")
public class Left extends AutoOpMode {
	@Override
	public void loop() {
		dt.drive(0, 0, 0);
		odo.updateOdoPosition();
		//MOVE OUT FROM BEHIND SUBMERSIBLE
		while(odo.centerE < 5507) {
			telemetry.addLine("MOVE OUT FROM BEHIND SUBMERSIBLE");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE > 5507) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//MOVING FORWARD IN FRONT OF BRICK LINE
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 17010) {
			telemetry.addLine("MOVING FORWARD IN FRONT OF BRICK LINE");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 17050) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//MOVING IN FRONT OF BRICK 1
		while(odo.centerE < 9107) {
			telemetry.addLine("MOVING IN FRONT OF BRICK 1");
			dt.drive(0, .3, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE > 9107) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		ElapsedTime matchTime = new ElapsedTime();
		//MOVING BACKWARDS INTO ZONE
		while(matchTime.seconds() < 4) {
			telemetry.addLine("MOVING BACKWARDS INTO ZONE");
			dt.drive(-.5, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//MOVING AROUND BRICK 1
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 2500) {
			telemetry.addLine("MOVING AROUND BRICK 1");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 2500) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);
		while(odo.centerE > 6007) {
			telemetry.addLine("MOVING AROUND BRICK 1");
			dt.drive(0, -.4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE < 6007) { //ERROR CORRECTION
			telemetry.addLine("MOVING AROUND BRICK 1");
			dt.drive(0, .2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);
		matchTime.reset();
		while(matchTime.seconds() < 1) {
			dt.drive(-.3, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//PUSHING BRICK 1 INTO NET ZONE
		while(odo.centerE < 12007) {
			telemetry.addLine("PUSHING BRICK 1 INTO NET ZONE");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE > 12007) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);
	}
}
