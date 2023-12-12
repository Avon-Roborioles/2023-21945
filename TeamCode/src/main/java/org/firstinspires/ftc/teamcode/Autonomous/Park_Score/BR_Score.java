package org.firstinspires.ftc.teamcode.Autonomous.Park_Score;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.TimeUnit;

@Autonomous(name="RR Score", group="Park + Score")
public class BR_Score extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {

        //important variables for auto - set to random values
        String propPosition = "MIDDLE";
        int aprilTagID = 2;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, false); //sets camera to start looking for prop

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence LeftPreloadScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence MiddlePreloadScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence RightPreloadScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence park = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

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
