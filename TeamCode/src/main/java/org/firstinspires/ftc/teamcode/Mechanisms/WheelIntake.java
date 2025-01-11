package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WheelIntake {
    public final CRServo leftServo, rightServo;
    private final DcMotor liftMotorTop, liftMotorBottom;

    public WheelIntake(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
        leftServo.setDirection(DcMotorSimple.Direction.REVERSE);
        rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");


        liftMotorTop = hardwareMap.get(DcMotor.class, "Wheel Slide Top");
        liftMotorBottom = hardwareMap.get(DcMotor.class, "Wheel Slide Bottom");
        liftMotorBottom.setDirection(DcMotor.Direction.REVERSE);
        liftMotorBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void spin(double in, double out) {
        //ignore stick/trigger drift
        if(in < .05)
            in = 0;
        if(out < .05)
            out = 0;
        //stops servo from freaking out if both are pressed
        double power = in-out;

        leftServo.setPower(power);
        rightServo.setPower(power);
    }

    public void lift (double in) {liftMotorTop.setPower(in); liftMotorBottom.setPower(in);}
    public double getPosition () {return liftMotorTop.getCurrentPosition();} //isn't currently in use but when it is change it to show both top and bottom
}