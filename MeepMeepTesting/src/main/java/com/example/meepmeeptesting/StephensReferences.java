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

   public Pose2d startPoseRR = new Pose2d(12,-60.6,Math.toRadians(90));

    public static Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));

    static double currentX = 0;
    static double currentY = 0;
    static double currentHeading = Math.toRadians(90);

    static Pose2d currentPose = new Pose2d(currentX,currentY,currentHeading); //Used in replacement of bot.getPoseEstimate()

    static void updatePoseEstimate(double x, double y,double heading){ //Used in replacement of bot.updatePoseEstimate()
        currentPose.plus(new Pose2d(x,y,heading));
        currentX = currentPose.getX();
        currentY = currentPose.getY();
        currentHeading = currentPose.getHeading();
    }

    static double poseX(double distance){
        currentX += distance;
        updatePoseEstimate(currentX,currentY,currentHeading);
        return currentX;
    }

    static double poseY(double distance){
        currentY += distance;
        updatePoseEstimate(currentX,currentY,currentHeading);
        return currentY;
    }

    static double poseHeading(double distance){
        currentHeading += distance;
        updatePoseEstimate(currentX,currentY,currentHeading);
        return currentY;
    }

    public static void main(String[] args){
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity fieldReference = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPoseRL)
                                .lineToLinearHeading(new Pose2d(poseX(10),poseY(10),poseHeading(Math.toRadians(90))))
                                .waitSeconds(10000)
                                .build()
                );


        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                //program to run
                .addEntity(fieldReference)

                .start();
    }
}
