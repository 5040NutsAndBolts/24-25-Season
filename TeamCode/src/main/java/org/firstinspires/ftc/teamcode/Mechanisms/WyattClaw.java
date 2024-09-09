package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

public class WyattClaw {
    private final Servo pinchServo;
    private final CRServo rollServo;
    private boolean open;
    public WyattClaw (HardwareMap hardwareMap) {
        pinchServo = hardwareMap.get(Servo.class, "Pinch Servo");
        rollServo = hardwareMap.get(CRServo.class, "Roll Servo");
    }
    public void pinch(Boolean input) {
        if (input) {
            if(!open)
                pinchServo.setPosition(1);
            else
                pinchServo.setPosition(0);
            open = true;
        }
        else if (!open) open = false;
    }
    //rolls claw up and down
    public void roll(double input) {
        rollServo.setPower(input);
    }
    public boolean isOpen(){return open;}
}