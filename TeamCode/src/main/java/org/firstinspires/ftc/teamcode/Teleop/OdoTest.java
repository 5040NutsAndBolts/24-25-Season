package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;

@TeleOp(name = "Odo Test", group = "Teleop")
public class OdoTest extends OpMode {
    private final Odometry odo = new Odometry(hardwareMap);

    @Override
    public void init() {
        odo.neutral();
    }

    @Override
    public void loop() {
        telemetry.addLine("x"+odo.getX());
        telemetry.addLine("y"+odo.getY());
        telemetry.addLine("theta"+odo.getThetaDegrees());
    }
}