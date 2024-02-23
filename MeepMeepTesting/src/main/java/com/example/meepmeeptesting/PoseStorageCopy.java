package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class PoseStorageCopy {

    /*
     * a simple program to hold widely used poses on the field
     * currentPose is a dynamic Pose2d variable for the robot's pose - often used to hand off robot's pose between opModes
     * the other poses are final and are direct points on the field
     * When adjusting auto programs,MAKE SURE TO UPDATE POSE COORDINATES
     */
        public static Pose2d currentPose = new Pose2d();

        //TODO - Red Alliance Poses
        public static final Pose2d firstStackR = new Pose2d(-52,-11, Math.toRadians(-180));
        public static final Pose2d secondStackR = new Pose2d(-52,-23, Math.toRadians(-180));
        public static final Pose2d thirdStackR = new Pose2d(-52,-35, Math.toRadians(-180));
        public static final Pose2d leftBoardPoseR = new Pose2d(39,-28,Math.toRadians(-180));
        public static final Pose2d middleBoardPoseR = new Pose2d(39,-34,Math.toRadians(-180));
        public static final Pose2d rightBoardPoseR = new Pose2d(39,-40,Math.toRadians(-180));

        //Red Left
        public static final Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));
        public static final Pose2d leftSpikePoseRL = new Pose2d(-45,-28,Math.toRadians(-180));
        public static final Pose2d middleSpikePoseRL = new Pose2d(-36,-11,Math.toRadians(-90));
        public static final Pose2d rightSpikePoseRL = new Pose2d(-40,-30,Math.toRadians(0));
        public static final Pose2d checkPoint1RL = new Pose2d(-36,-11,Math.toRadians(-180));
        public static final Pose2d checkPoint2RL = new Pose2d(34,-11,Math.toRadians(-180));
        public static final Pose2d parkSpotRL = new Pose2d(57,-8,Math.toRadians(-180));

        //Red Right
        public static final Pose2d startPoseRR = new Pose2d(12, -60.6, Math.toRadians(90));
        //TODO - Spike Poses
        public static final Pose2d leftSpikePoseRR = new Pose2d(13,-35,Math.toRadians(-180));
        public static final Pose2d middleSpikePoseRR = new Pose2d(13,-11,Math.toRadians(-90));
        public static final Pose2d rightSpikePoseRR = new Pose2d(23,-30,Math.toRadians(-180));
        public static final Pose2d checkPoint1RR = new Pose2d(-36,-58.5,Math.toRadians(-180));
        public static final Pose2d checkPoint2RR = new Pose2d(15,-58.5,Math.toRadians(-180));
        public static final Pose2d parkSpotRR = new Pose2d(57,-59,Math.toRadians(-180));



        //TODO - Blue Alliance Poses
        public static final Pose2d firstStackB = new Pose2d(52,-11, Math.toRadians(0));
        public static final Pose2d secondStackB = new Pose2d(52,-23, Math.toRadians(0));
        public static final Pose2d thirdStackB = new Pose2d(52,-35, Math.toRadians(0));

        //TODO Blue Left
        public static final Pose2d startPoseBL = new Pose2d(-12,-60.6,Math.toRadians(90));

        //TODO Blue Right
        public static final Pose2d startPoseBR = new Pose2d(35,-60.6,Math.toRadians(90));













}
