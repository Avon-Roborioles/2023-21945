package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.acmerobotics.roadrunner.geometry.Pose2d;

/*
* a simple program to hold widely used poses on the field
* currentPose is a dynamic Pose2d variable for the robot's pose - often used to hand off robot's pose between opModes
* the other poses are final and are direct points on the field
 */
public class PoseStorage {
    public static Pose2d currentPose = new Pose2d();

    public static final Pose2d startPoseRL = new Pose2d(-34,-60.6,Math.toRadians(90));

    public static Pose2d startPoseRR = new Pose2d(12, -60.6, Math.toRadians(90));

    public static final Pose2d checkpointStack = new Pose2d(-36,-11,Math.toRadians(-180));

    public static final Pose2d checkpoint2 = new Pose2d(34,-11,Math.toRadians(-180));

    public static final Pose2d LeftSpikePoseRL = new Pose2d(-45,-28,Math.toRadians(-180));

    public static final Pose2d MiddleSpikePoseRL = new Pose2d(-36,-8,Math.toRadians(-90));

    public static final Pose2d RightSpikePoseRL = new Pose2d(-40,-30,Math.toRadians(0));

    public static final Pose2d ThirdStackR = new Pose2d(-54,checkpointStack.getY() + 2, checkpointStack.getHeading());

    public static final Pose2d LeftBoardPoseR = new Pose2d(39,-28,Math.toRadians(-180));

    public static final Pose2d MiddleBoardPoseR = new Pose2d(39,-34,Math.toRadians(-180));

    public static final Pose2d RightBoardPoseR = new Pose2d(39,-40,Math.toRadians(-180));

    public static Pose2d ParkSpotRR = new Pose2d(57,-59,Math.toRadians(-180));

}
