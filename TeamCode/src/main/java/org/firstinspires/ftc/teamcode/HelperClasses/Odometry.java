package org.firstinspires.ftc.teamcode.HelperClasses;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;

public class Odometry extends Drivetrain {
    private final DcMotorEx leftOdom, rightOdom, centerOdom;
	private final int leftOffset, rightOffset, centerOffset;
	private int lastLeft, lastRight, lastCenter;
    public Position currentPosition;

	public Odometry(HardwareMap hardwareMap){
        super(hardwareMap);
        leftOdom = hardwareMap.get(DcMotorEx.class, "Front Left");
        rightOdom = hardwareMap.get(DcMotorEx.class, "Front Right");
        centerOdom = hardwareMap.get(DcMotorEx.class, "Back Left");

		leftOffset = leftOdom.getCurrentPosition();
		rightOffset = rightOdom.getCurrentPosition();
		centerOffset = centerOdom.getCurrentPosition();

        currentPosition = new Position();
    }

    public void update() {
		int robotCircumference = 100; // ROBOT CIRCUMFERENCE IN TICKS

		int deltaLeft =  leftOdom.getCurrentPosition() - lastLeft - leftOffset;
		int deltaRight = rightOdom.getCurrentPosition() - lastRight - rightOffset;
		int deltaCenter = centerOdom.getCurrentPosition() - lastCenter - centerOffset;

		lastLeft = leftOdom.getCurrentPosition() - leftOffset;
		lastRight = rightOdom.getCurrentPosition() - rightOffset;
		lastCenter = centerOdom.getCurrentPosition() - centerOffset;

		double deltaTheta = ((double) (deltaRight + deltaLeft) / 2) / robotCircumference * (2 * Math.PI) % (2 * Math.PI);

		double arcRadius = (double) (deltaRight + deltaLeft) / (2 * deltaCenter);
		double arcLength = arcRadius * deltaTheta;

		if(deltaTheta == 0) { //IN CASE OF NO ROTATION, ASSUME DIRECT MOTION
			currentPosition.x += arcLength * Math.cos(currentPosition.t);
			currentPosition.y += arcLength * Math.sin(currentPosition.t);
		}else {
			currentPosition.x += arcRadius * (Math.sin(currentPosition.t + deltaTheta) - Math.sin(currentPosition.t));
			currentPosition.y += arcRadius * (Math.cos(currentPosition.t) - Math.cos(currentPosition.t + deltaTheta));
		}

		currentPosition.t = (currentPosition.t + deltaTheta) % (2 * Math.PI);
    }

	@NonNull
	public String toString() {
		return currentPosition.toString();
	}
}