package org.firstinspires.ftc.teamcode.Mechanisms;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class WheelIntake {
	public final CRServo leftServo, rightServo;
	private final DcMotorEx slideMotorTop, slideMotorBottom;
	private int slideMotorTopOffset, slideMotorBottomOffset;
	private final PID topController, bottomController;
	private final LimitSwitch limitSwitch;

	public WheelIntake(HardwareMap hardwareMap) {
		leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
		leftServo.setDirection(DcMotorSimple.Direction.REVERSE);
		rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");

		slideMotorTop = hardwareMap.get(DcMotorEx.class, "Slide Motor Top");
		slideMotorBottom = hardwareMap.get(DcMotorEx.class, "Slide Motor Bottom");
		slideMotorTop.setDirection(DcMotorSimple.Direction.REVERSE);
		slideMotorTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		slideMotorBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

		slideMotorTopOffset = slideMotorTop.getCurrentPosition();
		slideMotorBottomOffset = slideMotorBottom.getCurrentPosition();

		limitSwitch = new LimitSwitch(hardwareMap, "Wheel Limit Switch");

		topController = new PID(.3,0,.00, this::getTopPosition);
		bottomController = new PID(.3,0,.00, this::getBottomPosition);
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

	public void update () {
		if(limitSwitch.isPressed())
			resetPosition();
		slideMotorTop.setPower(topController.autoControl(getTopPosition()));
		bottomController.setTarget(getBottomPosition());
	}

	public void update (double in) {
		if(limitSwitch.isPressed())
			resetPosition();
		if(Math.abs(in) > 0.05) {
			slideMotorTop.setPower(topController.teleOpControl(in));
			if(in < 0)
				slideMotorBottom.setPower(bottomController.teleOpControl(in));
		}
	}

	public void setSlideTarget(int target) {
		if(limitSwitch.isPressed())
			resetPosition();

		bottomController.setTarget(target < topController.getTarget() ? target : bottomController.getTarget());
		topController.setTarget(target);
	}



	private double getTopPosition () {
		return slideMotorTop.getCurrentPosition() - slideMotorTopOffset;
	}
	private double getBottomPosition () {
		return slideMotorBottom.getCurrentPosition() - slideMotorBottomOffset;
	}
	private void resetPosition() {
		slideMotorTop.setMode(STOP_AND_RESET_ENCODER);
		slideMotorBottom.setMode(STOP_AND_RESET_ENCODER);
		slideMotorTopOffset = slideMotorTop.getCurrentPosition();
		slideMotorBottomOffset = slideMotorBottom.getCurrentPosition();
	}

	@NonNull
	@Override public String toString() {
		return "Top Controller: " + topController.toString() + "\n" +
				"Bottom Controller: " + bottomController.toString() + "\n" +
				"Slide Motor Top Power: " + slideMotorTop.getPower() + "\n" +
				"Slide Motor Bottom Power: " + slideMotorBottom.getPower() + "\n" +
				"Slide Motor Top Position: " + getTopPosition() + "\n" +
				"Slide Motor Bottom Position: " + getBottomPosition() + "\n" +
				"Limit Switch: " + limitSwitch.isPressed();
	}
}