package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.firstinspires.ftc.teamcode.HelperClasses.BlueFinder;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import com.acmerobotics.dashboard.FtcDashboard;

@TeleOp(name = "Vision Test", group = "Teleop")
public class VisionTest extends LinearOpMode
{
    int auto = 1;

    @Override
    public void runOpMode() throws InterruptedException
    {
        //camera settup
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);

        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                //set this line to dimensions of webcam
                webcam.startStreaming(800, 600, OpenCvCameraRotation.UPRIGHT);
                //cheap logitechs are 320, 240
                //logitech brio is 1280, 720
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        FtcDashboard dashboard = FtcDashboard.getInstance();
        dashboard.startCameraStream(webcam, 0);
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        webcam.setPipeline(new BlueFinder());

        waitForStart();
        while(opModeIsActive())
        {
            if(BlueFinder.width < 30)
                auto = 3;
            else
            {
                if(BlueFinder.screenPosition.x > 70)
                    auto = 2;
                else
                    auto = 1;
            }

            telemetry.addData("auto num", auto);
            telemetry.addData("X Position", BlueFinder.screenPosition.x);
            telemetry.addData("Y Position", BlueFinder.screenPosition.y);
            telemetry.addLine();
            telemetry.addData("Area", BlueFinder.score);
            telemetry.addData("Width", BlueFinder.width);
            telemetry.addData("Height", BlueFinder.height);
            telemetry.update();

            dashboardTelemetry.addData("auto num", auto);
            dashboardTelemetry.addData("X Position", BlueFinder.screenPosition.x);
            dashboardTelemetry.addData("Y Position", BlueFinder.screenPosition.y);
            dashboardTelemetry.addLine();
            dashboardTelemetry.addData("Area", BlueFinder.score);
            dashboardTelemetry.addData("Width", BlueFinder.width);
            dashboardTelemetry.addData("Height", BlueFinder.height);
            dashboardTelemetry.update();
        }
    }
}


