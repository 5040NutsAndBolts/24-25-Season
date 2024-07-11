package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Mechanisms.Deposit;
import org.firstinspires.ftc.teamcode.Mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.Mechanisms.Dronelauncher;
import org.firstinspires.ftc.teamcode.Mechanisms.HangMotor;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Lift;
import org.firstinspires.ftc.teamcode.TeleOps.oneDrive;

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain dt;

    @Override
    public void init() {
        oneDrive.initFunc = true;
        dt = new Drivetrain(hardwareMap);
    }

    @Override
    public void loop() {}
}
