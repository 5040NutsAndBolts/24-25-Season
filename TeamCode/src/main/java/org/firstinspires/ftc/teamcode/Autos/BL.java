package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "Blue Left", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {
        while(odo.x < 10){
            dt.robot0Drive(0.5,0,0);
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
        }


        //this ends the opmode after everything is done to save battery :)
        terminateOpModeNow();
    }
}