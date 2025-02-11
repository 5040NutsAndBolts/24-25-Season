package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Left NC", group = "Autonomous")
public class Left extends AutoOpMode {
	private enum State {
		FIRST_ALIGNMENT_WITH_SUBMERSIBLE,
		FIRST_FORWARD_TOWARD_SUBMERSIBLE,
		FIRST_BRING_DOWN_SLIDES,
		FIRST_BACK_AWAY_FROM_SUBMERSIBLE,
		PARK
	}

	private State curState = State.FIRST_ALIGNMENT_WITH_SUBMERSIBLE;
	@Override
	public void loop() {
		updateTelemetry();

		switch(curState) {
			case FIRST_ALIGNMENT_WITH_SUBMERSIBLE: {
				if (odo.centerE < 5000) { //REPLACE VALUE
					drivetrain.drive(0, 1, 0);
				} else {
					drivetrain.drive(0, -.5, 0);
					curState = State.FIRST_FORWARD_TOWARD_SUBMERSIBLE;
					break;
				}
			}
			case FIRST_FORWARD_TOWARD_SUBMERSIBLE: {
				if((odo.rightE + odo.leftE) / 2 > 10000) { //TEST VALUE
					drivetrain.drive(-1,0 , 0);
				} else {
					drivetrain.drive(0, 0, 0);
					curState = State.FIRST_BRING_DOWN_SLIDES;
					break;
				}
				wheel.setTopTarget(1600);//TEST VALUE
				wheel.updateTopMotor();

			}
			case FIRST_BRING_DOWN_SLIDES: {
				boolean topSlidesWithin =false;
				wheel.setTopTarget(0); //KEEP VALUE
				wheel.updateTopMotor();
				wheel.setBottomTarget(-1000);//TEST VALUE
				wheel.updateBottomMotor();
				if(wheel.getTopPosition() > 10 || wheel.getTopPosition() < 10) {
					curState = State.FIRST_BACK_AWAY_FROM_SUBMERSIBLE;
					break;
				}
			}
			case FIRST_BACK_AWAY_FROM_SUBMERSIBLE: {
				if((odo.rightE + odo.leftE) / 2 > 1) // REPLACE VALUE
					drivetrain.drive(1,0,0);
				wheel.setBottomTarget(0);
				if((odo.rightE + odo.leftE) / 2 < 0) {
					curState = State.PARK;
					break;
				}
			}
			case PARK: {
				if(odo.centerE > -3000)
					drivetrain.drive(.2,-1,0);
				else stop();
			}
			default:
				throw new IllegalStateException("NO CURRENT AUTO STATE");
		}
	}
}