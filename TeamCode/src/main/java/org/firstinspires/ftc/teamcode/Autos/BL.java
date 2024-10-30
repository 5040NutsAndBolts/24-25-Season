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
        int t = 0;
        while((odo.leftE + odo.rightE)/2 < 16000){
            t++;
            telemetry.addLine("Moving forward");
            telemetry.addLine(""+t);
            telemetry.update();
            dt.frontLeft.setPower(-.2 + (odo.leftE > odo.rightE ? -.1 : 1) );
            dt.frontRight.setPower(-.2 + (odo.leftE > odo.rightE ? -.1 : 1) );
            dt.backLeft.setPower(-.2 + (odo.leftE > odo.rightE ? .1 : -1) );
            dt.backRight.setPower(-.2 + (odo.leftE > odo.rightE ? .1 : -1) );
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
        }
        telemetry.addLine("between");
        telemetry.update();
        dt.robot0Drive(0,0,0);
        odo.updatePositionRoadRunner();
        ElapsedTime sideSlamResetTimer = new ElapsedTime();
        while(odo.centerE < 10000 && sideSlamResetTimer.startTime() < 3){
            telemetry.addLine("Strafing Left");
            telemetry.addLine("timer: " + sideSlamResetTimer.startTime());
            dt.robot0Drive(0,-.2,0);
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
        }
        telemetry.addLine("between");
        telemetry.update();
        dt.robot0Drive(0,0,0);
    }

    @Override
    public void stop() {
        super.stop();
        terminateOpModeNow();
    }
}