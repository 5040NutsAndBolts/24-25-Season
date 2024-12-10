package org.firstinspires.ftc.teamcode.Teleop;
import android.annotation.SuppressLint;

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
    @SuppressLint("SuspiciousIndentation")
    @Override
    public void loop() {
        odo.updatePositionRoadRunner();
        telemetry.addLine("l: "+ odo.leftE);
        telemetry.addLine("r: "+odo.rightE);
        telemetry.addLine("c: "+odo.centerE);
        telemetry.update();
        dt.autoSlowMode();

            if (odo.leftE < 18000 && odo.rightE > -18000) {
                if (odo.centerE < 200) {
                    dt.strafe(-0.2);
                }
                dt.forward(-0.4);
                odo.updatePositionRoadRunner();
                telemetry.update();
            } else if(odo.centerE < 2500) {
            dt.strafe(0.3);
            }else if (odo.leftE < -19000 && odo.rightE > 19000) {
            dt.forward(0.3);
            odo.updatePositionRoadRunner();
            telemetry.update();
        }
                dt.forward(0);
    }

    @Override
    public void stop() {
        super.stop();
        terminateOpModeNow();
    }
}