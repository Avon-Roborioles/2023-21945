/*
* A simple programm to drive to robot
* Mainly Used for Demostrations and the Programming Bot
*/
package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
@TeleOp(name="FieldCentric Drive", group="")
public class FieldCentric_Drive extends LinearOpMode{
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(false);

    @Override
    public void runOpMode() throws InterruptedException {
        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);

        drivetrain.init_ftclib_drive(hardwareMap, gamepad1Ex);
        waitForStart();

        while(opModeIsActive()){
            //drivetrain.run_ftclib_drive(hardwareMap, gamepad1Ex);
        }
    }
}
