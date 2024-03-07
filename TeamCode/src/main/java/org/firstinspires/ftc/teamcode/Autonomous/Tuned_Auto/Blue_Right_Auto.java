package org.firstinspires.ftc.teamcode.Autonomous.Tuned_Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.concurrent.atomic.AtomicReference;

@Autonomous(name="Blue Right Auto", group="auto")
//@Disabled
public class Blue_Right_Auto extends AutoBase {
    //copied poses from PoseStorage
    public Pose2d startPoseBR = PoseStorage.startPoseBR;
    public Pose2d leftSpikePose = PoseStorage.leftSpikePoseBR;
    public Pose2d middleSpikePose = PoseStorage.middleSpikePoseBR;
    public Pose2d rightSpikePose = PoseStorage.rightSpikePoseBR;
    public Pose2d checkPoint1 = PoseStorage.checkPoint1BR;
    public Pose2d checkPoint2 = PoseStorage.checkPoint2BR;
    public Pose2d leftBoardPose = PoseStorage.leftBoardPoseB;
    public Pose2d middleBoardPose = PoseStorage.middleBoardPoseB;
    public Pose2d rightBoardPose = PoseStorage.rightBoardPoseB;
    public Pose2d firstStack = PoseStorage.firstStackB;
    public Pose2d secondStack = PoseStorage.secondStackB;
    public Pose2d thirdStack = PoseStorage.thirdStackB;
    public Pose2d ParkSpot = PoseStorage.parkSpotBR;

    //Auto Menu variables
    String checkPointType = "";
//    GamepadEx gamepad = new GamepadEx(gamepad1);
//
//    //auto menu code
//    ToggleButtonReader d_up = new ToggleButtonReader(
//            gamepad, GamepadKeys.Button.DPAD_UP
//    );
//    ToggleButtonReader d_down = new ToggleButtonReader(
//            gamepad, GamepadKeys.Button.DPAD_DOWN
//    );

    public void runOpMode() throws  InterruptedException{ //loop

        //Auto Menu
        //runAutoMenu(gamepad,d_up,d_down);
        //adjust auto parameters
//        if(selectedPath == AutoPath.DOWN){ //change auto path to down if selected in auto menu
//            checkPoint1 = PoseStorage.checkPoint1BL;
//            checkPoint2 = PoseStorage.checkPoint2BL;
//        }

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, false); //sets camera to start looking for prop

        bot.setPoseEstimate(startPoseBR);
        PoseStorage.currentPose = startPoseBR;
        AtomicReference<Pose2d> checkPoint2Start = new AtomicReference<>(new Pose2d());
        AtomicReference<Pose2d> parkStart = new AtomicReference<>(new Pose2d());

        //TODO
        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                //score purple pixel on spike mark
                .addTemporalMarker(0,()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .lineToLinearHeading(leftSpikePose)
                .waitSeconds(.1)
                .forward(7)
                .addDisplacementMarker(()->{
                    //System.out.println("\nWRIST DOWN");
                    intake.wrist_down();
                })
                .waitSeconds(.1)
                .back(5)
                .waitSeconds(.7)
                .turn(Math.toRadians(-1e-6))
                .addDisplacementMarker(()->{
                    //System.out.println("\nOPEN RIGHT CLAW");
                    intake.openClawV2(true,false);
                })
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .lineToLinearHeading(checkPoint2)
                .waitSeconds(.1)
                .lineToLinearHeading(checkPoint1)
                .waitSeconds(.1)

                //score on board
                .back(16)
                .waitSeconds(.1)
                .lineToLinearHeading(leftBoardPose)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nARM UP");
//                })
                .waitSeconds(.1)
                .back(5)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nOPEN LEFT CLAW");
//                })
                .waitSeconds(2)
                .forward(5)
//                .addDisplacementMarker(()->{
////                    System.out.println("\nARM DOWN");
////                    System.out.println("\nCLOSE CLAWS");
//                })
                .waitSeconds(.1)

                //TODO - Add Stacked Pixel Auto

                //park
                .splineToLinearHeading(new Pose2d(ParkSpot.getX() + 16,ParkSpot.getY()),ParkSpot.getHeading())
                .waitSeconds(.1)
                .lineToLinearHeading(ParkSpot)
                .build();
        //TODO
        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                //score purple pixel on spike mark
                .addTemporalMarker(0,()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .strafeRight(7)
                .waitSeconds(.1)
                .lineToLinearHeading(middleSpikePose)
                .waitSeconds(.1)
                .forward(2)
                .addDisplacementMarker(()->{
                   // System.out.println("\nWRIST DOWN");
                    intake.wrist_down();
                })
                .waitSeconds(.7)
                .turn(Math.toRadians(-1e-6))
                .addDisplacementMarker(()->{
                   // System.out.println("\nOPEN RIGHT CLAW");
                    intake.openClawV2(true,false);
                })
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })

