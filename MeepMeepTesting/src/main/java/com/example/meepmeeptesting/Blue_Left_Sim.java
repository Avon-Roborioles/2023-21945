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
        MeepMeep meepMeep = new MeepMeep(800); //600 for laptops
        ArrayList<String> propPoses = new ArrayList<>();
        propPoses.add("LEFT");
        propPoses.add("MIDDLE");
        propPoses.add("RIGHT");

        //input variables
        boolean manualInput;
        String propPosition = "";
        String checkPointType = "";

        //variables for poses to type faster
        Pose2d startPose = PoseStorageCopy.startPoseBL;
        Pose2d parkSpot = PoseStorageCopy.parkSpotBL;
        Pose2d checkPoint1 = PoseStorageCopy.checkPoint1BL; //default to BL Check points
        Pose2d checkPoint2 = PoseStorageCopy.checkPoint2BL;


        System.out.println("Manual or Auto Selection? (true/false): ");
        manualInput = input.nextBoolean();

        if(manualInput){
            //manual input
            //prop pose selection
            while(!propPoses.contains(propPosition)) { //continues prompting until a valid propPose is chosen
                System.out.println("\nSelect a PropPosition (LEFT, MIDDLE, RIGHT): ");
                propPosition = input.next();
            }
            System.out.println("Selected: " + propPosition);

            //checkpoint location selection
            while(!Objects.equals(checkPointType, "Up") && !Objects.equals(checkPointType, "Down")){
                System.out.println("\nSelect the Locations of the CheckPoints (Up or Down):");
                checkPointType = input.next();
            }
            System.out.println("Selected: " + checkPointType);
            if(checkPointType.equals("Up")){
                checkPoint1 = PoseStorageCopy.checkPoint1BR;
                checkPoint2 = PoseStorageCopy.checkPoint2BR;
            }

        } else {
            //auto input
             propPosition = propPoses.get((int)(Math.random() * 3));
             checkPointType = "Down";
            System.out.println("Randomized Prop Position: " + propPosition);
            System.out.println("CheckPoint Location: " + checkPointType);
        }





        //TODO
        RoadRunnerBotEntity Blue_Left_AutoL = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)
                                //score purple pixel on spike mark
                                .addTemporalMarker(0,()->{
                                    System.out.println("\nWRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.leftSpikePoseBL)
                                .waitSeconds(.1)
                                .back(9)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nWRIST DOWN");
                                })
                                .waitSeconds(.7)
                                .turn(Math.toRadians(-1e-6))
                                .addDisplacementMarker(()->{
                                    System.out.println("\nOPEN RIGHT CLAW");
                                })
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nWRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .waitSeconds(.1)

                                //score on board
                                .lineToLinearHeading(PoseStorageCopy.leftBoardPoseB)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nARM UP");
                                })
                                .waitSeconds(.1)
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nOPEN LEFT CLAW");
                                })
                                .waitSeconds(2)
                                .forward(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nARM DOWN");
                                    System.out.println("\nCLOSE CLAWS");
                                })
                                .waitSeconds(.1)

                                //TODO - Add Stacked Pixel Auto

                                //park
                                .splineToLinearHeading(new Pose2d(parkSpot.getX() + 16,parkSpot.getY()),parkSpot.getHeading())
                                .waitSeconds(.1)
                                .lineToLinearHeading(parkSpot)
                                .waitSeconds(100)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Blue_Left_AutoM = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)
                                //score purple pixel on spike mark
                                .addTemporalMarker(0,()->{
                                    System.out.println("\nWRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.middleSpikePoseBL)
                                .waitSeconds(.1)
                                .forward(2)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nWRIST DOWN");
                                })
                                .waitSeconds(.7)
                                .turn(Math.toRadians(-1e-6))
                                .addDisplacementMarker(()->{
                                    System.out.println("\nOPEN RIGHT CLAW");
                                })
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nWRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })

                                .waitSeconds(.01)
                                .strafeRight(11)
                                .waitSeconds(.01)

                                //score on board
                                .splineToLinearHeading(PoseStorageCopy.middleBoardPoseB,PoseStorageCopy.middleBoardPoseB.getHeading())
                                .addDisplacementMarker(()->{
                                    System.out.println("\nARM UP");
                                })
                                .waitSeconds(.1)
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nOPEN LEFT CLAW");
                                })
                                .waitSeconds(2)
                                .forward(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nARM DOWN");
                                    System.out.println("\nCLOSE CLAWS");
                                })
                                .waitSeconds(.1)

                                //TODO - Add Stacked Pixel Auto

                                //park
                                .splineToLinearHeading(new Pose2d(parkSpot.getX() + 16,parkSpot.getY()),parkSpot.getHeading())
                                .waitSeconds(.1)
                                .lineToLinearHeading(parkSpot)
                                .waitSeconds(100)
                                .build()
                );

        //TODO
        RoadRunnerBotEntity Blue_Left_AutoR = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPose)
                                //score purple pixel on spike mark
                                .addTemporalMarker(0,()->{
                                    System.out.println("\nWRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .waitSeconds(.1)
                                .lineToLinearHeading(PoseStorageCopy.rightSpikePoseBL)
                                .waitSeconds(.1)
                                .forward(7)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nWRIST DOWN");
                                })
                                .waitSeconds(.01)
                                .back(4)
                                .waitSeconds(.1)
                                .turn(Math.toRadians(-1e-6))
                                .addDisplacementMarker(()->{
                                    System.out.println("\nOPEN RIGHT CLAW");
                                })
                                .waitSeconds(1)
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nWRIST UP");
                                    System.out.println("CLOSE CLAWS");
                                })
                                .waitSeconds(.1)

                                //score on board
                                .lineToLinearHeading(PoseStorageCopy.rightBoardPoseB)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nARM UP");
                                })
                                .waitSeconds(.1)
                                .back(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nOPEN LEFT CLAW");
                                })
                                .waitSeconds(2)
                                .forward(5)
                                .addDisplacementMarker(()->{
                                    System.out.println("\nARM DOWN");
                                    System.out.println("\nCLOSE CLAWS");
                                })
                                .waitSeconds(.1)

                                //TODO - Add Stacked Pixel Auto

                                //park
                                .splineToLinearHeading(new Pose2d(parkSpot.getX() + 16,parkSpot.getY()),parkSpot.getHeading())
                                .waitSeconds(.1)
                                .lineToLinearHeading(parkSpot)
                                .waitSeconds(100)
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
