package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.RichardWheel;
import org.firstinspires.ftc.teamcode.Mechanisms.Slides;

@TeleOp(name = "Ricardo!", group = "Teleop")
public class RichardDrive extends OpMode {

    private Drivetrain dt;
    private Slides slides;
    private RichardWheel wheels;

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap);
        slides = new Slides(hardwareMap);
        wheels = new RichardWheel(hardwareMap);
    }

    @Override
    public void loop() {
        dt.drive(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
        slides.lift(gamepad2.left_stick_y);
        wheels.spinIn(gamepad2.left_trigger);
        wheels.spinOut(gamepad2.right_trigger);
    }
}
