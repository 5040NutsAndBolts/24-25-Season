package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public double rightTrigDown, leftTrigDown;
    private final Servo pinchServo;

    private final DcMotor liftMotorTop, liftMotorBottom, rollMotor;

    public Claw(HardwareMap hardwareMap) {
        pinchServo = hardwareMap.get(Servo.class, "Pinch Servo");


        rollMotor = hardwareMap.get(DcMotor.class, "Roll Motor");
        rollMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftMotorTop = hardwareMap.get(DcMotor.class, "Claw Slide Top");
        liftMotorBottom = hardwareMap.get(DcMotor.class, "Claw Slide Bottom");
    }
        public void liftSlides(double input){
            liftMotorTop.setPower(input);
            liftMotorBottom.setPower(input);
        }

        public double pinch( double inOne, double inTwo){
            double num = inOne - inTwo;
            if (num < -.05)
                pinchServo.setPosition(1);
            else if (num > 0.05)
                pinchServo.setPosition(0);

                return num;
        }


            public void pinch ( boolean open, boolean closed){

                if (open && pinchServo.getPosition() != 1) pinchServo.setPosition(1);
                else if (closed) pinchServo.setPosition(0);
            }
            public void liftClaw ( boolean down, boolean up){
                if (down)
                    rollMotor.setPower(-.8);
                else if (up)
                    rollMotor.setPower(.5);
                else rollMotor.setPower(0);
            }

            public int getPosition() {
                return (liftMotorTop.getCurrentPosition() + liftMotorBottom.getCurrentPosition()) / 2;
            }
        }

