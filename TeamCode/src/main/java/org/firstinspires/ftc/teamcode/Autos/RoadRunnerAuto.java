package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Odom Test",group="Autonomous")
public class RoadRunnerAuto extends AutoOpMode {
//Sideways and Rotation both work
    public void loop() {
        odo.robot0Drive(1000,0,0);
        terminateOpModeNow();
    }
}

