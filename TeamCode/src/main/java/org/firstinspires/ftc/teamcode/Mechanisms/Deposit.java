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

    /**
     * <p>Drop right servo</p>
     */
    public void rightDrop(){
        right.setPosition(.5);
    }
    /**
     * <p>Reset right servo</p>
     */
    public void rightZeroPosition() {
        right.setPosition(0);
    }
    /**
     * <p>Drop left servo</p>
     */
    public void leftDrop(){
        left.setPosition(.5);
    }
    /**
     * <p>Reset left servo</p>
     */
    public void leftZeroPosition(){
        left.setPosition(0);
    }
}
