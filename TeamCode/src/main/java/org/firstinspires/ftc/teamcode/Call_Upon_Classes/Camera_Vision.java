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
         String position = "";
         Auto_Marker_Processor markerProcessor;
        Scalar lower = new Scalar(150, 100, 100); // the lower hsv threshold for your detection
        Scalar upper = new Scalar(180, 255, 255); // the upper hsv threshold for your detection
        double minArea = 100; // the minimum area for the detection to consider for your prop
        WebcamName webcam1 = getCameraName(1);
        WebcamName webcam2 = getCameraName(2);

        markerProcessor = new Auto_Marker_Processor(
                lower,
                upper,
                minArea, // these are lambda methods, in case we want to change them while the match is running, for us to tune them or something
                () -> 213, // the left dividing line, in this case the left third of the frame
                () -> 426 // the left dividing line, in this case the right third of the frame
        );
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1")) //name of camera you will use
                .addProcessor(markerProcessor)
                .build();

        //gets the recorded prop position
        //TODO figure out this issue
         return position;
    }


    //returns the correct Target AprilTag ID based on the team prop position
    public int get_Apriltag_id(String propPostion, String alliance) {
        int tag_id = 0;

        if(Objects.equals(alliance, "BLUE")) {
            switch (propPostion) {
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
            switch (propPostion) {
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
    *   - reduce baring to zero (rotate),
    *   - reduce yaw to zero (strafe sideways),
    *   - reduce range to zero (drive forward)
     */
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
