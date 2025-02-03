package org.firstinspires.ftc.teamcode.HelperClasses;
import androidx.annotation.NonNull;

import java.util.function.Supplier;

public class PID {
	private final double kp, ki, kd;
	private double lastTime, deltaTime, currentTarget, errorSum, lastOutput, lastError;
	private Supplier<Double> getCurrent;

	/**
	 * Creates a PID controller
	 * @param kp Proportional gain (Gets near position faster, more prone to overshoot)
	 * @param ki Integral gain (Decreases steady-state error, more oscillative)
	 * @param kd Derivative gain (Reduces overshoot and smooths response)
	 * @param getCurrent Function that returns the current value of the variable being controlled
	 */
	public PID(double kp, double ki, double kd, Supplier<Double> getCurrent) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		lastTime = System.currentTimeMillis();
	}

	//Calculates power output
	private double calculate(double target) {
		double current = getCurrent.get();

		if(Math.abs(deltaTime) < .01)
			return lastOutput;

		double currentError = target - current;
		errorSum += currentError;

		double Proportional = kp * currentError;
		double Integral = ki * errorSum;
		double Derivative = kd * (currentError - lastError) / deltaTime;

		double output = Proportional + Integral + Derivative;

		lastError = currentError;
		lastOutput = output;
		lastTime = System.currentTimeMillis();

		return output;
	}

	//TeleOp control
	public double teleOpControl(double stickPower) {
		updateDeltaTime();
		return calculate(getCurrent.get() + stickPower * deltaTime);
	}

	public double autoControl (double current) {
		updateDeltaTime();
		return calculate(currentTarget);
	}

	public void setTarget(double target) {
		currentTarget = target;
	}

	private void updateDeltaTime() {
		long cur = System.currentTimeMillis();
		deltaTime = cur - lastTime;
		lastTime = cur;
	}

	public double getCurrentTarget() {
		return currentTarget;
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