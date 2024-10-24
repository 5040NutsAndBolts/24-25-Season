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
        while((odo.leftE + odo.rightE)/2 < 16000){
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
            dt.frontLeft.setPower(-.2 + (odo.leftE > odo.rightE ? -.1 : 1) );
            dt.frontRight.setPower(-.2 + (odo.leftE > odo.rightE ? -.1 : 1) );
            dt.backLeft.setPower(-.2 + (odo.leftE > odo.rightE ? .1 : -1) );
            dt.backRight.setPower(-.2 + (odo.leftE > odo.rightE ? .1 : -1) );
            odo.updatePositionRoadRunner();
            if((odo.leftE + odo.rightE)/2 >= 20000){
                dt.frontLeft.setPower(0);
                dt.frontRight.setPower(0);
                dt.backLeft.setPower(0);
                dt.backRight.setPower(0);
                return;
            }
        }
        ElapsedTime sideSlamResetTimer = new ElapsedTime();
        while(odo.centerE < 10000 && sideSlamResetTimer.seconds() < 3){
            telemetry.addLine("timer: " + sideSlamResetTimer.seconds());
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();
            dt.robot0Drive(0,-.2,0);
            odo.updatePositionRoadRunner();
            if(odo.centerE >= 10000 || sideSlamResetTimer.seconds() >= 3){
                dt.frontLeft.setPower(0);
                dt.frontRight.setPower(0);
                dt.backLeft.setPower(0);
                dt.backRight.setPower(0);
                odo.resetOdometry(0,0,0);
                return;
            }
        }
    }

    @Override
    public void stop() {
        super.stop();
        terminateOpModeNow();
    }
}