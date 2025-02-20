package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.WyattClaw;
@TeleOp(group = "Teleop", name = "WyattTest")
public class WyattTest extends OpMode {
    WyattClaw claw;

    @Override
    public void init() {
        claw = new WyattClaw(hardwareMap);
    }

    @Override
    public void loop() {

        claw.pinch(gamepad1.left_trigger, gamepad1.right_trigger);

        if (gamepad1.a)
            claw.clawMoveUp();

        if (gamepad1.b)
            claw.clawMoveDown();

        telemetry.addLine("tilt1" + gamepad1.a);
        telemetry.addLine("tilt2" + gamepad1.b);
        telemetry.addLine("serv" + claw.tiltServo.getPosition() );
        telemetry.addLine("triggervalue left" + gamepad1.left_trigger);
        telemetry.addLine("triggervalue right" + gamepad1.right_trigger);
    }
}
