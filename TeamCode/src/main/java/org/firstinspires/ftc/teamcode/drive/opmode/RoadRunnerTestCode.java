package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class RoadRunnerTestCode extends LinearOpMode {
    @Override
    public void runOpMode () {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Trajectory RRTest = drive.trajectoryBuilder(new Pose2d())
                .forward(10)
                .back(10)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(RRTest);    }
}
