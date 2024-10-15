package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;
import org.firstinspires.ftc.teamcode.Utils.Pose;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;
    protected int state = 0;
    protected Pose target = new Pose(0,0,0);

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap, telemetry);
    }
}