package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Ring Prototype Test
 * 
 * 3 October 2020
 */

@TeleOp(name = "Ring Prototype Test")
public class RingPrototypeTest extends LinearOpMode{
    private DcMotor motorFrontRight, motorFrontLeft, motorBackLeft, motorBackRight;

    private CRServo conveyor, elevator;
    private DcMotor intake, outtakeRight, outtakeLeft;
    private Servo flipper;

    //Figures for ring elevator calculations
    private static final double PINION_CIRCUMFERENCE = 2.57;
    private static final double ELEVATOR_HEIGHT = 5.0;
    private static final double PINION_REVOLUTIONS = ELEVATOR_HEIGHT/PINION_CIRCUMFERENCE;
    private static final double SERVO_RPM = 50.0;
    private static final double ELEVATOR_TIME = PINION_REVOLUTIONS/SERVO_RPM * 60;

    //Figures for telemetry calculations
    private static final int OUTTAKE_MOTOR_RPM = 1100;
    private static final double OUTTAKE_GEAR_RATIO = 3.0;
    private static final double OUTTAKE_WHEEL_RADIUS_IN = 2;
    private static final double OUTTAKE_WHEEL_RADIUS_M = OUTTAKE_WHEEL_RADIUS_IN*0.0254;

    @Override
    public void runOpMode() throws InterruptedException {
        motorFrontRight = hardwareMap.dcMotor.get("FR");
        motorFrontLeft = hardwareMap.dcMotor.get("FL");
        motorBackLeft = hardwareMap.dcMotor.get("BL");
        motorBackRight = hardwareMap.dcMotor.get("BR");

        //intake and conveyor
        intake = hardwareMap.dcMotor.get("intake");
        conveyor = hardwareMap.crservo.get("conveyor");

        //elevator and flipper
        elevator = hardwareMap.crservo.get("elevator");
        flipper = hardwareMap.servo.get("flipper");

        //launcher
        outtakeRight = hardwareMap.dcMotor.get("outtakeRight");
        outtakeLeft = hardwareMap.dcMotor.get("outtakeLeft");

        //reverse the needed motors
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //reverse one of the outtakes
        outtakeLeft.setDirection(DcMotor.Direction.REVERSE);

        double powerMod = 1.0;
        double intakeMod = 1.0;
        double outtakeMod = 1.0;

        waitForStart();

        while(opModeIsActive()){
            /*
            Checks if right bumper is pressed. If so, power is reduced
             */
            if(gamepad1.right_bumper){
                powerMod = 0.5;
            }else{
                powerMod = 1.0;
            }

            //click a button to move in position to launch***
            //stuff for wobble
            //click another button to move in position to launch at rings

            //everything intake
            /*
            Change direction of intake
            */
            if(gamepad2.a){//press and hold a while running intake
                intakeMod = -1.0;
            }else{
                intakeMod = 1.0;
            }
            double intakeSpeed = gamepad1.left_trigger * intakeMod;
            intake.setPower(intakeSpeed);
            conveyor.setPower(intakeSpeed);//turn conveyor on when the intake turns on

            //Ring elevator
            //Run by a continuous servo; run continuous servo for some amount of time
            if(gamepad2.x){
                raiseElevator();
            }

            if(gamepad2.y){
                lowerElevator();
            }

            //Ring flipper
            //Run by a servo, 1 is fully "flipped" position, 0 is fully "retracted" position
            //Hold down b button to flip ring out
            while(gamepad2.b && flipper.getPosition() <= 1){
                flipper.setPosition(flipper.getPosition() + 0.01);
            }

            while(!gamepad2.b && flipper.getPosition() >= 0){
                flipper.setPosition(flipper.getPosition() - 0.01);
            }

            telemetry.addData("flipper position", flipper.getPosition());


            //everything outtake/launch
            //do we want this on gamepad 1 or 2?
            //I'm putting it on 2 for now...
            /*
            Ability to test a variety of outtake motor speeds from 1 to 0
            */
            if(gamepad2.dpad_up){
                if(outtakeMod != 1.0){
                    outtakeMod += 0.1;
                }
            }
            if(gamepad2.dpad_down){
                if(outtakeMod != 0.0){
                    outtakeMod -= 0.1;
                }
            }
            //Sending data on power of outtake, outtake motor RPM, and tangential velocity of outtake wheel to telemetry
            double outtakePower = (gamepad2.right_trigger * outtakeMod);
            outtakeLeft.setPower(outtakePower);
            outtakeRight.setPower(outtakePower);

            double outtakeRPM = outtakePower * OUTTAKE_MOTOR_RPM * OUTTAKE_GEAR_RATIO;
            double outtakeWheelVelocity = (outtakeRPM * 2 * Math.PI * OUTTAKE_WHEEL_RADIUS_M)/60;


            //wobble stuff??

            //everything driving
            //Mecanum drive using trig
            double angle = Math.atan2(gamepad1.right_stick_y, gamepad1.right_stick_x) - (Math.PI/4);
            double r = Math.hypot(gamepad1.right_stick_x, gamepad1.right_stick_y);
            double rotation = gamepad1.left_stick_x;

            double powerOne = r*Math.sin(angle);
            double powerTwo = r*Math.cos(angle);

            motorFrontLeft.setPower((powerOne - (rotation))*powerMod);
            motorFrontRight.setPower((powerTwo + (rotation))*powerMod);
            motorBackLeft.setPower((powerTwo - (rotation))*powerMod);
            motorBackRight.setPower((powerOne + (rotation))*powerMod);


            //Sending data on power of outtake, outtake motor RPM, and tangential velocity of outtake wheel to telemetry
            telemetry.addData("Outtake Power", outtakePower);
            telemetry.addData("Outtake RPM", outtakeRPM);
            telemetry.addData("Outtake Wheel Velocity (m/s)", outtakeWheelVelocity);

            telemetry.update();
            idle();
        }
    }

    private void raiseElevator(){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        while(timer.seconds() < ELEVATOR_TIME){
            elevator.setPower(1);
        }

        elevator.setPower(0);
    }

    private void lowerElevator(){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        while(timer.seconds() < ELEVATOR_TIME){
            elevator.setPower(-1);
        }

        elevator.setPower(0);
    }
}
