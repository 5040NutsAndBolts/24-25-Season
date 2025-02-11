package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.teamcode.HelperClasses.ColourSensor;
import org.firstinspires.ftc.teamcode.HelperClasses.LimitSwitch;
import org.firstinspires.ftc.teamcode.HelperClasses.PID;
import org.firstinspires.ftc.teamcode.RobotOpMode.Color;

public class ScissorMech {
		private final DcMotor scissorMotor;
		private final LimitSwitch maximumSwitch,minimumSwitch;
		private final CRServo leftIntakeServo, rightIntakeServo;
		private final Servo tiltServo;
		private int scissorMotorOffset;
		private final PID scissorController;
		private final ColourSensor colourSensor;
		public Color teamColour;
		private boolean autoSpitOverride;
		private boolean spitOut = false;

		public ScissorMech(@NonNull HardwareMap hardwareMap) {
			scissorMotor = hardwareMap.get(DcMotor.class, "Scissor Motor");
			maximumSwitch = new LimitSwitch(hardwareMap, "Max Scissor Switch");
			minimumSwitch = new LimitSwitch(hardwareMap, "Min Scissor Switch");
			leftIntakeServo = hardwareMap.get(CRServo.class, "Left Scissor Intake Servo");
			rightIntakeServo = hardwareMap.get(CRServo.class, "Right Scissor Intake Servo");
			rightIntakeServo.setDirection(DcMotorSimple.Direction.REVERSE);
			tiltServo = hardwareMap.get(Servo.class, "Scissor Tilt Servo");
			scissorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
			scissorMotorOffset = scissorMotor.getCurrentPosition();
			scissorController = new PID(.5, 0, 0, this::getPosition);
			colourSensor = new ColourSensor(hardwareMap, "Scissor Colour Sensor");
		}

		public void updateScissor() {
			updateScissor(scissorController.autoControl());
		}

		public void updateScissor(double in) {
			if(minimumSwitch.isPressed()) {
				resetPosition();
				if(in < 0)
					return;
			}
			else if (maximumSwitch.isPressed() && in > 0)
				return;
			else if (Math.abs(in) < .06)
				in = 0;
			scissorMotor.setPower(scissorController.teleOpControl(in));
		}

		public void setTarget (int target) {
			scissorController.setTarget(target);
		}

		private void resetPosition() {
			scissorMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
			scissorMotorOffset = scissorMotor.getCurrentPosition();
		}

		public void spin (boolean in, boolean out) {
			if(minimumSwitch.isPressed())
				resetPosition();
			if(colourSensor.getBest() == (teamColour == Color.red ? Color.blue : Color.red) && !autoSpitOverride) // If colour sensor sees the opposite teams' colour, spit out
				spitOut = true;

			if(in && !out && !spitOut){
				leftIntakeServo.setPower(1);
				rightIntakeServo.setPower(1);
			}
			else if(out && !in || spitOut){
				leftIntakeServo.setPower(-1);
				rightIntakeServo.setPower(-1);
			}else {
				leftIntakeServo.setPower(0);
				rightIntakeServo.setPower(0);
			}
		}

		public void spin (double in, double out)  {
			spin(in > .1, out > .1);
		}
		public void tiltCarriage (boolean up, boolean down) {
			if(minimumSwitch.isPressed())
				resetPosition();
			if(up && !down)
				tiltServo.setPosition(1);
			else if(!up && down)
				tiltServo.setPosition(0);
		}

		public double getPosition () {
			return scissorMotor.getCurrentPosition() - scissorMotorOffset;
		}

		private boolean lastPressed = false;
		public void toggleAutoSpitOverride(boolean input) {
			if (lastPressed != input && input) autoSpitOverride = !autoSpitOverride;
			lastPressed = input;
		}


		@NonNull
		@Override
		public String toString() {
			return
				"Scissor Motor Power: " + scissorMotor.getPower() + "\n" +
				"Scissor Motor Position: " + scissorMotor.getCurrentPosition() + "\n" +
				"Maximum Switch: " + maximumSwitch.isPressed() + "\n" +
				"Minimum Switch: " + minimumSwitch.isPressed() + "\n" +
				"Left Intake Servo Power: " + leftIntakeServo.getPower() + "\n" +
				"Right Intake Servo Power: " + rightIntakeServo.getPower() + "\n" +
				"Tilt Servo Position: " + tiltServo.getPosition() + "\n" +
				"Auto Spit Override: " + autoSpitOverride + "\n" +
				"Colour Sensor: " + colourSensor.toString();
		}
}