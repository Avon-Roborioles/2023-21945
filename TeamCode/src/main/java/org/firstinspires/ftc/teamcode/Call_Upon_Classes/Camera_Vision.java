package org.firstinspires.ftc.teamcode.Call_Upon_Classes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Pipelines.*; //imports all pipelines
import org.firstinspires.ftc.vision.VisionPortal;
import org.opencv.core.Scalar;
/*
Main Program used by teleOp and Auto Programs for everything vision
    - Switches OpenCV pipelines/processors for object detection
    - Can even calculate distance in case of distance sensor failure
    - report which marker is selected for auto start
    - find nearest stacked cones to go to
    - possibly detect oncoming robots and use to reroute paths
 */
public class Camera_Vision {
    private WebcamName webcam1, webcam2;
    private VisionPortal visionPortal;
    public void init_cameras(HardwareMap hardwareMap,String name1, String name2, String Processor){ //setups up cameras
        /*
         Turns on your Cameras and selects which proccessor you want to use
         Options are: Auto Marker, AprilTag, Stacked Cones, or None
         */
        webcam1 = hardwareMap.get(WebcamName.class, name1);
        webcam2 = hardwareMap.get(WebcamName.class, name2);

        switch (Processor){
            case "None":
                break;
        }
    }

    public String getPropPosition(){ //runs Auto_Marker Pipeline and returns position as a string
         String position = "";

         return position;
    }

    public int get_Apriltag_id(String propPostion){ //gets AprilTag id from position - kind of redundant but makes code easier to read
        int tag_id = 0;

        switch(propPostion){
            case "LEFT":
                tag_id = 1; //TODO change Left Tag to actual id
                break;
            case "MIDDLE":
                tag_id = 2; //TODO change Middle Tag to actual id
                break;
            case "RIGHT":
                tag_id = 3; //TODO change Right Tag to actual id
                break;
            case "NONE":
                tag_id = 0; //TODO Change default tag to Middle id
        }

        return tag_id;
    }

    public void detect_stacked_pixels(){

    }

    public void close(){ //turns off camera

    }

}
