package org.firstinspires.ftc.teamcode.HelperClasses;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;

//EVERYTHING IS IN MILLIMETERS!!!!!!!!!!
public class Odometry {
    //Constants
    //private final static double TICKS_PER_MILLIMETER = 13.19710351; //FB and spin tests
    //private final static double CENTER_TICKS_PER_MILLIMETER = 12.94128571; //Strafe test
    //private final static double TRACKWIDTH = 393.7;//distance between left and right wheels
    //private final static double CENTER_ODO_OFFSET = 152.4;//center wheel offset

    //Encoders
    private final DcMotorEx leftOdo, centerOdo, rightOdo;
    //Leftovers from encoders not starting at zero
    private final double leftOffset, centerOffset, rightOffset;
    //Current position
    //public final Pose currentPose = new Pose(0,0,0);
    //Previous positions
    //private double pLeft, pCenter, pRight;
    public double curL, curC, curR, curLRA;


    public Odometry(HardwareMap hardwareMap) {
        leftOdo = hardwareMap.get(DcMotorEx.class, "Front Left");
        centerOdo = hardwareMap.get(DcMotorEx.class, "Back Left");
        rightOdo = hardwareMap.get(DcMotorEx.class, "Front Right");
        leftOffset = leftOdo.getCurrentPosition();
        centerOffset = centerOdo.getCurrentPosition();
        rightOffset = rightOdo.getCurrentPosition();
    }

    public void update() {
        curL = leftOdo.getCurrentPosition() - leftOffset;
        curC = centerOdo.getCurrentPosition() - centerOffset;
        curR = rightOdo.getCurrentPosition() - rightOffset;
        curLRA = (curL + curR) / 2.0;
        /*double deltaLeft = leftOdo.getCurrentPosition() - pLeft - leftOffset;
        double deltaCenter = centerOdo.getCurrentPosition() - pCenter - centerOffset;
        double deltaRight = rightOdo.getCurrentPosition() - pRight - rightOffset;

        double distanceLeft = deltaLeft * TICKS_PER_MILLIMETER;
        double distanceCenter = deltaCenter * CENTER_TICKS_PER_MILLIMETER;
        double distanceRight = deltaRight * TICKS_PER_MILLIMETER;

        double distanceTheta = (distanceRight - distanceLeft) / TRACKWIDTH;
        double distanceStrafe = distanceCenter - (CENTER_ODO_OFFSET * distanceTheta);
        double distanceForward = (distanceRight - distanceLeft) / 2.0;

        currentPose.x+= distanceForward * Math.cos(currentPose.theta) - distanceStrafe * Math.sin(currentPose.theta);
        currentPose.y+= distanceForward * Math.sin(currentPose.theta) + distanceStrafe * Math.cos(currentPose.theta);
        currentPose.theta+= distanceTheta;

        pLeft = deltaLeft;
        pCenter = deltaCenter;
        pRight = deltaRight;*/
    }

    @NonNull
    @Override
    public String toString() {
        return Arrays.toString(new double[] {curL, curC, curR, curLRA});
    }
}