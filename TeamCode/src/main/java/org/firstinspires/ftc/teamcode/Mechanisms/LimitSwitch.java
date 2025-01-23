package org.firstinspires.ftc.teamcode.Mechanisms;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LimitSwitch {
	private final DigitalChannel limitSwitch;
	public LimitSwitch (HardwareMap hardwareMap) {
		limitSwitch = hardwareMap.get(DigitalChannel.class, "Wheel Limit Switch");
	}

	public boolean getState () {
		return !limitSwitch.getState();
	}

	@NonNull
	@Override
	public String toString() {
		return "" + !limitSwitch.getState();
	}
}
