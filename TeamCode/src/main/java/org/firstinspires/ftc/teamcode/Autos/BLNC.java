package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "BLNC", group = "Autonomous")
public class BLNC extends AutoOpMode {
	@Override
	public void loop() {
		dt.drive(0, 0, 0);
		odo.updatePositionRoadRunner();
		//STRAFING LEFT
		while(odo.centerE < 6007) {
			telemetry.addLine("MOVING POS 1");
			dt.drive(0, -.4, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}if(odo.centerE > 6007) { //ERROR CORRECTION
			dt.drive(0, .2, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//MOVING FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 17010) {
			telemetry.addLine("MOVING POS 2");
			dt.drive(-.4, 0, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 17050) { //ERROR CORRECTION
			dt.drive(.2, 0, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//STRAFING LEFT
		while(odo.centerE < 9107) {
			telemetry.addLine("MOVING POS 3");
			dt.drive(0, -.4, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}if(odo.centerE > 9107) { //ERROR CORRECTION
			dt.drive(0, .2, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		ElapsedTime t = new ElapsedTime();
		//MOVING BACKWARDS
		while(t.seconds() < 3) {
			telemetry.addLine("MOVING POS 4");
			dt.drive(.4, 0, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		//SLIGHT MOVE FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) < 2500) {
			telemetry.addLine("MOVING POS 5");
			dt.drive(-.4, 0, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}if((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 2500) { //ERROR CORRECTION
			dt.drive(.2, 0, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0, 0, 0);

		//STRAFING LEFT
		while(odo.centerE < 10007) {
			telemetry.addLine("MOVING POS 6");
			dt.drive(0, -.4, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}if(odo.centerE > 10007) { //ERROR CORRECTION
			dt.drive(0, .2, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		t.reset();
		//MOVING BACKWARDS
		while(t.seconds() < 3) {
			telemetry.addLine("MOVING POS 7");
			dt.drive(.4, 0, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		while(true) updateOdoTelemetry();
	}

}
