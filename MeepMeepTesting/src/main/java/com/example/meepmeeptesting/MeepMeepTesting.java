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
        // The max stuff I have tested:
        // 1 Bot:   - 2985
        // 36 Bots: - 2559

        MeepMeep meepMeep = new MeepMeep(200);

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

// ==============================================================================
// ==============================================================================

// ----------------
// BLUE
// ----------------

        // -------
        // PARK
        // -------

        // Right
        RoadRunnerBotEntity brightpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .strafeLeft(45)
                                .build()
                );

        // Left
        RoadRunnerBotEntity bleftpark = new DefaultBotBuilder(meepMeep)
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

// ==============================================================================
// ==============================================================================

        // ---------
        // RIGHT
        // ---------

        /// TOP ///
        RoadRunnerBotEntity bright1_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(49,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity bright2_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(12,32))
                                .lineToLinearHeading(new Pose2d(49,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity bright3_top = new DefaultBotBuilder(meepMeep)
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
        /// TOP ///

        /// BOTTOM ///
        RoadRunnerBotEntity bright1_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(49,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,62,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity bright2_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(12,32))
                                .lineToLinearHeading(new Pose2d(49,32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,62,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity bright3_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(12,32,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(12,50))
                                .lineToConstantHeading(new Vector2d(49, 50))
                                .lineToLinearHeading(new Pose2d(49, 32, Math.toRadians(0)))
                                .waitSeconds(0.1)
                                .lineToLinearHeading(new Pose2d(49,62,Math.toRadians(0)))
                                .build()
                );

        /// BOTTOM ///


        // ---------
        // RIGHT
        // ---------

// ==============================================================================
// ==============================================================================

        // -------
        // LEFT
        // -------

        /// ---
        // TOP
        /// ---

        /// PATH 1 ///
        RoadRunnerBotEntity bleft1_top_1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-36, 10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,10))
                                .lineToConstantHeading(new Vector2d(49,32))
                                .waitSeconds(0.3)
                                .lineToConstantHeading(new Vector2d(49, 60))
                                .build()
                );

        RoadRunnerBotEntity bleft2_top_1 = new DefaultBotBuilder(meepMeep)
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
        RoadRunnerBotEntity bleft3_top_1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))

                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(0)))

                                .lineToLinearHeading(new Pose2d(-36, 10, Math.toRadians(0)))

                                .lineToConstantHeading(new Vector2d(49,10))
                                .lineToConstantHeading(new Vector2d(49, 33))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, 60,Math.toRadians(0)))
                                .build()
                );
        /// PATH 1 ///

        /// PATH 2 ///
        RoadRunnerBotEntity bleft1_top_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-36, 10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,10))
                                .lineToConstantHeading(new Vector2d(49,32))
                                .lineToConstantHeading(new Vector2d(49, 12))
                                .build()
                );

        RoadRunnerBotEntity bleft2_top_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(-36,34))
                                .lineToLinearHeading(new Pose2d(-36, 10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,10))
                                .lineToConstantHeading(new Vector2d(49, 33))
                                .lineToLinearHeading(new Pose2d(49, 12,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity bleft3_top_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(-36, 10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,10))
                                .lineToConstantHeading(new Vector2d(49, 33))
                                .lineToLinearHeading(new Pose2d(49, 12,Math.toRadians(0)))
                                .build()
                );
        /// PATH 2 ///

        /// ---
        // TOP
        /// ---

        /// ---
        // BOTTOM
        /// ---

        /// PATH 1 ///
        RoadRunnerBotEntity bleft1_bottom_1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-36,32, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity bleft2_bottom_1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(-36,34))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity bleft3_bottom_1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .build()
                );
        /// PATH 1 ///

        /// PATH 2 ///
        RoadRunnerBotEntity bleft1_bottom_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-36,32, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, 12, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity bleft2_bottom_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToConstantHeading(new Vector2d(-36,34))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, 12, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity bleft3_bottom_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, 62, Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-36,34, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(-36,59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,32, Math.toRadians(0)))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, 12, Math.toRadians(0)))
                                .build()
                );
        /// PATH 2 ///

        /// ---
        // BOTTOM
        /// ---

        // -------
        // LEFT
        // -------

// ----------------
// BLUE
// ----------------

// ==============================================================================
// ==============================================================================

// ----------------
// RED
// ----------------

        // ---------
        // PARK
        // ---------

        // Right
        RoadRunnerBotEntity rrightpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .strafeRight(45)
                                .build()
                );

        // Left
        RoadRunnerBotEntity rleftpark = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .forward(50)
                                .strafeRight(90)
                                .build()
                );

        // ---------
        // PARK
        // ---------

