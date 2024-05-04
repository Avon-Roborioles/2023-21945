/*
 * A simple programm to drive to robot
 * Mainly Used for Demostrations and the Programming Bot
 */
package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name="DecreasingVelocityOdometry", group="TeleOp")
//@Disabled
public class DecreasingVelocityOdometry extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drivetrain = new SampleMecanumDrive(hardwareMap);
        drivetrain.setPoseEstimate(PoseStorage.startPoseRL);
        double powerScale = 1.0;

        waitForStart();

        while(opModeIsActive()){
            drivetrain.update();
            Pose2d poseEstimate = drivetrain.getPoseEstimate();

            Vector2d input = new Vector2d(gamepad1.left_stick_x, -gamepad1.left_stick_y).rotated(-poseEstimate.getHeading());

            double distanceFromWall = 0;

            if(drivetrain.getPoseEstimate().getX() > 0){
                powerScale = .25;
            } else {
                powerScale = .9;
            }

            if(gamepad1.a){
                drivetrain.setPoseEstimate(new Pose2d(0,0,Math.toRadians(90)));
            }


            // Pass in the rotated input + right stick value for rotation
            // Rotation is not part of the rotated input thus must be passed in separately
            drivetrain.setWeightedDrivePower(
                    new Pose2d(input.getX() * powerScale, input.getY() * powerScale, -gamepad1.right_stick_x));

            telemetry.addData("X Value", drivetrain.getPoseEstimate().getX());
            telemetry.addData("Y Value", drivetrain.getPoseEstimate().getY());
            telemetry.addData("Heading", drivetrain.getPoseEstimate().getHeading());

            telemetry.update();
        }
    }
}