package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;
import org.firstinspires.ftc.teamcode.Utils.Pose;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;
    protected int state = 0;
    protected enum IntakeState {
        IN, OUT
    }

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap, telemetry);
    }

    protected void moveToPose(Pose dPose, int state, int liftAmount, BL.IntakeState intakeState) {
        if (this.state == state) {
            //if(intakeState == IntakeState.IN)
            //intake.spinIn(1);
            //else if(intakeState == IntakeState.OUT)
            //intake.spinOut(1);

            //if(liftAmount > lift.getPosition())
            //    lift.liftUp(.8);
            //else if (liftAmount < lift.getPosition)
            //    lift.goDown(.8)

            if (!odo.pose.within(dPose))
                odo.moveTo(dPose);

            if (odo.pose.within(dPose))
                this.state++;
        }
    }
}