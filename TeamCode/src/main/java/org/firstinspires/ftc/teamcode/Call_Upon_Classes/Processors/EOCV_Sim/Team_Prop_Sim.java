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
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.Locale;

public class Team_Prop_Sim extends OpenCvPipeline {

    private final Scalar lowerL = new Scalar(0, 50, 50); // 150, 100, 100
    private final Scalar upperL = new Scalar(10, 255, 255); // 180, 255, 255

    private final Scalar lowerU = new Scalar(160, 50, 50); // 150, 100, 100
    private final Scalar upperU = new Scalar(180, 255, 255); // 180, 255, 255


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
    private final Paint linePaint;

    private int telemetryCount = 0;


    Telemetry telemetry;

    public Team_Prop_Sim(Telemetry telemetry){
        this.telemetry = telemetry;

        // setting up the paint for the lines that comprise the box
        linePaint = new Paint();
        linePaint.setColor(Color.MAGENTA); // you may want to change this
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(10); // or this
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public void init(Mat frame) {
        contours = new ArrayList<>();
    }

    @Override
    public Mat processFrame(Mat frame) {
        //convert default RGB frame into HSV frame
        this.frame = frame;
        Mat hsvFrame = new Mat();
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);

        Scalar lowerL = new Scalar(0, 50, 50); // 150, 100, 100
        Scalar upperL = new Scalar(10, 255, 255); // 180, 255, 255

        Scalar lowerU = new Scalar(160, 50, 50); // 150, 100, 100
        Scalar upperU = new Scalar(180, 255, 255); // 180, 255, 255

        //create masks
        Mat mask1 = new Mat();
        Mat mask2 = new Mat();

        //filter frame by two color ranges
        Core.inRange(frame,lowerL,upperL,mask1);
        Core.inRange(frame,lowerU,upperU,mask2);

        //combine the masks to capture both red ranges
        Mat combinedMask = new Mat();
        Core.bitwise_or(mask1,mask2,combinedMask);

        //apply the mask to the final output
        Mat filteredFrame = new Mat();
        Core.bitwise_and(frame,frame, filteredFrame,combinedMask);

        //clear contour list
        contours.clear();
        contourCount = 0;

        //convert to gray scale
        Imgproc.cvtColor(filteredFrame,filteredFrame,Imgproc.COLOR_BGR2GRAY);

        //find all contours
        Imgproc.findContours(filteredFrame, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);


        // this sets up our largest contour area to be 0
        // and our currently found largest contour to be null
        largestContour = null;

        //set minArea
        minArea = 10;

        //count number of contours
        for (int i = 0; i < contours.size();i++) { //MatOfPoint contour : contours
            double area = Imgproc.contourArea(contours.get(i));
            if (area >= minArea) {
                contourCount++;
            }
        }

        //find largest Contour by area
        for (int i = 0; i < contours.size();i++) { //MatOfPoint contour : contours
            double area = Imgproc.contourArea(contours.get(i));

            if (area > largestContourArea && area > minArea) {
                largestContour = contours.get(i);
                largestContourArea = area;
                contourIndex = i;
            }
        }


        finalFrame = filteredFrame;
        return filteredFrame;
    }

    @Override
    public void onViewportTapped() {
        //do nothing
        //Imgproc.drawContours(finalFrame,contours,contourIndex,new Scalar(255,0,0),1);
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        if (largestContour != null) {
            Rect rect = Imgproc.boundingRect(largestContour);

            float[] points = {rect.x * scaleBmpPxToCanvasPx, rect.y * scaleBmpPxToCanvasPx, (rect.x + rect.width) * scaleBmpPxToCanvasPx, (rect.y + rect.height) * scaleBmpPxToCanvasPx};

            canvas.drawLine(points[0], points[1], points[0], points[3], linePaint);
            canvas.drawLine(points[0], points[1], points[2], points[1], linePaint);

            canvas.drawLine(points[0], points[3], points[2], points[3], linePaint);
            canvas.drawLine(points[2], points[1], points[2], points[3], linePaint);


        }
    }


}
