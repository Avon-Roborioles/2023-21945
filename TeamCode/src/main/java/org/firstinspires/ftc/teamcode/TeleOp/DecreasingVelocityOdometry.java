package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain;

@TeleOp(name = "Decreasing Velocity - Odometry", group = "TeleOp")
public class DecreasingVelocityOdometry extends LinearOpMode {
    private final Drivetrain drivetrain = new Drivetrain();

    //updates telemetry for all robot functions
    public void telemetryUpdate(){

        telemetry.update();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_red_drive_motors(hardwareMap);

        waitForStart();

        while(opModeIsActive()) {
            drivetrain.run_mecanum_drive(gamepad1,telemetry);


            telemetryUpdate();
        }
    }
}
