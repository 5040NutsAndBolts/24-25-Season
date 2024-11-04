package org.firstinspires.ftc.teamcode.HelperClasses;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Odometry extends Drivetrain {
    public double x = 0, y = 0, theta = 0;
    public DcMotorEx leftOdom, rightOdom, centerOdom;
    private double leftOdomTraveled, rightOdomTraveled, centerOdomTraveled;
    private int leftEncoderPos, rightEncoderPos, centerEncoderPos;
    private static final double ODOM_TICKS_PER_IN = 328.708657034, TRACKWIDTH = 15.5;
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

    private int getDeltaLeftTicks()
    {
        try
        {
            int total=-leftOdom.getCurrentPosition();
            int oldPos = leftEncoderPos;
            leftEncoderPos=total;
            return oldPos - total;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    private int getDeltaRightTicks()
    {
        try
        {
            int total=rightOdom.getCurrentPosition();
            int oldPos = rightEncoderPos;
            rightEncoderPos=total;
            return oldPos - total;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    private int getDeltaCenterTicks()
    {
        try
        {
            int total=-centerOdom.getCurrentPosition();
            int oldPos = centerEncoderPos;
            centerEncoderPos=total;
            return oldPos - total;
        }
        catch(Exception e)
        {
            return 0;
        }
    }

    public void updatePositionRoadRunner()
    {
        leftE = leftOdom.getCurrentPosition() - leftO;
        rightE = rightOdom.getCurrentPosition() - rightO;
        centerE = centerOdom.getCurrentPosition() - centerO;
        /*try
        {
            bulkData = expansionHub.getBulkInputData();
        }
        catch(Exception e)
        {
            return;
        }*/

        // Change in the distance (centimeters) since the last update for each odometer
        double deltaLeftDist = -(getDeltaLeftTicks()/ ODOM_TICKS_PER_IN );
        double deltaRightDist = -(getDeltaRightTicks()/ ODOM_TICKS_PER_IN);
        double deltaCenterDist = (getDeltaCenterTicks()/ ODOM_TICKS_PER_IN);

        //adjusts for physical diffrences in pods
        if(deltaLeftDist < 0)
            deltaRightDist *= (1.00052425 * 0.99770514);
        else if (deltaLeftDist > 0)
            deltaRightDist *= (1.00076168 * 0.99859913);

        leftOdomTraveled += deltaLeftDist;
        rightOdomTraveled += deltaRightDist;
        centerOdomTraveled += deltaCenterDist;
    }

    public void resetOdometry(double x, double y, double theta)
    {
        leftO = leftE;
        rightO = rightE;
        centerO = centerE;


        rightOdomTraveled = 0;
        leftOdomTraveled = 0;

        leftEncoderPos = 0;
        rightEncoderPos = 0;
        centerEncoderPos = 0;

        // Resets encoder values then sets them back to run without encoders because wheels and odometry are same pointer
        leftOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        centerOdom.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        centerOdom.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

}