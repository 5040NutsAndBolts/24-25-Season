package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "BL", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {
        moveTo(500, 500, 90);
        //this ends the opmode after everything is done to save battery :)
        terminateOpModeNow();
    }
}