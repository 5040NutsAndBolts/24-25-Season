package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LimitSwitch {
	private final DigitalChannel limitSwitch;
	public LimitSwitch (HardwareMap hardwareMap, String name) {
		limitSwitch = hardwareMap.get(DigitalChannel.class, name);
	}

	public boolean isPressed() {
		return !limitSwitch.getState();
	}

	@Override
	@NonNull
	public String toString() {
		return "" + isPressed();
	}
}
