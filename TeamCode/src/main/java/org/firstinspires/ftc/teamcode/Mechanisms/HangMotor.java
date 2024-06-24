package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HangMotor {
    private final DcMotorEx motor;
    public HangMotor (HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "Hang Motor");
    }

    /**
     * <p>If we're hanging, set the motor to brake and use full power.</p>
     */
    public void hang() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(1);
    }

    /**
     * <p>Solely exists for redundancy as we normally wont stop hanging mid-match, but exists to make it go limp so it doesn't use power</p>
     */
    public void hangZero() {
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setPower(0);
    }
}