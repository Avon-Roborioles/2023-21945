package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.*;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.Auto_Marker_Processor;
import org.opencv.core.Scalar;

import java.util.List;
import java.util.Objects;

/*
Main Program used by TeleOp and Auto Programs for everything vision
    - Switches OpenCV pipelines/processors for object detection
    - Can even calculate distance in case of distance sensor failure
    - report which marker is selected for auto start
    - find nearest stacked cones to go to
    - Calculate & Return AprilTag Poses To adjust robot position with RoadRunner (Returns array of info)
    - possibly detect oncoming robots and use to reroute paths
 */

public class Camera_Vision {

    //for each camera method, the program creates its own version of a VisionPortal to use
    private VisionPortal visionPortal;
    private WebcamName webcam1 = null;
    private WebcamName webcam2 = null;

    public void init_cameras(HardwareMap hardwareMap,String name1, String name2){ //setups up cameras
         webcam1 = hardwareMap.get(WebcamName.class, name1);
         webcam2 = hardwareMap.get(WebcamName.class, name2);
    }

    private WebcamName getCameraName(int camera){
        if(camera == 1){
            return webcam1;
        } else {
            return webcam2;
        }
    } //TODO Remove if not used

    public String getPropPosition(HardwareMap hardwareMap){ //runs Auto_Marker Pipeline and returns position as a string
        Auto_Marker_Processor colourMassDetectionProcessor;

        // the current range set by lower and upper is the full range
        // HSV takes the form: (HUE, SATURATION, VALUE)
        // which means to select our colour, only need to change HUE
        // the domains are: ([0, 180], [0, 255], [0, 255])
        // this is tuned to detect red, so you will need to experiment to fine tune it for your robot
        // and experiment to fine tune it for blue
        Scalar lower = new Scalar(90, 100, 100); // the lower hsv threshold for your detection - 90, 100, 100
        Scalar upper = new Scalar(180, 255, 255); // the upper hsv threshold for your detection
        double minArea = 1000; // the minimum area for the detection to consider for your prop

        colourMassDetectionProcessor = new Auto_Marker_Processor (
                lower,
                upper,
                100.0,
                () -> 190, // the left dividing line, in this case the left third-ish of the frame
                () -> 450 // the left dividing line, in this case the right third-ish of the frame
        );
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1")) // the camera on your robot is named "Webcam 1" by default
                .addProcessor(colourMassDetectionProcessor)
                .build();


        String position = String.valueOf(colourMassDetectionProcessor.getRecordedPropPosition()); //returns the position
         return position;
    }


    //returns the correct Target AprilTag ID based on the team prop position
    public int get_Apriltag_id(String propPosition, String alliance) {
        int tag_id = 0;

        if(Objects.equals(alliance, "BLUE")) {
            switch (propPosition) {
                case "LEFT":
                    tag_id = 1;
                    break;
                case "MIDDLE": case "NONE": //if no positino is found, just guess middle
                    tag_id = 2;
                    break;
                case "RIGHT":
                    tag_id = 3;
                    break;
            }

        } else if (Objects.equals(alliance, "RED")){
            switch (propPosition) {
                case "LEFT":
                    tag_id = 4;
                    break;
                case "MIDDLE": case "NONE": //if no position is found, just guess the middle position
                    tag_id = 5;
                    break;
                case "RIGHT":
                    tag_id = 6;
                    break;
            }
        }
        return tag_id;
    }

    /*
    * returns target AprilTag pose -
    * Simple algorithm to align robot with tag:
    *   - reduce bearing to zero (rotate),
    *   - reduce yaw to zero (strafe sideways),
    *   - reduce range to zero (drive forward)
     */ //TODO Change pose[0] to index 1 to fix outofBounds error
    public double[] get_Apriltag_pose(int tag){
        double[] pose = {}; //structure - {range, bearing, yaw, roll, pitch, elevation, x, y, z}
        AprilTagProcessor aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcam1, aprilTagProcessor);

        List<AprilTagDetection> detections = aprilTagProcessor.getDetections();
        for(AprilTagDetection detection : detections){
            if(tag == detection.id){
                AprilTagPoseFtc tagPose = detection.ftcPose;

                pose[0] = tagPose.range;
                pose[1] = tagPose.bearing;
                pose[2] = tagPose.yaw;
                pose[3] = tagPose.roll;
                pose[4] = tagPose.pitch;
                pose[5] = tagPose.elevation;
                pose[6] = tagPose.x;
                pose[7] = tagPose.y;
                pose[8] = tagPose.z;
            }
        }
        return pose;
    }

    public void detect_stacked_pixels(){
        visionPortal = VisionPortal.easyCreateWithDefaults(webcam2);
    } //TODO

    public void close(){ //turns off camera
        visionPortal.stopStreaming();
        visionPortal.close();
    }

}
