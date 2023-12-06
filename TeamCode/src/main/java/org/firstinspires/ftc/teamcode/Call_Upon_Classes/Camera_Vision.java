package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.Auto_Marker_Processor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
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
    private Auto_Marker_Processor colourMassDetectionProcessor;
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    private AprilTagProcessor aprilTag;


    public void init_cameras(HardwareMap hardwareMap,String name1, String name2){ //setups up cameras
         webcam1 = hardwareMap.get(WebcamName.class, name1);
         webcam2 = hardwareMap.get(WebcamName.class, name2);
    }

    public void init_camera(HardwareMap hardwareMap, String name1){
        webcam1 = hardwareMap.get(WebcamName.class, name1);
    }

    public void init_spike_detection(HardwareMap hardwareMap){
        // the current range set by lower and upper is the full range
        // HSV takes the form: (HUE, SATURATION, VALUE)
        // which means to select our colour, only need to change HUE
        // the domains are: ([0, 180], [0, 255], [0, 255])
        // this is tuned to detect red, so you will need to experiment to fine tune it for your robot
        // and experiment to fine tune it for blue
        Scalar lower = new Scalar(90, 100, 100); // the lower hsv threshold for your detection
        Scalar upper = new Scalar(180, 255, 255); // the upper hsv threshold for your detection
        double minArea = 100; // the minimum area for the detection to consider for your prop

        colourMassDetectionProcessor = new Auto_Marker_Processor(
                lower,
                upper,
                () -> minArea, // these are lambda methods, in case we want to change them while the match is running, for us to tune them or something
                () -> 213, // the left dividing line, in this case the left third of the frame
                () -> 426 // the left dividing line, in this case the right third of the frame
        );
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1")) // the camera on your robot is named "Webcam 1" by default
                .addProcessor(colourMassDetectionProcessor)
                .build();

        // you may also want to take a look at some of the examples for instructions on
        // how to have a switchable camera (switch back and forth between two cameras)
        // or how to manually edit the exposure and gain, to account for different lighting conditions
        // these may be extra features for you to work on to ensure that your robot performs
        // consistently, even in different environments
    }

    public String getPropPosition(){ //runs Auto_Marker Pipeline and returns position as a string
        String position = "";
        if (visionPortal.getCameraState() == VisionPortal.CameraState.STREAMING) {
            visionPortal.stopLiveView();
            visionPortal.stopStreaming();
        }

        // gets the recorded prop position
        Auto_Marker_Processor.PropPositions recordedPropPosition = colourMassDetectionProcessor.getRecordedPropPosition();

        // now we can use recordedPropPosition to determine where the prop is! if we never saw a prop, your recorded position will be UNFOUND.
        // if it is UNFOUND, you can manually set it to any of the other positions to guess
        if (recordedPropPosition == Auto_Marker_Processor.PropPositions.UNFOUND) {
            recordedPropPosition = Auto_Marker_Processor.PropPositions.MIDDLE;
        }

        // now we can use recordedPropPosition in our auto code to modify where we place the purple and yellow pixels
        switch (recordedPropPosition) {
            case LEFT:
                position = "LEFT";
                break;
            case MIDDLE:
                position = "MIDDLE";
                break;
            case RIGHT:
                position = "RIGHT";
                break;
        }

        //return position;
        return position;
    }

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

    private void initAprilTag() {

        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                // The following default settings are available to un-comment and edit as needed.
                //.setDrawAxes(false)
                //.setDrawCubeProjection(false)
                //.setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                //.setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                // ... these parameters are fx, fy, cx, cy.

                .build();

        // Adjust Image Decimation to trade-off detection-range for detection-rate.
        // eg: Some typical detection data using a Logitech C920 WebCam
        // Decimation = 1 ..  Detect 2" Tag from 10 feet away at 10 Frames per second
        // Decimation = 2 ..  Detect 2" Tag from 6  feet away at 22 Frames per second
        // Decimation = 3 ..  Detect 2" Tag from 4  feet away at 30 Frames Per Second (default)
        // Decimation = 3 ..  Detect 5" Tag from 10 feet away at 30 Frames Per Second (default)
        // Note: Decimation can be changed on-the-fly to adapt during a match.
        //aprilTag.setDecimation(3);

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(webcam1);
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Disable or re-enable the aprilTag processor at any time.
        //visionPortal.setProcessorEnabled(aprilTag, true);

    }   // end method initAprilTag()

    /*
    * returns target AprilTag pose -
    * Simple algorithm to align robot with tag:
    *   - reduce bearing to zero (rotate),
    *   - reduce yaw to zero (strafe sideways),
    *   - reduce range to zero (drive forward)
     */ //TODO finish code to get pose array
    public double[] get_Apriltag_pose(int tag){
        double[] pose = new double[9];
//        AprilTagProcessor aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
//        visionPortal = VisionPortal.easyCreateWithDefaults(webcam1, aprilTagProcessor);
//
//        List<AprilTagDetection> detections = aprilTagProcessor.getDetections();
//        for(AprilTagDetection detection : detections){
//            if(tag == detection.id){
//                AprilTagPoseFtc tagPose = detection.ftcPose;
//                pose[0] = tagPose.x;
//                pose[1] = tagPose.y;
//                pose[2] = tagPose.z;
//                pose[3] = tagPose.range;
//                pose[4] = tagPose.bearing;
//                pose[5] = tagPose.elevation;
//            }
//        }
        initAprilTag();

        List<AprilTagDetection> detections = aprilTag.getDetections();
        for(AprilTagDetection detection : detections){
            if(tag == detection.id){
                AprilTagPoseFtc tagPose = detection.ftcPose;
                pose[0] = tagPose.x;
                pose[1] = tagPose.y;
                pose[2] = tagPose.z;
                pose[3] = tagPose.range;
                pose[4] = tagPose.bearing;
                pose[5] = tagPose.elevation;
            }
        }

        return pose;
    }

    public void close(){ //turns off camera
        visionPortal.stopStreaming();
        visionPortal.close();
    }

}
