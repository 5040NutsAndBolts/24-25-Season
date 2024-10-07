package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Utils.*;

@Autonomous(name = "BL", group = "Autonomous")
public class BL extends AutoOpMode {



    @Override
    public void loop() {
        moveToPose(new Pose(0, 0, 0), 0, 0, null); // Example usage
    }
}
