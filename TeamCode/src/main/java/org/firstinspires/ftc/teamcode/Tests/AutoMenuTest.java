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

    Gamepad gamepad = new Gamepad();

    @Override
    public void runOpMode() throws InterruptedException {

        boolean dpad_up = gamepad.dpad_up;
        boolean dpad_down = gamepad.dpad_down;

        if (dpad_up) {
            if (selectedPath == AutoPath.UP) {
                selectedPath = AutoPath.DOWN;
            } else {
                selectedPath = AutoPath.UP;
            }
        }
        if (dpad_down) {
            if (BoardScore) {
                BoardScore = false;
            } else {
                BoardScore = true;
            }
        }
    }


    public void getTelemetry(Telemetry telemetry) {
        telemetry.addLine("Select the Auto Path by Toggling the D-pad Up Button");
        telemetry.addData("Current Auto Path Selected: ", selectedPath);
        telemetry.addLine(" ");
        telemetry.addLine("Select Board Scoring by Toggling the D-pad Down Button");
        telemetry.addData("Selected Board Score Status: ", BoardScore);
        telemetry.update();
    }


}
