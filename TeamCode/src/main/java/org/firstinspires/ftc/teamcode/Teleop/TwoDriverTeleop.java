package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        dt.toggleSlowMode(gamepad1.b);

        wheel.spinIn(gamepad1.left_trigger);
        wheel.spinOut(gamepad1.right_trigger);

        claw.roll(gamepad2.left_stick_y);
        claw.roll(gamepad2.dpad_up ? 1 : 0);
        claw.roll(gamepad2.dpad_down ? -1 : 0);

        telemetry.addLine("Slow:  "+dt.isSlow());
        telemetry.addLine("Claw Open:  "+claw.isOpen());
    }
}