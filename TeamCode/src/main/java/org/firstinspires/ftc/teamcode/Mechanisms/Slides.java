package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Slides {
	private DcMotorEx liftMotor;
	public Slides (HardwareMap hardwareMap) {
		liftMotor = hardwareMap.get(DcMotorEx.class, "Lift Motor");
	}
	public void lift (double in) {liftMotor.setPower(in);}
	public double getPosition () {return liftMotor.getCurrentPosition();}
}