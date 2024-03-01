package org.firstinspires.ftc.teamcode.Autonomous.Tuned_Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
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
    public Pose2d leftBoardPose = PoseStorage.leftBoardPoseB;
    public Pose2d middleBoardPose = PoseStorage.middleBoardPoseB;
    public Pose2d rightBoardPose = PoseStorage.rightBoardPoseB;
    public Pose2d firstStack = PoseStorage.firstStackB;
    public Pose2d secondStack = PoseStorage.secondStackB;
    public Pose2d thirdStack = PoseStorage.thirdStackB;

    public void runOpMode() throws  InterruptedException{
        GamepadEx gamepad = new GamepadEx(gamepad1);

        //auto menu code
        ToggleButtonReader d_up = new ToggleButtonReader(
                gamepad, GamepadKeys.Button.DPAD_UP
        );
        ToggleButtonReader d_down = new ToggleButtonReader(
                gamepad, GamepadKeys.Button.DPAD_DOWN
        );
        runAutoMenu(gamepad,d_up,d_down);


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
                .build();
        //TODO
        TrajectorySequence MiddleSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                .build();
        //TODO
        TrajectorySequence RightSpikeScore = bot.trajectorySequenceBuilder(startPoseBR)
                .build();

        //TODO
        TrajectorySequence CheckPoint1 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence CheckPoint2 = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();
        //TODO
        TrajectorySequence Middle_Preload = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();
        //TODO
        TrajectorySequence Right_Preload = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO -scores on the left side of the board
        TrajectorySequence Board_Score = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence Pixel_Stack = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //TODO
        TrajectorySequence Park = bot.trajectorySequenceBuilder(bot.getPoseEstimate())
                .build();

        //gets propPosition and needed april tag from vision class
        propPosition = vision.getPropPosition();
        aprilTagID = vision.get_Apriltag_id(propPosition,true);

        telemetry.addData("Detected Prop Position: ", propPosition);
        telemetry.addData("Required April Tag: ", aprilTagID);
        telemetry.update();

        waitForStart(); // loops through code above

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


    }
}
