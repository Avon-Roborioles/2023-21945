package org.firstinspires.ftc.teamcode.Autonomous.Park;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="RR Park", group="Park Programs")
public class RR_Park extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        init_classes();
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence BASIC = bot.trajectorySequenceBuilder(new Pose2d())
                .waitSeconds(0.5)
                .turn(Math.toRadians(-88)) //turns right 88 degrees
                .waitSeconds(0.5)
                .forward(30)
                .build();

        waitForStart();

        //runs trajectories we create
        bot.followTrajectorySequence(BASIC);

    }
}
