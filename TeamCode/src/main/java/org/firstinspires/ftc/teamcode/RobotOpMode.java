package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import org.firstinspires.ftc.teamcode.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.HelperClasses.PID;
import org.firstinspires.ftc.teamcode.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.WheelIntake;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain dt;
    protected WheelIntake wheel;
    protected Claw claw;

    @Override
    public void init() {
        dt = new Drivetrain (hardwareMap);
        wheel = new WheelIntake(hardwareMap);
        claw = new Claw(hardwareMap);
    }
    //Leave empty
    @Override public void loop() {}

    @Override
    public void stop() {
        PID p  = new PID(1,1,1);
        p.getPower(wheel::getPosition,0);
        terminateOpModeNow();
    }
}