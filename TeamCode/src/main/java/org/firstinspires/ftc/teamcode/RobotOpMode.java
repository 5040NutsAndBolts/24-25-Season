package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;
import org.firstinspires.ftc.teamcode.Mechanisms.Slides;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain dt;
    protected Slides slides;
    protected WheelIntake wheel;

    @Override
    public void init() {
        dt = new Drivetrain (hardwareMap);
        slides = new Slides(hardwareMap);
        wheel = new WheelIntake(hardwareMap);
    }
    //Leave empty
    @Override public void loop() {}
}
