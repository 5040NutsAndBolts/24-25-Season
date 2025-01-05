package org.firstinspires.ftc.teamcode.Autos;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.HelperClasses.Odometry;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@Disabled
public class AutoOpMode extends RobotOpMode {
    protected Odometry odo;

    protected enum MOVEDIR {
        FORWARD,
        BACKWARD,
        LEFTSTRAFE,
        RIGHTSTRAFE,
        STOP
    }

    @Override
    public void init() {
        super.init();
        odo = new Odometry(hardwareMap);
    }

    protected void move(MOVEDIR dir, double power) {
        power = Math.abs(power);
        switch(dir) {
            case FORWARD:
                dt.drive(-power,0,0);
                telemetry.addLine("Moving Forward");
                updateOdoTelemetry();
                break;
            case BACKWARD:
                dt.drive(power,0,0);
                telemetry.addLine("Moving Backward");
                updateOdoTelemetry();
                break;
            case LEFTSTRAFE:
                dt.drive(0,-power,0);
                telemetry.addLine("Moving Left");
                updateOdoTelemetry();
                break;
            case RIGHTSTRAFE:
                dt.drive(0,power,0);
                telemetry.addLine("Moving Right");
                updateOdoTelemetry();
                break;
            default:
                dt.drive(0,0,0);
                telemetry.addLine("Stopped");
                updateOdoTelemetry();
                break;
        }
    }

    protected void updateOdoTelemetry() {
        telemetry.addLine(odo.toString());
        telemetry.update();
    }
}