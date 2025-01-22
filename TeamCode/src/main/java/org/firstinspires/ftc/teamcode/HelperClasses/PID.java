package org.firstinspires.ftc.teamcode.HelperClasses;
import androidx.annotation.NonNull;

public class PID {
	private final double kp, ki, kd;
	private double lastIntegral, lastError;
	private long lastTime, deltaTime;
	private double currentTarget;

	/**
	 * Creates a PID controller
	 * @param kp Proportional gain (Gets near position faster, more prone to overshoot)
	 * @param ki Integral gain (Decreases steady-state error, more oscillative)
	 * @param kd Derivative gain (Reduces overshoot and smooths response)
	 */
	public PID(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		lastTime = System.currentTimeMillis();
	}

	//Calculates power output
	private double calculate(double current, double target) {
		currentTarget = target;
		double currentError = current - target;

		double Proportional = kp * currentError;
		double Integral = lastIntegral + (currentError + lastError)/2 * deltaTime * ki;
		double Derivative = kd * (currentError + lastError)/deltaTime;

		double output = Proportional + Integral + Derivative;

		lastIntegral = Integral;
		lastError += currentError;
		lastTime = System.currentTimeMillis();

		return output;
	}

	//TeleOp control
	public double teleOpControl(double current, double stickPower) {
		updateDeltaTime();
		return calculate(
				current,
				(current + stickPower * deltaTime)
			);
	}

	public double autoControl (double current) {
		updateDeltaTime();
		return calculate(
				current,
				currentTarget
			);
	}

	public void setTarget(double target) {
		currentTarget = target;
	}

	private void updateDeltaTime() {
		deltaTime = (System.currentTimeMillis() - lastTime);
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
