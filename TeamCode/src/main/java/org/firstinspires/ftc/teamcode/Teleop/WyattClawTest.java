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
        claw.roll(gamepad2.left_stick_y);
        claw.roll(gamepad2.dpad_up ? 1 : 0);
        claw.roll(gamepad2.dpad_down ? -1 : 0);

        telemetry.addLine("Claw Open:  "+claw.isOpen());
    }
}