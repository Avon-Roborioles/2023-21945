package org.firstinspires.ftc.teamcode.Autonomous.OLD.Park;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Disabled
//@Autonomous(name="BL Park", group="Park Programs")
public class BL_Park extends AutoBase {
    String propPosition = "";

    public void runOpMode() throws InterruptedException {
        init_classes();
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);
        //create trajectories for our bot to use
        TrajectorySequence BASIC = bot.trajectorySequenceBuilder(new Pose2d())
                .waitSeconds(.5)
                .turn(Math.toRadians(90)) //turns 89 degrees right
                .waitSeconds(.5)
                //.back(30)
                .forward(30)
                .build();

        waitForStart();

        //runs trajectories we create
        bot.followTrajectorySequence(BASIC);
    }
}
