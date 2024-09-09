package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo = new Odometry(hardwareMap);

    /**
     *
     * @param dist Distance from desired position
     * @return speed with faux PID loop
     */
    protected double rampSpeed(double dist) {
        //when the bot starts to slow down (in mm)
        short stoppingDist = 200;

        return dist < stoppingDist && dist > -stoppingDist ?
                -.5 * Math.cos((dist * Math.PI)/stoppingDist) + .5
                : 1;
    }

    /**
     * Move the robot to a certain coordinate (starting position is (0,0))
     * @param desX desired X value
     * @param desY desired Y value
     * @param desT desired Theta value (in degrees)
     */
    protected void moveTo(double desX, double desY, double desT) {
        while (odo.getX() != desX && odo.getY() != desY && odo.getThetaDegrees() != odo.getThetaRad())
            odo.drive(rampSpeed(desY - odo.getY()),//forward
                      rampSpeed(desX - odo.getX()),//sideways
                      rampSpeed(Math.toRadians(desT) - odo.getThetaRad()));//turn
    }
}