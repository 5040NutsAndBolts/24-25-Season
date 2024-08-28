package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OdoTrain extends Drivetrain {
    private final DcMotorEx leftOdom, rightOdom, centerOdom;

    // Conversion constants
    private static final double TICKSPERMM = 100;  // Encoder ticks per millimeter (adjust as needed)
    private static final double TRACK_WIDTH_MM = 300;  // Track width in millimeters

    // Robot state
    public double x, y, theta;  // x, y in millimeters; theta in degrees

    // Previous encoder values
    private int previousLeftEncoderTicks, previousRightEncoderTicks, previousCenterEncoderTicks;

    public OdoTrain(HardwareMap hardwareMap) {
        super(hardwareMap);
        leftOdom = hardwareMap.get(DcMotorEx.class, "Left Odom");
        rightOdom = hardwareMap.get(DcMotorEx.class, "Right Odom");
        centerOdom = hardwareMap.get(DcMotorEx.class, "Center Odom");

        leftOdom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightOdom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        centerOdom.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Initialize encoder values
        resetEncoderValues();

        // Initialize robot's starting position and orientation
        x = 0;
        y = 0;
        theta = 0;
    }

    private void updateOdometry() {
        // Get current encoder positions
        int leftTicks = leftOdom.getCurrentPosition();
        int rightTicks = rightOdom.getCurrentPosition();
        int centerTicks = centerOdom.getCurrentPosition();

        // Calculate the changes in encoder values
        double deltaLeftDistance = (leftTicks - previousLeftEncoderTicks) / TICKSPERMM;
        double deltaRightDistance = (rightTicks - previousRightEncoderTicks) / TICKSPERMM;
        double deltaCenterDistance = (centerTicks - previousCenterEncoderTicks) / TICKSPERMM;

        // Update previous encoder values
        previousLeftEncoderTicks = leftTicks;
        previousRightEncoderTicks = rightTicks;
        previousCenterEncoderTicks = centerTicks;

        // Calculate change in orientation (theta) and update it
        double deltaTheta = (deltaRightDistance - deltaLeftDistance) / TRACK_WIDTH_MM;
        theta += Math.toDegrees(deltaTheta);
        theta = (theta + 360) % 360; // Normalize theta

        // Calculate new position
        double thetaRadians = Math.toRadians(theta);
        // Forward movement is estimated from the center encoder
        x += deltaCenterDistance * Math.cos(thetaRadians);
        y += deltaCenterDistance * Math.sin(thetaRadians);
    }

    public void resetX(double newX) {
        x = newX;
        resetEncoderValues();
    }
    public void resetY(double newY) {
        y = newY;
        resetEncoderValues();
    }
    public void resetTheta(double newTheta) {
        theta = newTheta % 360; // Normalize theta
        resetEncoderValues();
    }
    private void resetEncoderValues() {
        previousLeftEncoderTicks = leftOdom.getCurrentPosition();
        previousRightEncoderTicks = rightOdom.getCurrentPosition();
        previousCenterEncoderTicks = centerOdom.getCurrentPosition();
    }

    @Override
    public void drive(double forward, double sideways, double rotation) {
        super.drive(forward, sideways, rotation);
        updateOdometry();
    }
}
