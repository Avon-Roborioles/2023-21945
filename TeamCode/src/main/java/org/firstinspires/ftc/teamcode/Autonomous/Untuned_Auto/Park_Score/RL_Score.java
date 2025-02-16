package org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park_Score;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="RL Score", group="Park + Score")
public class RL_Score extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {

        //important variables for auto - set to random values
        String propPosition = "MIDDLE";
        int aprilTagID = 2;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //TEST
        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(new Pose2d()) //Done testing
                .addTemporalMarker(0, () -> {
                    //intake.openClaw(false);
                    intake.closeClaws(true);
                    intake.wrist_up();
                })
                .forward(18)
                .waitSeconds(.1)
                .turn(Math.toRadians(75))
                .waitSeconds(.7)
                .forward(8)
                .waitSeconds(.7)
                .back(9)
                .addDisplacementMarker(()->{
                    intake.wrist_down();
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7)
                .back(3)
                .addDisplacementMarker(()->{
                    intake.closeClaws(true);
                })
                .build();

        //TEST
        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                    // intake.openClaw(false);
                    intake.closeClaws(true);
                    //intake.closePixelHolder(true);

                })
                .forward(24) //16
                .waitSeconds(.5)
                .back(8.75)
                .waitSeconds(2)
                .addTemporalMarker(3, () -> {
                    //intake.openClaw(false);
                    intake.openClawV2(true,false);
                    intake.wrist_down();
                })
                .addTemporalMarker(5.5, () -> {
                    //intake.openClaw(true);
                    intake.closeClaws(true);
                })
                .addTemporalMarker(6.5, () ->{
                    intake.wrist_up();
                })
                .back(5)
                .waitSeconds(10)
                .build();

        //TEST
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                    intake.closeClaws(true);
                    intake.wrist_up();
                })
                //get to spike
                .strafeLeft(5)
                .waitSeconds(.1)
                .forward(18) //17
                .waitSeconds(.1)
                .turn(Math.toRadians(-80))
                .addDisplacementMarker(()->{
                    intake.wrist_down();
                })

                //score spike
                .waitSeconds(.1) //------------
                .forward(8)
                .waitSeconds(.7) //-----------
                .back(5)
                .addDisplacementMarker(()->{
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7)
                .back(5)

                //park
//                .turn(Math.toRadians(-11))
//                .waitSeconds(.1)
//                .back(5)
//                .addDisplacementMarker(()->{
//                    intake.closeClaws(true);
//                    intake.wrist_up();
//                })
//                .waitSeconds(.1)
//                .strafeRight(21)
//                .waitSeconds(.1)
//                .back(22)
                .build();

        //auto code here
        waitForStart();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,false);

        //scores the purple preload pixel based on vision reading
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



    }
}
