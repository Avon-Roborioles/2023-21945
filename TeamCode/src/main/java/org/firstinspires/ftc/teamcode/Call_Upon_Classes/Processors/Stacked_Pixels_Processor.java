package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

/*
detects stacked pixels for alignment and auto decision making
    - If last stacked pixel is gone, or unavailable, go to next nearest one
    - Also can detect pixels on the floor
 */
public class Stacked_Pixels_Processor extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input)
    {
        return input;
    }

}
