package org.firstinspires.ftc.teamcode.Autonomous.Tuned_Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="Red Right Auto", group="auto")
//@Disabled
public class Red_Right_Auto extends AutoBase {
    //copied poses from PoseStorage
    public Pose2d startPoseRR = PoseStorage.startPoseRR;
    public Pose2d leftSpikePose = PoseStorage.leftSpikePoseRR;
    public Pose2d middleSpikePose = PoseStorage.middleSpikePoseRR;
    public Pose2d rightSpikePose = PoseStorage.rightSpikePoseRR;
    public Pose2d checkPoint1 = PoseStorage.checkPoint1RR;
    public Pose2d checkPoint2 = PoseStorage.checkPoint2RR;
    public Pose2d leftBoardPose = PoseStorage.leftBoardPoseR;
    public Pose2d middleBoardPose = PoseStorage.middleBoardPoseR;
    public Pose2d rightBoardPose = PoseStorage.rightBoardPoseR;
    public Pose2d firstStack = PoseStorage.firstStackR;
    public Pose2d secondStack = PoseStorage.secondStackR;
    public Pose2d thirdStack = PoseStorage.thirdStackR;
    public Pose2d ParkSpot = PoseStorage.parkSpotRR;

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

        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        //important variables for auto - set to random values
        String propPosition = "LEFT";
        int aprilTagID = 5;

        init_classes(); //initiates robot functions
        vision.init_prop_detection(hardwareMap, true); //sets camera to start looking for prop

        bot.setPoseEstimate(startPoseRR);
        PoseStorage.currentPose = startPoseRR;

        //TODO
        TrajectorySequence LeftSpikeScore = bot.trajectorySequenceBuilder(startPoseRR)
                //close claw
                .addTemporalMarker(0,()->{
                    intake.wrist_up();
                    intake.closeClaws(true);
                })

                //score spike
                .strafeRight(3)
                .waitSeconds(.1)
                .lineToLinearHeading(leftSpikePose)
                .waitSeconds(.1)
                .turn(1e-6)
                .addDisplacementMarker(()->{
                    intake.wrist_down();
                })
                .waitSeconds(.1)
                .forward(7)
                .waitSeconds(.7)
                .back(5)
                .waitSeconds(.1)
                .turn(1e-6)
                .addDisplacementMarker(() ->{
//                    System.out.println(" ");
//                    System.out.println("OPEN LEFT CLAW");
                    intake.openClawV2(true,false);
                })
                .waitSeconds(.7) //wait to score spike

                //score on left Area of board
                .lineToLinearHeading(leftBoardPose)
                .addDisplacementMarker(()->{
                    intake.closeClaws(true);
                    intake.wrist_up();
                })
                .waitSeconds(.7)
                .back(5)
//                .addDisplacementMarker(()->{
//                    System.out.println(" ");
//                    System.out.println("OPEN RIGHT CLAW");
//                })
                .waitSeconds(.7)
                .forward(5)
                .waitSeconds(.1)

                //park
                .lineToLinearHeading(new Pose2d(ParkSpot.getX() - 10,ParkSpot.getY(), ParkSpot.getHeading()))
                .waitSeconds(.1)
                .lineToLinearHeading(ParkSpot)
                .build();
        //TODO
        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(startPoseRR)

                .build();
        //TODO
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(startPoseRR)

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
