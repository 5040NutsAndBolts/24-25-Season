package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.WyattClaw;
import org.firstinspires.ftc.teamcode.RobotOpMode;
@TeleOp(name = "WyattClaw", group = "Teleop")
public class WyattClawTest extends TwoDriverTeleop {
    private WyattClaw claw;

    @Override
    public void init() {
        super.init();
        claw = new WyattClaw(hardwareMap);
    }

    @Override
    public void loop() {
        super.loop();
        claw.roll(gamepad1.right_trigger);
        claw.pinch(gamepad1.left_trigger);
    }
}