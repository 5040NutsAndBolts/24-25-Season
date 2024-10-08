package org.firstinspires.ftc.teamcode.TeleOps;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "outreach", group = "Teleop")
public class oneDrive extends RobotOpMode {
    private boolean b;
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {
        dt.robotODrive(gamepad1.left_stick_y, 0);

        dt.robotOTurn(gamepad1.right_stick_x);
        if (gamepad1.b && !b) {
            dl.launch();
            b = true;
        }
        else if (!b)
            b = false;
    }
}