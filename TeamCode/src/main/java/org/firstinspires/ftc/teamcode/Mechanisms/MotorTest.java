package org.firstinspires.ftc.teamcode.Mechanisms;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Mechanism Test", group = "Teleop")
public class MotorTest extends OpMode
{
    public DcMotor sillyMotor;
    public boolean bPressed = false;
    @Override
    public void init() //initialization method
    {
        sillyMotor = hardwareMap.get(DcMotor.class, "Sigma");
    }

    @Override
    public void loop() //teleop loop
    {
        if(gamepad1.right_trigger > .05)
        {
            sillyMotor.setPower(-gamepad1.right_trigger);
        }

        if(gamepad1.right_stick_x > 0.05)
        {
            sillyMotor.setPower(gamepad1.right_stick_x);
        } else if (gamepad1.right_stick_x < -0.05) {
            sillyMotor.setPower(gamepad1.right_stick_x);
        }
        //rahhh
        if (gamepad1.b && !bPressed){
            sillyMotor.setPower(1.0);
        } else if (!gamepad1.b) {
            bPressed = false;
        }
    }
}
