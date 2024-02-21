package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.EOCV_Sim;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;

public class OLD_Team_Prop_Sim extends OpenCvPipeline {
    private final Scalar lowerU = new Scalar(160, 50, 50); // 150, 100, 100
    private final Scalar upperU = new Scalar(180, 255, 255); // 180, 255, 255

    private final Scalar lowerL = new Scalar(0, 50, 50); // 150, 100, 100
    private final Scalar upperL = new Scalar(10, 255, 255); // 180, 255, 255

    private ArrayList<MatOfPoint> contours;
    private final Mat hierarchy = new Mat();
    private double largestContourX;
    private double largestContourY;
    private Mat frame;
    private Mat finalFrame;
    private int contourIndex = 0;
    private double largestContourWidth;
    private double largestContourArea = -1;
    private double minArea;
    private MatOfPoint largestContour;
    private int contourCount = 0;



    @Override
    public Mat processFrame(Mat frame) {
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);

        Core.inRange(frame,lowerL,upperL,frame);

        return frame;
    }
}
