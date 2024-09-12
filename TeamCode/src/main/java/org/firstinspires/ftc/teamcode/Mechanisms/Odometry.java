package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry extends Drivetrain {

    private int fart = 0;

    // Constants for odometry
    private static final double TICKS_PER_MM = 14.58756105; // Conversion factor: encoder ticks to mm
    private static final double DISTANCE_BETWEEN_WHEELS = 386.08; // Distance between left and right wheels (mm)
    private static final double CENTER_WHEEL_OFFSET = 171.45; // Offset of the center wheel from the robot's center of rotation (mm)

    // Odometry wheels
    private final DcMotorEx leftOdo, centerOdo, rightOdo;

    // Encoder offsets
    private int leftOdoOffset, centerOdoOffset, rightOdoOffset;

    // Current tick values after applying offsets
    public int leftTicks, centerTicks, rightTicks;

    // Previous tick values for comparison
    private int prevLeftTicks, prevRightTicks, prevCenterTicks;

    // Robot's position and orientation
    public double x, y, theta; // x and y in mm, theta in radians

    public Odometry(HardwareMap hardwareMap) {
        super(hardwareMap);

        // Initialize the odometry pods using the hardware map
        leftOdo = hardwareMap.get(DcMotorEx.class, "Left Odo");
        centerOdo = hardwareMap.get(DcMotorEx.class, "Center Odo");
        rightOdo = hardwareMap.get(DcMotorEx.class, "Right Odo");

        // Capture the initial encoder positions as offsets (to account for starting positions)
        leftOdoOffset = leftOdo.getCurrentPosition();
        centerOdoOffset = centerOdo.getCurrentPosition();
        rightOdoOffset = rightOdo.getCurrentPosition();

        // Initial position and orientation
        x = 0;
        y = 0;
        theta = 0;

        // Initialize previous ticks
        prevLeftTicks = leftOdo.getCurrentPosition();
        prevRightTicks = rightOdo.getCurrentPosition();
        prevCenterTicks = centerOdo.getCurrentPosition();
    }

    /**
     * Updates the robot's odometry based on encoder values.
     * This method calculates the robot's new position and orientation (x, y, theta).
     */
    public void update() {
        // Get current encoder positions and subtract the initial offset
        leftTicks = leftOdo.getCurrentPosition() - leftOdoOffset;
        rightTicks = rightOdo.getCurrentPosition() - rightOdoOffset;
        centerTicks = centerOdo.getCurrentPosition() - centerOdoOffset;

        // Only update if there's actual movement (change in encoder ticks)
        if (leftTicks != prevLeftTicks || rightTicks != prevRightTicks || centerTicks != prevCenterTicks) {

            // Convert encoder ticks to distance traveled (in mm)
            double leftDistMM = ticksToMM(leftTicks);
            double rightDistMM = ticksToMM(rightTicks);
            double centerDistMM = ticksToMM(centerTicks);

            // Calculate the robot's forward movement and change in orientation (theta)
            double forwardMovementMM = (leftDistMM + rightDistMM) / 2.0;
            double deltaThetaRad = (rightDistMM - leftDistMM) / DISTANCE_BETWEEN_WHEELS; // Change in radians

            // Update robot's orientation (theta) and normalize it between 0 and 2π
            theta = normalizeAngle(theta + deltaThetaRad);

            // Calculate the change in position (Δx, Δy) based on the new orientation
            double deltaX = forwardMovementMM * Math.cos(theta);
            double deltaY = forwardMovementMM * Math.sin(theta);

            // Adjust for the movement contribution from the center wheel (perpendicular movement)
            double lateralMovementMM = centerDistMM;
            deltaX += lateralMovementMM * Math.cos(theta + Math.PI / 2.0);
            deltaY += lateralMovementMM * Math.sin(theta + Math.PI / 2.0);

            // Update the robot's position (x, y)
            x += deltaX;
            y += deltaY;

            // Update previous tick values
            prevLeftTicks = leftTicks;
            prevRightTicks = rightTicks;
            prevCenterTicks = centerTicks;
        }

        fart++; // Increment fart counter for debug purposes
    }

    @Override
    public void drive(double forward, double sideways, double rotation) {
        super.drive(forward, sideways, rotation);
        update();
    }

    /**
     * Converts encoder ticks to distance traveled in millimeters.
     * @param ticks Encoder ticks.
     * @return Distance in millimeters.
     */
    private double ticksToMM(double ticks) {
        return ticks / TICKS_PER_MM;
    }

    /**
     * Normalizes an angle (in radians) to be within the range [0, 2π].
     * @param angle Angle in radians.
     * @return Normalized angle in radians.
     */
    private double normalizeAngle(double angle) {
        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < 0) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    @NonNull
    @Override
    public String toString() {
        return "[X: " + x + " mm,\n Y: " + y + " mm,\n Theta: " + Math.toDegrees(theta) + "°]\n" +
                "[Left Ticks: " + leftTicks + ", Center Ticks: " + centerTicks + ", Right Ticks: " + rightTicks + "]\nFart: " + fart;
    }
}
