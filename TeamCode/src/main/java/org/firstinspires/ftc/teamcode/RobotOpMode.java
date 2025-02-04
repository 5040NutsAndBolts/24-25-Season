package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import org.firstinspires.ftc.teamcode.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.ScissorMech;
import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain drivetrain;
    protected WheelIntake wheel;
    protected ScissorMech scissor;

    public enum Color {
        red, blue, yellow, other
    }

    protected Color teamColor;

    @Override
    public void init() {
        drivetrain = new Drivetrain (hardwareMap);
        wheel = new WheelIntake(hardwareMap);
    }

    @Override
    public void init_loop() {
        super.init_loop();
        if(gamepad1.dpad_left)
            teamColor = Color.red;
        else if (gamepad1.dpad_right)
            teamColor = Color.blue;
        telemetry.addLine("Color: " + teamColor);
        telemetry.update();
        scissor = new ScissorMech(hardwareMap, teamColor);
    }

    //Leave empty
    @Override public void loop() {}

    @Override
    public void stop() {
        terminateOpModeNow();
    }
}