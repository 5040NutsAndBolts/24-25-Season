package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.HelperClasses.Blinkin;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.Scissor;
import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;
import org.firstinspires.ftc.teamcode.Mechanisms.FlywheelIntake;

@Disabled
public class RobotOpMode extends OpMode {
    protected Blinkin blinkin;
    protected Drivetrain drivetrain;
    protected FlywheelIntake wheel;
    protected WheelIntake subWheel;
    protected Scissor scissor;
    protected Odometry odo;

    public enum TeamColor {
        red, blue, noColor
    }

    protected TeamColor pTeamColor = TeamColor.noColor;

    @Override
    public void init() {
        subWheel = new WheelIntake(hardwareMap);
        drivetrain = new Drivetrain (hardwareMap);
        wheel = new FlywheelIntake(hardwareMap);
        odo = new Odometry(hardwareMap);
        scissor = new Scissor(hardwareMap);
    }

    @Override
    public void init_loop() {
        if (gamepad1.dpad_up)
            pTeamColor = TeamColor.red;
        else if (gamepad1.dpad_down)
            pTeamColor = TeamColor.blue;

        subWheel.setTeamColour(pTeamColor);

        telemetry.addLine("TEAM COLOR: " + subWheel.getTeamColour());
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
}