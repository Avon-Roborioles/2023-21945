package org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.atomic.AtomicReference;

@Autonomous(name="RR Score Plus", group="Park + Score")
public class RR_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{

    //this enum defines our state
    enum State {
        LEFT_SPIKE_SCORE,
        MIDDLE_SPIKE_SCORE,
        RIGHT_SPIKE_SCORE,
        LEFT_BOARD_SCORE,
        MIDDLE_BOARD_SCORE,
        RIGHT_BOARD_SCORE,
        PARK,
        WAIT,
        IDLE
    }

    //define current state the robot is on
    //default to IDLE
    State currentState = State.IDLE;

    public void runOpMode() throws InterruptedException {

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;
        AtomicReference<String> state = new AtomicReference<>("Score Purple Pixel on Spike Mark");

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        bot.setPoseEstimate(PoseStorage.startPoseRR);

        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(PoseStorage.startPoseRR) //Done testing
                .addTemporalMarker(0, () -> {
                    //intake.openClaw(false);
                    //intake.closePixelHolder(true);
                    intake.closeClaws(true);
                    intake.wrist_up();
                })
                //get to spike
                .strafeRight(5)
                .waitSeconds(.1)
                .forward(18) //17
                .waitSeconds(.1)
                .turn(Math.toRadians(80))
                .addDisplacementMarker(()->{
                    intake.wrist_down();
                })

                //score spike
                .waitSeconds(.1) //------------
                .forward(8)
                .waitSeconds(.7) //-----------
                .back(5)
                .addDisplacementMarker(()->{
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7)

                //move out the way
                .turn(Math.toRadians(11))
                .waitSeconds(.1)
                .back(5)
                .addDisplacementMarker(()->{
                    intake.closeClaws(true);
                    intake.wrist_up();
                })
                //park
//                .waitSeconds(.1)
//                .strafeLeft(21)
//                .waitSeconds(.1)
//                .back(22)
                .build();

        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(PoseStorage.startPoseRR) //Done testing
                .addTemporalMarker(0, () -> {
                    // intake.openClaw(false);
                    intake.closeClaws(true);
                    //intake.closePixelHolder(true);

                })
                .forward(24) //16
                .waitSeconds(.5)
                .back(8.75)
                .waitSeconds(2)
                .addTemporalMarker(3, () -> {
                    //intake.openClaw(false);
                    intake.openClawV2(true,false);
                    intake.wrist_down();
                })
                .addTemporalMarker(5, () -> {
                    //intake.openClaw(true);
                    intake.closeClaws(true);
                })
                .addTemporalMarker(6, () ->{
                    intake.wrist_up();
                })
                .back(5)
                .waitSeconds(10)

                //park
//                .back(14)
//                .waitSeconds(1)
//                .strafeRight(33)
                .build();

        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(PoseStorage.startPoseRR) //Done testing
                .addTemporalMarker(0, () -> {
                    //intake.openClaw(false);
                    intake.closeClaws(true);
                    intake.wrist_up();
                })
                .forward(18)
                .waitSeconds(.1)
                .turn(Math.toRadians(80))
                .addDisplacementMarker(()->{
                    intake.wrist_down();
                })
                .waitSeconds(.7)
                .back(16)
                .addDisplacementMarker(()->{
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
                    intake.closeClaws(true);
                    intake.wrist_up();
                })
                .waitSeconds(.1)
                .turn(Math.toRadians(11))
                .back(5)
//                .waitSeconds(.1)
//                .strafeLeft(21)
//                .waitSeconds(.1)
//                .back(15)
                .build();

        //****************Board Score Trajectories include parking********************

        //TODO
        TrajectorySequence LeftBoardScore = bot.trajectorySequenceBuilder(LeftSpikeScore.end())
                .lineToLinearHeading(new Pose2d(PoseStorage.LeftBoardPoseR.getX(),PoseStorage.LeftBoardPoseR.getY(),PoseStorage.LeftBoardPoseR.getHeading()))
                .addDisplacementMarker(()->{
                    //state.set("Score Yellow Pixel on Left Board Region");
                    arm.up();
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
                    intake.openClawV2(true,true);
                })
                .waitSeconds(.7)
                .forward(5)
                .addDisplacementMarker(()->{
                    state.set("Park");
                    arm.down();
                    intake.closeClaws(true);
                })
                .waitSeconds(.7)
                .lineToLinearHeading(new Pose2d(PoseStorage.ParkSpotRR.getX()-20,PoseStorage.ParkSpotRR.getY(),PoseStorage.ParkSpotRR.getHeading()))
                .waitSeconds(.1)
                .lineToLinearHeading(PoseStorage.ParkSpotRR)
                .build();

        //TODO
        TrajectorySequence MiddleBoardScore = bot.trajectorySequenceBuilder(MiddleSpikeScore.end())
                .lineToLinearHeading(new Pose2d(PoseStorage.MiddleBoardPoseR.getX(),PoseStorage.MiddleBoardPoseR.getY(),PoseStorage.MiddleBoardPoseR.getHeading()))
                .addDisplacementMarker(()->{
                    //state.set("Score Yellow Pixel on Middle Board Region");
                    arm.up();
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
                    intake.openClawV2(true,true);
                })
                .waitSeconds(.7)
                .forward(5)
                .addDisplacementMarker(()->{
                    state.set("Park");
                    arm.down();
                    intake.closeClaws(true);
                })
                .waitSeconds(.7)
                .lineToLinearHeading(new Pose2d(PoseStorage.ParkSpotRR.getX()-20,PoseStorage.ParkSpotRR.getY(),PoseStorage.ParkSpotRR.getHeading()))
                .waitSeconds(.1)
                .lineToLinearHeading(PoseStorage.ParkSpotRR)
                .build();

        //TODO
        TrajectorySequence RightBoardScore = bot.trajectorySequenceBuilder(RightSpikeScore.end())
                .lineToLinearHeading(new Pose2d(PoseStorage.RightBoardPoseR.getX(),PoseStorage.RightBoardPoseR.getY(),PoseStorage.RightBoardPoseR.getHeading()))
                .addDisplacementMarker(()->{
                    //state.set("Score Yellow Pixel on Board");
                    arm.up();
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
                    intake.openClawV2(true,true);
                })
                .waitSeconds(.7)
                .forward(5)
                .addDisplacementMarker(()->{
                    state.set("Park");
                    arm.down();
                    intake.closeClaws(true);
                })
                .waitSeconds(.7)
                .lineToLinearHeading(new Pose2d(PoseStorage.ParkSpotRR.getX()-20,PoseStorage.ParkSpotRR.getY(),PoseStorage.ParkSpotRR.getHeading()))
                .waitSeconds(.1)
                .lineToLinearHeading(PoseStorage.ParkSpotRR)
                .build();

        //auto code here
        waitForStart();

        if(isStopRequested()) return;

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,false);

        //scores the purple preload pixel based on vision reading
        switch(propPosition){
            case "LEFT":
                currentState = State.LEFT_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(LeftSpikeScore);
                break;
            case "MIDDLE":
               currentState = State.MIDDLE_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(MiddleSpikeScore);
                break;
            case "RIGHT":
                currentState = State.RIGHT_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(RightSpikeScore);

                break;
        }

        //scores yellow pixel on preload pixel
//        switch(aprilTagID){
//            case 4:
//                bot.followTrajectorySequenceAsync(LeftBoardScore);
//                break;
//            case 6:
//                bot.followTrajectorySequenceAsync(MiddleBoardScore);
//                break;
//            default:
//                bot.followTrajectorySequenceAsync(RightBoardScore);
//                break;
//        }

        //init loop so we see if robot will start with correct propPosition
        while(opModeInInit()){
            telemetry.addData("Detected Prop Position: ", propPosition);
            telemetry.addData("Corresponding April Tag:",aprilTagID);        }

        while(opModeIsActive() && !isStopRequested()){
            //define the flow of the state machine through this switch statement
            switch (currentState){
                case LEFT_SPIKE_SCORE:
                    // Check if the drive class isn't busy
                    // `isBusy() == true` while it's following the trajectory
                    // Once `isBusy() == false`, the trajectory follower signals that it is finished
                    // We move on to the next state
                    // Make sure we use the async follow function
                    if(!bot.isBusy()){
                        currentState = State.LEFT_BOARD_SCORE;
                        bot.followTrajectorySequenceAsync(LeftBoardScore);
                    }
                    break;
                case MIDDLE_SPIKE_SCORE:
                    if(!bot.isBusy()){
                        currentState = State.MIDDLE_BOARD_SCORE;
                        bot.followTrajectorySequenceAsync(MiddleBoardScore);
                    }
                    break;
                case RIGHT_SPIKE_SCORE:
                    if(!bot.isBusy()){
                        currentState = State.RIGHT_BOARD_SCORE;
                        bot.followTrajectorySequenceAsync(RightBoardScore);
                    }
                    break;
                case LEFT_BOARD_SCORE:
                case MIDDLE_BOARD_SCORE:
                case RIGHT_BOARD_SCORE:
                    if(!bot.isBusy()){
                        currentState = State.IDLE;
                    }
                    break;
                case IDLE:
                    //Do nothing in IDLE except save position
                    //current state does not change once in IDLE
                    //This concludes the autonomous program
                    break;
            }

            //anything outside the switch statement will run independent of the currentState

            //we update drive continuously in the background regardless of state
            bot.update();

            //read pose
            Pose2d poseEstimate = bot.getPoseEstimate();

            //continuously write pose to 'PoseStorage'
            PoseStorage.currentPose = poseEstimate;

            arm.update(); //handles Arm PID control

            //telemetry data
            telemetry.addData("Detected Prop Position: ", propPosition);
            telemetry.addData("Corresponding April Tag:",aprilTagID);
            telemetry.addData("Current Objective: ",currentState);
            telemetry.addData("X: ",poseEstimate.getX());
            telemetry.addData("Y: ",poseEstimate.getY());
            telemetry.addData("Heading: ",poseEstimate.getHeading());
            telemetry.update();
        }
    }
}