                .waitSeconds(.01)
                .strafeLeft(11)
                .lineToLinearHeading(new Pose2d(checkPoint2.getX()+20,checkPoint2.getY(),checkPoint2.getHeading()))
                .waitSeconds(.01)

                .lineToLinearHeading(checkPoint2)
                .waitSeconds(.1)
                .lineToLinearHeading(checkPoint1)
                .waitSeconds(.1)

                //score on board
                .lineToLinearHeading(middleBoardPose)
//                .addDisplacementMarker(()->{
////                    System.out.println("\nARM UP");
//                })
                .waitSeconds(.1)
                .back(5)
//                .addDisplacementMarker(()->{
////                    System.out.println("\nOPEN LEFT CLAW");
//                })
                .waitSeconds(2)
                .forward(5)
//                .addDisplacementMarker(()->{
////                    System.out.println("\nARM DOWN");
////                    System.out.println("\nCLOSE CLAWS");
//                })
                .waitSeconds(.1)

                //TODO - Add Stacked Pixel Auto

                //park
                .splineToLinearHeading(new Pose2d(ParkSpot.getX() + 16,ParkSpot.getY()),ParkSpot.getHeading())
                .waitSeconds(.1)
                .lineToLinearHeading(ParkSpot)
                .build();
        //TODO
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                //score purple pixel on spike mark
                .addTemporalMarker(0,()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .lineToLinearHeading(rightSpikePose)
                .waitSeconds(.1)
                .back(10)
                .addDisplacementMarker(()->{
//                    System.out.println("\nWRIST DOWN");
                    intake.wrist_down();
                })
                .waitSeconds(.01)
                .turn(Math.toRadians(-1e-6))
                .addDisplacementMarker(()->{
                    //System.out.println("\nOPEN RIGHT CLAW");
                    intake.openClawV2(true,false);
                })
                .waitSeconds(1)
                .back(0.01)
                .addDisplacementMarker(()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .lineToLinearHeading(checkPoint2)
                .waitSeconds(.1)
                .lineToLinearHeading(checkPoint1)
                .waitSeconds(.1)

                //score on board
                .lineToLinearHeading(rightBoardPose)
//                .addDisplacementMarker(()->{
//                    System.out.println("\nARM UP");
//                })
                .waitSeconds(.1)
                .back(5)
//                .addDisplacementMarker(()->{
//                    System.out.println("\nOPEN LEFT CLAW");
//                })
                .waitSeconds(2)
                .forward(5)
//                .addDisplacementMarker(()->{
//                    System.out.println("\nARM DOWN");
//                    System.out.println("\nCLOSE CLAWS");
//                })
                .waitSeconds(.1)

                //TODO - Add Stacked Pixel Auto

                //park
                .splineToLinearHeading(new Pose2d(ParkSpot.getX() + 16,ParkSpot.getY()),ParkSpot.getHeading())
                .waitSeconds(.1)
                .lineToLinearHeading(ParkSpot)
                .build();

//
//        //TODO
//        TrajectorySequence LeftBoardScore = bot.trajectorySequenceBuilder(checkPoint1)
//                .waitSeconds(.01)
//                .back(16)
//                .waitSeconds(.1)
//                .lineToLinearHeading(leftBoardPose)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nARM UP");
//                })
//                .waitSeconds(.1)
//                .back(5)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nOPEN LEFT CLAW");
//                })
//                .waitSeconds(2)
//                .forward(5)
////                .addDisplacementMarker(()->{
//////                    System.out.println("\nARM DOWN");
//////                    System.out.println("\nCLOSE CLAWS");
////                    bot.updatePoseEstimate();
////                })
//                .waitSeconds(.1)
//                .addDisplacementMarker(()->{
//                    parkStart.set(bot.getPoseEstimate());
//                })
//                .build();
//        //TODO
//        TrajectorySequence MiddleBoardScore = bot.trajectorySequenceBuilder(checkPoint1)
//                .waitSeconds(.01)
//                .lineToLinearHeading(middleBoardPose)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nARM UP");
//                })
//                .waitSeconds(.1)
//                .back(5)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nOPEN LEFT CLAW");
//                })
//                .waitSeconds(2)
//                .forward(5)
////                .addDisplacementMarker(()->{
//////                    System.out.println("\nARM DOWN");
//////                    System.out.println("\nCLOSE CLAWS");
////                    bot.updatePoseEstimate();
////                })
//                .waitSeconds(.1)
//                .addDisplacementMarker(()->{
//                    parkStart.set(bot.getPoseEstimate());
//                })
//                .build();
//
//        //TODO -scores on the left side of the board
//        TrajectorySequence RightBoardScore = bot.trajectorySequenceBuilder(checkPoint1)
//                .lineToLinearHeading(rightBoardPose)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nARM UP");
//                })
//                .waitSeconds(.1)
//                .back(5)
//                .addDisplacementMarker(()->{
//                    //System.out.println("\nOPEN LEFT CLAW");
//                })
//                .waitSeconds(2)
//                .forward(5)
////                .addDisplacementMarker(()->{
//////                    System.out.println("\nARM DOWN");
//////                    System.out.println("\nCLOSE CLAWS");
////                    bot.updatePoseEstimate();
////                })
//                .waitSeconds(.1)
//                .addDisplacementMarker(()->{
//                    parkStart.set(bot.getPoseEstimate());
//                })
//                .build();

        //TODO
//        TrajectorySequence thirdStack = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
//                .build();


        //TODO - determine where bot is after scoring on board - will change later
       Pose2d parkStartSpot = new Pose2d();
//        switch(propPosition){
//            case "LEFT":
//                checkPoint2Start = LeftBoardScore.end();
//                break;
//            case "RIGHT":
//                checkPoint2Start = RightBoardScore.end();
//                break;
//            default:
//                checkPoint2Start = MiddleBoardScore.end();
//                break;
//        }

        TrajectorySequence Park = bot.trajectorySequenceBuilder(parkStart.get())
                .waitSeconds(.01)
                .splineToLinearHeading(new Pose2d(ParkSpot.getX() + 16,ParkSpot.getY()),ParkSpot.getHeading())
                .waitSeconds(.1)
                .lineToLinearHeading(ParkSpot)
                .build();

        while(!opModeIsActive()) {
            //gets propPosition and needed april tag from vision class
            propPosition = vision.getPropPosition();
            aprilTagID = vision.get_Apriltag_id(propPosition, true);

            //telemetry + Auto Menu
            telemetry.addData("Detected Prop Position: ", propPosition);
            telemetry.addData("Required April Tag: ", aprilTagID);

            telemetry.update(); //keep updating drivers with bot's detect prop, required tag, and auto menu
        }
        waitForStart(); //**loops through code above until robot starts***************************

        if(isStopRequested()) return;

        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition, true);
        //scores the purple preload pixel based on vision reading
        switch(propPosition){
            case "LEFT":
                currentState = State.LEFT_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(LeftSpikeScore);
                checkPoint2Start.set(LeftSpikeScore.end());
                break;
            case "MIDDLE":
                currentState = State.MIDDLE_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(MiddleSpikeScore);
                checkPoint2Start.set(MiddleSpikeScore.end());
                break;
            case "RIGHT":
                currentState = State.RIGHT_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(RightSpikeScore);
                checkPoint2Start.set(RightSpikeScore.end());
                break;
        }

        while(opModeIsActive()){

            //RoadRunner FSM Logic Control
            bot.update();

            //TODO - PID arm control - Need to retune arm
            //arm.update();

            //read pose
            Pose2d poseEstimate = bot.getPoseEstimate();

            //continuously write pose to 'PoseStorage'
            PoseStorage.currentPose = poseEstimate;

            //telemetry data
            telemetry.addData("Detected Prop Position: ", propPosition);
            telemetry.addData("Corresponding April Tag:",aprilTagID);
            telemetry.addData("Current Objective: ",currentState);
            telemetry.addData("X: ",poseEstimate.getX());
            telemetry.addData("Y: ",poseEstimate.getY());
            telemetry.addData("Heading: ",poseEstimate.getHeading());
            telemetry.update();
        }

    }
}
