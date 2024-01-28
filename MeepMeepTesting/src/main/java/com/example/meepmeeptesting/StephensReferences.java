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

   public Pose2d startPoseRR = new Pose2d(12,-60.6,Math.toRadians(90));

    public static Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));

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
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .strafeRight(5)
                                .waitSeconds(.1)
                                .forward(30)
                                .waitSeconds(.1)
                                .turn(Math.toRadians(90))


                                .waitSeconds(.1) //------------
                                .forward(10)
                                .waitSeconds(.1) //-----------
                                .back(8)
                                .waitSeconds(1)

                                .strafeRight(20)
                                .waitSeconds(.1)
                                .forward(25)
                                .waitSeconds(1)

                                //board
                                .back(95)
                                .waitSeconds(.1)
                                .strafeLeft(20)
                                .waitSeconds(.1)
                                .back(5) //score
                                .waitSeconds(1)

                                //2 pixels
                                .forward(5)
                                .waitSeconds(.1)
                                .strafeRight(20)
                                .waitSeconds(.1)
                                .forward(70)

                                .waitSeconds(.1)
                                .strafeRight(2)
                                .waitSeconds(.1)
                                .forward(25)
                                .waitSeconds(1) //pixel in left claw
                                .back(5)
                                .waitSeconds(.1)
                                .strafeLeft(4)
                                .waitSeconds(.1)
                                .forward(5)
                                .waitSeconds(1) //pixel in right claw
                                .strafeRight(2)
                                .waitSeconds(.1)

                                //score 2 pixels
                                .back(90)
                                .waitSeconds(.1)
                                .strafeLeft(20)
                                .waitSeconds(.1)
                                .back(10)
                                .waitSeconds(1.5) //score
                                .forward(10)

                                //park
                                .waitSeconds(.1)
                                .strafeRight(20)
                                .waitSeconds(.1)
                                .back(20) //end

                                .addTemporalMarker(30, () ->{
                                    System.out.println("Auto Time Finished!");
                                })

                                .waitSeconds(10000)
                                .build()
                );

//        RoadRunnerBotEntity RRScoreSim = new DefaultBotBuilder(meepMeep)
//                .setColorScheme(new ColorSchemeBlueDark())
//                .setConstraints(88.2809332, 47.4845458372762, 7.660142060308357, Math.toRadians(259.11086367346934), 10.5)
//                .followTrajectorySequence(drive ->
//                                drive.trajectorySequenceBuilder(startPoseRL)
//
//                );


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