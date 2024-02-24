package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class PoseReferences {
    public static void main(String[] args){
        MeepMeep meepMeep = new MeepMeep(800); //600 for laptops

        RoadRunnerBotEntity PoseReferences = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(PoseStorageCopy.startPoseBL)
                                .waitSeconds(15)
                                .splineToSplineHeading(PoseStorageCopy.checkPoint1BR,PoseStorageCopy.checkPoint1BR.getHeading())
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.checkPoint2BR)
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.firstStackB)
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.secondStackB)
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.thirdStackB)
                                .waitSeconds(.1)

                                .lineToLinearHeading(PoseStorageCopy.checkPoint2BL)
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.parkSpotBL)


                                .waitSeconds(100)
                                .build()
                );


        //Blue Alliance
        Image Blue_Alliance_Background = null;
        MeepMeep.Background Red_Alliance_Background = MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK;
        //change file path to absolute path of "Blue-Alliance-Background.jpg" Image

        //Stephen's Mac
//        try { Blue_Alliance_Background = ImageIO.read(new File("/Users/stepheno/Documents/GitHub/2023-21945/MeepMeepTesting/src/main/java/com/example/meepmeeptesting/Reference_Images/Blue-Alliance-Background.jpg")); }
//        catch (IOException e) {}

        //Stephen's PC
        try { Blue_Alliance_Background = ImageIO.read(new File("F:\\steph\\Documents\\Github\\2023-21945\\MeepMeepTesting\\src\\main\\java\\com\\example\\meepmeeptesting\\Reference_Images\\Blue-Alliance-Background.jpg")); }
        catch (IOException e) {}

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .setBackground(Blue_Alliance_Background)
                //program to run
                .addEntity(PoseReferences)
                .start();
    }
}
