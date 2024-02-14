package org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.atomic.AtomicReference;

@Autonomous(name="RR Score Plus", group="Park + Score")
public class RR_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
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
                .lineToLinearHeading(new Pose2d(PoseStorage.LeftBoardPoseR.getX()-20,PoseStorage.LeftBoardPoseR.getY(),PoseStorage.LeftBoardPoseR.getHeading()))
                .addDisplacementMarker(()->{
                    state.set("Score Yellow Pixel on Left Board Region");
                    arm.up();
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
//                .waitSeconds(.7)
//                .back(5)
//                .addDisplacementMarker(()->{
//                    intake.openClawV2(true,true);
//                })
//                .waitSeconds(.7)
//                .forward(5)
//                .addDisplacementMarker(()->{
//                    state.set("Park");
//                    arm.down();
//                    intake.closeClaws(true);
//                })
//                .waitSeconds(.7)
//                .lineToLinearHeading(new Pose2d(PoseStorage.ParkSpotRR.getX()-20,PoseStorage.ParkSpotRR.getY(),PoseStorage.ParkSpotRR.getHeading()))
//                .waitSeconds(.1)
//                .lineToLinearHeading(PoseStorage.ParkSpotRR)
                .build();

        //TODO
        TrajectorySequence MiddleBoardScore = bot.trajectorySequenceBuilder(MiddleSpikeScore.end())
                .lineToLinearHeading(new Pose2d(PoseStorage.MiddleBoardPoseR.getX()-20,PoseStorage.MiddleBoardPoseR.getY(),PoseStorage.MiddleBoardPoseR.getHeading()))
                .addDisplacementMarker(()->{
                    state.set("Score Yellow Pixel on Middle Board Region");
                    arm.up();
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .build();

        //TODO
        TrajectorySequence RightBoardScore = bot.trajectorySequenceBuilder(RightSpikeScore.end())
                .lineToLinearHeading(new Pose2d(PoseStorage.RightBoardPoseR.getX()-20,PoseStorage.RightBoardPoseR.getY(),PoseStorage.RightBoardPoseR.getHeading()))
                .addDisplacementMarker(()->{
                    state.set("Score Yellow Pixel on Board");
                    arm.up();
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .build();

        //auto code here
        waitForStart();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,false);

        //scores the purple preload pixel based on vision reading
        switch(propPosition){
            case "LEFT":
                bot.followTrajectorySequenceAsync(LeftSpikeScore);
                bot.followTrajectorySequenceAsync(LeftBoardScore);
                break;
            case "MIDDLE":
                bot.followTrajectorySequenceAsync(MiddleSpikeScore);
                break;
            case "RIGHT":
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

        while(opModeIsActive()){
            bot.update(); //handles RR logic
            arm.update(); //handles Arm PID control
            telemetry.addData("Detected Prop Position: ", propPosition);
            telemetry.addData("Corresponding April Tag:",aprilTagID);
            telemetry.addData("Current Objective: ",state);
            telemetry.update();
        }
    }
}
