package org.firstinspires.ftc.teamcode.Autonomous.Tuned_Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

//@Autonomous(name = "Red Left Auto", group = "auto")
@Disabled
public class Red_Left_Auto extends AutoBase {
    public static Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));
    public static Pose2d checkpoint1 = new Pose2d(-36,-11,Math.toRadians(-180));
    public static Pose2d checkpoint2 = new Pose2d(34,-11,Math.toRadians(-180));
    public static Pose2d LeftSpikePose = new Pose2d(-45,-28,Math.toRadians(-180));
    public static Pose2d MiddleSpikePose = new Pose2d(-36,-8,Math.toRadians(-90));
    public static Pose2d RightSpikePose = new Pose2d(-40,-30,Math.toRadians(0));
    public static Pose2d ThirdStack = new Pose2d(-54,checkpoint1.getY() + 2, checkpoint1.getHeading());
    public static Pose2d LeftBoardPose = new Pose2d(39,-28,Math.toRadians(-180));
    public static Pose2d MiddleBoardPose = new Pose2d(39,-34,Math.toRadians(-180));
    public static Pose2d RightBoardPose = new Pose2d(39,-40,Math.toRadians(-180));

    public static Pose2d ParkSpot = new Pose2d(57,-8,Math.toRadians(-180));

    public void runOpMode() throws  InterruptedException{
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //tells bot where it is on the field
        bot.setPoseEstimate(startPoseRL);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop - red only

        //TEST
        TrajectorySequence Left_Spike_Score = bot.trajectorySequenceBuilder(startPoseRL)
                .addSpatialMarker(startPoseRL.vec(),() ->{
                    //System.out.println(" ");
                    //System.out.println("CLOSE CLAWS");
                    intake.closeClaws(true);
                    telemetry.addLine("CLOSE CLAWS");
                    telemetry.update();

                })

                //score spike
                .lineToLinearHeading(LeftSpikePose)
                .addDisplacementMarker(30,() ->{
//                    System.out.println(" ");
//                    System.out.println("WRIST DOWN");
                    telemetry.addLine(" ");
                    telemetry.addLine("WRIST DOWN");
                    telemetry.update();
                    intake.wrist_down();
                })
                .waitSeconds(.1)
                .back(11)
                .addDisplacementMarker(45,() ->{
//                    System.out.println(" ");
//                    System.out.println("OPEN LEFT CLAW");
                    telemetry.addLine(" ");
                    telemetry.addLine("OPEN LEFT CLAW");
                    telemetry.update();
                    intake.openClawV2(true,true);
                })
                .waitSeconds(.7) //wait to score spike
                .back(2) //move a bit so we don't pickup pixel again
                .waitSeconds(.1)
                .build();

        //TEST
        TrajectorySequence Middle_Spike_Score = bot.trajectorySequenceBuilder(startPoseRL)
                //close claw
                .addSpatialMarker(startPoseRL.vec(),() ->{
//                    System.out.println(" ");
//                    System.out.println("CLOSE CLAWS");
                    telemetry.addLine(" ");
                    telemetry.addLine("CLOSE CLAWS");
                    telemetry.update();
                    intake.closeClaws(true);
                })

                //score spike
                .lineToLinearHeading(MiddleSpikePose)
                .addDisplacementMarker(40,()->{
//                    System.out.println(" ");
//                    System.out.println("WRIST DOWN");
                    telemetry.addLine(" ");
                    telemetry.addLine("WRIST DOWN");
                    telemetry.update();
                    intake.wrist_down();
                })

                .waitSeconds(.1)

                .forward(5)
                .addDisplacementMarker(50,() ->{
//                    System.out.println(" ");
//                    System.out.println("OPEN LEFT CLAW");
                    telemetry.addLine(" ");
                    telemetry.addLine("OPEN LEFT CLAW");
                    telemetry.update();
                    intake.openClawV2(true,true);
                })
                .waitSeconds(1)

                .back(4)
                .waitSeconds(.1)
                .build();

        //TEST
        TrajectorySequence Right_Spike_Score = bot.trajectorySequenceBuilder(startPoseRL)
                //close claw
                .addSpatialMarker(startPoseRL.vec(),() ->{
//                    System.out.println(" ");
//                    System.out.println("CLOSE CLAWS");
                    telemetry.addLine(" ");
                    telemetry.addLine("CLOSE CLAWS");
                    telemetry.update();
                    intake.closeClaws(true);
                })

                //score spike
                .lineToLinearHeading(RightSpikePose)
                .addDisplacementMarker(30,()->{
//                    System.out.println(" ");
//                    System.out.println("WRIST DOWN");
                    telemetry.addLine(" ");
                    telemetry.addLine("WRIST DOWN");
                    telemetry.update();
                    intake.wrist_down();
                })

                .waitSeconds(.1)

                .forward(5)
                .addDisplacementMarker(35,() ->{
//                    System.out.println(" ");
//                    System.out.println("OPEN LEFT CLAW");
                    telemetry.addLine(" ");
                    telemetry.addLine("OPEN LEFT CLAW");
                    telemetry.update();
                    intake.openClawV2(true,true);
                })
                .waitSeconds(1)

                .back(7)
                .waitSeconds(.1)

                .build();

        //TEST
        TrajectorySequence CheckPoint1 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .lineToLinearHeading(checkpoint1)
                .addDisplacementMarker(10,()->{
                    telemetry.addLine(" ");
                    telemetry.addLine("GOING TO CHECKPOINT 1");
                    telemetry.update();
                })
                .build();

        //TEST
        TrajectorySequence CheckPoint2 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .lineToLinearHeading(checkpoint2)
                .addDisplacementMarker(10,()->{
                    telemetry.addLine(" ");
                    telemetry.addLine("GOING TO CHECKPOINT 2");
                    telemetry.update();
                })
                .build();
        //TEST
        TrajectorySequence LeftBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                //score on left Area of board
                .lineToLinearHeading(LeftBoardPose)
                .addSpatialMarker(checkpoint2.vec(),()->{
//                    System.out.println(" ");
//                    System.out.println("ARM UP");
                    telemetry.addLine(" ");
                    telemetry.addLine("ARM UP");
                    telemetry.update();
                    arm.up();
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("OPEN RIGHT CLAW");
                    telemetry.addLine(" ");
                    telemetry.addLine("OPEN RIGHT CLAW");
                    telemetry.update();
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7)
                .forward(5)
                .waitSeconds(.1)
                .build();

        //TODO
        TrajectorySequence MiddleBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .lineToLinearHeading(MiddleBoardPose)
                .addSpatialMarker(checkpoint2.vec(),()->{
                    //System.out.println("ARM UP");
                    telemetry.addLine(" ");
                    telemetry.addLine("ARM UP");
                    telemetry.update();
                    arm.up();
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("OPEN RIGHT CLAW");
                    telemetry.addLine(" ");
                    telemetry.addLine("OPEN RIGHT CLAW");
                    telemetry.update();
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7)
                .forward(5)
                .waitSeconds(.1)
                .build();
        //TODO
        TrajectorySequence RightBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                //score on left Area of board
                .lineToLinearHeading(RightBoardPose)
                .addSpatialMarker(checkpoint2.vec(),()->{
                    //System.out.println("ARM UP");
                    telemetry.addLine(" ");
                    telemetry.addLine("ARM UP");
                    telemetry.update();
                    arm.up();
                })
                .waitSeconds(.1)
                .back(5)
                .addDisplacementMarker(165,()->{
//                    System.out.println(" ");
//                    System.out.println("OPEN RIGHT CLAW");
                    telemetry.addLine(" ");
                    telemetry.addLine("OPEN RIGHT CLAW");
                    telemetry.update();
                    intake.openClawV2(true, false);
                })
                .waitSeconds(.7)
                .forward(5)
                .waitSeconds(.1)
                .build();

        //TODO
//        TrajectorySequence Pixel_Stack = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
//                .build();

        //TODO
        TrajectorySequence Park = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .lineToLinearHeading(checkpoint2)
                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("ARM DOWN");
//                    System.out.println("CLOSE CLAWS");
                    telemetry.addLine(" ");
                    telemetry.addLine("ARM DOWN");
                    telemetry.addLine("CLOSE CLAWS");
                    telemetry.update();
                    arm.down();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .lineToLinearHeading(ParkSpot)
                .build();


        waitForStart();

        //***************AUTO CODE STARTS***************

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,false);

        //scores the purple preload pixel based on vision reading
        switch(propPosition){
            case "LEFT":
                bot.followTrajectorySequence(Left_Spike_Score);
                break;
            case "MIDDLE":
                bot.followTrajectorySequence(Middle_Spike_Score);
                break;
            case "RIGHT":
                bot.followTrajectorySequence(Right_Spike_Score);
                break;
        }

        //go to checkpoint 1
        bot.followTrajectorySequence(CheckPoint1);

        //go to checkpoint 2
        bot.followTrajectorySequence(CheckPoint2);

        //score pixel on board based on prop Pose
        switch(aprilTagID){
            case 4:
                bot.followTrajectorySequence(LeftBoardScore);
                break;
            case 5:
                bot.followTrajectorySequence(MiddleBoardScore);
                break;
            case 6:
                bot.followTrajectorySequence(RightBoardScore);
                break;
        }

        //park bot
        bot.followTrajectorySequence(Park);


    }
}
