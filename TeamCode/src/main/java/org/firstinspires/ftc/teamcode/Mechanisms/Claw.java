package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
	public double rightTrigDown, leftTrigDown;
	private final Servo pinchServo;
	private final DcMotor liftMotorTop, liftMotorBottom;
	public Claw(HardwareMap hardwareMap) {
		pinchServo = hardwareMap.get(Servo.class, "Pinch Servo");



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
	public void pinch(double leftTrig, double rightTrig){
		rightTrigDown = rightTrig;
		if (rightTrigDown > 0.5){
			pinchServo.setPosition(1);
		}
		leftTrigDown = leftTrig;
		if (leftTrigDown > 0.5){
			pinchServo.setPosition(-1);
		}
	}

	public int getPosition() {
		return (liftMotorTop.getCurrentPosition() + liftMotorBottom.getCurrentPosition()) / 2;
	}
}