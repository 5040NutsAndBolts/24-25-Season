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

	public Scissor (@NonNull HardwareMap hardwareMap) {
		scissorMotor = hardwareMap.get(DcMotor.class, "Scissor Motor");
		maximumSwitch = new LimitSwitch(hardwareMap, "Max Scissor Switch");
		minimumSwitch = new LimitSwitch(hardwareMap, "Min Scissor Switch");
		scissorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
		scissorMotorOffset = scissorMotor.getCurrentPosition();
		scissorController = new PID(.5, 0, 0, this::getPosition);
	}

	public void updateScissor (double in) {
		if (minimumSwitch.isPressed())
			resetPosition();
		scissorMotor.setPower(scissorController.teleOpControl(in));
	}

	public void setTarget (int target) {
		scissorController.setTarget(target);
	}

	private void resetPosition () {
		scissorMotorOffset = scissorMotor.getCurrentPosition();
	}

	public double getPosition () {
		return scissorMotor.getCurrentPosition() - scissorMotorOffset;
	}

	@NonNull
	@Override public String toString () {
		return
				"Get Scissor Position: " + getPosition() + "\n" +
				"Scissor Power: " + scissorMotor.getPower() + "\n" +
				"Max Switch: " + maximumSwitch.isPressed() + "\n" +
				"Min Switch: " + minimumSwitch.isPressed() + "\n" +
				scissorController.toString();
	}
}
