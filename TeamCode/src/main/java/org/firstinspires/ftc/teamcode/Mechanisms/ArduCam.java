package org.firstinspires.ftc.teamcode.Mechanisms;
import org.firstinspires.ftc.teamcode.Autos.AutoMethods;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import java.util.ArrayList;
import java.util.List;
public class ArduCam extends OpenCvPipeline {
    private double score;
    private double height = 0, width = 0;
    private Point screenPosition = new Point(0,0);
    private AutoMethods.AllianceColor allianceColor;
    private final Mat
                  rawImage,       // Raw image output from the camera
                  workingMat,     // The image currently being worked on and being modified
                  selectionMask,
                  hierarchy;

    /**
     * @param a set by autos, basically to swap between the colors
     */
    public void setCameraColor(AutoMethods.AllianceColor a){
        allianceColor = a;
    }

    /**
     * <p>Sets up all the variables to keep code clean</p> skibidi
     */
    public ArduCam() {
        rawImage = new Mat();
        workingMat = new Mat();
        selectionMask = new Mat();
        hierarchy = new Mat();
    }

    /**
     * @param input camera input
     * @return how much red/blue? (numerically)
     */
    private double calculateScore(Mat input) {
        // Validates input, returning the maximum value if invalid
        if(!(input instanceof MatOfPoint))
            return Double.MAX_VALUE;
        // Otherwise returns the calculated area of the contour
        return -Imgproc.contourArea(input);
    }

    /**
     * @param input current camera frame
     * @return return contour around largest object of chosen color
     */
    @Override
    public Mat processFrame(Mat input) {
        // Copies the original input to other materials to be worked on so they aren't overriding each other
        input.copyTo(rawImage);
        input.copyTo(workingMat);
        input.copyTo(selectionMask);


        // Sets the best fitting rectangle to the one currently selected
        Rect bestRect = new Rect();

        // Numerical value for the "best fit" rectangle
        // MAX_VALUE to find the lesser difference
        double lowestScore = Double.MAX_VALUE;

        //converts the image from rgb to hsv
        Imgproc.cvtColor(rawImage,workingMat,Imgproc.COLOR_RGB2HSV);

        /* controls the color range the camera is looking for in the hsv color space
           the hue value is scaled by .5, the saturation and value are scaled by 2.55
           only difference between red and blue finder, this just tells the camera to check what color
           to look for as indicated by the parameters within the constructor method */
        if (allianceColor == AutoMethods.AllianceColor.red)
            Core.inRange(workingMat,new Scalar(0,60,60),new Scalar(15,255,255),workingMat);
        else if(allianceColor == AutoMethods.AllianceColor.blue)
            Core.inRange(workingMat,new Scalar(90,60,60),new Scalar(120,255,255),workingMat);

        // Creates a list for all contoured objects the camera will find
        List<MatOfPoint> contoursList = new ArrayList<>();

        Imgproc.findContours(workingMat, contoursList, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        Imgproc.drawContours(selectionMask, contoursList,-1, new Scalar(40,40,40),2);

        // Scores all the contours and selects the best of them
        for(MatOfPoint contour : contoursList){
            // Calculate the "score" of the selected contour
            double score = calculateScore(contour);

            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(contour);

            // Draw the current found rectangle on the selections mask
            //     Drawn in blue
            Imgproc.rectangle(selectionMask, rect.tl(), rect.br(), new Scalar(0,0,255),2);

            // If the result is better then the previously tracked one,
            // and the top left coordinates are within the cropped area
            // set this rect as the new best
            if(score < lowestScore){
                lowestScore = score;
                bestRect = rect;
            }
        }

        // Draw the "best fit" rectangle on the selections mask and skystone only mask
        //     Drawn in red
        Imgproc.rectangle(selectionMask, bestRect.tl(), bestRect.br(), new Scalar(0,255,0),10);

        // Sets the position of the selected rectangle (relative to the screen resolution)
        screenPosition = new Point(bestRect.x, bestRect.y);

        score = bestRect.height * bestRect.width;
        height = bestRect.height;
        width = bestRect.width;

        return selectionMask;
    }

    // Getters/setters
    public double getScore() {
        return score;
    }

    public double getHeight() {return height;}
    public Point getScreenPosition() {return screenPosition;}
    public double getWidth() {return width;}
}