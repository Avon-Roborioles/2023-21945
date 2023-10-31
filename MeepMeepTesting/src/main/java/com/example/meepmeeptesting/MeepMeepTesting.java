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

        // -------
        // PARK
        // -------

        // Left
        RoadRunnerBotEntity bleftpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .strafeLeft(45)
                                .build()
                );

        // Right
        RoadRunnerBotEntity brightpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .forward(49)
                                .strafeLeft(90)
                                .build()
                );

        // -------
        // PARK
        // -------

        // -------
        // NEUTRAL
        // -------

        RoadRunnerBotEntity bleft1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(49,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity bleft2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(12,32))
                                .lineToLinearHeading(new Pose2d(49,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity bleft3 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(12,50))
                                .lineToConstantHeading(new Vector2d(49, 50))
                                .lineToLinearHeading(new Pose2d(49, 32, Math.toRadians(0)))
                                .waitSeconds(0.1)
                                .lineToLinearHeading(new Pose2d(49,10,Math.toRadians(0)))
                                .build()
                );

        // -------
        // NEUTRAL
        // -------

        // -------
        // TOP
        // -------

        RoadRunnerBotEntity bleft1_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(180)))
                                .lineToConstantHeading(new Vector2d(23, 34))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49, 60))
                                .build()
                );

        RoadRunnerBotEntity bleft3_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(-36,34))
                                .lineToLinearHeading(new Pose2d(-36, 10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,10))
                                .lineToConstantHeading(new Vector2d(49, 33))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, 60,Math.toRadians(0)))
                                .build()
                );



        // -------
        // TOP
        // -------

        // -------
        // BOTTOM
        // -------

        RoadRunnerBotEntity bleft1_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,32, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity bleft3_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .build()
                );

        // -------
        // BOTTOM
        // -------



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
                                .lineToLinearHeading(new Pose2d(49,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rright2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(12,-32))
                                .lineToLinearHeading(new Pose2d(49,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rright3 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(12,-32,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(12,-50))
                                .lineToConstantHeading(new Vector2d(49, -50))
                                .lineToLinearHeading(new Pose2d(49, -32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-10,Math.toRadians(0)))

                                .build()
                );

        RoadRunnerBotEntity rleft1_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(50,-32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(50, -60, Math.toRadians(180)))
                                .build()
                );

        RoadRunnerBotEntity rleft1_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-36,-59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rleft2_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(-36,-34))
                                .lineToLinearHeading(new Pose2d(-36, -10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,-10))
                                .lineToConstantHeading(new Vector2d(49, -33))
                                .waitSeconds(0.3)
                                //.lineToConstantHeading(new Vector2d(49, -60))
                                .lineToLinearHeading(new Pose2d(49, -60,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rleft2_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-34, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rleft3_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(-36,-34))
                                .lineToLinearHeading(new Pose2d(-36, -10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,-10))
                                .lineToConstantHeading(new Vector2d(49, -33))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, -60,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rleft3_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-34, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(-36,-59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.addEntity(bleftpark)
                //.addEntity(brightpark)
                //.addEntity(rrightpark)
                // .addEntity(rleftpark)
                .addEntity(bleft3)
                .start();
    }
}