package org.firstinspires.ftc.teamcode.HelperClasses;
import androidx.annotation.NonNull;

import java.util.function.Supplier;

public class PID {
	private final double kp, ki, kd;
	private double previngrl, lastError;
	private long lastTime, deltaTime;
	private double currentTarget;

	public PID(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		lastTime = System.currentTimeMillis();
	}

	//Calculates power output
	private double getPower(double current, int target) {
		currentTarget = target;
		double currentError = current - target;

		double Proportional = kp * currentError;
		double Integral = previngrl + (currentError + lastError)/2 * deltaTime * ki;
		double Derivative = kd * (currentError + lastError)/deltaTime;

		double output = Proportional + Integral + Derivative;

		previngrl = Integral;
		lastError += currentError;
		lastTime = System.currentTimeMillis();

		return output;
	}

	//TeleOp control
	public double teleOpControl(Supplier<Double> getCurrent, double stickPower) {
		updateDeltaTime();
		return getPower(
				getCurrent.get(),
				(int) (getCurrent.get() + stickPower * deltaTime)
			);
	}

	//Autonomous control
	public double autoControl (Supplier<Double> getCurrent, int target) {
		updateDeltaTime();
		return getPower(
				getCurrent.get(),
				target
			);
	}

	//Update deltaTime
	private void updateDeltaTime() {
		deltaTime = (System.currentTimeMillis() - lastTime);
	}

	@NonNull
	@Override
	public String toString() {
		return
			"PID Controller: \n" +
			"\tKp: " + kp + "\n" +
			"\tKi: " + ki + "\n" +
			"\tKd: " + kd+ "\n" +
			"\tcurrentTarget: " +currentTarget;
	}
}
