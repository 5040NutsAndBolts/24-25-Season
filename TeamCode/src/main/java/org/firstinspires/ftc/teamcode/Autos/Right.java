package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Right NC", group = "Autonomous")
public class Right extends AutoOpMode {
	@Override

	public void loop() {
		while (odo.centerE < 5000) {
			telemetry.addLine("Moving in front of chambers");
			dt.drive(0, -0.6, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
		if (odo.centerE > 5000){
			dt.drive(0,0.3,0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if(odo.leftE != 0 && odo.rightE != 0) {
			ElapsedTime temp = new ElapsedTime();
			while(temp.seconds() < .7)
				dt.drive(.5, 0, 0);
		}

		while ((Math.abs(odo.leftE) + Math.abs(odo.rightE))/2 < 9500) {
			telemetry.addLine("Moving toward chambers");
			dt.drive(-.5, 0, 0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}if((Math.abs(odo.leftE) + Math.abs(odo.rightE))/2 > 9500) {
			dt.drive(.3,0,0);
			odo.updateOdoPosition();
			updateOdoTelemetry();
		}
	}
	@Override
	public void stop() {
		super.stop();
	}
}
