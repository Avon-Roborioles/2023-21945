//package org.firstinspires.ftc.teamcode.Autonomous.Park_Score_Plus;
//
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.acmerobotics.roadrunner.ftc.Actions;
//import com.arcrobotics.ftclib.util.Timing;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
//
//import java.util.Vector;
//import java.util.concurrent.TimeUnit;
//
//@Autonomous(name="RR Score Plus", group="Park + Score")
//public class RR_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
//    Timing.Timer autoTimer = new Timing.Timer(30, TimeUnit.SECONDS);
//
//    int CycleCount = 0;
//     TrajectoryActionBuilder quickStrafe = null;
//
//     /*TODO - uncomment if currentPose object can't be used in certain cases
//    double Xaxis = 0;
//    double Yaxis = 0;
//    double heading = Math.toRadians(0);
//*/
//    //defined poses on the field to use
//    public Pose2d startPose = new Pose2d(0,0,Math.toRadians(90));
//    public Vector2d checkPoint1 = new Vector2d(20,20);
//    public Vector2d checkPoint2 = new Vector2d(-55,15);
//    public Vector2d LeftBoardPose = new Vector2d(45,20); //TODO Update These Board Poses
//    public Pose2d MiddleBoardPose = new Pose2d(45,15,Math.toRadians(180));
//    public Pose2d RightBoardPose = new Pose2d(45, 10,Math.toRadians(180));
//
//
//
//    MecanumDrive bot = new MecanumDrive(hardwareMap, startPose);
//
//    //easy to use methods that update bot pose AND return added/subtracted pose value
//    public double poseX(double distance){
//        bot.updatePoseEstimate();
//        return bot.pose.position.x + distance;
//    }
//
//    public double poseY(double distance){
//        bot.updatePoseEstimate();
//        return bot.pose.position.y + distance;
//    }
//
//    public double poseHeading(double degrees){
//        bot.updatePoseEstimate();
//        return bot.pose.heading.toDouble() + Math.toRadians(degrees);
//    }
//
//
//    //Done - used to move robot to available stack
//    public void QuickStrafe(double distance){
//        quickStrafe = bot.actionBuilder(bot.pose)
//                .lineToYConstantHeading(poseY(distance));
//        Actions.runBlocking(quickStrafe);
//    }
//
//    public void runOpMode() throws InterruptedException {
//        //important variables for auto - set to random values
//        String propPosition;
//        int aprilTagID;
//
//        init_classes(); //init robot functions
//
//        TrajectoryActionBuilder LeftSpikeScore = bot.actionBuilder(bot.pose)
//                //get to left spike
//                .afterTime(0,() -> intake.openClaws(false))
//                .lineToXConstantHeading(poseX(5))
//                .waitSeconds(.1)
//                .lineToYLinearHeading(poseY(20),poseHeading(90))
//                .waitSeconds(.1)
//
//                //move prop
//                .lineToXConstantHeading(poseX(-10))
//                .waitSeconds(.1)
//                .lineToXConstantHeading(poseX(10.25))
//
//                //score yellow pixel
//                .afterTime(7,() -> intake.wrist_down())
//                .afterTime(7.5, () -> intake.openClawV2(true, true))
//                .afterTime(8, () -> intake.wrist_up());
//
//
//
//        TrajectoryActionBuilder MiddleSpikeScore = bot.actionBuilder(bot.pose)
//                //get to middle spike
//                .afterTime(0, () -> intake.openClaws(false))
//                .lineToYConstantHeading(poseY(24))
//                .waitSeconds(.1)
//                .lineToYConstantHeading(poseY(-8.5))
//
//                //score yellow pixel
//                .afterTime(7,() -> intake.wrist_down())
//                .afterTime(7.5, () -> intake.openClawV2(true, true))
//                .afterTime(8, () -> intake.wrist_up());
//
//        TrajectoryActionBuilder RightSpikeScore = bot.actionBuilder(bot.pose)
//                //get to right spike
//                .lineToYLinearHeading(poseY(20),poseHeading(-90))
//                .waitSeconds(.1)
//
//                //move prop
//                .lineToXConstantHeading(poseX(10))
//                .waitSeconds(.1)
//                .lineToXConstantHeading(poseX(-10.5))
//
//                //score yellow pixel
//                .afterTime(7,() -> intake.wrist_down())
//                .afterTime(7.5, () -> intake.openClawV2(true, true))
//                .afterTime(8, () -> intake.wrist_up());
//
//        TrajectoryActionBuilder PreloadScore1 = bot.actionBuilder(bot.pose)
//                //get to backboard
//                .splineToConstantHeading(LeftBoardPose,poseHeading(0))
//                .waitSeconds(.1)
//
//                //score purple pixel
//                .afterTime(3,() -> arm.up_auto()) //arm up
//                .afterTime(4,() -> intake.openClawV2(true,false)) //open claw
//                .afterTime(4.5,() -> arm.down_auto()); //arm down
//
//
//        TrajectoryActionBuilder PreloadScore2 = bot.actionBuilder(bot.pose)
//                //get to backboard
//                .splineToLinearHeading(MiddleBoardPose,poseHeading(0))
//                .waitSeconds(.1)
//
//                //score purple pixel
//                .afterTime(3,() -> arm.up_auto()) //arm up
//                .afterTime(4,() -> intake.openClawV2(true,false)) //open claw
//                .afterTime(4.5,() -> arm.down_auto()); //arm down
//
//        TrajectoryActionBuilder PreloadScore3 = bot.actionBuilder(bot.pose)
//                //get to backboard
//                .splineToLinearHeading(RightBoardPose,poseHeading(0))
//                .waitSeconds(.1)
//
//                //score purple pixel
//                .afterTime(3,() -> arm.up_auto()) //arm up
//                .afterTime(4,() -> intake.openClawV2(true,false)) //open claw
//                .afterTime(4.5,() -> arm.down_auto()); //arm down
//
//        TrajectoryActionBuilder CheckPoint1 = bot.actionBuilder(bot.pose)
//                .splineToConstantHeading(checkPoint1, poseHeading(0));
//
//        TrajectoryActionBuilder CheckPoint2 = bot.actionBuilder(bot.pose)
//                .splineToConstantHeading(checkPoint2, poseHeading(0));
//
//
//
//        //returns to Checkpoint #1
//        TrajectoryActionBuilder get2StackedPixels = bot.actionBuilder(bot.pose); //TODO
//
//        TrajectoryActionBuilder ScorePixels = bot.actionBuilder(bot.pose); //TODO
//
//
//        TrajectoryActionBuilder Park = bot.actionBuilder(bot.pose); //TODO
//
//
//        //-------------------------------------------------------------------------------------------
//
//        waitForStart();
//
//        propPosition = vision.getPropPosition(); //get latest prop position
//        aprilTagID = vision.get_Apriltag_id(propPosition, false); //choose apriltag based on prop position
//
//        telemetry.addData("Prop Position: ", propPosition);
//        telemetry.addData("AprilTag ID: ", aprilTagID);
//        telemetry.update();
//
//        //scores purple pixel on spike based on vision reading
//        switch(propPosition){
//            case "LEFT":
//                Actions.runBlocking(LeftSpikeScore);
//                telemetry.addData("Left Pixel:  ", "SCORED");
//                break;
//            case "MIDDLE":
//                Actions.runBlocking(MiddleSpikeScore);
//                telemetry.addData("Middle Pixel:  ", "SCORED");
//                break;
//            case "RIGHT":
//                Actions.runBlocking(RightSpikeScore);
//                telemetry.addData("Right Pixel:  ", "SCORED");
//                break;
//        }
//        telemetry.update();
//
//        //score yellow pixel on board based on vision reading
//        switch (aprilTagID) {
//            case 4:
//                Actions.runBlocking(PreloadScore1);
//                telemetry.addData("Left Board Region:  ", "SCORED");
//                break;
//            case 5:
//                Actions.runBlocking(PreloadScore2);
//                telemetry.addData("Middle Board Region:  ", "SCORED");
//                break;
//            case 6:
//                Actions.runBlocking(PreloadScore3);
//                telemetry.addData("Right Board Region:  ", "SCORED");
//                break;
//        }
//        telemetry.update();
//
//        //getting ready to detect stacked pixels/april tags
//        vision.init_stack_detection(hardwareMap);
//        vision.initAprilTag();
//
//       //go to scoring checkpoint #1 (scoring region)
//        Actions.runBlocking(CheckPoint1);
//
//        //go to scoring checkpoint #2 (retrieving region)
//        Actions.runBlocking(CheckPoint2);
//
//        //strafes to 1 of 2 available stacks
//        double strafeDistance = vision.getStrafeDistance();
//        QuickStrafe(strafeDistance);
//
//        Actions.runBlocking(get2StackedPixels);   //FIRST Cycle Round
//
//        CycleCount++; //used in "get2StackedPixels" Sequence to determine arm/claw poses
//
//        Actions.runBlocking(CheckPoint2);
//
//        Actions.runBlocking(CheckPoint1);
//
//        //Done - check for board availability (able to see at least 2 tags)
//        boolean boardAvailable = vision.BoardIsAvailable();
//
//        telemetry.addData("Board Available: ", boardAvailable);
//        telemetry.update();
//
//        while(!boardAvailable){ //stops bot until board is available
//            if(autoTimer.remainingTime() >= 5){ //checks if bot has enough time to park
//                boardAvailable = vision.BoardIsAvailable();
//                telemetry.update();
//            } else {
//                boardAvailable = true; //passes board check to have time to park
//            }
//        }
//
//        if(autoTimer.remainingTime() >= 5){
//            Actions.runBlocking(ScorePixels);
//            Actions.runBlocking(CheckPoint1); //returns to CheckPoint #1
//        } else {
//            Actions.runBlocking(Park);
//        }
//
//        if(autoTimer.remainingTime() >= 10){ //retrieving pixels needs more time
//            Actions.runBlocking(CheckPoint2);
//
//            strafeDistance = vision.getStrafeDistance(); //gets
//            QuickStrafe(strafeDistance);
//            Actions.runBlocking(get2StackedPixels); //SECOND Cycle Round
//            Actions.runBlocking(CheckPoint2);
//            Actions.runBlocking(CheckPoint1);
//        } else {
//            Actions.runBlocking(Park);
//        }
//
//        Actions.runBlocking(Park); //parks in case it hasn't already
//    }
//}