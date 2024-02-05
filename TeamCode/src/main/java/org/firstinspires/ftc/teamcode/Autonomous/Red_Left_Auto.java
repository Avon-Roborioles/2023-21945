package org.firstinspires.ftc.teamcode.Autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "Red Left Auto", group = "auto")
public class Red_Left_Auto extends AutoBase{
    public static Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));

    public void runOpMode() throws  InterruptedException{
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop

        //TODO
        TrajectorySequence Left_Spike_Score = bot.trajectorySequenceBuilder(startPoseRL)
                .build();
        //TODO
        TrajectorySequence Middle_Spike_Score = bot.trajectorySequenceBuilder(startPoseRL)
                .build();
        //TODO
        TrajectorySequence Right_Spike_Score = bot.trajectorySequenceBuilder(startPoseRL)
                .build();

        //TODO
        TrajectorySequence CheckPoint1 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence CheckPoint2 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();
        //TODO
        TrajectorySequence Middle_Preload = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();
        //TODO
        TrajectorySequence Right_Preload = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO -scores on the left side of the board
        TrajectorySequence Board_Score = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence Pixel_Stack = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence Park = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();
    }
}
