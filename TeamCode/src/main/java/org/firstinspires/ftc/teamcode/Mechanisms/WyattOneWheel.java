package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WyattOneWheel {
    private CRServo servo;
    public WyattOneWheel(HardwareMap hardwareMap) {servo = hardwareMap.get(CRServo.class, "Intake");}
    //Spins the wheel inward
    public void spinIn(double in){servo.setPower(in);}
    //Spins the wheel outward
    public void spinOut(double in){servo.setPower(-in);}
}