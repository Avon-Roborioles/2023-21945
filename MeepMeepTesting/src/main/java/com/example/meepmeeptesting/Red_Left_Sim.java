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
    public static Pose2d MiddleSpikePose = new Pose2d(-36,-8,Math.toRadians(-90));
    public static Pose2d RightSpikePose = new Pose2d(-40,-30,Math.toRadians(0));
    public static Pose2d ThirdStack = new Pose2d(-54,checkpoint1.getY() + 2, checkpoint1.getHeading());
    public static Pose2d LeftBoardPose = new Pose2d(39,-28,Math.toRadians(-180));
    public static Pose2d MiddleBoardPose = new Pose2d(39,-34,Math.toRadians(-180));
    public static Pose2d RightBoardPose = new Pose2d(39,-40,Math.toRadians(-180));

    public static Pose2d ParkSpot = new Pose2d(57,-8,Math.toRadians(-180));

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

                                .waitSeconds(1000)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Red_Left_AutoL = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                //close claw
                                .addSpatialMarker(startPoseRL.vec(),() ->{
                                    System.out.println(" ");
                                    System.out.println("CLOSE CLAWS");
                                })

                                //score spike
                                .lineToLinearHeading(LeftSpikePose)
                                .addDisplacementMarker(30,() ->{
                                    System.out.println(" ");
                                    System.out.println("WRIST DOWN");
                                })
                                .waitSeconds(.1)
                                .back(11)
                                .addDisplacementMarker(45,() ->{
                                    System.out.println(" ");
                                    System.out.println("OPEN LEFT CLAW");
                                })
                                .waitSeconds(.7) //wait to score spike
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
                                .lineToLinearHeading(checkpoint2)
                                .waitSeconds(.1)

                                //score on left Area of board
                                .lineToLinearHeading(LeftBoardPose)
                                .addSpatialMarker(checkpoint2.vec(),()->{
                                    System.out.println(" ");
                                    System.out.println("ARM UP");
                                })
                                .waitSeconds(.7)
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println(" ");
                                    System.out.println("OPEN RIGHT CLAW");
                                })
                                .waitSeconds(.7)
                                .forward(5)
                                .waitSeconds(.1)


                                //park
                                //.lineToConstantHeading(new Vector2d(LeftBoardPose.getX(), ParkSpot.getY()))
                                .lineToLinearHeading(checkpoint2)
                                .addDisplacementMarker(()->{
                                    System.out.println(" ");
                                    System.out.println("ARM DOWN");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .waitSeconds(.1)
                                .lineToLinearHeading(ParkSpot)

                                //FINISH
                                .waitSeconds(100)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Red_Left_AutoM = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                //close claw
                                .addSpatialMarker(startPoseRL.vec(),() ->{
                                    System.out.println(" ");
                                    System.out.println("CLOSE CLAWS");
                                })

                                //score spike
                                .lineToLinearHeading(MiddleSpikePose)
                                .addDisplacementMarker(40,()->{
                                    System.out.println(" ");
                                    System.out.println("WRIST DOWN");
                                })

                                .waitSeconds(.1)

                                .forward(5)
                                .addDisplacementMarker(50,() ->{
                                    System.out.println(" ");
                                    System.out.println("OPEN LEFT CLAW");
                                })
                                .waitSeconds(1)

                               .back(4)
                                .waitSeconds(.1)
                                .lineToLinearHeading(checkpoint1)
                                .addDisplacementMarker(()->{
                                    System.out.println(" ");
                                    System.out.println("WRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })


                                //get to board
                                .waitSeconds(.1)
                                .lineToLinearHeading(checkpoint2)
                                .waitSeconds(.1)

                                //score on Middle Area of board
                                .lineToLinearHeading(MiddleBoardPose)
                                .addSpatialMarker(checkpoint2.vec(),()->{
                                    System.out.println("ARM UP");
                                })
                                .waitSeconds(.7)
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println(" ");
                                    System.out.println("OPEN RIGHT CLAW");
                                })
                                .waitSeconds(.7)
                                .forward(5)
                                .waitSeconds(.1)
//
//
//                                //park
                                //.lineToConstantHeading(new Vector2d(MiddleBoardPose.getX(), ParkSpot.getY()))
                                .lineToLinearHeading(checkpoint2)
                                .addDisplacementMarker(()->{
                                    System.out.println("ARM DOWN");
                                })
                                .waitSeconds(.1)
                                .lineToLinearHeading(ParkSpot)

                                //FINISH
                                .waitSeconds(1000)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Red_Left_AutoR = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                //close claw
                                .addSpatialMarker(startPoseRL.vec(),() ->{
                                    System.out.println(" ");
                                    System.out.println("CLOSE CLAWS");
                                })

                                //score spike
                                .lineToLinearHeading(RightSpikePose)
                                .addDisplacementMarker(30,()->{
                                    System.out.println(" ");
                                    System.out.println("WRIST DOWN");
                                })

                                .waitSeconds(.1)

                                .forward(5)
                                .addDisplacementMarker(35,() ->{
                                    System.out.println(" ");
                                    System.out.println("OPEN LEFT CLAW");
                                })
                                .waitSeconds(1)

                                .back(7)
                                .waitSeconds(.1)
                                .lineToLinearHeading(checkpoint1)
                                .addDisplacementMarker(45,()->{
                                    System.out.println(" ");
                                    System.out.println("WRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })


                                //get to board
                                .waitSeconds(.1)
                                .lineToLinearHeading(checkpoint2)
                                .waitSeconds(.1)

                                //score on left Area of board
                                .lineToLinearHeading(RightBoardPose)
                                .addSpatialMarker(checkpoint2.vec(),()->{
                                    System.out.println("ARM UP");
                                })
                                .waitSeconds(.1)
                                .back(5)
                                .addDisplacementMarker(165,()->{
                                    System.out.println(" ");
                                    System.out.println("OPEN RIGHT CLAW");
                                })
                                .waitSeconds(.7)
                                .forward(5)
                                .waitSeconds(.1)
//
//
//                                //park
                                //.lineToConstantHeading(new Vector2d(RightBoardPose.getX(), ParkSpot.getY()))
                                .lineToLinearHeading(checkpoint2)
                                .addDisplacementMarker(()->{
                                    System.out.println(" ");
                                    System.out.println("ARM DOWN");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .waitSeconds(.1)
                                .lineToLinearHeading(ParkSpot)

                                .waitSeconds(100)
                                .build()
                );

        switch(propPosition){
            case "LEFT":
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Red_Left_AutoL)
                        .start();
                break;
            case "RIGHT":
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Red_Left_AutoR)
                        .start();
                break;
            default:
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Red_Left_AutoM)
                        .start();
                break;
        }
    }
    }
