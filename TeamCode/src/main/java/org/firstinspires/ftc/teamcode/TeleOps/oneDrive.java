package org.firstinspires.ftc.teamcode.TeleOps;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.RobotOpMode;
public class oneDrive extends RobotOpMode{
    oneDrive robot;
    public oneDrive(HardwareMap hardwareMap) {
        super(hardwareMap);
    }
    @Override
    public void init() {
        robot = new oneDrive(hardwareMap);
    }
    @Override
    public void loop() {
        robot.drivetrain.robotODrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}