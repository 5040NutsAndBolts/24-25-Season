package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "BL", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void loop() {
        odo.moveTo(10,0,0);
        terminateOpModeNow();
    }
}