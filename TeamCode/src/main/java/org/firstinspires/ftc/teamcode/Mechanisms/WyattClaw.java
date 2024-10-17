package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
public class WyattClaw {
    private final CRServo rollServo, pinchServo;
    public WyattClaw (HardwareMap hardwareMap) {
        pinchServo = hardwareMap.get(CRServo.class, "Pinch Servo");
        rollServo = hardwareMap.get(CRServo.class, "Roll Servo");
    }
    public void pinch(double input) {pinchServo.setPower(input);}
    public void roll(double input) {rollServo.setPower(input);}
}