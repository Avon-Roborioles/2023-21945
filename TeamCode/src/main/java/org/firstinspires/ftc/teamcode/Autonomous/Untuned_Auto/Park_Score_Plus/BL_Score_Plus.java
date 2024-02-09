package org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="BL Score Plus", group="Park + Score Plus")
//@Disabled
public class BL_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        init_classes();
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, false); //sets camera to start looking for prop

        //TODO
        TrajectorySequence FullLeftAuto = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0,() ->{ //3.5
                    intake.closeClaws(true); //grip pixels
                    arm.down();
                })
                .addTemporalMarker(3.5, () -> {
                    intake.wrist_down();
                })
                .addTemporalMarker(3.9,() -> {
                    intake.openClawV2(true,false); //score purple pixel
                })
//
                .addTemporalMarker(4.6,() ->{
                    intake.closeClaws(true); //close claw & wrist up
                    intake.wrist_up();
                })
                .addTemporalMarker(8,() ->{
                    arm.up();
                    intake.wrist_up();
                })

                .addTemporalMarker(16.7,() -> {
                    intake.openClawV2(true,false);
                })

                .addTemporalMarker(17.2,() -> {
                    intake.closeClaws(true);
                    intake.wrist_up();
                    arm.down();
                })

                .forward(20)
                .waitSeconds(.1)
                .turn(Math.toRadians(80))

                //moving prop away
                .waitSeconds(.1) //------------
                .forward(5)
                .waitSeconds(.1) //-----------
                .back(5)
                .waitSeconds(.5) //waiting to score pixel
                .strafeRight(10)
                .waitSeconds(.1)
                .turn(Math.toRadians(-160))

                //get to board
                .back(15)
                .waitSeconds(.1)
                .strafeRight(15) //TODO adjust value
                .waitSeconds(.1) //raise arm and get ready to score
                .back(5)
                .waitSeconds(.8) //score pixel
                .forward(5)
                .waitSeconds(.1)

                //park
                .strafeRight(10)
                .waitSeconds(.1)
                .back(10)
                .waitSeconds(10)
                .build();

        //TODO
        TrajectorySequence FullMiddleAuto = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0,() ->{ //3.5
                    intake.closeClaws(true); //grip pixels
                    arm.down();
                })

                //score spike pixel
                .forward(24)
                .waitSeconds(0.1)
                .back(8.75)
                .waitSeconds(.7) //score purple pixel
                .back(5)
                .waitSeconds(.1)
                .turn(Math.toRadians(-80))

                //get to board
                .back(13)
                .waitSeconds(.1) //get ready to score
                .back(5)
                .waitSeconds(.7) //score yellow pixel
                .forward(5)
                .waitSeconds(.1)

                //park
                .strafeRight(13)
                .waitSeconds(.1)
                .back(10)
                .waitSeconds(10)
                .build();

        //TODO
        TrajectorySequence FullRightAuto = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0,() ->{ //3.5
                    intake.closeClaws(true); //grip pixels
                    arm.down();
                })

                //score purple pixel
                .strafeLeft(5)
                .waitSeconds(.1)
                .forward(22)
                .waitSeconds(.05)
                .turn(Math.toRadians(-85))
                .waitSeconds(.1)
                .forward(5)
                .waitSeconds(.1)
                .back(3)
                .waitSeconds(.7) //score pixel

                //go to backdrop
                .back(20)
                .waitSeconds(.5) //raise arm & get ready to score
                .strafeRight(10)
                .waitSeconds(.7) //raise arm & get ready to score
                .back(5)
                .waitSeconds(.7) //score pixels
                .forward(5) //lower arm & raise wrist

                //park
                .waitSeconds(.1)
                .strafeRight(10)
                .back(10)
                .waitSeconds(10)
                .build();

        //auto code here
        waitForStart();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,true);

        //scores the purple preload pixel based on vision reading
        //bot.followTrajectorySequence(FullLeftAuto);
        switch(propPosition){
            case "LEFT":
                bot.followTrajectorySequence(FullLeftAuto);
                break;
            case "MIDDLE":
                bot.followTrajectorySequence(FullMiddleAuto);
                break;
            case "RIGHT":
                bot.followTrajectorySequence(FullRightAuto);
                break;
        }
    }
}
