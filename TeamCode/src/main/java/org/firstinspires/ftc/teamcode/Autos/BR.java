package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "Blue Right", group = "Autonomous")
public class BR extends AutoOpMode {
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {
        //this ends the opmode after everything is done to save battery :)
        terminateOpModeNow();
    }
}