package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestThing extends Mechanism{
	TestThing(HardwareMap hardwareMap) {
		super(hardwareMap);
		addMotor("Motor");
		addMotor("Motor2");
		addSpinServo("Spin Servo");
		addPointServo("Point Servo");
	}
	public void testMotor() {
		searchMotor("Motor").setPower(1);
		searchSpinServo("Spin Servo").setPower(1);
		searchPointServo("Point Servo").setPosition(1);
	}
}