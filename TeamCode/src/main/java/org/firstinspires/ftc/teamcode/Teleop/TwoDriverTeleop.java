package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp (name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        wheel.lift (gamepad2.left_stick_y);
        telemetry.addLine("wheel power: "+ wheel.spin(gamepad2.left_trigger, gamepad2.right_trigger));
        telemetry.addLine("lt: " + gamepad1.left_trigger);
        telemetry.addLine("rt: " + gamepad1.right_trigger);
        telemetry.addLine(""+claw.pinch(gamepad1.right_trigger, gamepad1.left_trigger));

        //claw.liftSlides (gamepad2.right_stick_y);
        //claw.liftClaw(gamepad1.left_bumper ? 1 : gamepad1.right_bumper ? -.2 : 0);
        //if(gamepad1.left_trigger > .05)
        //    claw.open ();
        //else if(gamepad1.right_trigger > .05)
        //    claw.pinch ();
    }
}