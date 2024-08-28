package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Mechanisms.ArduCam;
@Autonomous (name = "BL", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void init() {
        super.init();
        cam = new ArduCam(AllianceColor.blue);
        initializeOpenCv();
    }
    @Override
    public void loop() {
        //this ends the opmode after everything is done to save battery :)
        terminateOpModeNow();
    }
}