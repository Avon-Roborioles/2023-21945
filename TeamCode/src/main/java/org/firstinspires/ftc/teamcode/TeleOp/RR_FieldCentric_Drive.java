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

@TeleOp(name="RR FieldCentric Drive", group="TeleOp")
//@Disabled
public class RR_FieldCentric_Drive extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drivetrain = new SampleMecanumDrive(hardwareMap);
        drivetrain.setPoseEstimate(PoseStorage.startPoseRR);

        waitForStart();

        while(opModeIsActive()){
            drivetrain.update();
            Pose2d poseEstimate = drivetrain.getPoseEstimate();

            Vector2d input = new Vector2d(
                    -gamepad1.left_stick_x,
                    -gamepad1.left_stick_y
            ).rotated(-poseEstimate.getHeading());

// Pass in the rotated input + right stick value for rotation
// Rotation is not part of the rotated input thus must be passed in separately
            drivetrain.setWeightedDrivePower(
                    new Pose2d(
                            input.getX(),
                            input.getY(),
                            -gamepad1.right_stick_x
                    )
            );

        }
    }
}
