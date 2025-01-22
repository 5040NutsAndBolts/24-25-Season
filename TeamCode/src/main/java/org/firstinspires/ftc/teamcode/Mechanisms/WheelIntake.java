package org.firstinspires.ftc.teamcode.Mechanisms;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;

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
    private final DcMotorEx liftMotorTop, liftMotorBottom;
    private final DigitalChannel limitSwitch;
    private int topSlideMotorOffset, bottomSlideMotorOffset;
    private final PID liftController = new PID(.3,0,.00);

    public WheelIntake(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
        leftServo.setDirection(DcMotorSimple.Direction.REVERSE);
        rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");

        liftMotorTop = hardwareMap.get(DcMotorEx.class, "Wheel Slide Top");
        liftMotorBottom = hardwareMap.get(DcMotorEx.class, "Wheel Slide Bottom");
        liftMotorTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorTop.setDirection(DcMotorSimple.Direction.REVERSE);

        limitSwitch = hardwareMap.get(DigitalChannel.class, "Wheel Limit Switch");

        topSlideMotorOffset = liftMotorTop.getCurrentPosition();
        bottomSlideMotorOffset = liftMotorBottom.getCurrentPosition();
    }

    //Spin controls for triggers
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

    //Spin controls for buttons
    public void spin(boolean in, boolean out) {
        if(in && ! out) {
            leftServo.setPower(1);
            rightServo.setPower(1);
        }else if (out && !in) {
            leftServo.setPower(-1);
            rightServo.setPower(-1);
        }else {
            leftServo.setPower(0);
            rightServo.setPower(0);
        }
    }

    //Set position for autos
    public void setPosition (double target) {
        liftController.setTarget(target);
    }

    public void update() {
        if(!limitSwitch.getState()) { //if limit switch is pressed, then reset slide position
            resetPosition();
            if(liftController.getCurrentTarget() <= 0) //If we are already at the limit switch and the target is 0, then don't do anything
                return;
        }


        double power = liftController.autoControl(getPosition());

        liftMotorBottom.setPower(power);
        liftMotorTop.setPower(power/2);
    }

    //Update for teleop
    public void update(double input) {
        if(!limitSwitch.getState()) {
            resetPosition();
            if(liftController.getCurrentTarget() <= 0)
                return;
        }

        double power = liftController.teleOpControl(getPosition(), input);

        liftMotorBottom.setPower(power);
        liftMotorTop.setPower(power/2);
    }

    //Getter for current slide position
    public double getPosition () {
        return (double) ((liftMotorTop.getCurrentPosition() - topSlideMotorOffset) + (liftMotorBottom.getCurrentPosition() - bottomSlideMotorOffset)) / 2;
    }

    //Reset slide encoder, the reset slide offset
    private void resetPosition() {
        liftMotorTop.setMode(STOP_AND_RESET_ENCODER);
        liftMotorBottom.setMode(STOP_AND_RESET_ENCODER);
        topSlideMotorOffset = liftMotorTop.getCurrentPosition();
        bottomSlideMotorOffset = liftMotorBottom.getCurrentPosition();
    }

    @NonNull
    @Override
    public String toString() {
        return
            "Left Servo Power: " + leftServo.getPower() + "\n" +
            "Right Servo Power: " + rightServo.getPower() + "\n" +
            "Lift Motor Top Power: " + liftMotorTop.getPower() + "\n" +
            "Lift Motor Top Position: " + (liftMotorTop.getCurrentPosition() - topSlideMotorOffset) + "\n" +
            "Lift Motor Bottom Power: " + liftMotorBottom.getPower() + "\n" +
            "Lift Motor Bottom Position: " + (liftMotorBottom.getCurrentPosition() - bottomSlideMotorOffset) + "\n" +
            "Lift Motor Position: " + getPosition() + "\n" +
            liftController.toString();
    }
}