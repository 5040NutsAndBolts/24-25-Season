package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HelperClasses.PID;
import org.firstinspires.ftc.teamcode.RobotOpMode.Color;

public class ScissorMech {
		private final DcMotor scissorMotor;
		private final LimitSwitch maximumSwitch,minimumSwitch;
		private final CRServo leftIntakeServo, rightIntakeServo;
		private final Servo tiltServo;
		private int scissorMotorOffset;
		private final PID scissorController;
		private final ColorSensor colorSensor;
		private final Color color;
		private boolean autoSpitOverride;
		public boolean spitOut = false;

		public ScissorMech(HardwareMap hardwareMap, Color color) {
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
			colorSensor = new ColorSensor(hardwareMap, "Scissor Color Sensor");
			this.color = color;
		}

		public void updateLift() {
			updateLift(scissorController.autoControl(getPosition()));
		}

		public void updateLift(double in) {
			if(minimumSwitch.isPressed()) {
				resetPosition();
				if(in < 0)
					return;
			}
			else if (maximumSwitch.isPressed() && in > 0)
				return;
			if (Math.abs(in) < .06)
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
			if(colorSensor.getBest() != color && colorSensor.getBest() != Color.yellow && !autoSpitOverride)
				spitOut = true;

			if(in && !out){
				leftIntakeServo.setPower(1);
				rightIntakeServo.setPower(1);
			}
			else if(out){
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
		public void toggleAutoSpit(boolean input) {
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
				"Tilt Servo Position: " + tiltServo.getPosition();
		}
}