package org.firstinspires.ftc.teamcode.HelperClasses;
import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry  {
    private final DcMotorEx leftOdom, rightOdom, centerOdom;
    private final int leftOffset, rightOffset, centerOffset;
    private static int lastLeft, lastRight, lastCenter;
    public static Position currentPosition;

    private final double CENTER_TO_RIGHT_POD, CENTER_TO_LEFT_POD; //Distance from tracking center of bot to right and left pods
    private final double CENTER_OFFSET;  //Distance between center encoder and tracking center
    private final double TICKS_PER_INCH = 337.4011905; //Number of ticks per inch (Check google drive for spreadsheet to calculate)

    public class Position {
        public double x,y,t;
        public Position(double x, double y, double t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
        public Position() {
            x = 0;
            y = 0;
            t = 0;
        }
        public void add(Position toAdd) {
            x += toAdd.x;
            y += toAdd.y;
            t += toAdd.t;
        }
        @NonNull
        @Override
        public String toString() {
            return
                    "X: " + x/TICKS_PER_INCH +
                    "\nY: " + y/TICKS_PER_INCH +
                    "\nT: " + t;
        }
    }

    public Odometry(HardwareMap hardwareMap, double centerToRightPod, double centerToLeftPod, double centerWheelOffset){
        leftOdom = hardwareMap.get(DcMotorEx.class, "Front Left");
        rightOdom = hardwareMap.get(DcMotorEx.class, "Front Right");
        centerOdom = hardwareMap.get(DcMotorEx.class, "Back Left");
        CENTER_TO_RIGHT_POD = centerToRightPod;
        CENTER_TO_LEFT_POD = centerToLeftPod;
        CENTER_OFFSET = centerWheelOffset;

        // Necessary because encoders are not zeroed at the beginning of the match
        leftOffset = leftOdom.getCurrentPosition();
        rightOffset = rightOdom.getCurrentPosition();
        centerOffset = centerOdom.getCurrentPosition();

        currentPosition = new Position();
    }

    public void update () {
        // Local encoder deltas
        int leftArc = leftOdom.getCurrentPosition() - lastLeft - leftOffset;
        int rightArc = rightOdom.getCurrentPosition() - lastRight - rightOffset;
        int centerArc = centerOdom.getCurrentPosition() - lastCenter - centerOffset;

        // Update previous odometry values
        lastLeft += leftArc;
        lastRight += rightArc;
        lastCenter += centerArc;

        double localDeltaX, localDeltaY;

        double deltaTheta = ((double)(leftArc - rightArc) / (CENTER_TO_RIGHT_POD+CENTER_TO_LEFT_POD)) % (2 * Math.PI);
        while(deltaTheta < 0) //Normalize theta between [0,2pi)
            deltaTheta +=  (2 * Math.PI);

        if(deltaTheta != 0) {
            localDeltaY = 2 * ((rightArc / deltaTheta) - (CENTER_TO_RIGHT_POD )) * Math.sin(deltaTheta / 2);
            localDeltaX = 2 * (centerArc / deltaTheta + CENTER_OFFSET) * Math.sin(deltaTheta / 2);
        }else {
            localDeltaY = (double) (leftArc+rightArc) / 2;
            localDeltaX = centerArc;
        }

        //Globalization
        double angleC = (Math.PI/2) - deltaTheta;
        double lengthK = localDeltaX/Math.sin(angleC);
        double deltaGlobalX = lengthK * Math.cos(currentPosition.t);
        double deltaGlobalY = lengthK  * Math.sin(currentPosition.t);

        currentPosition.add(new Position(deltaGlobalX, deltaGlobalY, deltaTheta));
    }

    @NonNull
    public String toString() {
        return
                currentPosition.toString() + "\n" +
                "leftOffset: " + leftOffset + "\n" +
                "rightOffset: " + rightOffset + "\n" +
                "centerOffset: " + centerOffset + "\n" +
                "leftCurrent: " + (leftOdom.getCurrentPosition() - leftOffset) + "\n" +
                "rightCurrent: " + (rightOdom.getCurrentPosition() - rightOffset) + "\n" +
                "centerCurrent: " + (centerOdom.getCurrentPosition() - centerOffset);
    }
}