package org.firstinspires.ftc.teamcode.Teleop;

import org.firstinspires.ftc.teamcode.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.RobotOpMode;

public class SlideTest extends RobotOpMode {
    Slides slides;

    @Override
    public void init() {
        super.init();
        Slides slides = new Slides(hardwareMap);
    }

    @Override
    public void loop() {
        slides.spin(gamepad1.left_stick_y);
    }
}
