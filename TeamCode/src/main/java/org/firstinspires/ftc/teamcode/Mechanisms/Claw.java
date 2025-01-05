package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
	public final Servo pinchServo;
	private final DcMotor liftMotorTop, liftMotorBottom, rollMotor;
	public Claw(HardwareMap hardwareMap) {
		pinchServo = hardwareMap.get(Servo.class, "Pinch Servo");

		rollMotor = hardwareMap.get(DcMotor.class, "Roll Motor");
		rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
	public void liftClaw(boolean down, boolean up) {
		if(down)
			rollMotor.setPower(-.8);
		else if(up)
			rollMotor.setPower(.5);
		else rollMotor.setPower(0);
	}

	public int getPosition() {
		return (liftMotorTop.getCurrentPosition() + liftMotorBottom.getCurrentPosition()) / 2;
	}
}