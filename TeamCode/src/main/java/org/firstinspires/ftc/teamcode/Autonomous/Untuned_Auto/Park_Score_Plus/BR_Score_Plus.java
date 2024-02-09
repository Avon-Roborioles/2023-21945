package org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="BR Score Plus", group="Park + Score Plus")
//@Disabled
public class BR_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
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
                //time-based robot functions
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
//                .addTemporalMarker(8,() ->{
//                    arm.up();
//                    intake.wrist_up();
//                })
//
//                .addTemporalMarker(16.7,() -> {
//                    intake.openClawV2(true,false);
//                })
//
//                .addTemporalMarker(17.2,() -> {
//                    intake.closeClaws(true);
//                    intake.wrist_up();
//                    arm.down();
//                })

                .forward(18)
                .waitSeconds(.1)
                .turn(Math.toRadians(70))

                //moving prop away
                .waitSeconds(.1) //------------
                .forward(5)
                .waitSeconds(.1) //-----------
                .back(5)
                .waitSeconds(.5) //waiting to score pixel

                .back(1.59)
                .waitSeconds(.05)
                .turn(Math.toRadians(8))
                .waitSeconds(.05)
                .strafeRight(16)
                .waitSeconds(.1)
                .turn(Math.toRadians(-150))

//                //get white pixel
//                .forward(16) //TODO adjust distance as needed
//                .waitSeconds(.7) //pickup white pixel
//                .back(7)
//
//                //go to board
//                .turn(Math.toRadians(5))
                .waitSeconds(.1)
                .back(43, SampleMecanumDrive.getVelocityConstraint(35, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)) //TODO - DECREASE SPEED!!!!!
                .waitSeconds(.1)
                .strafeRight(20) //TODO adjust as need
                .waitSeconds(.7) //raise arm & get ready to score
                .back(5)
                .waitSeconds(.7) //score pixels
                .forward(5) //put arm down
                .waitSeconds(.1)

                //park robot
                .strafeLeft(19)
                .waitSeconds(.1)
                .back(10)
                .waitSeconds(10)
                .build();

        //TODO
        TrajectorySequence FullMiddleAuto = bot.trajectorySequenceBuilder(new Pose2d())
                //time-based robot functions
                .addTemporalMarker(0,() ->{ //3.5
                    intake.closeClaws(true); //grip pixels
                    arm.down();
                })
                //TODO add back scoring functions
//                .addTemporalMarker(3.5, () -> {
//                    intake.wrist_down();
//                })
//                .addTemporalMarker(3.9,() -> {
//                    intake.openClawV2(true,true); //score purple pixel
//                })
////
//                .addTemporalMarker(4.6,() ->{
//                    intake.closeClaws(true); //close claw & wrist up
//                    intake.wrist_up();
//                })
//                .addTemporalMarker(16,() ->{
//                    arm.up();
//                    intake.wrist_up();
//                })
//
//                .addTemporalMarker(16.7,() -> {
//                    intake.openClawV2(true,false);
//                })
//
//                .addTemporalMarker(17.2,() -> {
//                    intake.closeClaws(true);
//                    intake.wrist_up();
//                    arm.down();
//                })

                //score pixel
                .forward(22)
                .waitSeconds(0.1)
                .back(8.75)
                .waitSeconds(.7) //score purple pixel

                //get one more pixel
                .strafeRight(7)
                .waitSeconds(.1)
                .forward(13) //TODO - test to get good distance
                .waitSeconds(.1)
                .turn(Math.toRadians(-88))
                .waitSeconds(.1) //raise arm and claw at this time
                .forward(15,SampleMecanumDrive.getVelocityConstraint(20, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .waitSeconds(.7) //TODO - pickup white pixel

                /* TODO add in if needed
                .turn(Math.toRadians(-8))
                .waitSeconds(.1)
                 */

                //go to board slowly
                .back(65,SampleMecanumDrive.getVelocityConstraint(30,DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .waitSeconds(.1)

                //score pixels
                .strafeRight(15)
                .waitSeconds(1.5) //raise arm & score
                .forward(3)
                .waitSeconds(.05)

                //park
                .strafeRight(15)
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

                //score purple pixel on spike
                .strafeLeft(2)
                .waitSeconds(.1)
                .forward(17 )
                .waitSeconds(.1)
                .turn(Math.toRadians(-80))
                .waitSeconds(.1)
                .forward(5)
                .waitSeconds(.1)
                .back(7)
                .waitSeconds(.7) //score purple pixel

                //pickup 1 white pixel
                .back(2)
                .waitSeconds(.1)
                .strafeLeft(15)
                .waitSeconds(.1)
                .forward(15)
                .waitSeconds(.7) //pickup pixel

                //go to backdrop
                /* TODO add in if needed
                .turn(Math.toRadians(-8))
                .waitSeconds(.1)
                 */

                //go to board slowly
                .back(65,SampleMecanumDrive.getVelocityConstraint(30,DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .waitSeconds(.1)

                //score pixels
                .strafeRight(1)
                .waitSeconds(1.5) //raise arm & score
                .back(5)
                .waitSeconds(.1)
                .forward(5) //put down arm

                //park
                .strafeRight(15)
                .waitSeconds(.1)
                .back(10)

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
    }
}
