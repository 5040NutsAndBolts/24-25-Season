package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HelperClasses.LimitSwitch;
import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class Scissor {
	private final DcMotor scissorMotor;
	private final LimitSwitch maximumSwitch,minimumSwitch;
	private int scissorMotorOffset;
	private final PID scissorController;

	public Scissor(@NonNull HardwareMap hardwareMap) {
		scissorMotor = hardwareMap.get(DcMotor.class, "Scissor Motor");
		maximumSwitch = new LimitSwitch(hardwareMap, "Max Scissor Switch");
		minimumSwitch = new LimitSwitch(hardwareMap, "Min Scissor Switch");
		scissorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		scissorMotorOffset = scissorMotor.getCurrentPosition();
		scissorController = new PID(.5, 0, 0, this::getPosition);
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

	public double getPosition () {
		return scissorMotor.getCurrentPosition() - scissorMotorOffset;
	}

	@NonNull
	@Override public String toString() {
		return
				"Get Scissor Position: " + getPosition() + "\n" +
				"Max Switch: " + maximumSwitch.toString() + "\n" +
				"Min Switch: " + minimumSwitch.toString() + "\n" +
				"Controller: " + scissorController.toString();
	}
}
