package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class Lift {
    //PID
    public static double
            p = .01,
            i = 0,
            d = 0;

    //Transfer Motors Declaration
    private final DcMotorEx transferM1,transferM2;

    public Lift(HardwareMap hardwareMap) {
        //Lift config
        transferM1 = hardwareMap.get(DcMotorEx.class, "Transfer Motor 1");
        transferM2 = hardwareMap.get(DcMotorEx.class, "Transfer Motor 2");

        //Stops us midair
        setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);

        //Due to how the motors are mounted
        transferM2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    /**
     * <p>Makes our lift go up</p>
     * @param g typically the gamepad2 joystick
     */

    public void goUp(double g) {
        setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);
            transferM1.setPower(-g);
            transferM2.setPower(-g);
    }

    /**
     * <p>Makes our slides go down</p>
     * <p>they'll eventually slow down so we dont crash them down too hard</p>
     * @param g Slides go down at speed proportional to g, often mapped to gp2 rstick
     */
    public void goDown(double g) {
        if(transferM1.getCurrentPosition() < 50) { //lets the slides gently rest if too low to prevent crashing
            setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.FLOAT);
            setPower(0);
        }
        else
            setPower(-g*.15);
    }

    /**
     * <p>Used to make sure we don't damage the spool and for autos</p>
     * @return integer average of the two slides
     */
    public int getSlidePosition() {
        return (transferM1.getCurrentPosition() + transferM2.getCurrentPosition()) / 2;
    }

    /**
     * <p>Still currently a WIP, but should move our slides upwards via PID</p>
     * @param desiredHeight height we want to go to (in motor ticks)
     */
    public void movePID(double desiredHeight) { //Likely only going to be used for auto
        PID pidController = new PID(desiredHeight - getSlidePosition(), p, i, d); //resets data values
        while(desiredHeight != getSlidePosition() || desiredHeight != getSlidePosition()){
            pidController.update(desiredHeight-getSlidePosition());
            goUp(pidController.getPID());
        }
    }

    /**
     * Moves the transfer upward via a certain power, used for teleop
     * @param amt power proportional to double, often mapped to right stick
     */
    private void setPower(double amt) {
        transferM1.setPower(amt);
        transferM2.setPower(amt);
    }

    /**
     * <p>Internal class use only to save me from writing lines of code</p>
     * @param zpb what the motors are set to
     */
    private void setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior zpb) {
        transferM1.setZeroPowerBehavior(zpb);
        transferM2.setZeroPowerBehavior(zpb);
    }
}
