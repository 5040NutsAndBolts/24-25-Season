package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//EVERYTHING IS IN MILLIMETERS!!!!!!!!!!
public class Odometry extends Drivetrain {
    //Where all the data is logged to
    Telemetry telemetry;

    //Constants
    private final double TICKS_PER_MILLIMETER = 10;
    private final double CENTER_ODO_OFFSET = 10;//center wheel offset
    private final double TRACKWIDTH = 10;//distance between left and right wheels

    //Encoders
    private final DcMotorEx leftOdo, centerOdo, rightOdo;

    //because encoders don't always start at zero
    private final double leftOffset, centerOffset, rightOffset;

    //previous encoder values
    private double pLeft, pCenter, pRight;

    //Current positions
    private double x, y, theta;

    public Odometry(HardwareMap hardwareMap, Telemetry t) {
        super(hardwareMap);

        telemetry = t;

        leftOdo = hardwareMap.get(DcMotorEx.class, "Left Odo");
        centerOdo = hardwareMap.get(DcMotorEx.class, "Center Odo");
        rightOdo = hardwareMap.get(DcMotorEx.class, "Right Odo");

        leftOffset = leftOdo.getCurrentPosition();
        centerOffset = centerOdo.getCurrentPosition();
        rightOffset = rightOdo.getCurrentPosition();
    }

    /**
     * Updates the odometry values based on the encoders
     */
    private void update() {
        //Distance values (Current tick amount)
        double dLeft = leftOdo.getCurrentPosition() - leftOffset;
        double dCenter = centerOdo.getCurrentPosition() - centerOffset;
        double dRight = rightOdo.getCurrentPosition() - rightOffset;

        //Delta values (Change in tick amount)
        double deltaLeft = dLeft - pLeft;
        double deltaCenter = dCenter - pCenter;
        double deltaRight = dRight - pRight;

        //Change in theta
        double deltaTheta = (deltaLeft - deltaRight) / TRACKWIDTH;
        //Midpoint orientation of theta (more accurate)
        double thetaMid = theta + (deltaTheta / 2);

        //Delta values (Change in distance)
        double deltaX = ((deltaLeft + deltaRight) / 2) * Math.cos(thetaMid);
        double deltaY = deltaCenter - (CENTER_ODO_OFFSET * deltaTheta);

        //Update previous values
        pLeft = dLeft;
        pCenter = dCenter;
        pRight = dRight;

        //Update current position
        x += deltaX * TICKS_PER_MILLIMETER;
        y += deltaY * TICKS_PER_MILLIMETER;
        theta += deltaTheta;

        //Normalize theta between 0 and 2pi
        theta %= (2 * Math.PI);
        if (theta < 0)
            theta += 2 * Math.PI;
    }

    /**
     * @param dist Distance from desired position
     * @return speed with faux PID loop
     */
    private double rampSpeed(double dist) {
        //when the bot starts to slow down (in mm)
        short stoppingDist = 200;

        return dist < stoppingDist && dist > -stoppingDist ?
                -.5 * Math.cos((dist * Math.PI) / stoppingDist) + .5
                : 1;
    }

    /**
     * Drive to a desired position
     * @param desX      desired X position
     * @param desY      desired Y position
     * @param desTheta  desired Theta position, in degrees
     */
    public void moveTo(double desX, double desY, double desTheta) {
        desTheta = Math.toRadians(desTheta);
        //Normalize theta
        desTheta %= (2 * Math.PI);
        if (desTheta < 0)
            desTheta += 2 * Math.PI;

        //account for oscillation
        while (Math.abs(desX - x) > 1 || Math.abs(desY - y) > 1 || Math.abs(desTheta - theta) > 1) {
            update();

            //Drive to desired position, adjusting speed based on distance from desired position
            drive(rampSpeed(x - desX), rampSpeed(y - desY), rampSpeed(desTheta - theta));

            //Log everything to telemetry
            telemetry.addLine("x:" + x + "\ny:" + y + "\ntheta:" + Math.toDegrees(theta));
            telemetry.addLine("desX:" + desX + "\ndesY:" + desY + "\ndesTheta:" + desTheta);
            telemetry.addLine("L:" + leftOdo.getCurrentPosition() + "\nC:" + centerOdo.getCurrentPosition() + "\nR:" + rightOdo.getCurrentPosition());
            telemetry.update();
        }
    }

    /**
     * Allows user to directly set drivetrain powers (for tuning/testing)
     * @param forward  forward power
     * @param sideways strafe power
     * @param rotation rotation power
     */
    public void driveManual(double forward, double sideways, double rotation) {
        update();
        super.drive(forward, sideways, rotation);
    }

    /**
     * @return current position in XYT and encoder values
     */
    public double[][] getCurrentPosition() {
        return new double[][] {{x, y, theta}, {leftOdo.getCurrentPosition(), centerOdo.getCurrentPosition(), rightOdo.getCurrentPosition()}};
    }
}