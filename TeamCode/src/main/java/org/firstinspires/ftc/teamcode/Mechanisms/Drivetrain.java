package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Drivetrain {
    //Drive Motors Declaration
    public static DcMotorEx frontLeft;
    public static DcMotorEx frontRight;
    public static DcMotorEx backLeft;
    public static DcMotorEx backRight;
    private double speed = 1;
    public Drivetrain turn;
    public Drivetrain(HardwareMap hardwareMap)
    {
        //Drive Motor Initialization
        frontLeft = hardwareMap.get(DcMotorEx.class, "fl");
        frontRight = hardwareMap.get(DcMotorEx.class, "fr");
        backLeft = hardwareMap.get(DcMotorEx.class, "bl");
        backRight = hardwareMap.get(DcMotorEx.class, "br");

        //Needed for how the motors are mounted
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * <p>Cancels out the angle of the mecanum wheels and also moves us forward</p>
     *
     * @param forward       forward position
     * @param sideways      sideways position

     */
    public void robotODrive(double forward, double sideways)
    {
        //Multiplied by speed variable, only changes when in slowmode
        forward *= speed;
        sideways *= 0;


        //adds all the inputs together to get the number to scale it by
        double scale = Math.abs(forward) + Math.abs(sideways);

        //Scales the inputs between 0-1 for the setPower() method
        if (scale > 1) {
            forward /= scale;

            sideways /= scale;


        }

        //Zeroes out and opposing or angular force from the Mecanum wheels
        frontLeft.setPower(forward - sideways);
        backLeft.setPower(forward + sideways);
        frontRight.setPower(forward + sideways);
        backRight.setPower(forward - sideways);
    }

    public void neutral() {
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
    public void robotOTurn(double rotation)
    {

        rotation *= speed;

        frontLeft.setPower(rotation);
        backLeft.setPower(rotation);
        frontRight.setPower(-rotation);
        backRight.setPower(-rotation);



    }

    /**
     * <p>halves our top speed for precision movements</p>
     */
    public void toggleSlowMode() {
        if(getSlowMode())
            speed=1;
        else speed=.5;
    }

    /**
     * @return returns whether or not the speed is in slow mode or not
     */
    public boolean getSlowMode() {
        return speed == 1;
    }
}