package org.firstinspires.ftc.teamcode.Teleop;
import org.firstinspires.ftc.teamcode.RobotOpMode;
public class OneDriverTeleop extends RobotOpMode {
    @Override
    public void loop() {
        dt.robotODrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}