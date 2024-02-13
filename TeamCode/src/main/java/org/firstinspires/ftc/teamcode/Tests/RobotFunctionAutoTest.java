package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="Robot Function Auto Test", group="Tests")
public class RobotFunctionAutoTest extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        init_classes();

        bot.setPoseEstimate(PoseStorage.startPoseRL);

        TrajectorySequence test = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0.1,() -> {
                    intake.wrist_down();
                })

                .addTemporalMarker(0.2,() -> {
                    intake.closeClaws(false);
                })

                .addTemporalMarker(0.7,() -> {
                    intake.closeClaws(true);
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
                    intake.closeClaws(true);
                })

                .addTemporalMarker(4.4,() -> {
                    arm.down();
                })
                .waitSeconds(100)
                .build();

        waitForStart();

        bot.followTrajectorySequenceAsync(test);

        while(opModeIsActive()){
            bot.update(); //handles pathing logic
            arm.update(); //handles arm PID controller

        }

    }
    }
