package com.example.meepmeeptesting;

// RoadRunner/MeepMeep imports
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

//other imports
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Blue_Left_Sim {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //input scanner for auto menu

        //meepMeep variables
        MeepMeep meepMeep = new MeepMeep(600);
        ArrayList<String> propPoses = new ArrayList<>();
        propPoses.add("LEFT");
        propPoses.add("MIDDLE");
        propPoses.add("RIGHT");

        //input variables
        boolean manualInput;
        String propPosition = "";

        System.out.println("Manual or Auto Selection? (true/false): ");
        manualInput = input.nextBoolean();

        if(manualInput){
            //manual input
            while(!propPoses.contains(propPosition)) { //continues prompting until a valid propPose is chosen
                System.out.println("Select a PropPosition (LEFT, MIDDLE, RIGHT): ");
                propPosition = input.next();
            }
            //start = true;
        } else {
            //auto input
             propPosition = propPoses.get((int)(Math.random() * 3));
            System.out.println("Randomized Prop Position: " + propPosition);

            //start = true;
        }



        //variables for poses to type faster
        Pose2d startPose = PoseStorageCopy.startPoseBL;
        Pose2d parkSpot = PoseStorageCopy.parkSpotBL;
        Pose2d checkPoint1 = PoseStorageCopy.checkPoint1BL;

        //TODO
        RoadRunnerBotEntity Blue_Left_AutoL = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)

                                .waitSeconds(1000)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Blue_Left_AutoM = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)

                                .waitSeconds(1000)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Blue_Left_AutoR = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)

                                .waitSeconds(1000)
                                .build()
                );



        //Blue Alliance
        Image Blue_Alliance_Background = null;

        //change file path to absolute path of "Blue-Alliance-Background.jpg" Image
        //Stephen's Mac
//        try { Blue_Alliance_Background = ImageIO.read(new File("/Users/stepheno/Documents/GitHub/2023-21945/MeepMeepTesting/src/main/java/com/example/meepmeeptesting/Reference_Images/Blue-Alliance-Background.jpg")); }
//        catch (IOException e) {}

        //Stephen's PC
        try { Blue_Alliance_Background = ImageIO.read(new File("F:\\steph\\Documents\\Github\\2023-21945\\MeepMeepTesting\\src\\main\\java\\com\\example\\meepmeeptesting\\Reference_Images\\Blue-Alliance-Background.jpg")); }
        catch (IOException e) {}

        //run program
        switch (propPosition) {
            case "LEFT":
                meepMeep.setBackground(Blue_Alliance_Background)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Blue_Left_AutoL)
                        .start();
            break;
            case "MIDDLE":
                meepMeep.setBackground(Blue_Alliance_Background)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Blue_Left_AutoM)
                        .start();
                break;
            case "RIGHT":
                meepMeep.setBackground(Blue_Alliance_Background)
                        .setDarkMode(true)
                        .setBackgroundAlpha(0.95f)
                        //program to run
                        .addEntity(Blue_Left_AutoR)
                        .start();

        }
    }
}
