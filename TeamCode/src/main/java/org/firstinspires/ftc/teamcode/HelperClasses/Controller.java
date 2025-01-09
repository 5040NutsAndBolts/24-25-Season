package org.firstinspires.ftc.teamcode.HelperClasses;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Controller {
	public final Gamepad gp;
	private final boolean[] previousButtonStates;

	public Controller(Gamepad gamepad) {
		this.gp = gamepad;
		// Initialize the previous button states array
		this.previousButtonStates = new boolean[10]; // Adjust size based on the number of buttons you're tracking
	}

	public boolean isButtonJustPressed(boolean currentButtonState, int buttonIndex) {
		boolean justPressed = currentButtonState && !previousButtonStates[buttonIndex];
		previousButtonStates[buttonIndex] = currentButtonState;
		return justPressed;
	}

	public boolean isButtonJustReleased(boolean currentButtonState, int buttonIndex) {
		boolean justReleased = !currentButtonState && previousButtonStates[buttonIndex];
		previousButtonStates[buttonIndex] = currentButtonState;
		return justReleased;
	}

	// Example for tracking specific buttons
	public boolean aJustPressed() {
		return isButtonJustPressed(gp.a, 0);
	}

	public boolean aJustReleased() {
		return isButtonJustReleased(gp.a, 0);
	}

	public boolean bJustPressed() {
		return isButtonJustPressed(gp.b, 1);
	}

	public boolean bJustReleased() {
		return isButtonJustReleased(gp.b, 1);
	}

	public boolean xJustPressed() {
		return isButtonJustPressed(gp.x, 2);
	}

	public boolean xJustReleased() {
		return isButtonJustReleased(gp.x, 2);
	}

	public boolean yJustPressed() {
		return isButtonJustPressed(gp.y, 3);
	}

	public boolean yJustReleased() {
		return isButtonJustReleased(gp.y, 3);
	}
	public boolean dpadUpJustPressed() {
		return isButtonJustPressed(gp.dpad_up, 0);
	}
	public boolean dpadDownJustPressed() {
		return isButtonJustPressed(gp.dpad_down, 1);
	}
	public boolean dpadLeftJustPressed() {
		return isButtonJustPressed(gp.dpad_left, 2);
	}
	public boolean dpadRightJustPressed() {
		return isButtonJustPressed(gp.dpad_right, 3);
	}

	// Add similar methods for other buttons if needed
}
