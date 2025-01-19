package org.firstinspires.ftc.teamcode.HelperClasses;


import androidx.annotation.NonNull;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry  {
    /**Encoders*/
    private final DcMotorEx leftOdom, rightOdom, centerOdom;
    /**Robot's initial encoder position, used to if encoder value does not start at 0*/
    private final int leftOffset, rightOffset, centerOffset;
    /**Robot's last encoder position*/
    private static int lastLeft, lastRight, lastCenter;
    /**Robot's current position*/
    public static Position currentPosition;
    private final double CENTER_TO_RIGHT_POD, CENTER_TO_LEFT_POD;
    private final double CENTER_OFFSET;
    private final double TICKS_PER_INCH;
    /**
     * Creates an Odometry object
     * @param hardwareMap  Config file as configured in DriverHub
     * @param centerToRightPod  Distance from tracking center of bot to right pod
     * @param centerToLeftPod Distance from tracking center of bot to left pod
     * @param centerWheelOffset Distance between center encoder and tracking center
     */
    public Odometry(HardwareMap hardwareMap, double centerToRightPod, double centerToLeftPod, double centerWheelOffset, double tickPerInch){
        leftOdom = hardwareMap.get(DcMotorEx.class, "Front Left");
        rightOdom = hardwareMap.get(DcMotorEx.class, "Front Right");
        centerOdom = hardwareMap.get(DcMotorEx.class, "Back Left");
        CENTER_TO_RIGHT_POD = centerToRightPod;
        CENTER_TO_LEFT_POD = centerToLeftPod;
        CENTER_OFFSET = centerWheelOffset;
        TICKS_PER_INCH = tickPerInch;

        // Necessary because encoders are not zeroed at the beginning of the match
        leftOffset = leftOdom.getCurrentPosition();
        rightOffset = rightOdom.getCurrentPosition();
        centerOffset = centerOdom.getCurrentPosition();

        currentPosition = new Position();
    }

    /**
     * Updates the odometry based on encoder differences
     */
    public void update () {
        // Local encoder deltas
        int leftArc = leftOdom.getCurrentPosition() - lastLeft - leftOffset;
        int rightArc = rightOdom.getCurrentPosition() - lastRight - rightOffset;
        int centerArc = centerOdom.getCurrentPosition() - lastCenter - centerOffset;

        // Update previous odometry values, must use previously stored values of it within the arc values in case getCurrentPosition() runs on a separate thread and has a different value
        lastLeft += leftArc;
        lastRight += rightArc;
        lastCenter += centerArc;

        //Local x and Y change in the current arc
        double localDeltaX, localDeltaY;
        double deltaTheta = ((double)(leftArc - rightArc) / (CENTER_TO_RIGHT_POD+CENTER_TO_LEFT_POD)) % (2 * Math.PI);
        while(deltaTheta < 0) //Normalize theta between [0,2pi)
            deltaTheta +=  (2 * Math.PI);

        if(deltaTheta != 0) { //If robot has not turned, then set x and y to according encoder values
            localDeltaY = 2 * ((rightArc / deltaTheta) - (CENTER_TO_RIGHT_POD )) * Math.sin(deltaTheta / 2);
            localDeltaX = 2 * (centerArc / deltaTheta + CENTER_OFFSET) * Math.sin(deltaTheta / 2);
        }else {
            localDeltaY = leftArc; //If left and right arcs were different, then the right arc is the same as the left arc, which is the local change in y (forward)
            localDeltaX = centerArc;  //As per above, the local change in x is the same as the center arc
        }

        //Globalization - interpreting the local change in x and y to a change in global x and y
        double angleC = (Math.PI/2) - deltaTheta; //Find third angle (that being, not the right angle between local x and y axes nor the deltaTheta angle)
        double lengthK = localDeltaX/Math.sin(angleC); //Distance between start of arc and end of arc
        double deltaGlobalX = lengthK * Math.cos(currentPosition.t); //Change in global x
        double deltaGlobalY = lengthK  * Math.sin(currentPosition.t); //Change in global y

        currentPosition.add(new Position(deltaGlobalX, deltaGlobalY, deltaTheta)); //Accumulate the change in global x and y
    }

    @NonNull
    public String toString() {
        StringBuilder positionInches = new StringBuilder();
        for(int i = 0; i < currentPosition.toArray().length; i++) {
            switch(i){
                case 0:
                    positionInches.append("X: ").append(currentPosition.toArray()[i] / TICKS_PER_INCH);
                case 1:
                    positionInches.append("\nY: ").append(currentPosition.toArray()[i] / TICKS_PER_INCH);
                case 2:
                    positionInches.append("\nT: ").append(currentPosition.toArray()[i] / TICKS_PER_INCH);
                default:
                    throw new IllegalStateException("Odometry has more than 3 dimensions");
            }
        }
        return
                positionInches + "\n" +
                "leftOffset: " + leftOffset + "\n" +
                "rightOffset: " + rightOffset + "\n" +
                "centerOffset: " + centerOffset + "\n" +
                "leftCurrent: " + (leftOdom.getCurrentPosition() - leftOffset) + "\n" +
                "rightCurrent: " + (rightOdom.getCurrentPosition() - rightOffset) + "\n" +
                "centerCurrent: " + (centerOdom.getCurrentPosition() - centerOffset);
    }
}