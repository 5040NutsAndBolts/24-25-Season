package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "BRNC", group = "Autonomous")
public class BRNC extends AutoOpMode {
	@Override
	public void loop() {
		dt.drive(0, 0, 0);
		odo.updatePositionRoadRunner();
		//STRAFING RIGHT
		while(odo.centerE > -6957) {
			telemetry.addLine("MOVING POS 1");
			dt.drive(0, .4, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}if(odo.centerE < -6957) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
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

		//STRAFING RIGHT
		while(odo.centerE > -9657) {
			telemetry.addLine("MOVING POS 3");
			dt.drive(0, .4, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}if(odo.centerE < -9657) { //ERROR CORRECTION
			dt.drive(0, -.2, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}
		dt.drive(0,0,0);

		ElapsedTime t = new ElapsedTime();
		//MOVING FORWARD
		while((((double) (Math.abs(odo.leftE) + Math.abs(odo.rightE))) / 2.0) > 0 && t.seconds() < 4) {
			telemetry.addLine("MOVING POS 4");
			dt.drive(.4, 0, 0);
			odo.updatePositionRoadRunner();
			updateOdoTelemetry();
		}

		terminateOpModeNow();

		//STRAFING RIGHT
	}

}
