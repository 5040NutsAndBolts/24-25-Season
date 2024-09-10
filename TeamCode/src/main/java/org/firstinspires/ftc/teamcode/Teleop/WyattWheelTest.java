package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.WyattOneWheel;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "WyattWheel", group = "Teleop")
public class WyattWheelTest extends RobotOpMode {
    private WyattOneWheel wheel;

    @Override
    public void init() {
        super.init();
        wheel = new WyattOneWheel(hardwareMap);
    }

    @Override
    public void loop() {
        wheel.spinIn(gamepad1.left_trigger);
        wheel.spinOut(gamepad1.right_trigger);
    }
}
