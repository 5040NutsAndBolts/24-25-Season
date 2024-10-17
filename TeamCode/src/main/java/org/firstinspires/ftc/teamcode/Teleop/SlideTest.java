package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "WyattWheel", group = "Teleop")
public class SlideTest extends TwoDriverTeleop {
    private Slides slides;

    @Override
    public void init() {
        super.init();
        slides = new Slides(hardwareMap);
    }

    @Override
    public void loop() {
        super.loop();
        slides.lift(gamepad1.right_stick_y);
    }
}
