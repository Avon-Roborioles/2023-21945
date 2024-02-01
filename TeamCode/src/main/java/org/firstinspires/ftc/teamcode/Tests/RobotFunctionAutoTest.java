package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="Robot Function Auto Test", group="Tests")
public class RobotFunctionAutoTest extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        init_classes();

        TrajectorySequence test = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0.1,() -> {
                    intake.wrist_down();
                })

                .addTemporalMarker(0.2,() -> {
                    intake.openClaws(false);
                })

                .addTemporalMarker(0.7,() -> {
                    intake.openClaws(true);
                })

                .addTemporalMarker(1.1,() -> {
                    intake.wrist_up();
                })

                .addTemporalMarker(1.7,() -> {
                    arm.up();
                })

                .addTemporalMarker(2.3,() -> {
                    intake.openClawV2(true,true);
                })

                .addTemporalMarker(2.9,() -> {
                    intake.openClawV2(true,false);
                })

                .addTemporalMarker(3.6,() -> {
                    intake.openClaws(true);
                })

                .addTemporalMarker(4.4,() -> {
                    arm.down();
                })
                .turn(Math.toRadians(-0.1)) //added in so it does read this test as empty
                .build();

        waitForStart();

        bot.followTrajectorySequence(test);

    }
    }
