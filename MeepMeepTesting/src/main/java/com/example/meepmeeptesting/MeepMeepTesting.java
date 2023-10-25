package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

         double posX = 11.5;
         double posY = 60;

        //testing
        RoadRunnerBotEntity testBot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(posX, posY, Math.toRadians(-90)))
                                .waitSeconds(.2)
                                .lineToConstantHeading(new Vector2d(posX, posY - 26))
                                .waitSeconds(.2)
                                .lineToLinearHeading(new Pose2d(posX,posY - 20, Math.toRadians(0)))
                                .waitSeconds(.2)
                                .lineToLinearHeading(new Pose2d(posX + 39,posY - 24, Math.toRadians(0)))
                                .waitSeconds(.2)
                                .lineToLinearHeading(new Pose2d(posX + 0,posY, Math.toRadians(180)))
                                .waitSeconds(.2)
                                .lineToLinearHeading(new Pose2d(posX - 50,posY, Math.toRadians(180)))
                                .waitSeconds(.2)
                                .lineToLinearHeading(new Pose2d(posX - 50,posY - 25, Math.toRadians(180)))
                                .waitSeconds(.2)
                                .lineToLinearHeading(new Pose2d(posX - 65,posY - 25, Math.toRadians(180)))
                                .waitSeconds(.1)
                                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(testBot)
                .start();
    }
}