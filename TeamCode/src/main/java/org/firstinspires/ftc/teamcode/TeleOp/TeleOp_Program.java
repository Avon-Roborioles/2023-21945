package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
import com.arcrobotics.ftclib.util.Timing;

import java.util.concurrent.TimeUnit;

@TeleOp
public class TeleOp_Program extends LinearOpMode {
    //creating objects for robot functions
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(false);
    private final Claw claw = new Claw();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Haptic_Feedback feedback = new Haptic_Feedback();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.DroneLauncher launcher = new DroneLauncher();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();


    //updates telemetry for all robot functions
    public void setTelemetry(){
        drivetrain.getTelemetry(telemetry);
        claw.getTelemetry(telemetry);
        launcher.getTelemetry(telemetry);
        arm.getTelemetry(telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_drive_motors(hardwareMap, "fl", "fr", "bl", "br");
        claw.init_claw(hardwareMap, "claw", "wrist");
        launcher.init_Launcher(hardwareMap, "launcher");
        arm.init_arm(hardwareMap, "arm");
        setTelemetry();
        Timing.Timer engameTimer = new Timing.Timer(2, TimeUnit.MINUTES);

        waitForStart();

        while(opModeIsActive()) { //robot loop
            drivetrain.run_drive_motors(gamepad1);
            claw.run_claw(gamepad2);
            launcher.run_Launcher(gamepad1);
            arm.run_arm(gamepad2);
            setTelemetry();
            telemetry.update(); //updates telemetry for all robot functions

            if (engameTimer.remainingTime() == 0.5) { //alerts drivers of engame starting
                feedback.endGame_Alert(gamepad1, gamepad2);
            }
        }
    }
}
