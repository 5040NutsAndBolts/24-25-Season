package org.firstinspires.ftc.teamcode.HelperClasses;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;

public class Odometry extends Drivetrain {

	public static class Position {
		public double x,y,t;
		public Position(double x, double y, double t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
		public Position() {
			this(0,0,0);
		}

		/**
		 * @param a first position to be compared
		 * @param b second position to be compared
		 * @param errorMargins x, y, and turning error margins (in inches/degrees)
		 * @return return what axes are within error margins
		 */
		public static boolean[] isEqual(Position a, Position b, double[] errorMargins) {
			return new boolean[] {
					Math.abs(a.x - b.x) < errorMargins[0],
					Math.abs(a.y - b.y) < errorMargins[1],
					Math.abs(a.t - b.t) < errorMargins[2]
			};
		}
	}

    private final DcMotorEx leftOdom, rightOdom, centerOdom;
	private final int leftOffset, rightOffset, centerOffset;
	private static int lastLeft, lastRight, lastCenter;
    public static Position currentPosition;
	private final double TRACKWIDTH = 100; //Distance between left and right encoders
	private final double CENTER_OFFSET = 100; //Distance between center encoder and middle of bot
	private final double TICKS_PER_INCH = 100; //Number of ticks per inch (Check google drive for spreadsheet)

	public Odometry(HardwareMap hardwareMap){
        super(hardwareMap);
        leftOdom = hardwareMap.get(DcMotorEx.class, "Front Left");
        rightOdom = hardwareMap.get(DcMotorEx.class, "Front Right");
        centerOdom = hardwareMap.get(DcMotorEx.class, "Back Left");

		// Necessary because encoders are not zeroed at the beginning of the match
		leftOffset = leftOdom.getCurrentPosition();
		rightOffset = rightOdom.getCurrentPosition();
		centerOffset = centerOdom.getCurrentPosition();

        currentPosition = new Position();

		//Handling poor configuration
		if(TRACKWIDTH == 0) throw new Error("Odometry configuration issue! Trackwidth is 0!");
		if(CENTER_OFFSET == 0) throw new Error("Odometry configuration issue! Center offset is 0!");
		if(TICKS_PER_INCH == 0) throw new Error("Odometry configuration issue! Ticks per inch is 0!");

    }

	public void update() {
		// Local encoder deltas
		int deltaLeft = leftOdom.getCurrentPosition() - lastLeft - leftOffset;
		int deltaRight = rightOdom.getCurrentPosition() - lastRight - rightOffset;
		int deltaCenter = centerOdom.getCurrentPosition() - lastCenter - centerOffset;

		// Update previous odometry values
		lastLeft = leftOdom.getCurrentPosition() - leftOffset;
		lastRight = rightOdom.getCurrentPosition() - rightOffset;
		lastCenter = centerOdom.getCurrentPosition() - centerOffset;

		//Get total forward, rotational, and strafe distances
		double deltaFwd = (double) (deltaLeft + deltaRight) / 2;
		double deltaTheta = (deltaRight - deltaLeft) / TRACKWIDTH;
		double deltaStrafe = deltaCenter - (CENTER_OFFSET * deltaTheta);

		double deltaLocalX = (deltaFwd / deltaTheta) * Math.sin(deltaTheta) - (deltaStrafe / deltaTheta) * (1-Math.cos(deltaTheta));
		double deltaLocalY = (deltaStrafe / deltaTheta) * Math.cos(deltaTheta) + (deltaFwd / deltaTheta) * Math.cos(deltaTheta);

		//Globalization
		double angleC = Math.atan(deltaLocalX/deltaLocalY);
		double lengthK = deltaLocalX/Math.sin(angleC);
		double angleZ = 180 - deltaTheta;
		double angleV = 180-angleZ-angleC;
		double deltaGlobalX = lengthK * Math.sin(angleV);
		double deltaGlobalY = lengthK * Math.cos(angleV);   
	}

	@NonNull
	public String toString() {
		return
			"X: " + (currentPosition.x/TICKS_PER_INCH) + "\n" +
			"Y: " + (currentPosition.y/TICKS_PER_INCH) + "\n" +
			"T: " + (Math.toDegrees(currentPosition.t)) + "\n" +
			"leftOffset: " + leftOffset + "\n" +
			"rightOffset: " + rightOffset + "\n" +
			"centerOffset: " + centerOffset + "\n" +
			"leftCurrent: " + (leftOdom.getCurrentPosition() - leftOffset) + "\n" +
			"rightCurrent: " + (rightOdom.getCurrentPosition() - rightOffset) + "\n" +
			"centerCurrent: " + (centerOdom.getCurrentPosition() - centerOffset);
	}
}