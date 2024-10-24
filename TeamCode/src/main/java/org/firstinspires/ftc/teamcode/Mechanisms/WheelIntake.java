package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WheelIntake {
    private final CRServo leftServo, rightServo;

    public WheelIntake(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
        rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");
        leftServo.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void spinIn(double in) {
        rightServo.setPower(in);
        leftServo.setPower(in);
    }
    public void spinOut(double in) {
        rightServo.setPower(-in);
        leftServo.setPower(-in);
    }
}
