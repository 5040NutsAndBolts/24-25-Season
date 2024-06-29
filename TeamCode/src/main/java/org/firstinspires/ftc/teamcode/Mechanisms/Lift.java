package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HelperClasses.PID;

public class Lift {

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
    public void goUp(double g) {
        setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.BRAKE);
            transferM1.setPower(-g);
            transferM2.setPower(-g);
    }
    public void goDown(double g) {
        if(transferM1.getCurrentPosition() < 50) { //lets the slides gently rest if too low to prevent crashing
            setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior.FLOAT);
            setPower(0);
        }
        else
            setPower(-g*.15);
    }
    public int getSlidePosition() {
        return (transferM1.getCurrentPosition() + transferM2.getCurrentPosition()) / 2;
    }
    private void setPower(double amt) {
        transferM1.setPower(amt);
        transferM2.setPower(amt);
    }
    private void setZeroPowerBehaviour(DcMotor.ZeroPowerBehavior zpb) {
        transferM1.setZeroPowerBehavior(zpb);
        transferM2.setZeroPowerBehavior(zpb);
    }
}
