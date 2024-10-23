package org.firstinspires.ftc.teamcode.Mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides{
    public DcMotor slideMotor;

    public Slides (HardwareMap hardwareMap){
        slideMotor = hardwareMap.get(DcMotor.class, "Slide Motor");
    }

    // This method rasies the slides
    public void spin(double power){
        if(!(motorPosition() <=0) && power >0)
           slideMotor.setPower(power);
    }
    public int motorPosition(){
        return slideMotor.getCurrentPosition();
    }
}

