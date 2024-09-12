package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
@Autonomous (name = "BL", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void loop() {
        moveTo(1000, 0, 0);
    }
}