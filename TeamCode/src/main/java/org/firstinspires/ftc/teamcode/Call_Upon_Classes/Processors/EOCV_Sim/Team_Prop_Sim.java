package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.EOCV_Sim;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class Team_Prop_Sim extends OpenCvPipeline {
    private final Scalar lower = new Scalar(0, 50, 50); // 150, 100, 100
    private final Scalar upper = new Scalar(10, 255, 255); // 180, 255, 255

    private final Scalar red = new Scalar(0, 255, 255);

    private ArrayList<MatOfPoint> contours;
    private final Mat hierarchy = new Mat();
    private double largestContourX;
    private double largestContourY;
    private double largestContourWidth;
    private double largestContourArea;
    private MatOfPoint largestContour;


    @Override
    public void init(Mat frame) {
        contours = new ArrayList<>();
    }

    @Override
    public Mat processFrame(Mat frame) {
        //convert default RGB frame into HSV frame
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);

        //filter frame by color
        Core.inRange(frame, lower, upper, frame);

        return frame;
    }

    @Override
    public void onViewportTapped() {
        //do nothing
    }
}
