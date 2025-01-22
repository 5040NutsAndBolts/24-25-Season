package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(group = "Autonomous", name = "TestAuto")
public class TestAuto extends AutoOpMode {
	private enum State {
		MOVEFORWARD,
		LIFTSLIDE
	}

	private State state;

	@Override
	public void init() {
		super.init();
		state = State.MOVEFORWARD;
	}

	@Override
	public void loop() {

		switch(state) {
			case MOVEFORWARD:
				drivetrain.drive(1,0,0);
				if(Math.abs(odo.getInchPosition().y - 12) < .5)
					state = State.LIFTSLIDE;
			case LIFTSLIDE:
				wheel.setPosition(500);
		}

		telemetry.addLine(drivetrain.toString());
		telemetry.update();
	}

}
