package org.firstinspires.ftc.teamcode.HelperClasses;

import androidx.annotation.NonNull;

public class Position {
    public double x,y,t;
	public Position(double x, double y, double t) {
		this.x = x;
		this.y = y;
		this.t = t;
	}
	public Position() {
		this(0,0,0);
	}
	@NonNull
	public String toString() {
		return "X: " + x + " Y: " + y + " T: " + t;
	}
}