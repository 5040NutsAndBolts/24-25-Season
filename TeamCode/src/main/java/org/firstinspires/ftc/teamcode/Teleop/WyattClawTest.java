package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.WyattClaw;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "WyattClaw", group = "Teleop")
public class WyattClawTest extends RobotOpMode {
    private WyattClaw claw;

    @Override
    public void init() {
        super.init();
        claw = new WyattClaw(hardwareMap);
    }

    @Override
    public void loop() {
        claw.open(gamepad1.left_bumper);
        claw.close(gamepad1.right_bumper);
    }
}