package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Deposit {
    //Deposit Servos Declaration
    private final Servo right, left;
    public Deposit(HardwareMap hardwareMap){
        //Deposit Servo Config
        right = hardwareMap.get(Servo.class, "Right Deposit");
        left = hardwareMap.get(Servo.class, "Left Deposit");
    }
    public void rightDrop(){
        right.setPosition(.5);
    }
    public void rightZeroPosition() {
        right.setPosition(0);
    }
    public void leftDrop(){
        left.setPosition(.5);
    }
    public void leftZeroPosition(){
        left.setPosition(0);
    }
}
