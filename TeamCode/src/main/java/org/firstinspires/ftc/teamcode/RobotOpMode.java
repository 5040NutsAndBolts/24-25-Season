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

@Disabled
public class RobotOpMode extends OpMode {
    protected Drivetrain drivetrain;
    public RobotOpMode(HardwareMap hardwareMap) {
        drivetrain = new Drivetrain(hardwareMap);
    }

    //DO NOT TOUCH THESE TWO METHODS THEY ARE EMPTY BECAUSE WE WILL NEVER RUN THIS CLASS
    @Override
    public void init() {}
    @Override
    public void loop() {}
}
