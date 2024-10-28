package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        wheel.lift(gamepad1.left_bumper ? .6 : 0);
        wheel.lift(gamepad1.right_bumper ? -.6 : 0);
        //wheel.spinIn (1);//gamepad1.left_trigger >   .8 ? gamepad1.left_trigger : 0);
        //wheel.spinOut(1);//gamepad1.right_trigger > .8 ? gamepad1.right_trigger : 0);

        wheel.leftServo.setPower(-1);
        wheel.rightServo.setPower(-1);

        claw.lift(gamepad1.x ? .6 : 0);
        claw.lift(gamepad1.b ? -.6 : 0);
    }
}