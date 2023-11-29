package org.firstinspires.ftc.teamcode.Autonomous.Park;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.arcrobotics.ftclib.util.Timing.Timer;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Auto_Marker;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.Auto_Marker_Processor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.TimeUnit;

@Autonomous(name="BL Park", group="Park Programs")
public class BL_Park extends AutoBase {
    String propPosition = "";

    public void runOpMode() throws InterruptedException {
        init_classes();
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);
        //create trajectories for our bot to use
        TrajectorySequence BASIC = bot.trajectorySequenceBuilder(new Pose2d())
                .waitSeconds(.5)
                .strafeLeft(20)
                .build();

        waitForStart();

        //runs trajectories we create
        bot.followTrajectorySequence(BASIC);
    }
}
