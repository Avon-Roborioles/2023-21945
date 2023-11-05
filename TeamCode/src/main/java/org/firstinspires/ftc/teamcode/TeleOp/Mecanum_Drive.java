/*
* A simple programm to drive to robot
* Mainly Used for Demostrations and the Programming Bot
*/
package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
@TeleOp
public class Mecanum_Drive extends LinearOpMode{
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(false);

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_drive_motors(hardwareMap);

        waitForStart();

        while(opModeIsActive()){
            drivetrain.run_mecanum_drive(gamepad1, telemetry);
        }
    }
}
