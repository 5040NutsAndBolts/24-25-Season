package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;

@Autonomous (name = "Blue Left", group = "Autonomous")
public class BL extends AutoOpMode {
    @Override
    public void init() {
        super.init();
    }
    @Override
    public void loop() {odo.updatePositionRoadRunner();
        telemetry.addLine("l: "+odo.leftE);
        telemetry.addLine("r: "+odo.rightE);
        telemetry.addLine("c: "+odo.centerE);
        telemetry.update();
        while (odo.leftE < 8000 && odo.rightE < 8000){
            dt.forward(0.5);
            telemetry.update();        }
    }

    @Override
    public void stop() {
        super.stop();
        terminateOpModeNow();
    }
}