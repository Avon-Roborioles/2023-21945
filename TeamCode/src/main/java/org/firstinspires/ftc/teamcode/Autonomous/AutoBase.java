package org.firstinspires.ftc.teamcode.Autonomous;
//Added Default Class for Basic Auto Functions (Used throughout all Autonomous Programs

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Untuned_Auto.Park_Score_Plus.RR_Score_Plus;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
import org.firstinspires.ftc.teamcode.Tests.AutoMenu;

public class AutoBase extends LinearOpMode {
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake intake = new Intake();
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision vision = new Camera_Vision();

    public enum AutoPath {
        UP,
        DOWN
    }

    //this enum defines our state
    public enum State {
        LEFT_SPIKE_SCORE,
        MIDDLE_SPIKE_SCORE,
        RIGHT_SPIKE_SCORE,
        CHECKPOINT1,
        CHECKPOINT2,
        LEFT_BOARD_SCORE,
        MIDDLE_BOARD_SCORE,
        RIGHT_BOARD_SCORE,
        PARK,
        WAIT,
        IDLE
    }

    //define current state the robot is on
    public State currentState = State.IDLE;

    public AutoPath selectedPath = AutoPath.UP;
    public boolean BoardScore = true;

    @Override
    public void runOpMode() throws InterruptedException {
    }

    public void init_classes(){
        //arm.init_arm_main(hardwareMap, "leftMotor", "rightMotor", true);
        arm.init_arm_auto(hardwareMap,"leftMotor", "rightMotor");
        intake.init_intake_V2(hardwareMap, "claw1", "claw2","wrist");
        vision.init_cameras(hardwareMap, "Webcam 1", "Webcam 2");
    }

    public void runAutoMenu(GamepadEx gamepad, ToggleButtonReader d_up, ToggleButtonReader d_down){
        if(d_up.getState()){ //Auto Path selection
            selectedPath = AutoPath.UP;
        } else {
            selectedPath = AutoPath.DOWN;
        }

        BoardScore = d_down.getState(); //Board Score Selection

        //menu
        telemetry.addLine("Select the Auto Path by Toggling the D-pad Up Button");
        telemetry.addData("Current Auto Path Selected: ", selectedPath);
        telemetry.addLine(" ");
        telemetry.addLine("Select Board Scoring by Toggling the D-pad Down Button");
        telemetry.addData("Selected Board Score Status: ", BoardScore);
        telemetry.addLine(" "); //space

        //keep checking for driver input
        d_up.readValue();
        d_down.readValue();
    }


}
