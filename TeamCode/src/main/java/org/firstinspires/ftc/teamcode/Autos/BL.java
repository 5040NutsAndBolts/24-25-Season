package org.firstinspires.ftc.teamcode.Autos;
import org.firstinspires.ftc.teamcode.Mechanisms.ArduCam;
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
        //Start of blue-left auto
    }
}