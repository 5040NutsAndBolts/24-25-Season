package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class ScissorMech {
		private final DcMotor scissorMotor;
		private final LimitSwitch maximumSwitch;
		private final LimitSwitch minimumSwitch;
		private final CRServo intakeServo;
		private final Servo tiltServo;

		public ScissorMech(HardwareMap hardwareMap) {
			scissorMotor = hardwareMap.get(DcMotor.class, "Scissor Motor");
			maximumSwitch = new LimitSwitch(hardwareMap, "Max Scissor Switch");
			minimumSwitch = new LimitSwitch(hardwareMap, "Min Scissor Switch");
			intakeServo = hardwareMap.get(CRServo.class, "Scissor Intake Servo");
			tiltServo = hardwareMap.get(Servo.class, "Tilt Servo");
			scissorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		}

		public void extend(double in ) {
			if (Math.abs(in) < .06)
				in = 0;

			if((maximumSwitch.isPressed() && (in > 0)) || (minimumSwitch.isPressed() && (in < 0)))
				return;

			if(maximumSwitch.isPressed())
				tiltServo.setPosition(1);
			else
				tiltServo.setPosition(0);

			scissorMotor.setPower(in);
		}
		public void spin (boolean in, boolean out){
			if(in && !out)
				intakeServo.setPower(1);
			if(!in && out)
				intakeServo.setPower(-1);
			else
				intakeServo.setPower(0);
		}

		@NonNull
		@Override
		public String toString() {
			return
				"Scissor Motor Power: " + scissorMotor.getPower() + "\n" +
				"Scissor Motor Position: " + scissorMotor.getCurrentPosition() + "\n" +
				"Maximum Switch: " + maximumSwitch.isPressed() + "\n" +
				"Minimum Switch: " + minimumSwitch.isPressed() + "\n" +
				"Intake Servo Power: " + intakeServo.getPower() + "\n" +
				"Tilt Servo Position: " + tiltServo.getPosition();
		}
}

