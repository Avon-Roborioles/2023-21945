package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.EOCV_Sim;

import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Typeface;
//import android.text.TextPaint;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.function.DoubleSupplier;


public class Stacked_Pixel_Sim extends OpenCvPipeline {
    private final double minArea = 100;
    private final Scalar lower = new Scalar(0,0,200);
    private final Scalar upper = new Scalar(180,30,255);
//    private final TextPaint textPaint = new TextPaint();
//    private final Paint linePaint = new Paint();
    private ArrayList<MatOfPoint> contours;
    private final Mat hierarchy = new Mat();
    private double largestContourX;
    private double largestContourY;
    private double largestContourWidth;
    private double largestContourArea;
    private MatOfPoint largestContour;


    @Override
    public void init(Mat input){
        // setting up the paint for the text in the center of the box
//        textPaint.setColor(Color.GREEN); // you may want to change this
//        textPaint.setTextAlign(Paint.Align.CENTER);
//        textPaint.setAntiAlias(true);
//        textPaint.setTextSize(40); // or this
//        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
//
//        // setting up the paint for the lines that comprise the box
//        linePaint.setColor(Color.GREEN); // you may want to change this
//        linePaint.setAntiAlias(true);
//        linePaint.setStrokeWidth(10); // or this
//        linePaint.setStrokeCap(Paint.Cap.ROUND);
//        linePaint.setStrokeJoin(Paint.Join.ROUND);
    }


    /**
     * @return the x position of the currently found largest contour in the range [0, camera width], or -1 if no largest contour has been determined
     */
    public double getLargestContourX() {
        return largestContourX;
    }

    /**
     * @return the y position of the currently found largest contour in the range [0, camera height], or -1 if no largest contour has been determined
     */
    public double getLargestContourY() {
        return largestContourY;
    }

    /**
     * @return the area of the currently found largest contour, or -1 if no largest contour has been determined
     */
    public double getLargestContourArea() {
        return largestContourArea;
    }

    /**
     * @return the width of the largest contour (stacked pixels)
     */
    public double getLargestContourWidth(){return largestContourWidth;}


    @Override
    public Mat processFrame(Mat frame) {
        //camera vision magic
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);

        Core.inRange(frame, lower, upper, frame);
        contours.clear();
        Imgproc.findContours(frame, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        largestContourArea = -1;
        largestContour = null;

        double minArea = this.minArea;

        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);

            // get contour and calculate width
            if (area > largestContourArea && area > minArea) {
                largestContour = contour;
                largestContourArea = area;

                Rect boundingRect = Imgproc.boundingRect(contour); //creates box around contour
                largestContourWidth = boundingRect.width; //gets width of box

            }
        }
            largestContourX = largestContourY = -1;


            if (largestContour != null) {
                Moments moment = Imgproc.moments(largestContour);
                largestContourX = (moment.m10 / moment.m00);
                largestContourY = (moment.m01 / moment.m00);
            }





        return frame;
    }
}
