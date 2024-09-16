package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RichardWheel {
    private final CRServo leftServo, rightServo;

    public RichardWheel(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(CRServo.class, "Left Intake Servo");
        rightServo = hardwareMap.get(CRServo.class, "Right Intake Servo");
        rightServo.setDirection(DcMotorSimple.Direction.REVERSE);
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
