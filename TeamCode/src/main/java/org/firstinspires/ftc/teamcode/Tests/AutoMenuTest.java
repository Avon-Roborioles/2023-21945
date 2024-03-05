package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class AutoMenuTest extends LinearOpMode {
    public enum AutoPath {
        UP,
        DOWN
    }
    boolean BoardScore = true;
    AutoPath selectedPath = AutoPath.UP;

    boolean allow_press_u = true;
    boolean allow_press_d = true;

    public void runMenu(Gamepad gamepad1) {
        boolean dpad_up = gamepad1.dpad_up;
        boolean dpad_down = gamepad1.dpad_down;



        if (dpad_up && allow_press_u) {
            if (selectedPath == AutoPath.UP) {
                selectedPath = AutoPath.DOWN;
            } else {
                selectedPath = AutoPath.UP;
            }
            allow_press_u = false;
        }
        else if (!dpad_up) {
            allow_press_u = true;
        }

        if (dpad_down && allow_press_d) {
            if (BoardScore) {
                BoardScore = false;
            } else {
                BoardScore = true;
            }
            allow_press_d = false;
        }
        else if (!dpad_down) {
            allow_press_d = true;
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while(opModeIsActive()) {
            runMenu(gamepad1);
            getTelemetry(telemetry);

            //getTelemetry(telemetry);
            telemetry.update();
        }
    }


    public void getTelemetry(Telemetry telemetry) {
        telemetry.addLine("Select the Auto Path by Toggling the D-pad Up Button");
        telemetry.addData("Current Auto Path Selected: ", selectedPath);
        telemetry.addLine(" ");
        telemetry.addLine("Select Board Scoring by Toggling the D-pad Down Button");
        telemetry.addData("Selected Board Score Status: ", BoardScore);
    }


}
