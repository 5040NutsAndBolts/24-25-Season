package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.Dronelauncher;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain dt;
    protected Dronelauncher dl;

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap);
        dl = new Dronelauncher(hardwareMap);
    }

    @Override
    public void loop() {}
}
