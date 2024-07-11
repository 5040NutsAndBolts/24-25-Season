package org.firstinspires.ftc.teamcode.TeleOps;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "outreach", group = "Teleop")
public class oneDrive extends RobotOpMode {
    public static boolean initFunc = false;
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {
        dt.robotODrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}