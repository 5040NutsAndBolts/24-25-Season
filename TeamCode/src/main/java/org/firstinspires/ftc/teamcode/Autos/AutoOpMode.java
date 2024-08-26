package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.ArduCam;
import org.firstinspires.ftc.teamcode.RobotOpMode;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Disabled
public class AutoOpMode extends RobotOpMode {
    public enum AllianceColor {red, blue}
    protected Odometry odo = new Odometry(hardwareMap);
    protected ArduCam cam;


    //leave empty
    @Override
    public void init() {super.init();}
    @Override
    public void loop(){super.loop();}



    protected void initializeOpenCv() {
        //camera setup
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);

        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                //set this to dimensions of camera
                webcam.startStreaming(1920, 1080, OpenCvCameraRotation.UPRIGHT);}
            @Override public void onError(int errorCode) {}});

        webcam.setPipeline(cam);
    }

    protected void displayCameraTelemetry() {
        telemetry.addData("X Pos: ", cam.getScreenPosition().x);
        telemetry.addData("Y Pos: ", cam.getScreenPosition().y);
        telemetry.addData("Score: ", cam.getScore());
        telemetry.addData("Width: ", cam.getWidth());
        telemetry.addData("Height: ", cam.getHeight());
        telemetry.update();
        dash.addData("X Position: ", cam.getScreenPosition().x);
        dash.addData("Y Position: ", cam.getScreenPosition().y);
        dash.addData("Score: ", cam.getScore());
        dash.addData("Width: ", cam.getWidth());
        dash.addData("Height: ", cam.getHeight());
        dash.update();
    }
    protected double calculateSpeedArc(double dist) {
        if (dist < 10 && dist > -10)
            return -1 * (.5 * (Math.cos(dist / Math.PI))) + .5;
        else
            return 1;
    }
    protected void updateOdoTelemetry() {
        telemetry.addData("x", odo.x);
        telemetry.addData("y", odo.y);
        telemetry.addData("theta", odo.theta);

        dash.addData("x", odo.x);
        dash.addData("y", odo.y);
        dash.addData("theta", odo.theta);
        displayCameraTelemetry();
        telemetry.update();
        dash.update();
    }
    protected void moveTo(double X, double Y, double T) {
        while (odo.x != X && odo.y != Y)
            dt.robotODrive(calculateSpeedArc(Y - odo.y), calculateSpeedArc(X - odo.x), calculateSpeedArc(T - odo.theta));
        odo.updatePositionRoadRunner();
        updateOdoTelemetry();
    }
}