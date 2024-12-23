package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp (name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        dt.toggleSlowMode(gamepad1.x);
        wheel.lift (gamepad2.left_stick_y);

        //claw.liftSlides (gamepad2.right_stick_y);
        //claw.liftClaw(gamepad1.left_bumper ? 1 : gamepad1.right_bumper ? -.2 : 0);
        //if(gamepad1.left_trigger > .05)
        //    claw.open ();
        //else if(gamepad1.right_trigger > .05)
        //    claw.pinch ();
    }
}