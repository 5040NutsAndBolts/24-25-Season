package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
	private final CRServo rollServoLeft, rollServoRight;
	private final Servo pinchServo;
	private final DcMotor liftMotorTop, liftMotorBottom;
	public Claw(HardwareMap hardwareMap) {
		pinchServo = hardwareMap.get(Servo.class, "Pinch Servo");

		rollServoLeft = hardwareMap.get(CRServo.class, "Claw Servo Left");
		rollServoLeft.setDirection(DcMotorSimple.Direction.REVERSE);
		rollServoRight = hardwareMap.get(CRServo.class, "Claw Servo Right");

		liftMotorTop = hardwareMap.get(DcMotor.class, "Claw Slide Top");
		liftMotorBottom = hardwareMap.get(DcMotor.class, "Claw Slide Bottom");
		liftMotorBottom.setDirection(DcMotor.Direction.REVERSE);
		liftMotorBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		liftMotorTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}
	public void liftClaw(double input) {
		rollServoLeft.setPower(input);
		rollServoRight.setPower(input);
	}
	public void liftSlides(double input) {
		liftMotorTop.setPower(input);
		liftMotorBottom.setPower(input);
	}
	public void pinch(){
		pinchServo.setPosition(1);
	}
	public void open(){
		pinchServo.setPosition(-1);
	}
	public int getPosition() {
		return (liftMotorTop.getCurrentPosition() + liftMotorBottom.getCurrentPosition()) / 2;
	}
}