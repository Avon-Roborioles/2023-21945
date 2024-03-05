package org.firstinspires.ftc.teamcode.Autonomous.Tuned_Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park_Score_Plus.RR_Score_Plus;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="Blue Left Auto", group="auto")
//@Disabled
public class Blue_Left_Auto extends AutoBase {
    //copied poses from PoseStorage
    public Pose2d startPoseBL = PoseStorage.startPoseBL;
    public Pose2d leftSpikePose = PoseStorage.leftSpikePoseBL;
    public Pose2d middleSpikePose = PoseStorage.middleSpikePoseBL;
    public Pose2d rightSpikePose = PoseStorage.rightSpikePoseBL;
    public Pose2d checkPoint1 = PoseStorage.checkPoint1BL;
    public Pose2d checkPoint2 = PoseStorage.checkPoint2BL;
    public Pose2d leftBoardPose = PoseStorage.leftBoardPoseB;
    public Pose2d middleBoardPose = PoseStorage.middleBoardPoseB;
    public Pose2d rightBoardPose = PoseStorage.rightBoardPoseB;
    public Pose2d firstStack = PoseStorage.firstStackB;
    public Pose2d secondStack = PoseStorage.secondStackB;
    public Pose2d thirdStack = PoseStorage.thirdStackB;
    public Pose2d ParkSpot = PoseStorage.parkSpotBL;

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

        bot.setPoseEstimate(startPoseBL);
        PoseStorage.currentPose = startPoseBL;

