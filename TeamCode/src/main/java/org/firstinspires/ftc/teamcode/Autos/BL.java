package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Blue Left", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {
        while(odo.x < 11){
            dt.robot0Drive(-0.5,0,0);
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
            if(odo.x > 10) dt.robot0Drive(0,0,0);
        }
        while(odo.y < 105){
            dt.robot0Drive(0,0,0.5);
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
            if(odo.x > 100) dt.robot0Drive(0,0,0);
        }
        ElapsedTime e = new ElapsedTime();
        e.startTime();
        while(e.seconds() < 10)
            telemetry.addLine("secs: "+e.seconds());

        //this ends the opmode after everything is done to save battery :)
        terminateOpModeNow();
    }
}