package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.IntakeTest;


@TeleOp(name = "Blinkin", group = "Teleop")
public class BlinkinTest extends OpMode
{
    private IntakeTest test;



    @Override
    public void init()
    {
         test = new IntakeTest(hardwareMap);

    }

    @Override
    public void loop() {
      test.update();

        telemetry.addLine("Red: " + test.colorTrueTest.getRed());
        telemetry.addLine("Blue: " + test.colorTrueTest.getBlue());
        telemetry.addLine("Yellow    " + test.colorTrueTest.getYellow());
        telemetry.addLine("Color Sensor Data (red, green, blue):" +     test.getSensorData());
    }
}
// RAHHHH I NEED TO FIX THIS