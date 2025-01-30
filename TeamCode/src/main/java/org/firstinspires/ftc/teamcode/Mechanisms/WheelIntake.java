package org.firstinspires.ftc.teamcode.Mechanisms;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class WheelIntake {
	public final CRServo leftServo, rightServo;
	private final DcMotorEx slideMotor;
	private int slideMotorOffset;
	private final PID liftController = new PID(.3,0,.00, this::getPosition);

	public WheelIntake(HardwareMap hardwareMap) {
		leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
		leftServo.setDirection(DcMotorSimple.Direction.REVERSE);
		rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");

		slideMotor = hardwareMap.get(DcMotorEx.class, "Wheel Slide Motor");
		slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		slideMotorOffset = slideMotor.getCurrentPosition();
	}

	//Spin controls for triggers
	public void spin(double in, double out) {
		//ignore stick/trigger drift
		if(in < .05)
			in = 0;
		if(out < .05)
			out = 0;

		int power = in-out > 0 ? 1 : in-out < 0 ? -1 : 0;
		leftServo.setPower(power);
		rightServo.setPower(power);
	}

	//Spin controls for buttons
	public void spin(boolean in, boolean out) {
		if(in && ! out) {
			leftServo.setPower(1);
			rightServo.setPower(1);
		}else if (out && !in) {
			leftServo.setPower(-1);
			rightServo.setPower(-1);
		}else {
			leftServo.setPower(0);
			rightServo.setPower(0);
		}
	}

	//Lift controls for sticks
	public void update(double in) {

		if(in > 0.05)
			slideMotor.setPower(liftController.teleOpControl(in));
	}

	public void update() {

		slideMotor.setPower(liftController.autoControl(getPosition()));
	}

	//Set position for autos
	public void setPosition (int target) {
		//if(limitSwitch.getState()) //If limit switch is pressed, then reset slide position
		//    resetPosition();

		liftController.setTarget(target);
	}

	//Getter for current slide position
	public double getPosition () {
		return slideMotor.getCurrentPosition() - slideMotorOffset;
	}

	//Reset slide encoder, the reset slide offset
	private void resetPosition() {
		slideMotor.setMode(STOP_AND_RESET_ENCODER);
		slideMotorOffset = slideMotor.getCurrentPosition();
	}

	public void rawLift(double power) {
		slideMotor.setPower(power);
	}

	@NonNull
	@Override
	public String toString() {
		return
				"Left Servo Power: " + leftServo.getPower() + "\n" +
						"Right Servo Power: " + rightServo.getPower() + "\n" +
						"Slide Motor Power: " + slideMotor.getPower() + "\n" +
						"Slide Motor Position: " + getPosition() + "\n" +
						liftController.toString();
	}
}