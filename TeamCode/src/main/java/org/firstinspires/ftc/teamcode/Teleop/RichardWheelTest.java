package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;

@TeleOp(name = "Richard Wheel", group = "Teleop")
public class RichardWheelTest extends TwoDriverTeleop {
    private WheelIntake wheel;

    @Override
    public void init() {
        super.init();
        wheel = new WheelIntake(hardwareMap);
    }

    @Override
    public void loop() {
        super.loop();
        wheel.spinIn(gamepad1.left_trigger);
        wheel.spinOut(gamepad1.right_trigger);
        telemetry.addData("Left", gamepad1.left_trigger);
        telemetry.addData("Right", gamepad1.right_trigger);
        telemetry.addData("gyatt", wheel.toString());
    }
}
