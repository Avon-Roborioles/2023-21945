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

        RoadRunnerBotEntity redLeftSpike1 = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                                .forward(30)
                                .turn(Math.toRadians(90))
                                .waitSeconds(1)
                                .strafeLeft(25)
                                .forward(22)
                                .strafeRight(22)
                                .waitSeconds(1)
                                .back(1.5)
                                .strafeLeft(5)
                                .forward(1.5)
                                .waitSeconds(1)
                                .back(1.5)
                                .strafeLeft(20)
                                .back(90.5)
                                .strafeRight(23)
                                .back(13)
                                .waitSeconds(3)
                                .strafeLeft(25)
                                .back(13)


                                .build()
                );
        RoadRunnerBotEntity redLeftSpike2 = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                                .forward(25)
                                .waitSeconds(1)
                                .back(4)
                                .turn(Math.toRadians(90))
                                .strafeLeft(25)
                                .forward(22)
                                .strafeRight(31)
                                .waitSeconds(1)
                                .back(1.5)
                                .strafeLeft(5)
                                .forward(1.5)
                                .waitSeconds(1)
                                .back(1.5)
                                .strafeLeft(20)
                                .back(90.5)
                                .strafeRight(23)
                                .back(13)
                                .waitSeconds(3)
                                .strafeLeft(25)
                                .back(13)


                                .build()
                );

        RoadRunnerBotEntity redLeftSpike3 = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                                .forward(30)
                                .turn(Math.toRadians(-90))
                                .waitSeconds(1)
                                .strafeRight(15)
                                .turn(Math.toRadians(180))
                                .strafeLeft(10)
                                .forward(22)
                                .strafeRight(22)
                                .waitSeconds(1)
                                .back(1.5)
                                .strafeLeft(5)
                                .forward(1.5)
                                .waitSeconds(1)
                                .back(1.5)
                                .strafeLeft(20)
                                .back(90.5)
                                .strafeRight(23)
                                .back(13)
                                .waitSeconds(3)
                                .strafeLeft(25)
                                .back(13)


                                .build()
                );

// ==============================================================================
// ==============================================================================


// ==============================================================================
// ==============================================================================

        // MAIN RUNNING / BOT CONFIG
        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                .addEntity(redLeftSpike3)




                .start();
    }
}