package org.firstinspires.ftc.teamcode.Mechanisms;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
	private final Servo pinchServo;
	private final DcMotor liftMotorTop, liftMotorBottom, rollMotor;
	private final double rollPositionOffset;
	public Claw(HardwareMap hardwareMap) {
		pinchServo = hardwareMap.get(Servo.class, "Pinch Servo");

		rollMotor = hardwareMap.get(DcMotor.class, "Roll Motor");
		rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		rollPositionOffset = rollMotor.getCurrentPosition();

		liftMotorTop = hardwareMap.get(DcMotor.class, "Claw Slide Top");
		liftMotorBottom = hardwareMap.get(DcMotor.class, "Claw Slide Bottom");
		liftMotorBottom.setDirection(DcMotor.Direction.REVERSE);
		liftMotorBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		liftMotorTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	public void liftSlides(double input) {
		liftMotorTop.setPower(input);
		liftMotorBottom.setPower(input);
	}
	public void pinch(boolean open, boolean closed){
		if (open && pinchServo.getPosition() != 1 && !closed)
			pinchServo.setPosition(1);
		else if (closed && !open)
			pinchServo.setPosition(0);
	}
	public void rollClaw(boolean down, boolean up) {
		if(down)
			rollMotor.setPower(-.8);
		else if(up)
			rollMotor.setPower(.5);
		else rollMotor.setPower(0);
	}

	public int getRollMotorPosition() {
		return (int) (rollMotor.getCurrentPosition() - rollPositionOffset);
	}

	public int getSlidePosition() {
		return (int) ((double) (liftMotorTop.getCurrentPosition() + liftMotorBottom.getCurrentPosition()) / 2);
	}

	@NonNull
	@Override
	public String toString() {
		return
			"Top Motor Position: " + liftMotorTop.getCurrentPosition() + "\n" +
			"Bottom Motor Position: " + liftMotorBottom.getCurrentPosition() + "\n" +
			"Average Slide Position: " + getSlidePosition() + "\n" +
			"Roll Motor Position: " + rollMotor.getCurrentPosition() + "\n" +
			"Adjusted Roll Motor Position: " + getRollMotorPosition() + "\n" +
			"Pinch Servo Position: " + pinchServo.getPosition() + "\n" +
			"Roll Motor Offset: " + rollPositionOffset;
	}
}