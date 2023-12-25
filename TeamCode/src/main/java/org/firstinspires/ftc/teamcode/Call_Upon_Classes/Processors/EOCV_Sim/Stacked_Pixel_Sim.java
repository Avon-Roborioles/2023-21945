package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.EOCV_Sim;

import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Typeface;
//import android.text.TextPaint;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.Locale;
import java.util.function.DoubleSupplier;


public class Stacked_Pixel_Sim extends OpenCvPipeline {
    private final double minArea = 100;
    private final Scalar lower = new Scalar(0, 0, 200);
    private final Scalar upper = new Scalar(180, 50, 255);
    //    private final TextPaint textPaint = new TextPaint();
//    private final Paint linePaint = new Paint();
    private ArrayList<MatOfPoint> contours;
    private final Mat hierarchy = new Mat();
    private double largestContourX;
    private double largestContourY;
    private double largestContourWidth;
    private double largestContourArea;
    private MatOfPoint largestContour;
    Telemetry telemetry = new Telemetry() {}


    @Override
    public void init(Mat input) {
        contours = new ArrayList<>();
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
    public double getLargestContourWidth() {
        return largestContourWidth;
    }


    @Override
    public Mat processFrame(Mat frame) {
        //camera vision magic
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);

        Core.inRange(frame, lower, upper, frame);
        //contours.clear();
        Imgproc.findContours(frame, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
//



       largestContourArea = -1;
        largestContour = null;
//
//        double minArea = 100;
//
        for (MatOfPoint contour : contours) {
            double area = Imgproc.contourArea(contour);

            // get contour and calculate width
            if (area > largestContourArea && area > minArea) {
                largestContour = contour;
                largestContourArea = area;

                //Rect boundingRect = Imgproc.boundingRect(contour); //creates box around contour


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


    @Override
    public void onViewportTapped() {
        // Executed when the image display is clicked by the mouse or tapped
        // This method is executed from the UI thread, so be careful to not
        // perform any sort heavy processing here! Your app might hang otherwise
//
//        if (largestContour != null) {
//            Rect rect = Imgproc.boundingRect(largestContour);
//
//            float[] points = {rect.x * scaleBmpPxToCanvasPx, rect.y * scaleBmpPxToCanvasPx, (rect.x + rect.width) * scaleBmpPxToCanvasPx, (rect.y + rect.height) * scaleBmpPxToCanvasPx};
//
//            canvas.drawLine(points[0], points[1], points[0], points[3], linePaint);
//            canvas.drawLine(points[0], points[1], points[2], points[1], linePaint);
//
//            canvas.drawLine(points[0], points[3], points[2], points[3], linePaint);
//            canvas.drawLine(points[2], points[1], points[2], points[3], linePaint);
//
//            String text = String.format(Locale.ENGLISH, "%s", "Pixel Stack");
//
//            canvas.drawText(text, (float) largestContourX * scaleBmpPxToCanvasPx, (float) largestContourY * scaleBmpPxToCanvasPx, textPaint);
//        }


    }



}

