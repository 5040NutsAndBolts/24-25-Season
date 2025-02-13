package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class WyattClaw {
    private final Servo tiltServo, pinchServo;

    public WyattClaw(HardwareMap hardwareMap) {
        tiltServo = hardwareMap.get(Servo.class, "Claw Tilt Servo");
        pinchServo = hardwareMap.get(Servo.class, "Claw Pinch Servo");
    }

    public void pinch(boolean up, boolean down){
        if(up && !down){
            pinchServo.setPosition(0);
        }else if(!up && down){
            pinchServo.setPosition(1);
        }
    }
    public void pinch (double in, double out){
        pinch(in > out, in < out);
    }

    public void clawMoveUp(){
        tiltServo.setPosition(0);
    }
    public void clawMoveDown(){
        tiltServo.setPosition(1);
    }
}
