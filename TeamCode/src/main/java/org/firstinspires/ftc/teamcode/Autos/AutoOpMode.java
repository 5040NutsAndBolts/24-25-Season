package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap);
    }

    protected void updateOdoTelemetry() {
        telemetry.addLine("l: "+odo.leftE);
        telemetry.addLine("r: "+odo.rightE);
        telemetry.addLine("c: "+odo.centerE);
        telemetry.update();
    }
}