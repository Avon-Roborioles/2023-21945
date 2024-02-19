package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.Pixel_Stack_Processor;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.Team_Prop_Processor;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.opencv.core.Scalar;

import java.util.List;

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
    private Team_Prop_Processor teamPropProcessor;
    private Pixel_Stack_Processor pixelStackProcessor;
    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera
    private AprilTagProcessor aprilTag;
    double strafeLength = 0;
    double minArea;
    Scalar lower;
    Scalar upper;

    //sets up both cameras (front for prop detection & back for apriltag alignment)
    public void init_cameras(HardwareMap hardwareMap,String name1, String name2){ //setups up cameras
         webcam1 = hardwareMap.get(WebcamName.class, name1);
         webcam2 = hardwareMap.get(WebcamName.class, name2);
    }

    //sets up front camera
    public void init_camera(HardwareMap hardwareMap, String name1){
        webcam1 = hardwareMap.get(WebcamName.class, name1);
    }

    //starts detecting prop position through front camera
    public void init_prop_detection(HardwareMap hardwareMap, boolean redAlliance){
        // the current range set by lower and upper is the full range
        // HSV takes the form: (HUE, SATURATION, VALUE)
        // which means to select our colour, only need to change HUE
        // the domains are: ([0, 180], [0, 255], [0, 255])
        // this is tuned to detect red, so you will need to experiment to fine tune it for your robot
        // and experiment to fine tune it for blue

        if(redAlliance){
            //TODO adjust red color scale to account for lower and upper ranges
            //color limit for red
            lower = new Scalar(150, 100, 100); // 150, 100, 100
            upper = new Scalar(180, 255, 255); // 180, 255, 255
        } else {
            //color limit for blue
            lower = new Scalar(100, 100, 100); //
            upper = new Scalar(140, 255, 255); //
        }

//        lower = new Scalar(90, 100, 100); // the lower hsv threshold for your detection - 90,100,100
//        upper = new Scalar(180, 255, 255); // the upper hsv threshold for your detection - 180,255,255
         minArea = 100; // the minimum area for the detection to consider for your prop

        teamPropProcessor = new Team_Prop_Processor(
                lower,
                upper,
                () -> minArea, // these are lambda methods, in case we want to change them while the match is running, for us to tune them or something
                () -> 213, // the left dividing line, in this case the left third of the frame
                () -> 426 // the left dividing line, in this case the right third of the frame
        );
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1")) // the camera on your robot is named "Webcam 1" by default
                .addProcessor(teamPropProcessor)
                .build();

        // you may also want to take a look at some of the examples for instructions on
        // how to have a switchable camera (switch back and forth between two cameras)
        // or how to manually edit the exposure and gain, to account for different lighting conditions
        // these may be extra features for you to work on to ensure that your robot performs
        // consistently, even in different environments
    }

    //starts finding apriltags through the back camera - don't use this method as it's already called in the one below
    public void initAprilTag() {

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
    }

    //TODO -  starts detecting stacked pixels on the field
    public void init_stack_detection(HardwareMap hardwareMap){
        lower = new Scalar(0,0,200); //test these values to detect white
        upper = new Scalar(180,30,255);
        minArea = 100;

        pixelStackProcessor = new Pixel_Stack_Processor(
                lower,
                upper,
                () -> minArea,
                () -> 213,
                () -> 426
        );

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .addProcessor(pixelStackProcessor)
                .build();
    }

    //TODO Add Method to start recording matches from front camera
    public void startFRONTRecording(){}

    //TODO Add Method to start recording matches from back camera
    public void startBACKRecording(){}

    //TODO Add Method to start recording matches from both cameras (Front/Back POV)
    public void startPOVRecording(){}

    //------------------------------------------------------------------------------------------------------------------

    //TODO Add Method to stop camera recording
    public void stopRecording(){}

    //returns the detected prop position ("LEFT", "MIDDLE", or "RIGHT")
    public String getPropPosition(){ //runs Auto_Marker Pipeline and returns position as a string
        String position = "";
        if (visionPortal.getCameraState() == VisionPortal.CameraState.STREAMING) {
            visionPortal.stopLiveView();
            visionPortal.stopStreaming();
        }

        // gets the recorded prop position
        Team_Prop_Processor.PropPositions recordedPropPosition = teamPropProcessor.getRecordedPropPosition();

        // now we can use recordedPropPosition to determine where the prop is! if we never saw a prop, your recorded position will be UNFOUND.
        // if it is UNFOUND, you can manually set it to any of the other positions to guess
        if (recordedPropPosition == Team_Prop_Processor.PropPositions.UNFOUND) {
            recordedPropPosition = Team_Prop_Processor.PropPositions.MIDDLE;
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

    //TODO - returns the strafing distance needed to align robot with stacked pixels
    public double getStrafeDistance(){
        strafeLength = pixelStackProcessor.getAlignmentX();

        return strafeLength;
    }

    //returns the desired apriltag ID based on propPosition and alliance color (1, 2, 3, etc)
    public int get_Apriltag_id(String propPosition, boolean blueAlliance) {
        int tag_id = 0;

        if(blueAlliance) {
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

        } else {
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

    //returns an array of pose values of desired tag - only use this method to get apriltag info
    public double[] get_Apriltag_pose(int tag){
         //ArrayList<Double> pose = new ArrayList<Double>();
        double[] pose = new double[6];
        initAprilTag();

        List<AprilTagDetection> detections = aprilTag.getDetections();
        for(AprilTagDetection detection : detections){
            if(tag == detection.id){
                AprilTagPoseFtc tagPose = detection.ftcPose;
                pose[0] = tagPose.x;
                pose[1] = tagPose.y;
                pose[2] = tagPose.z;
                pose[3] = tagPose.range;
                pose[4] = tagPose.pitch;
                pose[5] = tagPose.bearing;
            }
        }

        return pose;
    } //TEST

    //TODO returns true is robot can detect at least 2 tags
    public boolean boardAvailable(){
        return true;
    }

    //closes Vision Portal and Camera - use between diffrent camera usecases (Prop to Stack)
    public void close(){
        visionPortal.stopStreaming();
        visionPortal.close();
    }

}
