package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.HelperClasses.Blinkin;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.Scissor;
import org.firstinspires.ftc.teamcode.Mechanisms.SubmersibleFlywheelIntake;
import org.firstinspires.ftc.teamcode.Mechanisms.ChamberFlywheelDeposit;

@Disabled
public class RobotOpMode extends OpMode {
    protected Blinkin blinkin;
    protected Drivetrain drivetrain;
    protected ChamberFlywheelDeposit chamberWheel;
    protected SubmersibleFlywheelIntake subWheel;
    protected Scissor scissor;
	protected Odometry odo;

    public enum TeamColor {
        red, blue, noColor
    }

    public static TeamColor pTeamColor = TeamColor.noColor;

    @Override
    public void init() {
	    drivetrain = new Drivetrain (hardwareMap);
        subWheel = new SubmersibleFlywheelIntake(hardwareMap);
        chamberWheel = new ChamberFlywheelDeposit(hardwareMap);
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

    public void hangYourself (boolean perm) {
        if (perm) {
            ElapsedTime e = new ElapsedTime();
            chamberWheel.setTopTarget(1200);
            while(e.seconds() < 3) {
                chamberWheel.updateTopMotor();
                chamberWheel.updateBottomMotor();
                telemetry.addLine("Raising");
                telemetry.update();
            }while(e.seconds() < 5) {
                chamberWheel.updateTopMotor();
                chamberWheel.updateBottomMotor();
                drivetrain.drive (.4,0,0);
                telemetry.addLine("Forwarding");
                telemetry.update();
            }

            chamberWheel.setBottomTarget(500);
            chamberWheel.setTopTarget(0);

            e.reset();;
            while(e.seconds() < 10){
                drivetrain.drive (gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
                chamberWheel.updateTopMotor();
                chamberWheel.rawBottomPower(-1);
                telemetry.addLine("HANGING");
                telemetry.addLine(chamberWheel.toString());
                telemetry.update();
            }
        }
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