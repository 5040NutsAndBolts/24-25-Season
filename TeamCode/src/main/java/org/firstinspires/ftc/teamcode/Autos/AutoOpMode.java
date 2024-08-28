package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Mechanisms.OdoTrain;
import org.firstinspires.ftc.teamcode.Mechanisms.ArduCam;
import org.firstinspires.ftc.teamcode.RobotOpMode;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Disabled
public class AutoOpMode extends RobotOpMode {
    public enum AllianceColor {red, blue}
    protected OdoTrain odo = new OdoTrain(hardwareMap);
    protected ArduCam cam;

    protected void initializeOpenCv() {
        //camera setup (lowkey copy pasted so dont break please :))
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

    protected double rampSpeed(double dist) {
        //when the bot starts to slow down (in inches)
        byte stoppingDist = 8;

        return dist < stoppingDist && dist > -stoppingDist ?
                .5 * Math.cos((dist * Math.PI)/16) + .5
                : 1;
    }

    protected void moveTo(double X, double Y, double T) {
        while (odo.x != X && odo.y != Y)
            odo.drive(rampSpeed(Y - odo.y),//forward
                    rampSpeed(X - odo.x),//sideways
                    rampSpeed(T - odo.theta));//turn
    }
}