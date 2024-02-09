package org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Disabled
//@Autonomous(name="BR Park", group="Park Programs")
public class BR_Park extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        init_classes();
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence BASIC = bot.trajectorySequenceBuilder(new Pose2d())
                .waitSeconds(0.5)
                .forward(24)
                .turn(Math.toRadians(90))
                .waitSeconds(0.5)
                .forward(40)

//                .turn(Math.toRadians(88)) //turns right 88 degrees
//                .waitSeconds(0.5)
//                .forward(50)
                .build();

        waitForStart();

        //runs trajectories we create
        bot.followTrajectorySequence(BASIC);
    }
}
