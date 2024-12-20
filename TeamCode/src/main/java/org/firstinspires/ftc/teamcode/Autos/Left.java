package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Left NC", group = "Autonomous")
public class Left extends AutoOpMode {
	@Override
	public void loop() {
		dt.drive(0, 0, 0);
		odo.updateOdoPosition();
		//STRAFING LEFT
		while(odo.centerE < 5507) {
			telemetry.addLine("MOVING POS 1");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE > 5507) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//MOVING FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 17010) {
			telemetry.addLine("MOVING POS 2");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 17050) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//STRAFING LEFT
		while(odo.centerE < 9107) {
			telemetry.addLine("MOVING POS 3");
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
		//MOVING BACKWARDS
		while(matchTime.seconds() < 4) {
			telemetry.addLine("MOVING POS 4");
			dt.drive(-.5, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//SLIGHT MOVE FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 2500) {
			telemetry.addLine("MOVING POS 5");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 2500) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//STRAFING RIGHT
		while(odo.centerE > 7007) {
			telemetry.addLine("MOVING POS 6");
			dt.drive(0, -.4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE < 7007) { //ERROR CORRECTION
			dt.drive(0, .2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//SLIGHT MOVE BACKWARDS
		matchTime.reset();
		while(matchTime.seconds() < 2) {
			telemetry.addLine("MOVING POS 7");
			dt.drive(-.6, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//STRAFING LEFT
		while(odo.centerE < 10007) {
			telemetry.addLine("MOVING POS 8");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE > 10007) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//MOVING FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 17010) {
			telemetry.addLine("MOVING POS 9");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 17050) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		matchTime.reset();
		//STRAFING LEFT
		while(odo.centerE < 13000 && matchTime.seconds() < 2) {
			telemetry.addLine("MOVING POS 10");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE > 13000) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		matchTime.reset();
		//MOVING BACKWARDS
		while(matchTime.seconds() < 3) {
			telemetry.addLine("MOVING POS 12");
			dt.drive(-.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//MOVING FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 17510) {
			telemetry.addLine("MOVING POS 13");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 17050) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		matchTime.reset();
		//STRAFING LEFT
		while(matchTime.seconds() < 3) {
			telemetry.addLine("MOVING POS 14");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		matchTime.reset();
		//MOVING BACKWARDS
		while(matchTime.seconds() < 3) {
			telemetry.addLine("MOVING POS 15");
			dt.drive(-.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//MOVING FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 15510) {
			telemetry.addLine("MOVING POS 16");
			dt.drive(.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 15050) { //ERROR CORRECTION
			dt.drive(-.2, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		matchTime.reset();
		//STRAFING LEFT
		while(matchTime.seconds() < 3) {
			telemetry.addLine("MOVING POS 17");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//STRAFING RIGHT
		while(odo.centerE > 5507) {
			telemetry.addLine("MOVING POS 18");
			dt.drive(0, -.2, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.centerE < 5507) { //ERROR CORRECTION
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		matchTime.reset();
		//MOVING BACKWARDS
		while(matchTime.seconds() < 3) {
			telemetry.addLine("MOVING POS 19");
			dt.drive(-.4, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		matchTime.reset();
		//STRAFING RIGHT
		while(matchTime.seconds() < 10) {
			telemetry.addLine("MOVING POS 20");
			dt.drive(0, .4, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
	}

}
