package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "BL", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void loop() {
        moveTo(100, 100, 90);
        moveTo(10, 20, 45);
        //this ends the opmode after everything is done to save battery :)
        terminateOpModeNow();
    }
}