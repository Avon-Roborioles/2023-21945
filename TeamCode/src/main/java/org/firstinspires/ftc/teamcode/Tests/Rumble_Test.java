package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Haptic_Feedback;

@TeleOp
public class Rumble_Test extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Haptic_Feedback feedback = new Haptic_Feedback();
        waitForStart();

        while(opModeIsActive()){
            feedback.alert_driver(gamepad1);
        }
    }
}
