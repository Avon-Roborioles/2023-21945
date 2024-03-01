/*
* A simple programm to drive to robot
* Mainly Used for Demostrations and the Programming Bot
*/
package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name="FieldCentric Drive", group="TeleOp")
//@Disabled
public class FieldCentric_Drive extends LinearOpMode{
    Drivetrain drivetrain = new Drivetrain();

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_fieldCentric_drive(hardwareMap,true);
        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);

        //RR localizer code to track pose on field
        SampleMecanumDrive localizer = new SampleMecanumDrive(hardwareMap);
        localizer.setPoseEstimate(PoseStorage.parkSpotRR); //start from here for accurate pose

        double PoseX = localizer.getPoseEstimate().getX();
        double PoseY = localizer.getPoseEstimate().getY();
        double PoseHeading = localizer.getPoseEstimate().getHeading();

        //telemetry update
        telemetry.addLine("Field Centric Drive Activated");
        telemetry.addLine("If IMU starts to drift or have problems, simply press A on Gamepad1 to reset it.");
        telemetry.addData("Current IMU Value: ", drivetrain.getHeading());
        telemetry.update();

        waitForStart(); //loop code above until we start program

        while(opModeIsActive()){
            drivetrain.run_fieldCentric_drive(gamepad1Ex,true);

            //update localizer
            PoseX = localizer.getPoseEstimate().getX();
            PoseY = localizer.getPoseEstimate().getY();
            PoseHeading = localizer.getPoseEstimate().getHeading();

            //telemetry update
            telemetry.addData("X Position: ", PoseX);
            telemetry.addData("Y Position: ", PoseY);
            telemetry.addData("Heading Position: ", PoseHeading);
            telemetry.update();

        }
    }
}
