package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class WyattClaw {
    public final Servo tiltServo, pinchServo;

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


    public void pinchIn(double open, double close){
        if(open > 0.05 && close < 0.05)
            pinchServo.setPosition(0);
        if(open < 0.05 && close > 0.05)
            pinchServo.setPosition(1);
    }


    public void clawMoveUp(){
        tiltServo.setPosition(0);
    }
    public void clawMoveDown(){
        tiltServo.setPosition(1);
    }
}

