package com.example.meepmeeptesting;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

//        RoadRunnerBotEntity myFirstBot = new DefaultBotBuilder(meepMeep)
//                // We set this bot to be blue
//                .setColorScheme(new ColorSchemeBlueDark())
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .build()
//                );
        // ----------------
        // BLUE
        // ----------------
        RoadRunnerBotEntity bleftpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .strafeLeft(45)
                                .build()
                );

        RoadRunnerBotEntity brightpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .forward(50)
                                .strafeLeft(90)
                                .build()
                );
        RoadRunnerBotEntity bleft1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(50,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50,10,Math.toRadians(180)))
                                .build()
                );
        RoadRunnerBotEntity bleft2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(12,32))
                                .lineToLinearHeading(new Pose2d(50,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50,10,Math.toRadians(180)))
                                .build()
                );
        RoadRunnerBotEntity bleft3 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(12,50))
                                .lineToConstantHeading(new Vector2d(50, 50))
                                .lineToLinearHeading(new Pose2d(50, 32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50,10,Math.toRadians(180)))

                                .build()
                );
        // ----------------
        // RED
        // ----------------
        RoadRunnerBotEntity rrightpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .strafeRight(45)
                                .build()
                );

        RoadRunnerBotEntity rleftpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .forward(50)
                                .strafeRight(90)
                                .build()
                );
        RoadRunnerBotEntity rright1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(12,-32,Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(50,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50,-10,Math.toRadians(180)))
                                .build()
                );
        RoadRunnerBotEntity rright2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(12,-32))
                                .lineToLinearHeading(new Pose2d(50,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50,-10,Math.toRadians(180)))
                                .build()
                );
        RoadRunnerBotEntity rright3 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(12,-32,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(12,-50))
                                .lineToConstantHeading(new Vector2d(50, -50))
                                .lineToLinearHeading(new Pose2d(50, -32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50,-10,Math.toRadians(180)))

                                .build()
                );
        RoadRunnerBotEntity rleft1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .forward(50)
                                .strafeRight(90)
                                .build()
                );
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
//                .addEntity(bleftpark)
//                .addEntity(brightpark)
//                .addEntity(rrightpark)
//                .addEntity(rleftpark)
                .addEntity(bleft3)
                .start();
    }
}