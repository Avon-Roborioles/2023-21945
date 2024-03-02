package org.firstinspires.ftc.teamcode.Autonomous.Tuned_Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous
public class PoseReference extends AutoBase {
    public void runMode() throws InterruptedException{

     Pose2d startPoseBR = PoseStorage.startPoseBR;
     Pose2d leftSpikePose = PoseStorage.leftSpikePoseBR;
     Pose2d checkPoint2 = PoseStorage.checkPoint2BR;

        init_classes();

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //bot.setPoseEstimate(startPoseBR);

        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                //score purple pixel on spike mark
                .addTemporalMarker(0,()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .lineToLinearHeading(leftSpikePose)
                .waitSeconds(.1)
                .forward(7)
                .addDisplacementMarker(()->{
                    //System.out.println("\nWRIST DOWN");
                    intake.wrist_down();
                })
                .waitSeconds(.1)
                .back(5)
                .waitSeconds(.7)
                .turn(Math.toRadians(-1e-6))
                .addDisplacementMarker(()->{
                    //System.out.println("\nOPEN RIGHT CLAW");
                    intake.openClawV2(true,false);
                })
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(30)
                .build();

        waitForStart();

        bot.followTrajectorySequence(LeftSpikeScore);

    }
}
