package org.firstinspires.ftc.teamcode.Autonomous.Park_Score;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.TimeUnit;

@Autonomous(name="RR Score", group="Park + Score")
public class RR_Score extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //Done testing
                .addTemporalMarker(0, () -> {
                    intake.openClaw(false);
                })
                .strafeRight(5)
                .waitSeconds(.5)
                .forward(20)
                .waitSeconds(.5)
                .turn(Math.toRadians(88))
                .waitSeconds(4)
                .addTemporalMarker(4, () -> {
                    intake.openClaw(false);
                    intake.wrist_down();
                })
                .addTemporalMarker(5, () -> {
                    intake.openClaw(true);
                })
                .addTemporalMarker(6, () ->{
                    intake.wrist_up();
                })
                .strafeLeft(25)
                .waitSeconds(1)
                .back(30)
                .build();

        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                    intake.openClaw(false);
                })
                .forward(16)
                .waitSeconds(3)
                .addTemporalMarker(2, () -> {
                    intake.openClaw(false);
                    intake.wrist_down();
                })
                .addTemporalMarker(3, () -> {
                    intake.openClaw(true);
                })
                .addTemporalMarker(4, () ->{
                    intake.wrist_up();
                })
                .back(15)
                .waitSeconds(1)
                .strafeRight(35)
                .build();
//
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                    intake.openClaw(false);
                })
                .forward(20)
                .waitSeconds(.5)
                .turn(Math.toRadians(-88))
                .waitSeconds(4)
                .addTemporalMarker(4, () -> {
                    intake.openClaw(false);
                    intake.wrist_down();
                })
                .addTemporalMarker(5, () -> {
                    intake.openClaw(true);
                })
                .addTemporalMarker(6, () ->{
                    intake.wrist_up();
                })
                .forward(3)
                .waitSeconds(.5)
                .strafeRight(25)
                .waitSeconds(1)
                .forward(30)
                .build();

//        TrajectorySequence LeftPreloadScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();
//
//        TrajectorySequence MiddlePreloadScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();
//
//        TrajectorySequence RightPreloadScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();
//
//        TrajectorySequence park = bot.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(90))) //TODO
//                .lineToLinearHeading(new Pose2d(0,10,Math.toRadians(90)))
//                .waitSeconds(.7)
//                .lineToLinearHeading(new Pose2d(30, 0, Math.toRadians(90)))
//                .build();

        //auto code here
        waitForStart();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,false);

        //scores the purple preload pixel based on vision reading
        switch(propPosition){
            case "LEFT":
                bot.followTrajectorySequence(LeftSpikeScore);
                break;
            case "MIDDLE":
                bot.followTrajectorySequence(MiddleSpikeScore);
                break;
            case "RIGHT":
                bot.followTrajectorySequence(RightSpikeScore);
                break;
        }

        //bot.followTrajectorySequence(RightSpikeScore);

        //score pixel
        //bot.followTrajectorySequence(LeftPreloadScore);
//        switch(aprilTagID){ //TODO Add back when ready
//            case 4:
//                bot.followTrajectorySequence(LeftPreloadScore);
//                break;
//            case 5:
//                bot.followTrajectorySequence(MiddlePreloadScore);
//                break;
//            case 6:
//                bot.followTrajectorySequence(RightPreloadScore);
//                break;
//        }

        //park robot
       // bot.followTrajectorySequence(park); //TODO Add back when ready




    }
}
