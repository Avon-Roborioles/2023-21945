package org.firstinspires.ftc.teamcode.Autonomous.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.TimeUnit;

/*
 * NL means not localized -> doesn't keep track of where the bot is on the field
 * L means localized -> keeps track of where the bot is on the field
 */

@Autonomous(name="RL Score Plus", group="Park + Score")
public class RL_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
//    Timing.Timer autoTimer = new Timing.Timer(30, TimeUnit.SECONDS);
//
//    int CycleCount = 0;
//     TrajectorySequence quickStrafe = null;
//
//     /*TODO - uncomment if currentPose object can't be used in certain cases
//    double Xaxis = 0;
//    double Yaxis = 0;
//    double heading = Math.toRadians(0);
//*/
//    //defined poses on the field to use
//    public Pose2d startPose = new Pose2d(0,0,Math.toRadians(90));
//    public Vector2d checkPoint1 = new Vector2d(20,20);
//    public Vector2d checkPoint2 = new Vector2d(-55,15);
//    public Vector2d LeftBoardPose = new Vector2d(45,20); //TODO Update These Board Poses
//    public Pose2d MiddleBoardPose = new Pose2d(45,15,Math.toRadians(180));
//    public Pose2d RightBoardPose = new Pose2d(45, 10,Math.toRadians(180));
//
//
//
//    //easy to use methods that update bot pose AND return added/subtracted pose value
//    public double poseX(double distance){
//        bot.updatePoseEstimate();
//        return bot.getPoseEstimate().getX() + distance;
//    }
//
//    public double poseY(double distance){
//        bot.updatePoseEstimate();
//        return bot.getPoseEstimate().getY() + distance;
//    }
//
//    public double poseHeading(double degrees){
//        bot.updatePoseEstimate();
//        return bot.getPoseEstimate().getHeading() + Math.toRadians(degrees);
//    }
//
//    TrajectoryVelocityConstraint slowerSpeed(double speed){
//        return SampleMecanumDrive.getVelocityConstraint(speed, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH);
//    }
    public void runOpMode() throws InterruptedException {
       SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop


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
                    intake.openClawV2(true,true); //score purple pixel
                })
//
                .addTemporalMarker(4.6,() ->{
                    intake.closeClaws(true); //close claw & wrist up
                    intake.wrist_up();
                })
                .addTemporalMarker(16,() ->{
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

                .forward(22)
                .waitSeconds(.1)
                .turn(Math.toRadians(80))

                //moving prop away
                .waitSeconds(.1) //------------
                .forward(5)
                .waitSeconds(.1) //-----------
                .back(7)
                .waitSeconds(.5) //waiting to score pixel

                .forward(1.59)
                .waitSeconds(.05)
                .turn(Math.toRadians(-8))
                .waitSeconds(.05)
                .strafeRight(16)
                .waitSeconds(.1)
                .forward(16) //TODO adjust distance as needed
                .waitSeconds(.7) //pickup white pixel
                .back(7)

                //go to board
                .turn(Math.toRadians(-5))
                .waitSeconds(.1)
                .back(57, SampleMecanumDrive.getVelocityConstraint(35, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL)) //TODO - DECREASE SPEED!!!!!
                .waitSeconds(.1)
                .strafeLeft(12)
                .waitSeconds(.1)
                .back(5) //score pixels
                .waitSeconds(.1)
                .forward(5)
                .waitSeconds(.1)

                //park robot
                .strafeRight(10.5)
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
                .forward(24)
                .waitSeconds(0.1)
                .back(8.75)
                .waitSeconds(.7) //score purple pixel

                //get one more pixel
                .strafeLeft(7)
                .waitSeconds(.1)
                .forward(13) //TODO - test to get good distance
                .waitSeconds(.1)
                .turn(Math.toRadians(88))
                .waitSeconds(.1) //raise arm and claw at this time
                .forward(10,SampleMecanumDrive.getVelocityConstraint(20, DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .waitSeconds(.7) //TODO - pickup white pixel

                /* TODO add in if needed
                .turn(Math.toRadians(-8))
                .waitSeconds(.1)
                 */

                //go to board slowly
                .back(65,SampleMecanumDrive.getVelocityConstraint(30,DriveConstants.MAX_ANG_VEL,DriveConstants.TRACK_WIDTH),SampleMecanumDrive.getAccelerationConstraint(DriveConstants.MAX_ACCEL))
                .waitSeconds(.1)

                //score pixels
                .strafeLeft(15)
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
                .forward(22)
                .waitSeconds(.1)
                .turn(Math.toRadians(-80))
                .waitSeconds(.1)
                .forward(5)
                .waitSeconds(.1)
                .back(7)
                .waitSeconds(.7) //score purple pixel

                //pickup 1 white pixel
                .back(5)
                .waitSeconds(.1)
                .turn(Math.toRadians(170))
                .waitSeconds(.1)
                .strafeRight(15)
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
                .strafeLeft(17)
                .waitSeconds(1.5) //raise arm & score
                .forward(3)
                .waitSeconds(.05)

                //park
                .strafeRight(18)
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
