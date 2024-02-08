package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Red_Right_Sim {
    public static Pose2d startPoseRR = new Pose2d(12, -60.6, Math.toRadians(90));
    public static Pose2d checkpoint2 = new Pose2d(34,-58,Math.toRadians(-180));
    public static Pose2d LeftSpikePose = new Pose2d(14,-34,Math.toRadians(-180));
    public static Pose2d MiddleSpikePose = new Pose2d(14,-10,Math.toRadians(-90));
    public static Pose2d RightSpikePose = new Pose2d(35,-30,Math.toRadians(-180));
    public static Pose2d LeftBoardPose = new Pose2d(39,-28,Math.toRadians(-180));
    public static Pose2d MiddleBoardPose = new Pose2d(39,-34,Math.toRadians(-180));
    public static Pose2d RightBoardPose = new Pose2d(39,-40,Math.toRadians(-180));

    public static Pose2d ParkSpot = new Pose2d(57,-59,Math.toRadians(-180));

    public static void main(String[] args){
        MeepMeep meepMeep = new MeepMeep(600);
        String[] propPoses = {"LEFT", "MIDDLE", "RIGHT"};

        String propPosition = propPoses[(int)(Math.random() * 3)];
        System.out.println("Randomized Prop Position: " + propPosition);

        RoadRunnerBotEntity PoseReferences = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRR)

                                .waitSeconds(1000)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Red_Right_AutoL = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60,60,Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRR)
                                //close claw
                                .addSpatialMarker(startPoseRR.vec(),() ->{
                                    System.out.println(" ");
                                    System.out.println("CLOSE CLAWS");
                                })

                                //score spike
                                .strafeRight(3)
                                .waitSeconds(.1)
                                .lineToLinearHeading(LeftSpikePose)
                                .addDisplacementMarker(30,() ->{
                                    System.out.println(" ");
                                    System.out.println("WRIST DOWN");
                                })
                                .waitSeconds(.1)
                                .forward(7)
                                .waitSeconds(.7)
                                .back(5)
                                .addDisplacementMarker(40,() ->{
                                    System.out.println(" ");
                                    System.out.println("OPEN LEFT CLAW");
                                })
                                .waitSeconds(.7) //wait to score spike

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
                                .lineToLinearHeading(new Pose2d(ParkSpot.getX() - 10,ParkSpot.getY(), ParkSpot.getHeading()))
                                .waitSeconds(.1)
                                .lineToLinearHeading(ParkSpot)
                                .waitSeconds(1000)
                                .build()
                        );
        //TODO
        RoadRunnerBotEntity Red_Right_AutoM = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60,60,Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRR)
                                //close claw
                        .addSpatialMarker(startPoseRR.vec(),() ->{
                            System.out.println(" ");
                            System.out.println("CLOSE CLAWS");
                        })
                                //score spike
                                .strafeRight(5)
                                .waitSeconds(.1)
                                .lineToLinearHeading(MiddleSpikePose)
                                .addDisplacementMarker(50,()->{
                                    System.out.print(" ");
                                    System.out.println("WRIST DOWN");
                                })
                                .waitSeconds(.1)
                                .forward(3)
                                .addDisplacementMarker(56,()->{
                                    System.out.print(" ");
                                    System.out.println("OPEN LEFT CLAW");
                                })
                                .waitSeconds(.5)
                                .back(3)
                                .waitSeconds(.1)

                                //get to board
                                .strafeLeft(12)
                                .addDisplacementMarker(63,()->{
                                    System.out.print(" ");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .splineToLinearHeading(MiddleBoardPose, MiddleSpikePose.getHeading())

                                .addDisplacementMarker(80,()->{
                                    System.out.print(" ");
                                    System.out.println("ARM UP");
                                })

                                .addDisplacementMarker(105,()->{
                                    System.out.print(" ");
                                    System.out.print("OPEN RIGHT CLAW");
                                })

                                //score spike
                                .back(5)
                                .addDisplacementMarker(115,()->{
                                    System.out.println(" ");
                                    System.out.println("ARM DOWN");
                                    System.out.println("CLOSE CLAWS");

                                })
                                .waitSeconds(.7)
                                .forward(5)

                                //park
                                .lineToLinearHeading(new Pose2d(ParkSpot.getX() - 10,ParkSpot.getY(), ParkSpot.getHeading()))
                                .waitSeconds(.1)
                                .lineToLinearHeading(ParkSpot)

                                .waitSeconds(1000)
                                .build()
                );
        //TODO
        RoadRunnerBotEntity Red_Right_AutoR = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60,60,Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRR)
                                //close claw
                                .addSpatialMarker(startPoseRR.vec(),() ->{
                                    System.out.println(" ");
                                    System.out.println("CLOSE CLAWS");
                                })

                                //score spike
                                .splineToSplineHeading(RightSpikePose, RightSpikePose.getHeading())
                                .waitSeconds(.7)
                                .forward(8)
                                .waitSeconds(.7)
                                .back(8)
                                .addDisplacementMarker(60,()->{
                                    System.out.println(" ");
                                    System.out.println("OPEN LEFT CLAW");
                                })


                                .waitSeconds(1000)
                                .build()
                );



        switch(propPosition){
            case "LEFT":
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Red_Right_AutoR)
                        .start();
                break;
            case "RIGHT":
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Red_Right_AutoR)
                        .start();
                break;
            default:
                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Red_Right_AutoR)
                        .start();
                break;
        }
    }

}
