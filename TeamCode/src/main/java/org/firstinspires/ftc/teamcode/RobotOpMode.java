package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.*;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain dt;

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap);
    }
    //Leave empty
    @Override public void loop() {}
}