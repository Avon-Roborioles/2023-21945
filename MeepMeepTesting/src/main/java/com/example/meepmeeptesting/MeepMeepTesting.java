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

        RoadRunnerBotEntity blueRight = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, 60, Math.toRadians(270)))
                                .forward(24)
                                .waitSeconds(2)
                                .turn(Math.toRadians(-90))
                                .back(82)
                                .waitSeconds(2)
                                .forward(100)
                                .strafeLeft(23)
                                .forward(4)
                                .waitSeconds(2)
                                .back(3)
                                .strafeLeft(3)
                                .forward(3)
                                .waitSeconds(2)
                                .back(3)
                                .strafeRight(25)
                                .back(100)
                                .waitSeconds(2)
                                .strafeRight(25)
                                .back(13)

                                .build()
                );

        RoadRunnerBotEntity blueLeft = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(10, 60, Math.toRadians(270)))
                                .forward(23)
                                .waitSeconds(2)
                                .turn(Math.toRadians(-90))
                                .back(37)
                                .waitSeconds(2)
                                .forward(105)
                                .waitSeconds(2)
                                .back(3)
                                .strafeLeft(3)
                                .forward(3)
                                .waitSeconds(2)
                                .back(3)
                                .strafeRight(3)
                                .back(105)
                                .waitSeconds(2)
                                .forward(5)
                                .strafeRight(25)
                                .back(15)
                                .build()
                );
        RoadRunnerBotEntity redRight = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(10, -60, Math.toRadians(90)))
                                .forward(25)
                                .waitSeconds(2)
                                .turn(Math.toRadians(90))
                                .back(37)
                                .waitSeconds(2)
                                .forward(105)
                                .waitSeconds(2)
                                .back(3)
                                .strafeLeft(3)
                                .forward(3)
                                .waitSeconds(2)
                                .back(3)
                                .strafeRight(3)
                                .back(102)
                                .waitSeconds(2)
                                .forward(5)
                                .strafeLeft(25)
                                .back(17)

                                .build()
                );
        RoadRunnerBotEntity redLeft = new DefaultBotBuilder(meepMeep)
                // We set this bot to be blue
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, -60, Math.toRadians(90)))
                                .forward(24)
                                .waitSeconds(2)
                                .turn(Math.toRadians(90))
                                .back(82)
                                .waitSeconds(2)
                                .forward(100)
                                .strafeRight(23)
                                .forward(4)
                                .waitSeconds(2)
                                .back(3)
                                .strafeRight(3)
                                .forward(3)
                                .waitSeconds(2)
                                .back(3)
                                .strafeLeft(25)
                                .back(100)
                                .waitSeconds(2)
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

                .addEntity(blueRight)




                .start();
    }
}