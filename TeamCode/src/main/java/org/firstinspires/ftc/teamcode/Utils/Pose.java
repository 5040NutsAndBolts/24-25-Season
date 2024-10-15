package org.firstinspires.ftc.teamcode.Utils;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class Pose implements Comparable<Pose> {
	public double x, y, r;

	public Pose(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	// Returns true if the current pose is outside the acceptable range of the target pose
	public boolean notWithin(Pose p) {
		double range = 10; // 10mm for X and Y
		double angleRange = Math.toRadians(5); // Allowable error for angle is 5 degrees

		return !(Math.abs(p.x - x) < range &&
				Math.abs(p.y - y) < range &&
				Math.abs(p.r - r) < angleRange);
	}

	@Override
	public int compareTo(Pose p) {
		return (int) Math.round(x - p.x + y - p.y + r - p.r);
	}

	@NonNull
	@Override
	public String toString() {
		return Arrays.toString(new double[]{x, y, r});
	}
}
