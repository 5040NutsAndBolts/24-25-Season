package org.firstinspires.ftc.teamcode.Mechanisms;
import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class ScissorMech {
		private final DcMotor scissorMotor;
		private final LimitSwitch maximumSwitch,minimumSwitch;
		private final CRServo intakeServo;
		private final Servo tiltServo;
		private final PID scissorController = new PID(0,0,0, this::getPosition);

		public ScissorMech(HardwareMap hardwareMap) {
			scissorMotor = hardwareMap.get(DcMotor.class, "Scissor Motor");
			maximumSwitch = new LimitSwitch(hardwareMap, "Max Scissor Switch");
			minimumSwitch = new LimitSwitch(hardwareMap, "Min Scissor Switch");
			intakeServo = hardwareMap.get(CRServo.class, "Scissor Intake Servo");
			tiltServo = hardwareMap.get(Servo.class, "Scissor Tilt Servo");
			scissorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		}

		public void extend(double in ) {
			if (Math.abs(in) < .06)
				in = 0;
			else if(in > .7)
				scissorMotor.setPower(.7);
			else if (in < -.7)
				scissorMotor.setPower(-.7);

			if((maximumSwitch.isPressed() && (in > 0)) || (minimumSwitch.isPressed() && (in < 0)))
				return;

			scissorMotor.setPower(in);
		}
		public void spin (boolean in, boolean out){
			if(in && !out)
				intakeServo.setPower(-1);
			else if(out)
				intakeServo.setPower(1);
			else
				intakeServo.setPower(0);
		}
		public void spin (double in, double out){
				spin(in > .06, out > .06);
		}
		public void tiltCarriage(boolean up, boolean down) {
				if(up && !down)
					tiltServo.setPosition(1);
				else if(!up && down)
					tiltServo.setPosition(0);
		}
		public double getPosition() {
			return scissorMotor.getCurrentPosition();
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