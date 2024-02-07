package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.SampleMecanumDrive;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

public class Red_Left_Sim {
    //useful coordinates on the field to use
    public static Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));
    public static Pose2d checkpoint1 = new Pose2d(-36,-11,Math.toRadians(-180));
    public static Pose2d checkpoint2 = new Pose2d(34,-11,Math.toRadians(-180));
    public static Pose2d LeftSpikePose = new Pose2d(-45,-28,Math.toRadians(-180));
    public static Pose2d MiddleSpikePose;
    public static Pose2d RightSpikePose;
    public static Pose2d ThirdStack = new Pose2d(-54,checkpoint1.getY() + 2, checkpoint1.getHeading());
    public static Pose2d LeftBoardPose = new Pose2d(47,-28,Math.toRadians(-180));
    public static Pose2d MiddleBoardPose = new Pose2d(47,-34,Math.toRadians(-180));
    public static Pose2d RightBoardPose = new Pose2d(47,-40,Math.toRadians(-180));

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        String[] propPoses = {"LEFT", "MIDDLE", "RIGHT"};

        String propPosition = propPoses[(int)(Math.random() * 3)];
        System.out.println("Randomized Prop Position: " + propPosition);


        RoadRunnerBotEntity PoseReferences = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                //close claw
                                .addSpatialMarker(startPoseRL.vec(),() ->{
                                    System.out.println("CLOSE CLAWS");
                                })

                                //move prop & place wrist down
                                .lineToLinearHeading(LeftSpikePose)
                                .addSpatialMarker(LeftSpikePose.vec(),() ->{
                                    System.out.println(" ");
                                    System.out.println("WRIST DOWN");
                                })
                                .waitSeconds(.1)
                                .back(11)
                                .addSpatialMarker(new Vector2d(LeftSpikePose.getX() + 11,LeftSpikePose.getY()),() ->{
                                    System.out.println(" ");
                                    System.out.println("OPEN LEFT CLAW");
                                })

                                //wait to score spike
                                .waitSeconds(.7)
                                .back(2) //move a bit so we don't pickup pixel again
                                .waitSeconds(.1)

                                //get to checkpoint 1 + raise wrist + get ready to pickup pixel
                                .lineToLinearHeading(checkpoint1)
                                .waitSeconds(.7)
                                .addSpatialMarker(new Vector2d(-32.2,-17.6),()->{
                                    System.out.println(" ");
                                    System.out.println("RAISE WRIST");
                                    System.out.println("CLOSE CLAWS");
                                })

                                //get to stack
                                .lineToLinearHeading(ThirdStack)
                                .waitSeconds(.7)
                                .addSpatialMarker(new Vector2d(-55.3,-7.8),()->{
                                    System.out.println(" ");
                                    System.out.println("RAISE ARM TO PIXEL5");
                                    System.out.println("MOVE CLAW TO PIXEL5");
                                    System.out.println("OPEN LEFT CLAW");
                                })

                                //pickup white pixel
                                //.forward(3)
                                .lineToConstantHeading(new Vector2d(ThirdStack.getX() - 3, ThirdStack.getY()))
                                .addSpatialMarker(new Vector2d(ThirdStack.getX() +20, ThirdStack.getY()),()->{
                                    System.out.println(" ");
                                    System.out.println("CLOSE LEFT CLAW");
                                })
                                .waitSeconds(.7)

                                //get back to checkpoint 1
                                .lineToLinearHeading(checkpoint1)
                                .waitSeconds(.1)

                                //get to checkpoint 2
                                .lineToLinearHeading(checkpoint2)
                                .waitSeconds(.1)

                                //score on left Area of board
                                .lineToLinearHeading(LeftBoardPose)
                                .addSpatialMarker(new Vector2d(-16, 38),()->{
                                    System.out.println("ARM UP");
                                })
                                .waitSeconds(.1)
                                .back(5)
                                .forward(5)
//                                .waitSeconds(1)
//                                .strafeRight(10)
//                                .waitSeconds(.1)
//                                .lineToLinearHeading(checkpoint1)
//                                .waitSeconds(.1)
//                                .lineToLinearHeading(checkpoint2)
//                                .waitSeconds(.1)
//                                .lineToLinearHeading(LeftBoardPose)
//                                .waitSeconds(.1)
//                                .lineToLinearHeading(MiddleBoardPose)
//                                .waitSeconds(.1)
//                                .lineToLinearHeading(RightBoardPose)
//                                .waitSeconds(.1)
//                                .splineToLinearHeading(checkpoint2,checkpoint2.getHeading())
//                                .waitSeconds(.1)
//                                .lineToLinearHeading(checkpoint1)
//                                .waitSeconds(.1)
//                                .lineToLinearHeading(startPoseRL)
                                //long wait to keep window open
                                .waitSeconds(1000)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Red_Left_PlusL = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)


                                .waitSeconds(10)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Red_Left_PlusM = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .waitSeconds(10)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Red_Left_PlusR = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity RL_Spike = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity RL_Park = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .waitSeconds(10)
                                .build()
                );



        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                //program to run
                .addEntity(PoseReferences)
                .start();
    }
    }
