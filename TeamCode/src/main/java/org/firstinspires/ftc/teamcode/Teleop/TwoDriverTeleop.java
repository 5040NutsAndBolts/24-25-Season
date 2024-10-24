package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        dt.toggleSlowMode(gamepad1.b);

        slides.lift(gamepad2.right_stick_y);

        wheel.spinIn(gamepad1.left_trigger);
        wheel.spinOut(gamepad1.right_trigger);

        telemetry.addLine("Slow:  "+dt.isSlow());
    }
}