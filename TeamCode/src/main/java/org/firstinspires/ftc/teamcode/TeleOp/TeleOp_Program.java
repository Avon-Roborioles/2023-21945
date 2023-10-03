package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
import com.arcrobotics.ftclib.util.Timing;

import java.util.concurrent.TimeUnit;

@TeleOp
public class TeleOp_Program extends LinearOpMode {
    //creating objects for robot functions
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(false);
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake intake = new Intake();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Haptic_Feedback feedback = new Haptic_Feedback();


    //updates telemetry for all robot functions
    public void setTelemetry(){
        drivetrain.getTelemetry(telemetry);
        intake.getTelemetry(telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_drive_motors(hardwareMap, "fl", "fr", "bl", "br");
        intake.init_intake(hardwareMap, "intake");
        setTelemetry();
        Timing.Timer engameTimer = new Timing.Timer(2, TimeUnit.MINUTES);

        waitForStart();

        drivetrain.run_drive_motors(gamepad1,telemetry);
        intake.run_intake(gamepad2, telemetry);
        telemetry.update(); //updates telemetry for all robot functions

        if(engameTimer.remainingTime() == 0.5){ //alerts drivers of engame starting
            feedback.endGame_Alert(gamepad1, gamepad2);
        }
    }
}
