package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Blue Left", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {
        while(odo.curC > -7000) {
            dt.drive(0,-.3,0);
        }
    }

    @Override
    public void stop() {
        super.stop();
        terminateOpModeNow();
    }
}