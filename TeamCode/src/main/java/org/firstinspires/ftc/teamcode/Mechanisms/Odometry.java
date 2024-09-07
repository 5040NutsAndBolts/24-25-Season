package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Odometry extends Drivetrain{
    private final DcMotorEx left, right, center;
    private final double TICKS_PER_MM = 100;
    private final double DISTANCE_BETWEEN_WHEELS = 200;
    private final double CENTER_WHEEL_OFFSET = 50;

    public double x, y, theta;

    public Odometry(HardwareMap hardwareMap) {
        super(hardwareMap);
        left = hardwareMap.get(DcMotorEx.class, "Left Odo Pod");
        right = hardwareMap.get(DcMotorEx.class, "Right Odo Pod");
        center = hardwareMap.get(DcMotorEx.class, "Center Odo Pod");
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        center.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        x = 0;
        y = 0;
        theta = 0;
    }

    public void update() {
        // Get current encoder values
        int leftTicks = left.getCurrentPosition();
        int rightTicks = right.getCurrentPosition();
        int centerTicks = center.getCurrentPosition();

        // Convert ticks to distance traveled in millimeters
        double dL = leftTicks / TICKS_PER_MM;
        double dR = rightTicks / TICKS_PER_MM;
        double dC = centerTicks / TICKS_PER_MM;

        // Get motor velocities
        double[] velocities = getMotorVelocities();
        double leftSpeed = velocities[0];
        double rightSpeed = velocities[1];
        double backLeftSpeed = velocities[2];
        double backRightSpeed = velocities[3];

        // Calculate distance traveled by left and right wheels
        double dX = (dL + dR) / 2;

        // Calculate change in orientation in radians
        double dThetaRad = (dR - dL) / DISTANCE_BETWEEN_WHEELS;

        // Convert change in orientation to degrees
        double dThetaDeg = dThetaRad * (180 / Math.PI);

        // Update theta
        theta += dThetaDeg;
        if (theta >= 360) theta -= 360;
        if (theta < 0) theta += 360;

        // Calculate the change in position
        double dXChange = dX * Math.cos(Math.toRadians(theta));
        double dYChange = dX * Math.sin(Math.toRadians(theta));

        // Update x and y
        x += dXChange;
        y += dYChange;

        // Adjust for center wheel
        x += dC * Math.cos(Math.toRadians(theta + 90));
        y += dC * Math.sin(Math.toRadians(theta + 90));
    }
}