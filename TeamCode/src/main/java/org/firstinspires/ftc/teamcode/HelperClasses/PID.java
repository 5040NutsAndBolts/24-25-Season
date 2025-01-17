package org.firstinspires.ftc.teamcode.HelperClasses;
import java.util.function.Supplier;
public class PID {
	/*
	 * error = target - current
	 *
	 */

	private final double kp, ki, kd;
	private double previngrl, prevError, lastOvershoot;
	private long lastTime, deltaTime;
	public PID(double kp, double ki, double kd) {
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		lastTime = System.currentTimeMillis();
	}

	private double getPower(Supplier<Double> getCurrent, double target) {
		updateDeltaTime();
		double current = getCurrent.get();

		double currentOvershoot = current - target;
		double error = ((currentOvershoot - lastOvershoot) / 2) + (lastOvershoot * deltaTime);

		double Proportional = kp * error;
		double Integral = ki * (error/2 + prevError) * deltaTime + previngrl;
		double Derivative = kd * (error-prevError)/deltaTime;

		double output = Proportional + Integral + Derivative;

		previngrl = Integral;
		prevError = error;
		lastTime = System.currentTimeMillis();
		lastOvershoot = currentOvershoot;

		return output;
	}

	public double teleOpControl(double stickPower, Supplier<Double> getCurrent) {
		updateDeltaTime();
		return getPower(
				getCurrent,
				getCurrent.get() + stickPower * deltaTime
		);
	}

	public double setHeight(Supplier<Double> getCurrent, double target)

	private void updateDeltaTime() {
		deltaTime = (System.currentTimeMillis() - lastTime);
	}
}
