package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@Autonomous (name = "Blue Left 2", group = "Autonomous")
public class BL2 extends RobotOpMode {
    private Odometry odo;
    @Override

    public void init() {
        super.init();
        odo = new Odometry(hardwareMap);
        dt.neutral();
    }
    @Override
    public void loop() {
        odo.updatePositionRoadRunner();
        telemetry.addLine("l: "+ odo.leftE);
        telemetry.addLine("r: "+odo.rightE);
        telemetry.addLine("c: "+odo.centerE);
        telemetry.update();
        dt.autoSlowMode();

        if (odo.leftE > -8000 && odo.rightE < 8000) {
            dt.forward(0.1);
            odo.updatePositionRoadRunner();
            telemetry.update();

        } else dt.forward(0); dt.neutral();

    }

    @Override
    public void stop() {
        super.stop();
        terminateOpModeNow();
    }
}