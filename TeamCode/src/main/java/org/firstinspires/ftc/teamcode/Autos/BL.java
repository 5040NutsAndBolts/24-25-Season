package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Utils.Pose;

@Autonomous(name = "BL", group = "Autonomous")
public class BL extends AutoOpMode {
    int runNum = 0;
    boolean completed = false;

    @Override
    public void loop() {
        runNum++;
        telemetry.addLine("State: " + state);
        telemetry.addLine("Run Number: " + runNum);
        odo.update();

        while(!(odo.pose.x > 20 && odo.pose.x < 30)) {
            if(odo.pose.x < 20) {
                telemetry.addLine("<20x");
                odo.drive(-.5, 0, 0);
            }
            else if (odo.pose.x > 30) {
                telemetry.addLine(">30x");
                odo.drive(.5, 0, 0);
            }
            else return;
        }
        while(!(odo.pose.y > 20 && odo.pose.y < 30)) {
            if(odo.pose.y < 20) {
                telemetry.addLine("<20y");
                odo.drive(0, -.5, 0);
            }
            else if (odo.pose.y > 30) {
                telemetry.addLine(">30y");
                odo.drive(0, .5, 0);
            }
            else return;
        }

        telemetry.update();
        terminateOpModeNow();
    }
}