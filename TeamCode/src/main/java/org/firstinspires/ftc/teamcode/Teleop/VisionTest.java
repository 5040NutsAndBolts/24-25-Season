package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.HelperClasses.CameraTest;
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
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
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

        webcam.setPipeline(new CameraTest());

        waitForStart();
        while(opModeIsActive())
        {
            if(CameraTest   .width < 30)
                auto = 3;
            else
            {
                if(CameraTest.screenPosition.x > 70)
                    auto = 2;
                else
                    auto = 1;
            }

            telemetry.addData("auto num", auto);
            telemetry.addData("X Position", CameraTest.screenPosition.x);
            telemetry.addData("Y Position", CameraTest.screenPosition.y);
            telemetry.addLine();
            telemetry.addData("Area", CameraTest.score);
            telemetry.addData("Width", CameraTest.width);
            telemetry.addData("Height", CameraTest.height);
            telemetry.update();

            dashboardTelemetry.addData("auto num", auto);
            dashboardTelemetry.addData("X Position", CameraTest.screenPosition.x);
            dashboardTelemetry.addData("Y Position", CameraTest.screenPosition.y);
            dashboardTelemetry.addLine();
            dashboardTelemetry.addData("Area", CameraTest.score);
            dashboardTelemetry.addData("Width", CameraTest.width);
            dashboardTelemetry.addData("Height", CameraTest.height);
            dashboardTelemetry.update();
        }
    }
}