package org.firstinspires.ftc.teamcode.HelperClasses;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;

public class Odometry extends Drivetrain {
    public DcMotorEx leftOdom, rightOdom, centerOdom;
    public int leftE, rightE, centerE;
    private int leftO, rightO, centerO;

    public Odometry(HardwareMap hardwareMap){
        super(hardwareMap);
        leftOdom = hardwareMap.get(DcMotorEx.class, "Front Left");
        rightOdom = hardwareMap.get(DcMotorEx.class, "Front Right");
        centerOdom = hardwareMap.get(DcMotorEx.class, "Back Left");
        leftO = leftOdom.getCurrentPosition();
        rightO = rightOdom.getCurrentPosition();
        centerO = centerOdom.getCurrentPosition();
    }

    public void updateOdoPosition() {
        leftE = leftOdom.getCurrentPosition() - leftO;
        rightE = rightOdom.getCurrentPosition() - rightO;
        centerE = centerOdom.getCurrentPosition() - centerO;
    }

    public void resetOdometry(int l, int r, int c) {
        leftO = leftE + l;
        rightO = rightE + r;
        centerO = centerE + c;

        updateOdoPosition();

        // Resets encoder values then sets them back to run without encoders because wheels and odometry are same pointer
        leftOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        centerOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        centerOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void resetOdometry() {
        leftO = leftOdom.getCurrentPosition();
        rightO = rightOdom.getCurrentPosition();
        centerO = centerOdom.getCurrentPosition();

        // Resets encoder values then sets them back to run without encoders because wheels and odometry are same pointer
        leftOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        centerOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        centerOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}