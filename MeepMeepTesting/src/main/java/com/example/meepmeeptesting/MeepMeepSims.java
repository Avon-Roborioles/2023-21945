package com.example.meepmeeptesting;

//imports
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import sun.jvm.hotspot.ui.DeadlockDetectionPanel;

public class MeepMeepSims {

    public static void main(String[] args){
        double trackWidth = 10.5;
        Pose2d startPoseRL;
        Pose2d startPoseRR;
        Pose2d startPoseBL;
        Pose2d startPoseBR;
        

        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity RL_Score_Plus = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), trackWidth)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(12, 62, Math.toRadians(270)))
                        .strafeLeft(45)
                        .build()

    }
}
