package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Color;

public class StephensReferences {

    public static Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));
    public static Pose2d startPoseRR = new Pose2d(12,-60.6,Math.toRadians(90));
    public static Pose2d startPoseBL = new Pose2d(-34,60,Math.toRadians(-90));
    public static Pose2d startPoseBR = new Pose2d(11.7,60,Math.toRadians(-90));

    //TODO set to startPose
    static double currentX = 0;
    static double currentY = 0;
    static double currentHeading = Math.toRadians(90);

    static Pose2d currentPose = new Pose2d(currentX,currentY,currentHeading); //Used in replacement of bot.getPoseEstimate()

    static void updatePoseEstimate(double x, double y,double heading){ //Used in replacement of bot.updatePoseEstimate()
        currentPose.plus(new Pose2d(x,y,heading));
        currentX = currentPose.getX();
        currentY = currentPose.getY();
        currentHeading = currentPose.getHeading();
    }

    static double poseX(double distance){
        updatePoseEstimate(currentX + distance,currentY,currentHeading);
        currentX += distance;
        return currentX;
    }

    static double poseY(double distance){
        updatePoseEstimate(currentX + distance,currentY,currentHeading);
        currentY += distance;
        return currentY;
    }

    static double poseHeading(double angle){
        updatePoseEstimate(currentX + angle,currentY,currentHeading);
        currentHeading += angle;
        return currentY;
    }

    public static void main(String[] args){
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity fieldReference = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseBL)
                                //TODO add code here

                                .waitSeconds(100)
                                .build()
                );

        RoadRunnerBotEntity testAuto = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .forward(22)
                                .waitSeconds(.1)
                                .turn(Math.toRadians(80))

                                //moving prop away
                                .waitSeconds(.1) //------------
                                .forward(5)
                                .waitSeconds(.1) //-----------
                                .back(5)
                                .waitSeconds(.5) //waiting to score pixel

                                //-------------
                                //scoring purple pixel
                                //--------------

                                //TODO get second pixel - split to different TrajectorySquence before Comp
                                .strafeRight(15)
                                .waitSeconds(.1)
                                .forward(14)
                                .waitSeconds(.7)

//                //board
//                                .back(65) //TODO - DECREASE SPEED to 30!!!!!
//                                .waitSeconds(.1)
//                                .strafeLeft(17)
//                                .waitSeconds(.1)
//                                .back(5) //score
//                                .waitSeconds(.7)
////
////                //2 pixels
//                                //.strafeLeft(5)
//                                //.waitSeconds(.1)
//                                .strafeLeft(5)
//                                .waitSeconds(.1)
//                                .forward(60) //TODO - SLOW SPEED to 20!!!
//                                .waitSeconds(.1)
//                                .strafeRight(20)
//                                .waitSeconds(.1)
//                                .forward(14)
//                                .waitSeconds(.1)
//
//                                //to board
                                .back(65) //TODO - DECREASE SPEED to 30!!!!!
                                .waitSeconds(.1)
                                .strafeLeft(17)
                                .waitSeconds(.1)
                                .back(5) //score
                                .waitSeconds(.1)
                                .strafeRight(17)
                                .waitSeconds(.1)
                                .back(8)

                                .waitSeconds(10000)
                                .build()
                );


        RoadRunnerBotEntity RLLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(startPoseRL)
                                        //TODO add code here

                                        .waitSeconds(10)
                                        .build()
                        );

        RoadRunnerBotEntity RLMiddle = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity RLRight = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity RRLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRR)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity RRMiddle = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRR)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity RRRight = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRR)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity BLLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseBL)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity BLMiddle = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseBL)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity BLRight = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseBL)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity BRLeft = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseBR)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity BRMiddle = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseBR)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );

        RoadRunnerBotEntity BRRight = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseBR)
                                //TODO add code here

                                .waitSeconds(10)
                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                //program to run
                .addEntity(fieldReference)

                .start();
//        while(meepMeep.getWindowFrame().isActive()){
//            System.out.println("Current X Pose: ");
//
//        }
    }
}
