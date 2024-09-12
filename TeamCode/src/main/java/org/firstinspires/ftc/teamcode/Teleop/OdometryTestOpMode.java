package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.Odometry;

@TeleOp(name="Odometry Test", group="Teleop")
public class OdometryTestOpMode extends OpMode {
    private Odometry odo;

    @Override
    public void init() {
        odo = new Odometry(hardwareMap);
        odo.neutral();
    }

    @Override
    public void loop() {
        // Handle both forward and backward movement in one call to drive()
        double forward = gamepad1.dpad_up ? 1 : (gamepad1.dpad_down ? -1 : 0);
        odo.drive(forward, 0 , 0);
        odo.update();
        telemetry.addLine(odo.toString());
    }
}
