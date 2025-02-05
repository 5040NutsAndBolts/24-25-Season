package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.RobotOpMode;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@Disabled
public class AutoOpMode extends RobotOpMode {
    boolean lastParkButton = false;
    boolean parkToggle = true;
    public void togglePark(boolean input) {
        if(lastParkButton != input && input)
                parkToggle = !parkToggle;
        lastParkButton = input;
    }

    @Override
    public void init_loop() {
        super.init_loop();
        togglePark(gamepad1.dpad_up);
        telemetry.addLine("PARK: " + parkToggle);
        telemetry.update();
    }

    protected void updateTelemetry() {
        telemetry.addLine(odo.toString());
        telemetry.addLine("PARK: " + parkToggle);
        telemetry.update();
    }
}