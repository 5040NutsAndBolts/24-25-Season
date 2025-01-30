package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Left NC", group = "Autonomous")
public class Left extends AutoOpMode {
	private enum State {

	}

	private State curState;
	@Override
	public void loop() {
		updateTelemetry();

		switch(curState) {
			default:
				break;
		}
	}
}