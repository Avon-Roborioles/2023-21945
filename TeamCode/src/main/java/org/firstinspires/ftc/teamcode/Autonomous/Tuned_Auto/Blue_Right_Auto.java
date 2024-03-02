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
    GamepadEx gamepad = new GamepadEx(gamepad1);

    //auto menu code
    ToggleButtonReader d_up = new ToggleButtonReader(
            gamepad, GamepadKeys.Button.DPAD_UP
    );
    ToggleButtonReader d_down = new ToggleButtonReader(
            gamepad, GamepadKeys.Button.DPAD_DOWN
    );

    public void runOpMode() throws  InterruptedException{ //loop

        //Auto Menu
        runAutoMenu(gamepad,d_up,d_down);
        //adjust auto parameters
        if(selectedPath == AutoPath.DOWN){
            checkPoint1 = PoseStorage.checkPoint1BL;
            checkPoint2 = PoseStorage.checkPoint2BL;
        }

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, false); //sets camera to start looking for prop

        bot.setPoseEstimate(startPoseBR);
        PoseStorage.currentPose = startPoseBR;

        //TODO
        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                //score purple pixel on spike mark
                .addTemporalMarker(0,()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                })
                .waitSeconds(.1)
                .lineToLinearHeading(leftSpikePose)
                .waitSeconds(.1)
                .forward(7)
                .addDisplacementMarker(()->{
                    //System.out.println("\nWRIST DOWN");
                })
                .waitSeconds(.1)
                .back(5)
                .waitSeconds(.7)
                .turn(Math.toRadians(-1e-6))
                .addDisplacementMarker(()->{
                    //System.out.println("\nOPEN RIGHT CLAW");
                })
                .back(5)
                .addDisplacementMarker(()->{
//                    System.out.println("\nWRIST UP");
//                    System.out.println("CLOSE CLAWS");
                })
                .waitSeconds(.1)
                .build();
        //TODO
        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                .build();
        //TODO
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
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
                .build();
        //TODO
        TrajectorySequence MiddleBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO -scores on the left side of the board
        TrajectorySequence RightBoardScore = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence thirdStack = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence Park = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,true);

        //telemetry + Auto Menu
        telemetry.addData("Detected Prop Position: ", propPosition);
        telemetry.addData("Required April Tag: ", aprilTagID);

        telemetry.update();

        waitForStart(); //**loops through code above*********************************

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
                        currentState = State.CHECKPOINT2;
                        bot.followTrajectorySequenceAsync(CheckPoint2);
                    }
                    break;
                case CHECKPOINT2:
                    if(!bot.isBusy()){
                        currentState = State.CHECKPOINT1;
                        bot.followTrajectorySequenceAsync(CheckPoint1);
                    }
                    break;
                case CHECKPOINT1:
                    if(!bot.isBusy()){
                        //TODO - switch to correct board score
                    }
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
            arm.update();

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
