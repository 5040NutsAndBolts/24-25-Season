package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Left NC", group = "Autonomous")
public class Left extends AutoOpMode {
	boolean parkToggle = true;
	boolean buttonLogicForParkToggle = false;

	@Override
	public void init() {
		super.init();
		if(gamepad1.dpad_up && !buttonLogicForParkToggle) {
			parkToggle = false;
			buttonLogicForParkToggle = true;
		}else if (!gamepad1.dpad_up)
			buttonLogicForParkToggle = false;
		telemetry.addLine("PARK: " + parkToggle);
		updateOdoTelemetry();
	}

	@Override
	public void loop() {
		dt.drive(0, 0, 0);
		odo.updateOdoPosition();
		//MOVE OUT FROM BEHIND SUBMERSIBLE
		while(odo.centerE > -7200) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("MOVE OUT FROM BEHIND SUBMERSIBLE");
			dt.drive(0, -.4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE <-7500) { //ERROR CORRECTION
			dt.drive(0, .2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.leftE != 0 && odo.rightE != 0) {
			ElapsedTime temp = new ElapsedTime();
			while(temp.seconds() < .7)
				dt.drive(.5, 0, 0);
		}
		dt.drive(0, 0, 0);
		odo.updateOdoPosition();

		//MOVING FORWARD IN FRONT OF BRICK LINE
		while(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) < 15800) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("MOVING FORWARD IN FRONT OF BRICK LINE");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) > 16300) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//MOVE IN FRONT OF SPECIMEN
		while(odo.centerE > -10800) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("MOVE IN FRONT OF SPECIMEN");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE < -11300) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//MOVING SPECIMEN NEAR ZONE
		while(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) > 2400) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("MOVING SPECIMEN NEAR ZONE");
			dt.drive(-.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) < 2400) { //ERROR CORRECTION
			dt.drive(.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//MOVING AWAY FROM SPECIMEN --- ENDS ON LINE (101)
		while(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) < 3600) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("MOVING AWAY FROM SPECIMEN (FORWARD)");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(((Math.abs(odo.leftE) + Math.abs(odo.rightE)) / 2.0) > 3600) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		while(odo.centerE < -4000) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("MOVE IN AWAY FROM SPECIMEN (RIGHT)");
			dt.drive(0, -.4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE > -4000) { //ERROR CORRECTION
			dt.drive(0, .2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		ElapsedTime wallSmashTimer = new ElapsedTime();
		while(wallSmashTimer.seconds() < 2) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("MOVE AWAY FROM SPECIMEN (BACKWARDS)");
			dt.drive(-.3, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}

		//PUSHING SPECIMEN INTO NET ZONE
		while(odo.centerE > -13000) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("PUSHING SPECIMEN INTO NET ZONE");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE <-13000) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);
		odo.updateOdoPosition();
		if(odo.leftE != 0 && odo.rightE != 0) {
			ElapsedTime temp = new ElapsedTime();
			while(temp.seconds() < .7)
				dt.drive(-.5, 0, 0);
		}
		dt.drive(0, 0, 0);
		odo.updateOdoPosition();


		ElapsedTime moveToParkTime = new ElapsedTime();
		while(moveToParkTime.seconds() < 8) {
			telemetry.addLine("PARK: " + parkToggle);
			telemetry.addLine("PARKING");
			dt.drive(0, -.4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.leftE != 0 && odo.rightE != 0) {
			ElapsedTime temp = new ElapsedTime();
			while(temp.seconds() < .7)
				dt.drive(-.5, 0, 0);
		}
		terminateOpModeNow();
	}

	@Override
	public void stop() {
		super.stop();
	}
}
