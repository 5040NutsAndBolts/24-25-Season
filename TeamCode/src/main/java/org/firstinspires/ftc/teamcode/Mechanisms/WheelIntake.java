package org.firstinspires.ftc.teamcode.Mechanisms;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class WheelIntake {
    public final CRServo leftServo, rightServo;
    private final DcMotorEx liftMotor;
    private final DigitalChannel limitSwitch;
    private double slideOffset;
    private final PID liftController = new PID(.01,0,.01);

    public WheelIntake(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
        leftServo.setDirection(DcMotorSimple.Direction.REVERSE);
        rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");

        liftMotor = hardwareMap.get(DcMotorEx.class, "Wheel Slides");
        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        limitSwitch = hardwareMap.get(DigitalChannel.class, "Wheel Limit Switch");
        slideOffset = liftMotor.getCurrentPosition();
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

    public void lift (double in) {
        if(!limitSwitch.getState())
            slideOffset = liftMotor.getCurrentPosition();
        liftMotor.setPower(liftController.teleOpControl(this::getPosition,in));
    }

    public double getPosition () {
        return liftMotor.getCurrentPosition() - slideOffset;
    }

    @NonNull
    @Override
    public String toString() {
        return
            "Left Servo Power: " + leftServo.getPower() + "\n" +
            "Right Servo Power: " + rightServo.getPower() + "\n" +
            "Lift Motor Power: " + liftMotor.getPower() + "\n" +
            "Lift Motor Position: " + getPosition() + "\n" +
            liftController.toString();
    }
}