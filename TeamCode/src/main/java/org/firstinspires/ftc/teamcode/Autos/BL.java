package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Mechanisms.ArduCam;
@Autonomous (name = "BL", group = "Autonomous")
public class BL extends AutoMethods {
    @Override
    public void init() {
        super.init();
        allianceColor = AllianceColor.blue;
        cam = new ArduCam();
        spikemark = findSMPos(0,0);
    }
    @Override
    public void loop() {
        moveTo(10,10,2);
        moveTo(0,0,10);

        //this ends the opmode after everything is done to save battery :)
        terminateOpModeNow();
    }
}