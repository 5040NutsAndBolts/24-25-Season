package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Odom Test",group="Autonomous")
public class RoadRunnerAuto extends AutoOpMode {
    public void init(){
        super.init();
        odo.resetOdometry(0,0,0);

    }
    public void loop() {

        //moveTo(10,0,0);

        updateOdoTelemetry();
        odo.updatePositionRoadRunner();
    }
}

