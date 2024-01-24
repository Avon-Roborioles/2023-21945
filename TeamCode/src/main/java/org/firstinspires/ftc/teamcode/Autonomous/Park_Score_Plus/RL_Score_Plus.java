package org.firstinspires.ftc.teamcode.Autonomous.Park_Score_Plus;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MecanumVelocityConstraint;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.TimeUnit;

/*
 * NL means not localized -> doesn't keep track of where the bot is on the field
 * L means localized -> keeps track of where the bot is on the field
 */

@Autonomous(name="RL Score Plus", group="Park + Score")
public class RL_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    Timing.Timer autoTimer = new Timing.Timer(30, TimeUnit.SECONDS);

    int CycleCount = 0;
     TrajectorySequence quickStrafe = null;

     /*TODO - uncomment if currentPose object can't be used in certain cases
    double Xaxis = 0;
    double Yaxis = 0;
    double heading = Math.toRadians(0);
*/
    //defined poses on the field to use
    public Pose2d startPose = new Pose2d(0,0,Math.toRadians(90));
    public Vector2d checkPoint1 = new Vector2d(20,20);
    public Vector2d checkPoint2 = new Vector2d(-55,15);
    public Vector2d LeftBoardPose = new Vector2d(45,20); //TODO Update These Board Poses
    public Pose2d MiddleBoardPose = new Pose2d(45,15,Math.toRadians(180));
    public Pose2d RightBoardPose = new Pose2d(45, 10,Math.toRadians(180));

    SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);


    //easy to use methods that update bot pose AND return added/subtracted pose value
    public double poseX(double distance){
        bot.updatePoseEstimate();
        return bot.getPoseEstimate().getX() + distance;
    }

    public double poseY(double distance){
        bot.updatePoseEstimate();
        return bot.getPoseEstimate().getY() + distance;
    }

    public double poseHeading(double degrees){
        bot.updatePoseEstimate();
        return bot.getPoseEstimate().getHeading() + Math.toRadians(degrees);
    }


    public void runOpMode() throws InterruptedException {

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop




        TrajectorySequence LeftSpikeScoreNL = bot.trajectorySequenceBuilder(new Pose2d()) //Done testing
                .addTemporalMarker(0, () -> {
                    intake.openClaws(false);
                    intake.wrist_up();
                })
                .strafeRight(5)
                .waitSeconds(.5)
                .forward(20)
                .waitSeconds(.5)
                .turn(Math.toRadians(88))


                .waitSeconds(.5) //------------
                .forward(10)
                .waitSeconds(.5) //-----------
                .back(10.25)
                .waitSeconds(5)

                .addTemporalMarker(7, () -> {
//                    intake.openClaw(false);
//                    intake.wrist_down();
                    intake.wrist_down();
                    intake.openClaws(false);
                })
                .addTemporalMarker(8, () -> {
//                    intake.openClaw(true);
                    intake.openClawV2(true,true);
                })
                .addTemporalMarker(9, () ->{
                    intake.wrist_up();
                })
                .addTemporalMarker(9.3, () -> { //closes claw on way up
                    intake.openClawV2(false,true);
                })
                .turn(Math.toRadians(5))
                .strafeLeft(25) //25

                //-----------------------
                .waitSeconds(1)
                .back(31) //35
                .build();

        TrajectorySequence MiddleSpikeScoreNL = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                    intake.openClaws(false);
                    intake.wrist_up();

                })
                .forward(24) //16
                .waitSeconds(.5)
                .back(8.75)
                .waitSeconds(5)
                .addTemporalMarker(3, () -> {
                    //intake.openClaw(false);
                    intake.openClaws(false);
                    intake.wrist_down();
                })
                .addTemporalMarker(4, () -> {
                    //intake.openClaw(true);
                    intake.openClawV2(true,true);
                })
                .addTemporalMarker(5, () ->{
                    intake.wrist_up();
                })
                .addTemporalMarker(5.3, () -> {
                    intake.openClawV2(false, true);
                })
                .back(14)
                .waitSeconds(1)
                //----------------------
                .strafeRight(33)
                .build();
