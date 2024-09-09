package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "Red Left", group = "Autonomous")
public class RL extends AutoOpMode {
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