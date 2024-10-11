package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

public class WyattClaw {
    private final CRServo pinchServo;

    public WyattClaw (HardwareMap hardwareMap) {
        pinchServo = hardwareMap.get(CRServo.class, "Pinch Servo");

    }
    public void close(Boolean buttonInput) {
        if (buttonInput)
            pinchServo.setPower(0.7);
        else
            pinchServo.setPower(0);
    }

    public void open(Boolean buttonInput2) {
        if (buttonInput2)
            pinchServo.setPower(-0.7);
        else
            pinchServo.setPower(0);
    }
}