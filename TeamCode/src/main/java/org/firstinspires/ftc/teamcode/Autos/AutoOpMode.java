package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.HelperClasses.Camera;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@Disabled
public class AutoOpMode extends RobotOpMode {
    boolean lastParkButton = false;
    boolean parkToggle = true;

    protected Camera camera;

    public void togglePark(boolean input) {
        if(lastParkButton != input && input)
                parkToggle = !parkToggle;
        lastParkButton = input;
    }

    @Override
    public void init() {
        super.init();
        //camera = new Camera(hardwareMap);
    }

    @Override
    public void init_loop() {
        super.init_loop();
        togglePark(gamepad1.dpad_left);
        //camera.color = pTeamColor;
    }

    protected void updateTelemetry() {
        telemetry.addLine(odo.toString());
        telemetry.addLine(wheel.toString());
        telemetry.addLine("PARK: " + parkToggle);
        telemetry.update();
    }

    @Override
    public void auto180(boolean input) {
        throw new UnsupportedOperationException("Not available for autonomous, may cause major issues");
    }
}