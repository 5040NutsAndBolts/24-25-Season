package org.firstinspires.ftc.teamcode.Autos;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.HelperClasses.BlueFinder;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(name = "Blue Left Auto", group = "Autonomous")
public class BlueLeft extends AutoOpMode
{

	public enum specPosition //finds the position of the
	{
		isFound,
		isNotFound
	}
	specPosition auto = specPosition.isNotFound;

	public boolean isStarted = false;


	public void loop()
	{

		Odometry robot = new Odometry(hardwareMap);
		isStarted = true;

		int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
		OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);

		webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
		webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
		{
			@Override
			public void onOpened()
			{
				//set this to dimensions of camera
				webcam.startStreaming(800, 600, OpenCvCameraRotation.UPRIGHT);
			}

			@Override
			public void onError(int errorCode)
			{

			}
		});
		webcam.setPipeline(new BlueFinder());

		Telemetry dashboardTelemetry = FtcDashboard.getInstance().getTelemetry();


			telemetry.addData("BlueFinder X", BlueFinder.screenPosition.x);
			telemetry.addData("BlueFinder Y", BlueFinder.screenPosition.y);
			dashboardTelemetry.update();



			while(isStarted = true) {
					if (BlueFinder.screenPosition.x < 395)
						dt.drive(0,0.3,0);
					if (BlueFinder.screenPosition.x > 405)
						dt.drive(0,-0.3,0);

					while (BlueFinder.screenPosition.x >= 395 && BlueFinder.screenPosition.x <= 405) {
						auto = specPosition.isFound;

						dt.drive(-0.3,0,0);

					}





				}


				telemetry.addData("Auto", auto);

				telemetry.update();
				dashboardTelemetry.addData("auto", auto);
				dashboardTelemetry.update();
			}

	public void stop(){
		super.stop();
	}
}


