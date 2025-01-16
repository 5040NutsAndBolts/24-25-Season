package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.ArrayList;

abstract class Mechanism {
	protected HardwareMap hardwareMap;

	protected ArrayList <Motor> motors;
	protected ArrayList <SpinServo> spinServos;
	protected ArrayList <PointServo> pointServos;

	protected static class Motor {
		public final String name;
		private final DcMotorEx internalMotor;
		public Motor(String name,HardwareMap hardwareMap) {
			this.name = name;
			internalMotor = hardwareMap.get(DcMotorEx.class, name);
		}
		public void setPower(double power) {
			internalMotor.setPower(power);
		}
		public void setDirection(DcMotor.Direction direction) {
			internalMotor.setDirection(direction);
		}
		public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior) {
			internalMotor.setZeroPowerBehavior(zeroPowerBehavior);
		}
		@NonNull
		@Override
		public String toString() {
			return "\n\tMotor: " + name + "\n\tCurrent Position: " + internalMotor.getCurrentPosition() + "\n\tPower: " + internalMotor.getPower() + "\n\tDirection: " + internalMotor.getDirection() + "\n\tZeroPowerBehavior: " + internalMotor.getZeroPowerBehavior();
		}
	}
	protected static class SpinServo {
		public final String name;
		private final CRServo internalServo;
		public SpinServo(String name, HardwareMap hardwareMap) {
			this.name = name;
			this.internalServo = hardwareMap.get(CRServo.class, name);
		}
		public void setPower(double power) {
			internalServo.setPower(power);
		}
		public void setDirection(CRServo.Direction direction) {
			internalServo.setDirection(direction);
		}

		@NonNull
		@Override
		public String toString() {
			return "\n\tSpinServo: " + name + "\n\tPower: " + internalServo.getPower() + "\n\tDirection: " + internalServo.getDirection();
		}
	}
	protected static class PointServo {
		public final String name;
		private final Servo internalServo;
		public PointServo(String name, HardwareMap hardwareMap) {
			this.name = name;
			this.internalServo = hardwareMap.get(Servo.class, name);
		}
		public void setPosition(double position) {
			internalServo.setPosition(position);
		}
		@NonNull
		@Override
		public String toString() {
			return "\n\tPointServo: " + name + "\n\tPosition: " + internalServo.getPosition();
		}
	}

	Mechanism(HardwareMap hardwareMap) {
		this.hardwareMap = hardwareMap;
		spinServos = new ArrayList<>();
		pointServos = new ArrayList<>();
		motors = new ArrayList<>();
	}

	protected void addMotor(String name) {
		for(Motor motor : motors)
			if(motor.name.equals(name))
				throw new IllegalArgumentException("Motor with name " + name + " already exists");
		motors.add(new Motor(name, hardwareMap));
	}
	protected void addSpinServo(String name) {
		for (SpinServo spinServo : spinServos)
			if (spinServo.name.equals(name))
				throw new IllegalArgumentException("SpinServo with name " + name + " already exists");
		spinServos.add(new SpinServo(name, hardwareMap));
	}
	protected void addPointServo(String name) {
		for(PointServo pointServo : pointServos)
			if(pointServo.name.equals(name))
				throw new IllegalArgumentException("PointServo with name " + name + " already exists");
		pointServos.add(new PointServo(name, hardwareMap));
	}

	protected Motor getMotor(String name) {
		for(Motor motor : motors)
			if(motor.name.equals(name))
				return motor;
		throw new IllegalArgumentException("Motor with name " + name + " does not exist");
	}
	protected SpinServo getSpinServo(String name) {
		for(SpinServo spinServo : spinServos)
			if(spinServo.name.equals(name))
				return spinServo;
		throw new IllegalArgumentException("SpinServo with name " + name + " does not exist");
	}
	protected PointServo getPointServo(String name) {
		for(PointServo pointServo : pointServos)
			if(pointServo.name.equals(name))
				return pointServo;
		throw new IllegalArgumentException("PointServo with name " + name + " does not exist");
	}

	@NonNull
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getSimpleName()).append("\n");

		builder.append("Motors:\n");
		if(!motors.isEmpty())
			for(Motor motor : motors)
				builder.append("\t").append(motor.toString()).append("\n");
		else builder.append("\tNone\n");

		builder.append("Spin Servos:\n");
		if(!spinServos.isEmpty())
			for(SpinServo spinServo : spinServos)
				builder.append("\t").append(spinServo.toString()).append("\n");
		else builder.append("\tNone\n");

		builder.append("Point Servos:\n\t");
		if(!pointServos.isEmpty())
			for(PointServo pointServo : pointServos)
				builder.append("\t").append(pointServo.toString()).append("\n");
		else builder.append("\tNone\n");

		return builder.toString();
	}
}