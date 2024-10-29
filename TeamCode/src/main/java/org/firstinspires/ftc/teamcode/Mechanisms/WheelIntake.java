package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WheelIntake {
    public final CRServo leftServo, rightServo;
    private final DcMotorEx liftMotor;

    public WheelIntake(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
        leftServo.setDirection(DcMotorSimple.Direction.REVERSE);

        rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");

        liftMotor = hardwareMap.get(DcMotorEx.class, "Wheel Slides");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void spinIn(double in) {
        rightServo.setPower(in);
        leftServo.setPower(in);
    }
    public void spinOut(double in) {
        rightServo.setPower(-in);
        leftServo.setPower(-in);
    }
    public void lift (double in) {liftMotor.setPower(in);}
    public double getPosition () {return liftMotor.getCurrentPosition();}
}