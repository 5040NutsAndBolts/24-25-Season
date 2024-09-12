package org.firstinspires.ftc.teamcode.Mechanisms;
import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry extends Drivetrain {
    // Constants for odometry
    private static final double TICKS_PER_MM = 14.58756105; // Conversion factor: encoder ticks to mm
    private static final double DISTANCE_BETWEEN_WHEELS = 386.08; // Distance between left and right wheels (mm)
    private static final double CENTER_WHEEL_OFFSET = 171.45; // Offset of center wheel from center of robot (mm)

    // Odometry wheels
    private final DcMotorEx leftOdo, centerOdo, rightOdo;

    // Encoder offsets
    private final int LEFT_ODO_OFFSET, CENTER_ODO_OFFSET, RIGHT_ODO_OFFSET;

    // Previous tick values
    private int pLeftTicks, pCenterTicks, pRightTicks;

    // Robot's position and orientation
    public double x, y, theta; // x and y in mm, theta in radians

    // Constructor to initialize odometry hardware
    public Odometry(HardwareMap hardwareMap) {
	    super(hardwareMap);
	    leftOdo = hardwareMap.get(DcMotorEx.class, "Left Odo");
        centerOdo = hardwareMap.get(DcMotorEx.class, "Center Odo");
        rightOdo = hardwareMap.get(DcMotorEx.class, "Right Odo");

        // Capture initial encoder positions to account for the offsets
        LEFT_ODO_OFFSET = leftOdo.getCurrentPosition();
        CENTER_ODO_OFFSET = centerOdo.getCurrentPosition();
        RIGHT_ODO_OFFSET = rightOdo.getCurrentPosition();

        // Initialize previous tick values
        pLeftTicks = leftOdo.getCurrentPosition();
        pRightTicks = rightOdo.getCurrentPosition();
        pCenterTicks = centerOdo.getCurrentPosition();

        // Initialize robot's position and orientation
        x = 0;
        y = 0;
        theta = 0;
    }

    /**
     * Updates the robot's odometry based on encoder values.
     * This method calculates the robot's new position and orientation (x, y, theta).
     */
    private static final int TICK_THRESHOLD = 5; // Threshold to ignore small changes from noise

    public void update() {
        // Get current encoder positions and subtract the initial offsets
        int leftTicks = leftOdo.getCurrentPosition() - LEFT_ODO_OFFSET;
        int rightTicks = rightOdo.getCurrentPosition() - RIGHT_ODO_OFFSET;
        int centerTicks = centerOdo.getCurrentPosition() - CENTER_ODO_OFFSET;

        // Only update if there’s significant movement (above the threshold)
        if (Math.abs(leftTicks - pLeftTicks) > TICK_THRESHOLD ||
                Math.abs(rightTicks - pRightTicks) > TICK_THRESHOLD ||
                Math.abs(centerTicks - pCenterTicks) > TICK_THRESHOLD) {

            // Calculate the change in distances and orientation
            double deltaLeft = (leftTicks - pLeftTicks) * TICKS_PER_MM;
            double deltaRight = (rightTicks - pRightTicks) * TICKS_PER_MM;
            double deltaTheta = (deltaLeft - deltaRight) / DISTANCE_BETWEEN_WHEELS;

            // Calculate midpoint theta for arc integration
            double thetaMid = theta + (deltaTheta / 2.0);

            // Calculate the change in position based on deltaTheta and wheel distances
            double deltaX = ((deltaLeft + deltaRight) / 2.0) * Math.cos(thetaMid);
            double deltaY = ((deltaLeft + deltaRight) / 2.0) * Math.sin(thetaMid);

            // Apply center wheel lateral movement correction
            double deltaYLat = centerTicks * TICKS_PER_MM; // Lateral movement from center wheel
            double deltaYOffset = CENTER_WHEEL_OFFSET * deltaTheta; // Lateral offset due to turn
            double deltaYLatCorrected = deltaYLat - deltaYOffset;

            // Update position and orientation
            x += deltaX;
            y += deltaY + deltaYLatCorrected;
            theta += deltaTheta;

            // Update previous ticks for the next cycle
            pLeftTicks = leftTicks;
            pRightTicks = rightTicks;
            pCenterTicks = centerTicks;
        }
    }

    @NonNull
    public String toString() {
        return "X: " + x + "\nY: " + y + "\nTheta: " + theta;
    }
}
