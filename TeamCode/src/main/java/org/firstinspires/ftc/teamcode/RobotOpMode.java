package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.ScissorMech;
import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain drivetrain;
    protected WheelIntake wheel;
    protected ScissorMech scissor;
    protected Odometry odo;

    public enum Color {
        red, blue, yellow, noColor
    }

    protected Color teamColor;

    @Override
    public void init() {
        drivetrain = new Drivetrain (hardwareMap);
        wheel = new WheelIntake(hardwareMap);
        odo = new Odometry(hardwareMap);
        scissor = new ScissorMech(hardwareMap);
    }

    @Override
    public void init_loop() {
        if(gamepad1.dpad_up) {
            teamColor = Color.red;
            scissor.teamColour = Color.red;
        }
        else if(gamepad1.dpad_down) {
            teamColor = Color.blue;
            scissor.teamColour = Color.blue;
        }
        telemetry.addLine("TEAM COLOR: " + scissor.teamColour);
        telemetry.update();
    }

    public void auto180 (boolean input) {
        if(odo != null) {
            if(input){
                odo.resetOdometry();
                while (odo.centerE < 8000)
                    drivetrain.drive(0, 0, 1);
                drivetrain.drive(0, 0, -.5);
            } else return;
        }else throw new NullPointerException("Odometry object is null");
    }

    //Leave empty
    @Override public void loop() {}

    @Override
    public void stop() {
        terminateOpModeNow();
    }
}