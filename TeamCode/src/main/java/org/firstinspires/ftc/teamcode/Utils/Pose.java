package org.firstinspires.ftc.teamcode.Utils;

public class Pose implements Comparable<Pose>{
	public double x,y,r;
	public Pose (double x,double y, double r) {
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public boolean within(Pose p) {
		double range = 10;
		return Math.abs(p.x - x) > range &&
			   Math.abs(p.y - y) > range &&
			   Math.abs(p.r - r) > range;
	}

	@Override
	public int compareTo (Pose p) {
		return (int) Math.round(x - p.x + y - p.y + r - p.r);
	}
}
