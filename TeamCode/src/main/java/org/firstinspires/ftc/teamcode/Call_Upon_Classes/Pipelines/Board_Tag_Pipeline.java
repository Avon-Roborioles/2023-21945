package org.firstinspires.ftc.teamcode.Call_Upon_Classes.Pipelines;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

//detects and tracks Apriltags on the board for alignment
public class Board_Tag_Pipeline extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input)
    {
        return input;
    }

}
