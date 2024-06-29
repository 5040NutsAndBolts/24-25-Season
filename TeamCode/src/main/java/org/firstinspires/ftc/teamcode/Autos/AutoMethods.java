package org.firstinspires.ftc.teamcode.Autos;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.HelperClasses.PID;
import org.firstinspires.ftc.teamcode.Mechanisms.ArduCam;
import org.firstinspires.ftc.teamcode.Mechanisms.LineSensor;
import org.firstinspires.ftc.teamcode.RobotOpMode;
import org.openftc.easyopencv.*;

@Disabled
public class AutoMethods extends RobotOpMode {
    private int boundLeft;
    private int boundRight;

    public enum AllianceColor {red, blue}
    public enum SpikeMarkPosition {left,right,centre}
    protected Odometry odo = new Odometry(hardwareMap);
    protected ArduCam cam;
    protected OpenCvWebcam openCV = initializeOpenCv();
    protected Telemetry dash = FtcDashboard.getInstance().getTelemetry();
    protected SpikeMarkPosition spikemark;
    protected LineSensor ls;
    protected AllianceColor allianceColor;
    @Override
    public void init() {
        super.init();
        ls = new LineSensor(hardwareMap);
    }
    protected OpenCvWebcam initializeOpenCv() {
        if(allianceColor == null)
            throw new NullPointerException("Uninitialized Alliance Color"); //makes sure code doesn't break if someone forgets to initialize the alliance color

        //camera setup
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        OpenCvWebcam webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam"), cameraMonitorViewId);

        webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                //set this to dimensions of camera
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);}
            @Override public void onError(int errorCode) {}});

        webcam.setPipeline(cam);
        return webcam;
    }

    protected SpikeMarkPosition findSMPos (int boundLeft, int boundRight) {
        if(cam.getScreenPosition().x > boundLeft)
            return SpikeMarkPosition.right;
        else if (cam.getScreenPosition().x < boundRight)
            return SpikeMarkPosition.left;
        else
            return SpikeMarkPosition.centre;
    }

    protected void displayCameraTelemetry() {
        telemetry.addData("Auto: ", spikemark);
        telemetry.addData("X Pos: ", cam.getScreenPosition().x);
        telemetry.addData("Y Pos: ", cam.getScreenPosition().x);
        telemetry.addData("Score: ", cam.getScore());
        telemetry.addData("Width: ", cam.getWidth());
        telemetry.addData("Height: ", cam.getHeight());
        telemetry.update();
        dash.addData("spike mark: ", spikemark);
        dash.addData("X Position: ", cam.getScreenPosition().x);
        dash.addData("Y Position: ", cam.getScreenPosition().y);
        dash.addData("Score: ", cam.getScore());
        dash.addData("Width: ", cam.getWidth());
        dash.addData("Height: ", cam.getHeight());
        dash.update();
    }
    protected boolean lineSeen() {
        if(allianceColor == AllianceColor.blue)
            return ls.getBlueValue() > 175;
        else if (allianceColor == AllianceColor.red)
            return ls.getRedValue() > 175;
        else throw new NullPointerException("Uninitialized Alliance Color");
    }
    protected double calculateSpeedArc(double dist) {
        if (dist < 10 && dist > -10)
            return -1 * (.5 * (Math.cos(dist / Math.PI))) + .5;
        else
            return 1;
    }
    protected void updateAutoTelemetry() {
        telemetry.addData("x", odo.x);
        telemetry.addData("y", odo.y);
        telemetry.addData("theta", odo.theta);

        dash.addData("x", odo.x);
        dash.addData("y", odo.y);
        dash.addData("theta", odo.theta);
        telemetry.update();
        dash.update();
    }
    protected void moveTo(double x, double y, double theta) {
        while (odo.x != x && odo.y != y)
            dt.robotODrive(calculateSpeedArc(y - odo.y), calculateSpeedArc(x - odo.x), 0);
        odo.updatePosition();
        updateAutoTelemetry();
    }
    protected void raiseLift(int desiredHeight){
        double  p = .01,
                i = 0,
                d = 0;
        PID pidController = new PID(desiredHeight - lift.getSlidePosition(), p, i, d); //resets data values
        while(desiredHeight != lift.getSlidePosition() || desiredHeight != lift.getSlidePosition()){
            pidController.update(desiredHeight-lift.getSlidePosition());
            lift.goUp(pidController.getPID());
        }
    }
    protected void dropPixels() {
        deposit.rightDrop();
        deposit.leftDrop();
    }
}