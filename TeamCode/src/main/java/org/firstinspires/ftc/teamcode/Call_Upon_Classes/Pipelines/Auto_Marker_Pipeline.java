package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Pipelines;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

//detects marker signal at the start of autonomous
public class Auto_Marker_Pipeline extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input)
    {
        return input;
    }

}
