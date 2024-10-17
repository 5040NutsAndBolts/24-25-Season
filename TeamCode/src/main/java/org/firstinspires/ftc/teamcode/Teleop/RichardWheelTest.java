package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.RichardWheel;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "Richard Wheel", group = "Teleop")
public class RichardWheelTest extends TwoDriverTeleop {
    private RichardWheel wheel;

    @Override
    public void init() {
        super.init();
        wheel = new RichardWheel(hardwareMap);
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
