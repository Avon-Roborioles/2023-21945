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

@Autonomous(name="Red Left Auto", group="auto")
//@Disabled
public class Red_Left_Auto extends AutoBase {
    //copied poses from PoseStorage
    public Pose2d startPoseRL = PoseStorage.startPoseRL;
    public Pose2d leftSpikePose = PoseStorage.leftSpikePoseRL;
    public Pose2d middleSpikePose = PoseStorage.middleSpikePoseRL;
    public Pose2d rightSpikePose = PoseStorage.rightSpikePoseRL;
    public Pose2d checkPoint1 = PoseStorage.checkPoint1RL;
    public Pose2d checkPoint2 = PoseStorage.checkPoint2RL;
    public Pose2d leftBoardPose = PoseStorage.leftBoardPoseR;
    public Pose2d middleBoardPose = PoseStorage.middleBoardPoseR;
    public Pose2d rightBoardPose = PoseStorage.rightBoardPoseR;
    public Pose2d firstStack = PoseStorage.firstStackR;
    public Pose2d secondStack = PoseStorage.secondStackR;
    public Pose2d thirdStack = PoseStorage.thirdStackR;
    public Pose2d ParkSpot = PoseStorage.parkSpotRL;

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
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop

        bot.setPoseEstimate(startPoseRL);
        PoseStorage.currentPose = startPoseRL;

        //TODO
        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(startPoseRL)
//close claw
                .addSpatialMarker(startPoseRL.vec(),() ->{
//                    System.out.println(" ");
//                    System.out.println("CLOSE CLAWS");
                    intake.closeClaws(true);
                    intake.wrist_up();
                })

                //score spike
                .lineToLinearHeading(leftSpikePose)
                .addDisplacementMarker(30,() ->{
//                    System.out.println(" ");
//                    System.out.println("WRIST DOWN");
                    intake.wrist_down();
                })
                .waitSeconds(.1)
                .back(11)
                .addDisplacementMarker(45,() ->{
//                    System.out.println(" ");
//                    System.out.println("OPEN LEFT CLAW");
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7) //wait to score spike
                .back(2) //move a bit so we don't pickup pixel again
                .waitSeconds(.1)
                .turn(1e-6)
                .addDisplacementMarker(()->{
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .build();
        //TODO
        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(startPoseRL)
//close claw
                .addTemporalMarker(0,() ->{
//                    System.out.println(" ");
//                    System.out.println("CLOSE CLAWS");
                    intake.closeClaws(true);
                    intake.wrist_up();
                })

                //score spike
                .lineToLinearHeading(middleSpikePose)
                .addDisplacementMarker(40,()->{
//                    System.out.println(" ");
//                    System.out.println("WRIST DOWN");
                    intake.wrist_down();
                })

                .waitSeconds(.1)

                .forward(5)
                .addDisplacementMarker(50,() ->{
//                    System.out.println(" ");
//                    System.out.println("OPEN LEFT CLAW");
                    intake.openClawV2(true,false);
                })
                .waitSeconds(1)

                .back(4)
                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("WRIST UP");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })
                .build();
        //TODO
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(startPoseRL)
                .addDisplacementMarker(() ->{
//                    System.out.println(" ");
//                    System.out.println("CLOSE CLAWS");
                    intake.wrist_up();
                    intake.closeClaws(true);
                })

                //score spike
                .lineToLinearHeading(rightSpikePose)
                .addDisplacementMarker(30,()->{
//                    System.out.println(" ");
//                    System.out.println("WRIST DOWN");
                    intake.wrist_down();
                })

                .waitSeconds(.1)

                .forward(5)
                .addDisplacementMarker(35,() ->{
//                    System.out.println(" ");
//                    System.out.println("OPEN LEFT CLAW");
                    intake.openClawV2(true,false);
                })
                .waitSeconds(1)

                .back(7)
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
        TrajectorySequence LeftBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .lineToLinearHeading(leftBoardPose)
                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("ARM UP");
                    arm.up();
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("OPEN RIGHT CLAW");
                    intake.openClawV2(true,true);
                })
                .waitSeconds(.7)
                .forward(5)
                .waitSeconds(.1)
                .build();
        //TODO
        TrajectorySequence MiddleBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                //score on Middle Area of board
                .lineToLinearHeading(middleBoardPose)
                .addDisplacementMarker(()->{
                    //System.out.println("ARM UP");
                    arm.up();
                })
                .waitSeconds(.7)
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("OPEN RIGHT CLAW");
                    intake.openClawV2(true,true);
                })
                .waitSeconds(.7)
                .forward(5)
                .addDisplacementMarker(()->{
                    arm.down();
                })
                .waitSeconds(.1)
                .build();

        //TODO -scores on the left side of the board
        TrajectorySequence RightBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                //score on left Area of board
                .lineToLinearHeading(rightBoardPose)
                .addDisplacementMarker(()->{
                    //System.out.println("ARM UP");
                    arm.up();
                })
                .waitSeconds(.1)
                .back(5)
                .addDisplacementMarker(165,()->{
//                    System.out.println(" ");
//                    System.out.println("OPEN RIGHT CLAW");
                    intake.openClawV2(true,true);
                })
                .waitSeconds(.7)
                .forward(5)
                .waitSeconds(.1)
                .build();

        //TODO
//        TrajectorySequence thirdStack = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
//                .build();

        //TODO
        TrajectorySequence Park = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .waitSeconds(.01)
                .splineToLinearHeading(new Pose2d(ParkSpot.getX() - 16,ParkSpot.getY()),ParkSpot.getHeading())
                .waitSeconds(.1)
                .lineToLinearHeading(ParkSpot)
                .build();


        waitForStart(); //**loops through code above until robot starts***************************

        if(isStopRequested()) return;

        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition, true);

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
                        currentState = State.CHECKPOINT1;
                        bot.followTrajectorySequenceAsync(CheckPoint1);
                    }
                    break;
                case CHECKPOINT2:
                    if(!bot.isBusy()){
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

//                        currentState = State.CHECKPOINT1;
//                        bot.followTrajectorySequenceAsync(CheckPoint1);
                        //currentState = State.IDLE;
                    }
                    break;
                case CHECKPOINT1:
                    if(!bot.isBusy()){
                        currentState = State.CHECKPOINT2;
                        bot.followTrajectorySequenceAsync(CheckPoint2);
                        //Done - switch to correct board score
//                        switch(propPosition){
//                            case "LEFT":
//                                currentState = State.LEFT_BOARD_SCORE;
//                                bot.followTrajectorySequenceAsync(LeftBoardScore);
//                                break;
//                            case "RIGHT":
//                                currentState = State.RIGHT_BOARD_SCORE;
//                                bot.followTrajectorySequenceAsync(RightBoardScore);
//                                break;
//                            default:
//                                currentState = State.MIDDLE_BOARD_SCORE;
//                                bot.followTrajectorySequenceAsync(MiddleBoardScore);
//                                break;
//                        }
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
