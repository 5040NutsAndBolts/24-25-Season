package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangMotor {
    private final DcMotorEx motor;
    public HangMotor (HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "Hang Motor");
    }
    public void hang() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(1);
    }
    public void hangZero() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setPower(0);
    }
}