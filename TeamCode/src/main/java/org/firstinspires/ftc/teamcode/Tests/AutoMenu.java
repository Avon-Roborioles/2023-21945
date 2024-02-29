package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class AutoMenu extends LinearOpMode {
    public enum AutoPath {
        UP,
        DOWN
    }
    AutoPath selectedPath = AutoPath.UP;
    boolean BoardScore = true;

    GamepadEx gamepad = new GamepadEx(gamepad1);

    //controls for auto path
    ToggleButtonReader d_up = new ToggleButtonReader(
            gamepad, GamepadKeys.Button.DPAD_UP
    );

    ToggleButtonReader d_down = new ToggleButtonReader(
            gamepad, GamepadKeys.Button.DPAD_DOWN
    );

    @Override
    public void runOpMode() throws InterruptedException{
        //menu code
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
        telemetry.update();

        //updates
        d_up.readValue();
        d_down.readValue();

        waitForStart();

        while(opModeIsActive()){
            //display final readings
            telemetry.addData("Selected Path: ", selectedPath);
            telemetry.addData("Scoring on Board?: ", BoardScore);
            telemetry.update();
        }
    }
}
