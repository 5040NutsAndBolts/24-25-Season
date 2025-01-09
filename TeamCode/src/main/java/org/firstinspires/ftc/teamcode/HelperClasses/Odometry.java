package org.firstinspires.ftc.teamcode.HelperClasses;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;

public class Odometry extends Drivetrain {
    public DcMotorEx leftOdom, rightOdom, centerOdom;
    public int leftE, rightE, centerE;
    private int leftO, rightO, centerO;
    public Position currentPosition;

	public Odometry(HardwareMap hardwareMap){
        super(hardwareMap);
        leftOdom = hardwareMap.get(DcMotorEx.class, "Front Left");
        rightOdom = hardwareMap.get(DcMotorEx.class, "Front Right");
        centerOdom = hardwareMap.get(DcMotorEx.class, "Back Left");
        leftO = leftOdom.getCurrentPosition();
        rightO = rightOdom.getCurrentPosition();
        centerO = centerOdom.getCurrentPosition();
        currentPosition = new Position();
    }

    public void update() {
        leftE = leftOdom.getCurrentPosition() - leftO;
        rightE = rightOdom.getCurrentPosition() - rightO;
        centerE = centerOdom.getCurrentPosition() - centerO;

        int robotCircumference = 100; // ROBOT CIRCUMFERENCE IN TICKS

        currentPosition = new Position(
                Math.sin(currentPosition.t) * (centerE), //HORIZONTAL MOVEMENT
                Math.cos(currentPosition.t) * ((double) (rightE - leftE) / 2),//PERPENDICULAR MOVEMENT
                ((double) (rightE - leftE) / 2) / robotCircumference * (2 * Math.PI) % (2 * Math.PI) //ANGULAR MOVEMENT
        );
    }

}