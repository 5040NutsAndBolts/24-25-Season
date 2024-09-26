package org.firstinspires.ftc.teamcode.Teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.RobotOpMode;

@TeleOp(name = "One Driver", group = "Teleop")
public class OneDriverTeleop extends RobotOpMode {
    //just makes it easier to read, cubes input similar to mouse acceleration
    private double cube(double x) {return x*x*x;}

    private boolean bPressed,yPressed,rawDrive;
    @Override
    public void loop() {
        if(gamepad1.b) { //slowmode toggle
            dt.toggleSlowMode();
            gamepad1.rumble(200);
            bPressed = true;
        }else if(!bPressed) bPressed = false;
        if(gamepad1.y) { //raw drive toggle
            gamepad1.rumble(200);
            yPressed = true;
            rawDrive = true;
        }else if(!yPressed) yPressed = false;

        if(rawDrive)
            dt.drive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        else dt.drive(cube(gamepad1.left_stick_y), cube(gamepad1.left_stick_x), cube(gamepad1.right_stick_x));


        telemetry.addLine("Slow:  "+ dt.isSlow());
        telemetry.addLine("Accelerated Input: " + !rawDrive);
    }
}