package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;

    @Override
    public void init() {
        odo = new Odometry(hardwareMap);
    }

    protected double calculateSpeedArc(double dist) {
        if (dist < 10 && dist > -10)
            return -1 * (.5 * (Math.cos(dist / Math.PI))) + .5;
        else
            return 1;
    }

    protected void updateOdoTelemetry() {
        telemetry.addData("x", odo.x);
        telemetry.addData("y", odo.y);
        telemetry.addData("theta", odo.theta);
        telemetry.update();
    }

    protected void moveTo(double X, double Y, double T) {
        while (odo.x != X && odo.y != Y && odo.theta != T) {
            dt.robot0Drive(calculateSpeedArc(Y - odo.y), calculateSpeedArc(X - odo.x), calculateSpeedArc(T - odo.theta));
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
        }
    }
}