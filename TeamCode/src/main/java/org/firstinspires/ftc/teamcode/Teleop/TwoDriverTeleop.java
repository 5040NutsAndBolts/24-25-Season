package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "Two Driver", group = "Teleop")
public class TwoDriverTeleop extends RobotOpMode {
    //hi
    @Override
    public void loop() {
        dt.frontLeft.setPower(gamepad1.x ? .15 : 0);
        dt.frontRight.setPower(gamepad1.y ? .15 : 0);
        dt.backLeft.setPower(gamepad1.a ? .5 : 0);
        dt.backRight.setPower(gamepad1.b ? .5 : 0);
        dt.robot0Drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}