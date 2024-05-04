/*
* A simple programm to drive to robot
* Mainly Used for Demostrations and the Programming Bot
*/
package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name="RR FieldCentric Drive", group="TeleOp")
//@Disabled
public class RR_FieldCentric_Drive extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drivetrain = new SampleMecanumDrive(hardwareMap);
        drivetrain.setPoseEstimate(PoseStorage.startPoseRL);
        Rev2mDistanceSensor distanceSensor = hardwareMap.get(Rev2mDistanceSensor.class,"distance_sensor");

        double powerScale = 1.0;
        double distance = 0;

        waitForStart();

        while(opModeIsActive()){
            drivetrain.update();
            Pose2d poseEstimate = drivetrain.getPoseEstimate();
            distance = distanceSensor.getDistance(DistanceUnit.INCH);

            Vector2d input = new Vector2d(gamepad1.left_stick_x, -gamepad1.left_stick_y).rotated(-poseEstimate.getHeading());


            if(gamepad1.right_trigger > .3) {
                powerScale = 1;
            } else {
                if (distance <= 35) {
                    powerScale = .1;
                } else {
                    powerScale = 1;
                }

                if (gamepad1.a) {
                    drivetrain.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(90)));
                }
            }

    // Pass in the rotated input + right stick value for rotation
    // Rotation is not part of the rotated input thus must be passed in separately
            drivetrain.setWeightedDrivePower(
                    new Pose2d(input.getX() * powerScale, input.getY() * powerScale, -gamepad1.right_stick_x));

            String distanceResult = distance + " Inches";

            telemetry.addData("X Value:", drivetrain.getPoseEstimate().getX());
            telemetry.addData("Y Value:", drivetrain.getPoseEstimate().getY());
            telemetry.addData("Heading:", drivetrain.getPoseEstimate().getHeading());
            telemetry.addData("Distance Sensor Reading:", distanceResult);

            telemetry.update();
        }
    }
}
