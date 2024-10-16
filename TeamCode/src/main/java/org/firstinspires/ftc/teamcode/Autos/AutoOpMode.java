package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap);
    }

    protected double calculateSpeedArc(double dist) {
        return .1;
        /*if (dist < 10 && dist > -10)
            return -1 * (.5 * (Math.cos(dist / Math.PI))) + .5;
        else
            return 1;*/
    }

    protected void updateOdoTelemetry() {
        telemetry.addLine("x" + odo.x);
        telemetry.addLine("y" + Math.toDegrees(odo.y));
        telemetry.addLine("theta" + odo.theta);
        telemetry.addLine("odo wheel l"+odo.leftOdom.getCurrentPosition());
        telemetry.addLine("odo wheel r"+odo.rightOdom.getCurrentPosition());
        telemetry.addLine("odo wheel c"+odo.centerOdom.getCurrentPosition());
        telemetry.update();
    }

    protected void moveTo(double X, double Y, double T) {
        while (odo.x != X || odo.y != Y || odo.theta != T) {
            dt.robot0Drive(calculateSpeedArc(Y - odo.y),
                           calculateSpeedArc(X - odo.x),
                           calculateSpeedArc(T - odo.theta));
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
        }
    }
}