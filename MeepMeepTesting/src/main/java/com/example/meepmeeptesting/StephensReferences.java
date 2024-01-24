package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class StephensReferences {
    public static void main(String[] args){
        MeepMeep meepMeep = new MeepMeep(600);

        Pose2d startPoseRR = new Pose2d(12,-60.6,Math.toRadians(90));

        Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));

        Pose2d currentPose = new Pose2d(0,0,Math.toRadians(90)); //Used in replacement of bot.getPoseEstimate()

        


        RoadRunnerBotEntity fieldReference = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .waitSeconds(10000)
                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(fieldReference)
                .start();
    }
}