        //TODO
        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(startPoseBL)
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
                .back(9)
                .addDisplacementMarker(()->{
                    //System.out.println("\nWRIST DOWN");
                    intake.wrist_down();
                })
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
                .build();
        //TODO
        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(startPoseBL)
//score purple pixel on spike mark
                .addTemporalMarker(0,()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .lineToLinearHeading(middleSpikePose)
                .waitSeconds(.1)
                .forward(2)
                .addDisplacementMarker(()->{
                    //System.out.println("\nWRIST DOWN");
                    intake.wrist_down();
                })
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

                .waitSeconds(.01)
                .strafeRight(11)
                .waitSeconds(.01)
                .build();
        //TODO
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(startPoseBL)
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
                .forward(7)
                .addDisplacementMarker(()->{
                    //System.out.println("\nWRIST DOWN");
                    intake.wrist_down();
                })
                .waitSeconds(.01)
                .back(4)
                .waitSeconds(.1)
                .turn(Math.toRadians(-1e-6))
                .addDisplacementMarker(()->{
                    //System.out.println("\nOPEN RIGHT CLAW");
                    intake.openClawV2(true,false);
                })
                .waitSeconds(1)
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .build();

        //TODO
        TrajectorySequence CheckPoint1 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .waitSeconds(.1)
                .lineToLinearHeading(checkPoint1)
                .build();

        //TODO
        TrajectorySequence CheckPoint2 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .waitSeconds(.1)
                .lineToLinearHeading(checkPoint2)
                .build();
        //TODO
        TrajectorySequence LeftBoardScore = bot.trajectorySequenceBuilder(LeftSpikeScore.end())
                .lineToLinearHeading(leftBoardPose)
                .addDisplacementMarker(()->{
                    //System.out.println("\nARM UP");
                    //arm.up();
                    intake.wrist_up();
                })
                .waitSeconds(.1)
                .back(5)
                .addDisplacementMarker(()->{
                   //System.out.println("\nOPEN LEFT CLAW");
                    intake.openClawV2(true,true);
                })
                .waitSeconds(2)
                .forward(5)
                .addDisplacementMarker(()->{
                    //System.out.println("\nARM DOWN");
                    //System.out.println("\nCLOSE CLAWS");
                    //arm.down();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .addDisplacementMarker(bot::updatePoseEstimate)
                .build();
        //TODO
        TrajectorySequence MiddleBoardScore = bot.trajectorySequenceBuilder(MiddleSpikeScore.end())
                .splineToLinearHeading(middleBoardPose,middleBoardPose.getHeading())
                .addDisplacementMarker(()->{
                    //System.out.println("\nARM UP");
                    //arm.up();
                    intake.wrist_up();
                })
                .waitSeconds(.1)
                .back(5)
                .addDisplacementMarker(()->{
                   // System.out.println("\nOPEN LEFT CLAW");
                    intake.openClawV2(true,true);
                })
                .waitSeconds(2)
                .forward(5)
                .addDisplacementMarker(()->{
//                    System.out.println("\nARM DOWN");
//                    System.out.println("\nCLOSE CLAWS");
                    //arm.down();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .addDisplacementMarker(bot::updatePoseEstimate)
                .build();

        //TODO -scores on the left side of the board
        TrajectorySequence RightBoardScore = bot.trajectorySequenceBuilder(RightSpikeScore.end())
                //score on board
                .lineToLinearHeading(rightBoardPose)
                .addDisplacementMarker(()->{
                    //System.out.println("\nARM UP");
                    //arm.up();
                })
                .waitSeconds(.1)
                .back(5)
                .addDisplacementMarker(()->{
                    //System.out.println("\nOPEN LEFT CLAW");
                    intake.openClawV2(true,true);
                })
                .waitSeconds(2)
                .forward(5)
                .addDisplacementMarker(()->{
//                    System.out.println("\nARM DOWN");
//                    System.out.println("\nCLOSE CLAWS");
                    //arm.down();
                    intake.closeClaws(true);
                })
                .waitSeconds(.1)
                .addDisplacementMarker(bot::updatePoseEstimate)
                .build();

        //TODO
//        TrajectorySequence thirdStack = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
//                .build();

        //TODO
        TrajectorySequence Park = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .waitSeconds(.01)
                //.splineToLinearHeading(new Pose2d(ParkSpot.getX() + 16,ParkSpot.getY()),ParkSpot.getHeading())
                .lineToLinearHeading(new Pose2d(ParkSpot.getX() + 16,ParkSpot.getY()))
//                .addDisplacementMarker(()->{
//                    //arm.down();
//                })
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

        //scores the purple preload pixel based on vision reading
        switch(propPosition){
            case "LEFT":
                currentState = State.LEFT_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(LeftSpikeScore);
                break;
            case "MIDDLE":
                currentState = State.MIDDLE_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(MiddleSpikeScore);
                break;
            case "RIGHT":
                currentState = State.RIGHT_SPIKE_SCORE;
                bot.followTrajectorySequenceAsync(RightSpikeScore);
                break;
        }

        while(opModeIsActive()){
            //UPDATES POSE ESTIMATE!!!
            bot.updatePoseEstimate();

            //FSM Logic
            switch (currentState){
                case LEFT_SPIKE_SCORE:
                case MIDDLE_SPIKE_SCORE:
                case RIGHT_SPIKE_SCORE:
                    // Check if the drive class isn't busy
                    // `isBusy() == true` while it's following the trajectory
                    // Once `isBusy() == false`, the trajectory follower signals that it is finished
                    // We move on to the next state
                    // Make sure we use the async follow function
                   if(!bot.isBusy()){
//                        currentState = State.CHECKPOINT2;
//                        bot.followTrajectorySequenceAsync(CheckPoint2);
                       switch(propPosition){
                           case "LEFT":
                               currentState = State.LEFT_BOARD_SCORE;
                               bot.followTrajectorySequenceAsync(LeftBoardScore);
                               break;
                           case "RIGHT":
                               currentState = State.RIGHT_BOARD_SCORE;
                               bot.followTrajectorySequenceAsync(RightBoardScore);
                               break;
                           default:
                               currentState = State.MIDDLE_BOARD_SCORE;
                               bot.followTrajectorySequenceAsync(MiddleBoardScore);
                               break;
                       }
                   }
                    break;
                case CHECKPOINT2:
                    if(!bot.isBusy()){
                        currentState = State.CHECKPOINT1;
                        bot.followTrajectorySequenceAsync(CheckPoint1);
                        //currentState = State.IDLE;
                    }
                    break;
                case CHECKPOINT1:
                    if(!bot.isBusy()){
                        //Done - switch to correct board score
                        switch(propPosition){
                            case "LEFT":
                                currentState = State.LEFT_BOARD_SCORE;
                                bot.followTrajectorySequenceAsync(LeftBoardScore);
                                break;
                            case "RIGHT":
                                currentState = State.RIGHT_BOARD_SCORE;
                                bot.followTrajectorySequenceAsync(RightBoardScore);
                                break;
                            default:
                                currentState = State.MIDDLE_BOARD_SCORE;
                                bot.followTrajectorySequenceAsync(MiddleBoardScore);
                                break;
                        }
                    }
                    break;
                case LEFT_BOARD_SCORE:
                case MIDDLE_BOARD_SCORE:
                case RIGHT_BOARD_SCORE:
                    if(!bot.isBusy()){
                        currentState = State.PARK;
                        bot.followTrajectorySequenceAsync(Park);
                    }
                    break;
                case PARK:
                    if(!bot.isBusy()){
                        currentState = State.IDLE;
                    }
                    break;
                case IDLE:
                    //Do nothing in IDLE except save position
                    //current state does not change once in IDLE
                    //This concludes the autonomous program
                    break;
            }

            //RoadRunner FSM Logic Control
            bot.update();

            //PID arm control
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
