package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Mechanisms.*;

@Disabled
public class RobotOpMode extends OpMode {
    protected Telemetry dash = FtcDashboard.getInstance().getTelemetry();

    protected Drivetrain dt;

    @Override
    public void init() {
        dt = new Drivetrain(hardwareMap);
    }
    //Leave empty
    @Override public void loop() {}
}