// ==============================================================================
// ==============================================================================

        // --------------
        // RIGHT
        // --------------

        /// TOP ///
        RoadRunnerBotEntity rright1_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(12,-32,Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(49,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rright2_top = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(12,-32))
                                .lineToLinearHeading(new Pose2d(49,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-10,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rright3_top = new DefaultBotBuilder(meepMeep)
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
        /// TOP ///


        /// BOTTOM ///
        RoadRunnerBotEntity rright1_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(12,-32,Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(49,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-62,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rright2_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(12,-32))
                                .lineToLinearHeading(new Pose2d(49,-32,Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-62,Math.toRadians(0)))
                                .build()
                );
        RoadRunnerBotEntity rright3_bottom = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(12,-32,Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(12,-50))
                                .lineToConstantHeading(new Vector2d(49, -50))
                                .lineToLinearHeading(new Pose2d(49, -32, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-62,Math.toRadians(0)))

                                .build()
                );
        /// BOTTOM ///

        // ---------
        // RIGHT
        // ---------

// ==============================================================================
// ==============================================================================

        // ---------
        // LEFT
        // ---------

        /// ---
        // TOP
        /// ---

        /// PATH 1 ///
        RoadRunnerBotEntity rleft1_top_1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(180)))
                                .lineToConstantHeading(new Vector2d(-36, -13))
                                .lineToConstantHeading(new Vector2d(14, -13))
                                .lineToLinearHeading(new Pose2d(49,-13, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49, -34))
                                .waitSeconds(0.3)
                                .lineToConstantHeading(new Vector2d(49, -60))
                                .build()
                );

        RoadRunnerBotEntity rleft2_top_1 = new DefaultBotBuilder(meepMeep)
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

        RoadRunnerBotEntity rleft3_top_1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(-36, -13))
                                .lineToConstantHeading(new Vector2d(14, -13))
                                .lineToLinearHeading(new Pose2d(49,-13, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49, -34))
                                .waitSeconds(0.3)
                                .lineToConstantHeading(new Vector2d(49, -60))
                                .build()
                );
        /// PATH 1 ///

        /// PATH 2 ///
        RoadRunnerBotEntity rleft1_top_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(180)))
                                .lineToConstantHeading(new Vector2d(-36, -13))
                                .lineToConstantHeading(new Vector2d(14, -13))
                                .lineToLinearHeading(new Pose2d(49,-13, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49, -34))
                                .lineToConstantHeading(new Vector2d(49, -13))
                                .build()
                );

        RoadRunnerBotEntity rleft2_top_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToConstantHeading(new Vector2d(-36,-34))
                                .lineToLinearHeading(new Pose2d(-36, -10, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49,-10))
                                .lineToConstantHeading(new Vector2d(49, -33))
                                .lineToLinearHeading(new Pose2d(49, -13,Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity rleft3_top_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(-36, -13))
                                .lineToConstantHeading(new Vector2d(14, -13))
                                .lineToLinearHeading(new Pose2d(49,-13, Math.toRadians(0)))
                                .lineToConstantHeading(new Vector2d(49, -34))
                                .lineToConstantHeading(new Vector2d(49, -13))
                                .build()
                );
        /// PATH 2 ///

        /// ---
        // TOP
        /// ---

        /// ---
        // BOTTOM
        /// ---

        /// PATH 1 ///
        RoadRunnerBotEntity rleft1_bottom_1 = new DefaultBotBuilder(meepMeep)
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

        RoadRunnerBotEntity rleft2_bottom_1 = new DefaultBotBuilder(meepMeep)
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

        RoadRunnerBotEntity rleft3_bottom_1 = new DefaultBotBuilder(meepMeep)
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
        /// PATH 1 ///

        /// PATH 2 ///
        RoadRunnerBotEntity rleft1_bottom_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-32, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-36,-59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(0)))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, -13, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity rleft2_bottom_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-34, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(0)))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, -13, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity rleft3_bottom_2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -62, Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-36,-34, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(-36,-59, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49, -60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(0)))
                                .waitSeconds(0.3)
                                .lineToLinearHeading(new Pose2d(49, -13, Math.toRadians(0)))
                                .build()
                );
        /// PATH 2 ///

        /// ---
        // BOTTOM
        /// ---

        // ---------
        // LEFT
        // ---------

// ==============================================================================
// ==============================================================================

        // MAIN RUNNING / BOT CONFIG
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                .addEntity(bleft1_top_1)
                .addEntity(bleft2_top_1)
                .addEntity(bleft3_top_1)

                .addEntity(bleft1_top_2)
                .addEntity(bleft2_top_2)
                .addEntity(bleft3_top_2)

                .addEntity(bleft1_bottom_1)
                .addEntity(bleft2_bottom_1)
                .addEntity(bleft3_bottom_1)

                .addEntity(bleft1_bottom_2)
                .addEntity(bleft2_bottom_2)
                .addEntity(bleft3_bottom_2)

                .addEntity(bright1_bottom)
                .addEntity(bright2_bottom)
                .addEntity(bright3_bottom)

                .addEntity(bright1_top)
                .addEntity(bright2_top)
                .addEntity(bright3_top)

                .addEntity(rleft1_top_1)
                .addEntity(rleft2_top_1)
                .addEntity(rleft3_top_1)

                .addEntity(rleft1_top_2)
                .addEntity(rleft2_top_2)
                .addEntity(rleft3_top_2)

                .addEntity(rleft1_bottom_1)
                .addEntity(rleft2_bottom_1)
                .addEntity(rleft3_bottom_1)

                .addEntity(rleft1_bottom_2)
                .addEntity(rleft2_bottom_2)
                .addEntity(rleft3_bottom_2)

                .addEntity(rright1_bottom)
                .addEntity(rright2_bottom)
                .addEntity(rright3_bottom)

                .addEntity(rright1_top)
                .addEntity(rright2_top)
                .addEntity(rright3_top)



                .start();
    }
}