package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
public class Claw {
	private final CRServo rollServoLeft, rollServoRight, pinchServo;
	private final DcMotor liftMotorTop, liftMotorBottom;
	public Claw(HardwareMap hardwareMap) {
		pinchServo = hardwareMap.get(CRServo.class, "Pinch Servo");

		rollServoLeft = hardwareMap.get(CRServo.class, "Claw Servo Left");
		rollServoRight = hardwareMap.get(CRServo.class, "Claw Servo Right");

		liftMotorTop = hardwareMap.get(DcMotor.class, "Claw Slide 1");
		liftMotorBottom = hardwareMap.get(DcMotor.class, "Claw Slide 2");
		liftMotorBottom.setDirection(DcMotor.Direction.REVERSE);
		liftMotorBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		liftMotorTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
	}

	public void pinch(double input) {
		pinchServo.setPower(input);
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
		pinchServo.setPower(1);
	}
	public void open(){
		pinchServo.setPower(-1);
	}
	public int getPosition() {
		return (liftMotorTop.getCurrentPosition() + liftMotorBottom.getCurrentPosition()) / 2;
	}
}