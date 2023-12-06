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
    public void runOpMode() throws InterruptedException {
        String propPosition = "MIDDLE";
        int aprilTagID = 5;
        init_classes();
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence PreloadScore = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        TrajectorySequence getStackedPixel = bot.trajectorySequenceBuilder(new Pose2d())//TODO
                .build();

        TrajectorySequence scorePixel = bot.trajectorySequenceBuilder(new Pose2d())
                .build();

        TrajectorySequence park = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .build();

        //auto code here
        waitForStart();

        //variables stored after 3 seconds of camera processing
        Timing.Timer clock = new Timing.Timer(3, TimeUnit.SECONDS);
        while(clock.isTimerOn()) {
            propPosition = vision.getPropPosition();
            aprilTagID = vision.get_Apriltag_id(propPosition, "RED");
        }

        //scores the purple preload pixel based on vison reading
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
        bot.followTrajectorySequence(PreloadScore);

        //park robot
        bot.followTrajectorySequence(park);




    }
}
