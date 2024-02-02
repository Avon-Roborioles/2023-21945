package org.firstinspires.ftc.teamcode.Autonomous.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

/*
 * NL means not localized -> doesn't keep track of where the bot is on the field
 * L means localized -> keeps track of where the bot is on the field
 */

@Autonomous(name="RR Score Plus", group="Park + Score")
public class RR_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{

    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop

        //TODO
        TrajectorySequence FullLeftAuto = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0,() ->{ //3.5
                    intake.closeClaws(true); //grip pixels
                    arm.down();
                })

                .forward(22)
                .waitSeconds(.1)
                .turn(Math.toRadians(80))

                //moving prop away
                .waitSeconds(.1) //------------
                .forward(5)
                .waitSeconds(.1) //-----------
                .back(7)
                .waitSeconds(.5) //waiting to score pixel

                //get to board
                .back(20)
                .waitSeconds(.1)
                .strafeRight(15) //TODO adjust value
                .waitSeconds(.1) //raise arm and get ready to score
                .back(5)
                .waitSeconds(.8) //score pixel
                .forward(5)
                .waitSeconds(.1)

                //park
                .strafeLeft(16)
                .waitSeconds(.1)
                .back(10)
                .waitSeconds(10) //added in case we might get index out of bounds exception error - time based robot functions longer than actual auto movement
                .build();

        //TODO
        TrajectorySequence FullMiddleAuto = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0,() ->{ //3.5
                    intake.closeClaws(true); //grip pixels
                    arm.down();
                })

                .waitSeconds(10)
                .build();

        //TODO
        TrajectorySequence FullRightAuto = bot.trajectorySequenceBuilder(new Pose2d())
                .addTemporalMarker(0,() ->{ //3.5
                    intake.closeClaws(true); //grip pixels
                    arm.down();
                })

                .waitSeconds(10)
                .build();


        //auto code here
        waitForStart();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,false);

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
//
//        //score pixel (& park bot if Not Localized - NL)
//        switch(aprilTagID){
//            case 4:
//                bot.followTrajectorySequence(LeftPreloadScoreNL);
//                break;
//            case 5:
//                bot.followTrajectorySequence(MiddlePreloadScoreNL);
//                break;
//            case 6:
//                bot.followTrajectorySequence(RightPreloadScoreNL);
//                break;
//        }

        //end of program
    }
}
