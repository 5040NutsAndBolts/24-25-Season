package org.firstinspires.ftc.teamcode.Autos;

public class TestAuto extends AutoOpMode{
	@Override
	public void init() {
		super.init();
	}

	private enum State {
		TEST_ONE,
		TEST_TWO,
		TEST_THREE
	}

	private State curState = State.TEST_ONE;

	@Override
	public void loop() {
		if(curState == State.TEST_ONE) {

		}
	}
}
