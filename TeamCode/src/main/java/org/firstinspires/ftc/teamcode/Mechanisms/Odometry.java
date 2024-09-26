package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

//EVERYTHING IS IN MILLIMETERS!!!!!!!!!!
public class Odometry extends Drivetrain {
    //Where all the data is logged to (necessary bc of all the weird loops :(
    private final Telemetry telemetry;

    //Constants
    private final double TICKS_PER_MILLIMETER = 13.07332296; //FB and spin tests
    private final double CENTER_TICKS_PER_MILLIMETER = 12.94128571; //Strafe test
    private final double CENTER_ODO_OFFSET = 152.4;//center wheel offset
    private final double TRACKWIDTH = 393.7;//distance between left and right wheels

    //Encoders
    private final DcMotorEx leftOdo, centerOdo, rightOdo;

    //because encoders don't always start at zero
    private final double leftOffset, centerOffset, rightOffset;
    //previous encoder values
    private double pLeft, pCenter, pRight;

    //Current positions
    private double x, y, theta;
    //Previous positions
    private double pX, pY, pTheta;

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
    public void update() {
        // Get the current encoder values, accounting for the initial offsets
        double dLeft = leftOdo.getCurrentPosition() - leftOffset;
        double dCenter = centerOdo.getCurrentPosition() - centerOffset;
        double dRight = rightOdo.getCurrentPosition() - rightOffset;

        // Calculate delta values (change in encoder positions)
        double deltaLeft = dLeft - pLeft;
        double deltaCenter = dCenter - pCenter;
        double deltaRight = dRight - pRight;

        // Update previous encoder positions for the next cycle
        pLeft = dLeft;
        pCenter = dCenter;
        pRight = dRight;

        // Calculate the change in theta (robot rotation) based on left and right wheel movement
        double deltaTheta = (deltaLeft - deltaRight) / TRACKWIDTH;

        // Calculate the midpoint orientation for better accuracy in translation calculations
        double thetaMid = theta + (deltaTheta / 2);

        // If the robot is rotating significantly, focus on updating theta and avoid changing X or Y
        if (Math.abs(deltaTheta) > 0.01) {
            // Update theta
            theta += deltaTheta;

            // Normalize theta to stay between 0 and 2π
            theta %= (2 * Math.PI);
            if (theta < 0) {
                theta += 2 * Math.PI;
            }

            // Since this is a rotation, there's no significant change in X or Y
            return;
        }

        // Calculate deltaX for forward/backward movement (average of left and right wheels)
        // Convert to millimeters using TICKS_PER_MILLIMETER
        double deltaX = ((deltaLeft + deltaRight) / 2) / TICKS_PER_MILLIMETER;

        // Calculate deltaY for lateral movement using the center odometry wheel
        // Subtract the rotational effect to prevent the center wheel from adding false lateral movement
        double deltaY = (deltaCenter / CENTER_TICKS_PER_MILLIMETER) - (CENTER_ODO_OFFSET * deltaTheta);

        // Apply a rotational transformation to deltaX and deltaY to map them to world coordinates
        // This adjusts for the robot's current orientation (thetaMid) when updating X and Y
        double deltaXWorld = deltaX * Math.cos(thetaMid) - deltaY * Math.sin(thetaMid);
        double deltaYWorld = deltaX * Math.sin(thetaMid) + deltaY * Math.cos(thetaMid);

        //Update previous coordinates
        pX = x;
        pY = y;
        pTheta = theta;

        // Update the current position in the global coordinate system
        x += deltaXWorld;
        y += deltaYWorld;

        // Update theta again to account for any small rotational changes during minor movement
        theta += deltaTheta;

        // Normalize theta between 0 and 2π
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
        short stoppingDist = 500;

        return dist < stoppingDist && dist > -stoppingDist ?
                -.5 * Math.cos((dist * Math.PI) / stoppingDist) + .5
                : 1;
    }

    /**
     * Drive to a desired position / coordinate (all in mm)
     * @param desX      desired X position
     * @param desY      desired Y position
     * @param desTheta  desired Theta position (deg)
     */
    public void moveTo(double desX, double desY, double desTheta) {
        //Normalize Theta in radians
        desTheta = Math.toRadians(desTheta);
        desTheta %= 2;
        if(desTheta > 0) desTheta += Math.PI * 2;

        while(x != desX || y != desY || theta != desTheta){
            update();

            //These are kept in 'if' statements so it can create a smooth transition
            if(x != desX){
                drive(rampSpeed(x-desX),0,0);

                //In case of drift
                while(y != pY)
                    drive(0,rampSpeed(y-pY),0);
                while(theta != pTheta)
                    drive(0,0,rampSpeed(theta-pTheta));
            }
            if(y != desY){
                drive(0,rampSpeed(y-desY),0);

                //In case of drift
                while(x != pX)
                    drive(rampSpeed(x-pX),0,0);
                while(theta != pTheta)
                    drive(0,0,rampSpeed(theta-pTheta));
            }
            if(theta != desTheta) {
                drive(0,0,rampSpeed(theta-desTheta));

                //In case of drift
                while(x != pX)
                    drive(rampSpeed(x-pX),0,0);
                while(y != pY)
                    drive(0,rampSpeed(y-pY),0);
            }
        }
    }

    /**
     * Rather than specifying a position, specify an amount you want to move in a certain direction
     * @param forward   forward / backward movement
     * @param sideways  strafe movement
     * @param rotation  rotation (deg)
     */
    public void moveForward(double forward, double sideways, double rotation) {
        moveTo(x + forward, y + sideways, theta + rotation);
    }


    /**
     * Allows user to directly set drivetrain powers (for tuning/testing)
     * @param forward   forward power
     * @param sideways  strafe power
     * @param rotation  rotation power
     */
    @Override
    public void drive(double forward, double sideways, double rotation){
        update();

        //Log XYT and encoder values to telemetry
        telemetry.addData("X", x);
        telemetry.addData("Y", y);
        telemetry.addData("Theta", theta);
        telemetry.addData("Left: ", (leftOdo.getCurrentPosition() - leftOffset));
        telemetry.addData("Center: ", (centerOdo.getCurrentPosition() - centerOffset));
        telemetry.addData("Right: ", (rightOdo.getCurrentPosition() - rightOffset));
        telemetry.update();

        super.drive(forward, sideways, rotation);
    }
}