package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        dt.toggleSlowMode(gamepad1.y);

        wheel.lift (gamepad2.left_stick_y);
        wheel.spinIn (gamepad2.left_trigger > .1 ? 1 : gamepad2.right_trigger > .1 ? -1 : 0);

        //claw.liftSlides (gamepad2.right_stick_y);
        //if(gamepad2.x)
        //    claw.pinch ();
        //else if (gamepad2.b)
        //    claw.open ();

        telemetry.addLine("Slowmode: " + dt.isSlow());
        //telemetry.addLine("Claw Position: " + claw.getPosition());
        telemetry.addLine("Wheel Position: " + wheel.getPosition());
    }

    @Override
    public void stop() {
        terminateOpModeNow();
    }
}