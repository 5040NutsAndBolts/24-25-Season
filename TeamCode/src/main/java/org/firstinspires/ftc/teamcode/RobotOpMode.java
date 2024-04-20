package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
public class RobotOpMode extends OpMode {

    //pull up motor
    protected DcMotorEx hangMotor,
                                          frontLeft, frontRight, backLeft, backRight,
                                          transferM1, transferM2;
    protected CRServo transferCR1, transferCR2,
                                    intakeServo;
    protected Servo droneLaunch,
                                depositServo1, depositServo2;
    protected DcMotor intakeMotor;
    public RobotOpMode(HardwareMap hardwareMap) {
        //Hang Motor: Used to hang from the truss
        hangMotor = hardwareMap.get(DcMotorEx.class, "Hang Motor");


        //Drive Motor: Motors used to run teh drivetrain
        frontLeft = hardwareMap.get(DcMotorEx.class, "Front Left");
        frontRight = hardwareMap.get(DcMotorEx.class, "Front Right");
        backLeft = hardwareMap.get(DcMotorEx.class, "Back Left");
        backRight = hardwareMap.get(DcMotorEx.class, "Back Right");
        //Set to brake so we don't fly across the field
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //These are reversed just because of how things are physically mounted
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


        //Transfer RAISE motors
        transferM1 = hardwareMap.get(DcMotorEx.class, "Transfer Motor 1");
        transferM2 = hardwareMap.get(DcMotorEx.class, "Transfer Motor 2");
        //This holds our  position in the air
        transferM1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferM2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //The encoder is used to make sure we don't burn out our motors, if we are below a certain position we just drop
        transferM1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        transferM1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //We must set this to reverse due to how it is strung together
        transferM2.setDirection(DcMotorSimple.Direction.REVERSE);


        //Intake Config: Transfer servos spin with intake servo/motor
        intakeServo = hardwareMap.get(CRServo.class, "Intake Servo");
        intakeMotor = hardwareMap.get(DcMotor.class, "Intake Motor");
        transferCR1 = hardwareMap.get(CRServo.class, "Transfer Servo 1");
        transferCR2 = hardwareMap.get(CRServo.class, "Transfer Servo 2");


        //Deposit Servos
        depositServo1 = hardwareMap.get(Servo.class, "Right Deposit");
        depositServo2 = hardwareMap.get(Servo.class, "Left Deposit");


        //Drone Launcher
        droneLaunch = hardwareMap.get(Servo.class, "Drone Launcher");
    }

    //DO NOT TOUCH THESE TWO METHODS THEY ARE EMPTY BECAUSE WE WILL NEVER RUN THIS CLASS
    @Override
    public void init() {}
    @Override
    public void loop() {}
}
