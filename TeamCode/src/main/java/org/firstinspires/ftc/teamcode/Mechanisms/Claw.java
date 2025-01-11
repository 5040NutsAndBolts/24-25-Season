package org.firstinspires.ftc.teamcode.Mechanisms;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
	private final Servo pinchServo;
	private final DcMotor liftMotor, rollMotor;
	private final double rollPositionOffset;
	private boolean dontCrash = false;
	public Claw(HardwareMap hardwareMap) {
		pinchServo = hardwareMap.get(Servo.class, "Pinch Servo");

		rollMotor = hardwareMap.get(DcMotor.class, "Roll Motor");
		rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		rollPositionOffset = rollMotor.getCurrentPosition();

		liftMotor = hardwareMap.get(DcMotorEx.class, "Claw Slide");

		liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


	}

	public void liftSlides(double input) {
		liftMotor.setPower(input);

	}
	public void pinch(boolean open, boolean closed){
		if (open && pinchServo.getPosition() != 1 && !closed)
			pinchServo.setPosition(1);
		else if (closed && !open)
			pinchServo.setPosition(0);
	}
	public void rollClaw(boolean down, boolean up) {
		if(!dontCrash) {
			rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
			if(down && up) return;
			else if(down)
				rollMotor.setPower(-.8);
			else if(up)
				rollMotor.setPower(.8);
			else rollMotor.setPower(0);
		}
		else {
			if(down && up)
				return;
			if(down) {
				rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
				if(getRollMotorPosition() > -400)
					rollMotor.setPower(-.8);
				else if (getRollMotorPosition() < -700) {
					rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
					rollMotor.setPower(.1);
				}
				else
					rollMotor.setPower(-.2);
			}
			else if(up) {
				rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
				if(getRollMotorPosition() > -300)
					rollMotor.setPower(-.08);
				else if (getRollMotorPosition() > -400)
					rollMotor.setPower(.5);
				else rollMotor.setPower(.7);
			}
			else {
				if(getRollMotorPosition() < 50)
					rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
				else rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
				rollMotor.setPower(0);
			}
		}
	}

	public int getRollMotorPosition() {
		return (int) (rollMotor.getCurrentPosition() - rollPositionOffset);
	}

	public int getSlidePosition() {
		return (int) ((double) (liftMotor.getCurrentPosition()));
	}

	@NonNull
	@Override
	public String toString() {
		return
		"Crash detection: " + dontCrash + "\n" +
		"Top Motor Position: " + liftMotor.getCurrentPosition() + "\n" +
		"Top Motor Power: " + liftMotor.getPower() + "\n" +
		"Average Slide Position: " + getSlidePosition() + "\n" +
		"Roll Motor Position: " + rollMotor.getCurrentPosition() + "\n" +
		"Adjusted Roll Motor Position: " + getRollMotorPosition() + "\n" +
		"Roll Motor Power: " + rollMotor.getPower() + "\n" +
		"Pinch Servo Position: " + pinchServo.getPosition() + "\n" +
		"Roll Motor Offset: " + rollPositionOffset;
	}
}