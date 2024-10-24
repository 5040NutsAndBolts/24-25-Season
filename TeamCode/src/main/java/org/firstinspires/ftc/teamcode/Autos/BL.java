package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name = "Blue Left", group = "Autonomous")
public class BL extends AutoOpMode {
    private boolean xOn = true;
    private boolean yOn = false;
    @Override
    public void init() {
        super.init();
        odo.resetOdometry(0,0,0);
        updateOdoTelemetry();
        odo.updatePositionRoadRunner();
    }

    @Override
    public void loop() {


        while (odo.x < 10 && xOn == true) {
            dt.robot0Drive(-0.5, 0, 0);
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();

            if (odo.x >= 9) {
                dt.robot0Drive(0,0,0);
                odo.resetOdometry(0, 0, 0);
                yOn = true;
                xOn = false;

            }
        }
        while (odo.y < 900 && yOn) {
            dt.robot0Drive(0, -0.5, 0);
            odo.updatePositionRoadRunner();
            updateOdoTelemetry();

            if (odo.y >= 900) {
                odo.resetOdometry(0, 0, 0);
                yOn = false;
                xOn = true;
            }
            while (odo.x < 10 && xOn) {
                dt.robot0Drive(0.5, 0, 0);
                odo.updatePositionRoadRunner();
                updateOdoTelemetry();

                if (odo.x >= 10) {
                    odo.resetOdometry(0, 0, 0);
                    xOn = false;
                    yOn = true;
                }
            }
            while (odo.x < 300 && yOn) {
                dt.robot0Drive(-0.5, 0, 0);
                odo.updatePositionRoadRunner();
                updateOdoTelemetry();

                if (odo.x >= 300)
                    odo.resetOdometry(0, 0, 0);
            }
            while (odo.y < 40 && yOn) {
                dt.robot0Drive(0, -0.5, 0);
                odo.updatePositionRoadRunner();
                updateOdoTelemetry();

                if (odo.y >= 40) {
                    odo.resetOdometry(0, 0, 0);
                    yOn = false;
                    xOn = true;
                }
            }
            while (odo.y < 300 && xOn){
                dt.robot0Drive(0, -0.5, 0);
                odo.updatePositionRoadRunner();
                updateOdoTelemetry();

                if (odo.y >= 300) {
                    odo.resetOdometry(0, 0, 0);
                    yOn = false;
                    xOn = false;
                }

            }
                ElapsedTime e = new ElapsedTime();
                e.startTime();
                while (e.seconds() < 10)
                    telemetry.addLine("secs: " + e.seconds());

                //this ends the opmode after everything is done to save battery :)
                terminateOpModeNow();
            }
        }

    }

