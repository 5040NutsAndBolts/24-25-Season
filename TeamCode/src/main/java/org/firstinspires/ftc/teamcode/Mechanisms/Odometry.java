package org.firstinspires.ftc.teamcode.Mechanisms;
import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry extends Drivetrain {

    // Constants for odometry
    private static final double TICKS_PER_MM = 100.0; // Conversion factor: encoder ticks to mm
    private static final double DISTANCE_BETWEEN_WHEELS = 200.0; // Distance between left and right wheels (mm)
    private static final double CENTER_WHEEL_OFFSET = 50.0; // Offset of the center wheel from the robot's center of rotation (mm)

    // Odometry wheels
    private final DcMotorEx leftOdo,centerOdo,rightOdo;

    // Robot's position and orientation
    public double x, y, theta; // x and y in mm, theta in radians

    public Odometry(HardwareMap hardwareMap) {
        super(hardwareMap);

        leftOdo = hardwareMap.get(DcMotorEx.class, "Left Odo");
        centerOdo = hardwareMap.get(DcMotorEx.class, "Center Odo");
        rightOdo = hardwareMap.get(DcMotorEx.class, "Right Odo");

        // Initial position and orientation
        x = 0;
        y = 0;
        theta = 0;
    }

    /**
     * Updates the robot's odometry based on encoder values.
     * This method calculates the robot's new position and orientation (x, y, theta).
     */
    public void update() {
        // Get data from the encoders (both on expansion and control hub

        // Get current encoder positions from the odometry pods
        int leftTicks = leftOdo.getCurrentPosition();
        int rightTicks = rightOdo.getCurrentPosition();
        int centerTicks = centerOdo.getCurrentPosition();

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
        double lateralMovementMM = centerDistMM + CENTER_WHEEL_OFFSET * deltaThetaRad;
        deltaX += lateralMovementMM * Math.cos(theta + Math.PI / 2.0);
        deltaY += lateralMovementMM * Math.sin(theta + Math.PI / 2.0);

        // Update the robot's position (xPos, yPos)
        x += deltaX;
        y += deltaY;
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

    @Override
    public void drive(double forward, double sideways, double rotation) {
        super.drive(forward, sideways, rotation);
        update();
    }

    @NonNull
    @Override
    public String toString() {return "[X: "+x+",\n Y: "+y+",\n Theta: "+theta+"]\n[Left: "+leftOdo.getCurrentPosition()+",\n Center: "+centerOdo.getCurrentPosition()+",\n Right: "+rightOdo.getCurrentPosition()+"]";}
}
