package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;

@TeleOp(name="Odo Test", group="Teleop")
public class OdometryTestOpMode extends OpMode {
    private Odometry odo;

    @Override
    public void init() {
        odo = new Odometry(hardwareMap);
        odo.neutral();
    }

    @Override
    public void loop() {
        odo.update();
        telemetry.addLine(odo.toString());
    }
}