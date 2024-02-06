package org.firstinspires.ftc.teamcode.Autonomous.OLD.Park_Score;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.TimeUnit;

@Autonomous(name="BR Score", group="Park + Score")
public class BR_Score extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {

        //important variables for auto - set to random values
        String propPosition = "MIDDLE";
        int aprilTagID = 2;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, false); //sets camera to start looking for prop

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //Done testing
                .addTemporalMarker(0, () -> {
                    //intake.openClaw(false);
                    //intake.openClawV2(false,true);
                    intake.closeClaws(true);
                    intake.closePixelHolder(true);
                })
                .strafeRight(5)
                .waitSeconds(.5)
                .forward(20)
                .waitSeconds(.5)
                .turn(Math.toRadians(88))


                .waitSeconds(.5) //------------
                .forward(10)
                .waitSeconds(.5) //-----------
                .back(10.5)
                .waitSeconds(5)

                .addTemporalMarker(7, () -> {
                   // intake.openClaw(false);
                    intake.openClawV2(true, true);
                    intake.wrist_down();
                })
                .addTemporalMarker(8, () -> {
                    //intake.openClaw(true);
                    intake.closeClaws(true);
                })
                .addTemporalMarker(9, () ->{
                    intake.wrist_up();
                })
                .turn(Math.toRadians(5))
                .strafeLeft(23) //25

                //-----------------------
//                .waitSeconds(1)
//                .back(33) //35
                .build();

        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                   // intake.openClaw(false);
                    //intake.closePixelHolder(true);
                    intake.closeClaws(true);

                })
                .forward(24) //16
                .waitSeconds(.5)
                .back(9.5)
                .waitSeconds(5)
                .addTemporalMarker(3, () -> {
                   // intake.openClaw(false);
                    intake.openClawV2(true,true);
                    intake.wrist_down();
                })
                .addTemporalMarker(4, () -> {
                    //intake.openClaw(true);
                    intake.openClawV2(false,true);
                })
                .addTemporalMarker(5, () ->{
                    intake.wrist_up();
                })
                .back(15)
//                .waitSeconds(1)
//                //----------------------
//                .strafeRight(35)
                .build();
//
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                   // intake.openClaw(false);
                    intake.closeClaws(true);
                    //intake.closePixelHolder(true);

                })
                .forward(20)
                .waitSeconds(.5)
                .turn(Math.toRadians(-88))
                .waitSeconds(.5) //------------
                .forward(10)
                .waitSeconds(.5) //-----------
                .back(10.5)
                .waitSeconds(4)
                .addTemporalMarker(6, () -> {
                    //intake.openClaw(false);
                    intake.openClawV2(true,true);
                    intake.wrist_down();
                })
                .addTemporalMarker(7, () -> {
                    //intake.openClaw(true);
                    intake.closeClaws(true);
                })
                .addTemporalMarker(8, () ->{
                    intake.wrist_up();
                })
                .forward(3)
                .waitSeconds(.5)
                .strafeRight(20) //25
//                .waitSeconds(1)
//                //---------------------
//                .forward(30) //35
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
//        TrajectorySequence park = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();

        //auto code here
        waitForStart();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,true);

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
        //bot.followTrajectorySequence(park); //TODO Add back when ready




    }
}