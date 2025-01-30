package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;
    boolean lastParkButton = false;
    boolean parkToggle = true;
    public void togglePark(boolean input) {
        if(lastParkButton != input && input)
                parkToggle = !parkToggle;
        lastParkButton = input;
    }

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap);
        togglePark(gamepad1.dpad_up);
        updateTelemetry();
    }

    protected void updateTelemetry() {
        telemetry.addLine(odo.toString());
        telemetry.addLine("PARK: " + parkToggle);
        telemetry.update();
    }
}