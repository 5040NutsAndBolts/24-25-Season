package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class WyattClaw {
    private CRServo turnServo;
    private Servo leftHand, rightHand;

    public WyattClaw(HardwareMap hardwareMap) {
        turnServo = hardwareMap.get(CRServo.class, "turnServo");
        leftHand = hardwareMap.get(Servo.class, "leftHand");
        rightHand = hardwareMap.get(Servo.class, "rightHand");
    }

    public void pinch(double in, double out){
        if(in < .05)
            in = 0;
        if(out < .05)
            out = 0;

        if(in > .05){
          turnServo.setPower(in);

        } else turnServo.setPower(0);
        if(out > .05){
            turnServo.setPower(out);
        } else turnServo.setPower(0);

    }

    public void clawMoveUp(){
        leftHand.setPosition(144);
        rightHand.setPosition(144);

    }
    public void clawMoveDown(){
        leftHand.setPosition(22);
        rightHand.setPosition(22);

    }

}
