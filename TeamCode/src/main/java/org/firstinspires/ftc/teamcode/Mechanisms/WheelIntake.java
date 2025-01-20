package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WheelIntake {
    public final CRServo leftServo, rightServo;
    private final DcMotor liftMotorTop, liftMotorBottom;
    private final DigitalChannel limitSwitch;
    private int slideResetPosition;

    public WheelIntake(HardwareMap hardwareMap) {
        leftServo = hardwareMap.get(CRServo.class, "Left Wheel Servo");
        leftServo.setDirection(DcMotorSimple.Direction.REVERSE);
        rightServo = hardwareMap.get(CRServo.class, "Right Wheel Servo");

        liftMotorTop = hardwareMap.get(DcMotor.class, "Wheel Slide Top");
        liftMotorBottom = hardwareMap.get(DcMotor.class, "Wheel Slide Bottom");
        liftMotorBottom.setDirection(DcMotor.Direction.REVERSE);
        liftMotorBottom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftMotorTop.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        limitSwitch = hardwareMap.get(DigitalChannel.class, "Limit Switch");
        limitSwitch.setMode(DigitalChannel.Mode.INPUT);

        slideResetPosition = 0;
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

    public void lift(double in) {
        // Check if limit switch is pressed and reset encoder if necessary
        if (!limitSwitch.getState()) { // Assumes active-low switch
            resetEncoder();
        }
        if(in > 0) liftMotorTop.setPower(in);
        else liftMotorBottom.setPower(in);
    }

    public double getPosition() {
        return liftMotorTop.getCurrentPosition() - slideResetPosition; // Adjusted to account for resets
    }

    public boolean isLimitSwitchPressed() {
        return !limitSwitch.getState(); // Return true if the switch is pressed
    }

    public void resetEncoder() {
        liftMotorTop.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotorBottom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotorTop.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotorBottom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slideResetPosition = 0;
    }
}