//
        TrajectorySequence RightSpikeScoreNL = bot.trajectorySequenceBuilder(new Pose2d(0,0,Math.toRadians(0))) //Done testing
                .addTemporalMarker(0, () -> {
                    intake.openClaws(false);
                    intake.wrist_up();

                })
                .forward(20)
                .waitSeconds(.5)
                .turn(Math.toRadians(-88))
                .waitSeconds(.5) //------------
                .forward(10)
                .waitSeconds(.5) //-----------
                .back(10.5)
                .waitSeconds(4)
                .addTemporalMarker(6, () -> {
                    //intake.openClaw(false);
                    intake.openClaws(false);
                    intake.wrist_down();
                })
                .addTemporalMarker(7, () -> {
                    //intake.openClaw(true);
                    intake.openClawV2(true, true);
                })
                .addTemporalMarker(8, () ->{
                    intake.wrist_up();
                })
                .addTemporalMarker(8.3, () -> {
                    intake.openClawV2(false, true);
                })
                .forward(3)
                .waitSeconds(.5)
                .strafeRight(20) //25
                .waitSeconds(1)
                //---------------------
                .forward(30) //35
                .build();

        TrajectorySequence LeftPreloadScoreNL = bot.trajectorySequenceBuilder(new Pose2d())
                .back(30) //TODO adjust distance - get to board
                .waitSeconds(.1)
                .strafeRight(10) //TODO adjust - align with left tag
                .waitSeconds(5)
                .addTemporalMarker(3,() -> {
                    arm.up_auto();
                    intake.wrist_up();
                })
                .addTemporalMarker(4,() -> {
                    intake.openClaws(true); //scores
                })
                .addTemporalMarker(4.5, () -> {
                    arm.down_auto();
                    intake.openClaws(false);
                })
                .strafeLeft(25) //TODO adjust value - get to parking
                .waitSeconds(.1)
                .back(10) //TODO adjust distance - park
                .build();

        TrajectorySequence MiddlePreloadScoreNL = bot.trajectorySequenceBuilder(new Pose2d())
                //move away from pixel
                .turn(90)
                .waitSeconds(.2)
                .forward(10)
                .waitSeconds(.1)
                .strafeLeft(10)

                .waitSeconds(.1)
                .back(25) //TODO adjust distance - get to board - 5 less than LeftPreloadScoreNL
                .waitSeconds(.1)
                .strafeRight(10) //TODO adjust - align with left tag
                .waitSeconds(5)
                .addTemporalMarker(6,() -> {
                    arm.up_auto();
                    intake.wrist_up();
                })
                .addTemporalMarker(7,() -> {
                    intake.openClaws(true); //scores
                })
                .addTemporalMarker(7.5, () -> {
                    arm.down_auto();
                    intake.openClaws(false);
                })
                .strafeLeft(25) //TODO adjust value - get to parking
                .waitSeconds(.1)
                .back(10) //TODO adjust distance - park
                .build();

        TrajectorySequence RightPreloadScoreNL = bot.trajectorySequenceBuilder(new Pose2d()) //TODO
                .turn(90)
                .waitSeconds(.05)
                .turn(88)
                .waitSeconds(.1)
                .strafeLeft(10) //TODO - adjust
                .back(30) //TODO - adjust distance - get to board
                .waitSeconds(.1)
                .strafeRight(10) //TODO adjust - align with left tag
                .waitSeconds(5)
                .addTemporalMarker(3,() -> {
                    arm.up_auto();
                    intake.wrist_up();
                })
                .addTemporalMarker(4,() -> {
                    intake.openClaws(true); //scores
                })
                .addTemporalMarker(4.5, () -> {
                    arm.down_auto();
                    intake.openClaws(false);
                })
                .strafeLeft(25) //TODO adjust value - get to parking
                .waitSeconds(.1)
                .back(10) //TODO adjust distance - park
                .build();


        //auto code here
        waitForStart();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,false);

        //scores the purple preload pixel based on vision reading
        switch(propPosition){
            case "LEFT":
                bot.followTrajectorySequence(LeftSpikeScoreNL);
                break;
            case "MIDDLE":
                bot.followTrajectorySequence(MiddleSpikeScoreNL);
                break;
            case "RIGHT":
                bot.followTrajectorySequence(RightSpikeScoreNL);
                break;
        }

        //score pixel (& park bot if Not Localized - NL)
        switch(aprilTagID){
            case 4:
                bot.followTrajectorySequence(LeftPreloadScoreNL);
                break;
            case 5:
                bot.followTrajectorySequence(MiddlePreloadScoreNL);
                break;
            case 6:
                bot.followTrajectorySequence(RightPreloadScoreNL);
                break;
        }

     //end of program
    }
}
