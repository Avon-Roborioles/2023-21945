package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.EOCV_Sim;

import android.graphics.Canvas;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.opencv.core.Mat;

//TODO
public class BoardAprilTags implements VisionProcessor {
    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;


    @Override
    public void init(int width, int height, CameraCalibration calibration) {
        aprilTag = new AprilTagProcessor.Builder()
                .build();

    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        return null;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {

    }
}
