package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.*;

@Disabled
public class RobotOpMode extends OpMode {
    protected HangMotor hangMotor;
    protected Drivetrain dt;
    protected Intake intake;
    protected Lift lift;
    protected Deposit deposit;
    protected Dronelauncher dl;
    @Override
    public void init() {
        hangMotor = new HangMotor(hardwareMap);
        dt = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
        lift = new Lift(hardwareMap);
        deposit = new Deposit(hardwareMap);
        dl = new Dronelauncher(hardwareMap);
    }
    @Override public void loop() {}
}
