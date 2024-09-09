package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Odometry extends Drivetrain {

    // Constants for odometry
    private static final double TICKS_PER_MM = 100.0; // Conversion factor: encoder ticks to mm
    private static final double DISTANCE_BETWEEN_WHEELS = 200.0; // Distance between left and right wheels (mm)
    private static final double CENTER_WHEEL_OFFSET = 50.0; // Offset of the center wheel from the robot's center of rotation (mm)

    // Odometry wheels
    private final DcMotorEx leftOdoWheel;
    private final DcMotorEx rightOdoWheel;
    private final DcMotorEx centerOdoWheel;

    // Robot's position and orientation
    private double xPos, yPos, thetaRad; // x and y in mm, theta in radians

    public Odometry(HardwareMap hardwareMap) {
        super(hardwareMap);

        // Initialize odometry wheels
        leftOdoWheel = hardwareMap.get(DcMotorEx.class, "Left Odo Pod");
        rightOdoWheel = hardwareMap.get(DcMotorEx.class, "Right Odo Pod");
        centerOdoWheel = hardwareMap.get(DcMotorEx.class, "Center Odo Pod");

        // Set zero power behavior
        leftOdoWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightOdoWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        centerOdoWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Initialize position and orientation
        xPos = 0.0;
        yPos = 0.0;
        thetaRad = 0.0; // In radians
    }

    /**
     * Updates the robot's odometry based on encoder values.
     * This method calculates the robot's new position and orientation (x, y, theta).
     */
    public void update() {
        // Get current encoder values for each wheel
        double leftTicks = leftOdoWheel.getCurrentPosition();
        double rightTicks = rightOdoWheel.getCurrentPosition();
        double centerTicks = centerOdoWheel.getCurrentPosition();

        // Convert encoder ticks to distance traveled (in mm)
        double leftDistMM = ticksToMM(leftTicks);
        double rightDistMM = ticksToMM(rightTicks);
        double centerDistMM = ticksToMM(centerTicks);

        // Calculate the robot's forward movement and change in orientation (theta)
        double forwardMovementMM = (leftDistMM + rightDistMM) / 2.0;
        double deltaThetaRad = (rightDistMM - leftDistMM) / DISTANCE_BETWEEN_WHEELS; // Change in radians

        // Update robot's orientation (theta) and normalize it between 0 and 2π
        thetaRad = normalizeAngle(thetaRad + deltaThetaRad);

        // Calculate the change in position (Δx, Δy) based on the new orientation
        double deltaX = forwardMovementMM * Math.cos(thetaRad);
        double deltaY = forwardMovementMM * Math.sin(thetaRad);

        // Adjust for the movement contribution from the center wheel (perpendicular movement)
        double lateralMovementMM = centerDistMM + CENTER_WHEEL_OFFSET * deltaThetaRad;
        deltaX += lateralMovementMM * Math.cos(thetaRad + Math.PI / 2.0);
        deltaY += lateralMovementMM * Math.sin(thetaRad + Math.PI / 2.0);

        // Update the robot's position (xPos, yPos)
        xPos += deltaX;
        yPos += deltaY;
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

    /**
     * Converts the robot's orientation from radians to degrees.
     * @return Orientation (theta) in degrees.
     */
    public double getThetaDegrees() {
        return Math.toDegrees(thetaRad);
    }

    // Getter methods for position
    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public double getThetaRad() {
        return thetaRad;
    }
}
