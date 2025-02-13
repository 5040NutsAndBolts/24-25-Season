package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Left NC", group = "Autonomous")
public class Left extends AutoOpMode {
	@Override
	public void loop() {
		while(odo.centerE < 5000) {
			drivetrain.drive(0, .6, 0);
			odo.updateOdoPosition();
			telemetry.addLine("LONGITUDINAL SUBMERSIBLE ALIGNMENT");
			updateTelemetry();
		}
		wheel.setTopTarget(1600); //TEST VALUE
		while((odo.rightE + odo.leftE) / 2 < 9200) {
			drivetrain.drive(.6,0 , 0);
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("LATERAL SUBMERSIBLE ALIGNMENT");
			updateTelemetry();
		}
		while(wheel.getTopPosition() < 1600) {
			drivetrain.drive(0,0 , 0);
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("SLIDE EXACTING SUBMERSIBLE ALIGNMENT");
			updateTelemetry();
		}
		wheel.setTopTarget(0);
		wheel.setBottomTarget(300);
		while(wheel.getBottomPosition() < 300) {
			wheel.spin(.7,.7);
			wheel.updateBottomMotor();
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("DOWNFORCE SLIDE MOVEMENT");
			updateTelemetry();
		}
		wheel.setBottomTarget(0);
		while(wheel.getBottomPosition() > 0 && wheel.getTopPosition() > 0) {
			wheel.updateBottomMotor();
			wheel.updateTopMotor();
			odo.updateOdoPosition();
			telemetry.addLine("DROPPING SLIDES");
			updateTelemetry();
		}

		stop();

		/*
		switch(curState) {
			case FIRST_ALIGNMENT_WITH_SUBMERSIBLE: {
				if (odo.centerE < 5000) { //REPLACE VALUE
					drivetrain.drive(0, -.7, 0);
				} else {
					drivetrain.drive(0, .5, 0);
					curState = State.FIRST_FORWARD_TOWARD_SUBMERSIBLE;
				}
			}
			case FIRST_FORWARD_TOWARD_SUBMERSIBLE: {
				if((odo.rightE + odo.leftE) / 2 > 10000) { //TEST VALUE
					drivetrain.drive(-.7,0 , 0);
				} else {
					drivetrain.drive(0, 0, 0);
					curState = State.FIRST_BRING_DOWN_SLIDES;
				}
				wheel.setTopTarget(1600);//TEST VALUE
				wheel.updateTopMotor();
			}
			case FIRST_BRING_DOWN_SLIDES: {
				wheel.setTopTarget(0); //KEEP VALUE
				wheel.updateTopMotor();
				wheel.setBottomTarget(-1000);//TEST VALUE
				wheel.updateBottomMotor();
				if(wheel.getTopPosition() > 10 || wheel.getTopPosition() < 10) {
					curState = State.FIRST_BACK_AWAY_FROM_SUBMERSIBLE;
				}
			}
			case FIRST_BACK_AWAY_FROM_SUBMERSIBLE: {
				if((odo.rightE + odo.leftE) / 2 > 1) // REPLACE VALUE
					drivetrain.drive(.7,0,0);
				wheel.setBottomTarget(0);
				if((odo.rightE + odo.leftE) / 2 < 0) {
					curState = State.PARK;
				}
			}
			case PARK: {
				if(odo.centerE > -3000)
					drivetrain.drive(.2,1,0);
				else stop();
			}
			default:
				while (true) {odo.updateOdoPosition();updateTelemetry();}
		}*/
	}
}