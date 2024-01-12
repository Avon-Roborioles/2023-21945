package org.firstinspires.ftc.teamcode.Autonomous.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.TimeUnit;

//@Autonomous(name="RR Score", group="Park + Score")
@Disabled
public class RR_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{

    SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

    TrajectorySequence quickStrafe = null;
    double Xaxis = 0;
    double Yaxis = 0;
    double heading = Math.toRadians(0);
    public void QuickStrafe(double distance){
        quickStrafe = bot.trajectorySequenceBuilder(new Pose2d(Xaxis, Yaxis, heading))
                .lineToLinearHeading(new Pose2d(Xaxis, Yaxis + distance, heading))
                .build();
        Yaxis += distance;
    }

    public void runOpMode() throws InterruptedException {


        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //init robot functions
        //SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence PreloadScore1 = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();
        TrajectorySequence PreloadScore2 = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();
        TrajectorySequence PreloadScore3 = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

//        //safe spot for scoring region
//        TrajectorySequence SafeSpot1 = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();
//
//        //safe spot for retrieving region
//        TrajectorySequence SafeSpot2 = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();
//
//
//        TrajectorySequence get2StackedPixels = bot.trajectorySequenceBuilder(new Pose2d())//TODO
//                .build();
//
//        TrajectorySequence scorePixels = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();
//
//        TrajectorySequence park = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
//                .build();

        //auto code here
        waitForStart();

        //variables stored after 3 seconds of camera processing
        propPosition = vision.getPropPosition(); //get latest prop position
        aprilTagID = vision.get_Apriltag_id(propPosition, false);


        //scores purple pixel on spike based on vision reading
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

        //score yellow pixel on board based on vision reading
        switch (aprilTagID) {
            case 4:
                bot.followTrajectorySequence(PreloadScore1);
                break;
            case 5:
                bot.followTrajectorySequence(PreloadScore2);
                break;
            case 6:
                bot.followTrajectorySequence(PreloadScore3);
        }
//
//        //getting ready to detect stacked pixels
//        //vision.init_stack_detection(hardwareMap);
//
//        //go to scoring safe spot
//        bot.followTrajectorySequence(SafeSpot1);
//
//        //go to retrieving safe spot
//        bot.followTrajectorySequence(SafeSpot2);
//
//        //strafes to 1 of 2 available stacks
////        double strafeDistance = vision.getStrafeDistance();
////        QuickStrafe(strafeDistance);
//
//        bot.followTrajectorySequence(get2StackedPixels);
//
//        bot.followTrajectorySequence(SafeSpot2);
//
//        bot.followTrajectorySequence(SafeSpot1);
//
//        //TODO check for board availablility (able to find at least 2 tags?)
//
//        bot.followTrajectorySequence(scorePixels);
//
//        bot.followTrajectorySequence(SafeSpot1);
//
//        bot.followTrajectorySequence(SafeSpot2);
//
//        //strafes to 1 of 2 available stacks
////        double strafeDistance = vision.getStrafeDistance();
////        QuickStrafe(strafeDistance);
//
//        bot.followTrajectorySequence(get2StackedPixels);
//
//        bot.followTrajectorySequence(SafeSpot2);
//
//        bot.followTrajectorySequence(SafeSpot1);
//
//        //TODO check for board availablility (able to find at least 2 tags?)
//
//        bot.followTrajectorySequence(scorePixels);
//
//        bot.followTrajectorySequence(SafeSpot1);
//
//        //park robot
//        bot.followTrajectorySequence(park);




    }
}
