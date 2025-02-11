package org.firstinspires.ftc.teamcode.HelperClasses;
import androidx.annotation.NonNull;

import java.util.function.Supplier;

public class PID {
	private final double kp, ki, kd;
	private double lastTime, deltaTime, currentTarget, errorSum, lastOutput, lastError;
	private double currentOutput;
	private final Supplier<Double> getCurrent;

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
		this.getCurrent = getCurrent;
		lastTime = System.currentTimeMillis();
	}

	//Calculates power output
	private double calculate() {
		double currentPosition = getCurrent.get();

		if(Math.abs(deltaTime) < .01)
			return lastOutput;

		double currentError = currentTarget - currentPosition;
		errorSum += currentError;

		double Proportional = kp * currentError;
		double Integral = ki * errorSum;
		double Derivative = kd * (currentError - lastError) / deltaTime;

		double output = Proportional + Integral + Derivative;

		lastError = currentError;
		lastOutput = output;
		lastTime = System.currentTimeMillis();

		currentOutput = output;
		return output;
	}

	//TeleOp control
	public double teleOpControl(double stickPower) {
		updateDeltaTime();
		setTarget(getCurrent.get() + stickPower * deltaTime);
		return calculate();
	}

	public double autoControl () {
		updateDeltaTime();
		return calculate();
	}

	public void setTarget(double target) {
		currentTarget = target;
	}

	private void updateDeltaTime() {
		long cur = System.currentTimeMillis();
		deltaTime = cur - lastTime;
		lastTime = cur;
	}

	public double getTarget() {
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
			"\tcurrentOutput: " + currentOutput+ "\n" +
			"\tcurrentTarget: " +currentTarget;
	}
}