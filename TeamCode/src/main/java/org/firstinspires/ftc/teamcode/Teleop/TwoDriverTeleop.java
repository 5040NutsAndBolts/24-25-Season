package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;

import java.util.Arrays;

@TeleOp (name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive (-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x);

        wheel.lift (gamepad2.left_stick_y);
        wheel.spin(gamepad2.right_trigger,gamepad2.left_trigger);

        claw.liftSlides (gamepad2.right_stick_y);
        claw.liftClaw(gamepad1.left_bumper, gamepad1.right_bumper);

        claw.pinch(gamepad1.right_trigger > .05, gamepad1.left_trigger > .05);

        telemetry.addLine("Pinchpos" + claw.pinchServo.getPosition());
        telemetry.addLine("lrt" + gamepad1.left_trigger +" " + gamepad2.right_trigger);
        telemetry.update();
    }
}