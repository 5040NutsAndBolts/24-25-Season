package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Mechanisms.*;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain dt;
    protected WyattClaw claw;
    protected WyattOneWheel wheel;

    @Override
    public void init() {
        dt = new Drivetrain (hardwareMap);
        claw = new WyattClaw (hardwareMap);
        wheel = new WyattOneWheel(hardwareMap);
    }
    //Leave empty
    @Override public void loop() {}
}
