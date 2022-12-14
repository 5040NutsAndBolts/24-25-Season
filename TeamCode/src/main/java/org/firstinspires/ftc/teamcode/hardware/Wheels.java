package org.firstinspires.ftc.teamcode.hardware;

import static java.lang.Math.abs;
import static java.lang.Math.max;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Wheels {
    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;
    private final LinearOpMode opMode;

    public Wheels(LinearOpMode opMode) {
        this.opMode = opMode;
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "FrontLeft");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "FrontRight");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "BackLeft");
        backRight = opMode.hardwareMap.get(DcMotor.class, "BackRight");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void driveByJoystick(double x, double y, double rot) {
        double fr = y - x - rot;
        double br = y + x - rot;
        double fl = y + x + rot;
        double bl = y - x + rot;

        double norm = max(max(abs(fr), abs(br)), max(abs(fr), abs(br)));

        if (norm > 1) {
            fr /= norm;
            br /= norm;
            fl /= norm;
            bl /= norm;
        }

        if (opMode.gamepad1.left_stick_button || opMode.gamepad1.left_trigger>.4) {
            fr *= .25;
            br *= .25;
            fl *= .25;
            bl *= .25;
        } else if (opMode.gamepad1.right_trigger>.4) {
            fr *= .8;
            br *= .8;
            fl *= .8;
            bl *= .8;
        } else {
            fr *= .6;
            br *= .6;
            fl *= .6;
            bl *= .6;
        }

        frontRight.setPower(fr);
        backRight.setPower(br);
        frontLeft.setPower(fl);
        backLeft.setPower(bl);
    }
}