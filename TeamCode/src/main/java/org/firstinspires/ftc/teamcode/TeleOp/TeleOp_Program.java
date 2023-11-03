package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
import com.arcrobotics.ftclib.util.Timing;

import java.util.concurrent.TimeUnit;

@TeleOp
public class TeleOp_Program extends LinearOpMode {
    //creating objects for robot functions
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(true); //change to false if driving still isn't fixed
    private final Intake intake = new Intake();
    //private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Haptic_Feedback feedback = new Haptic_Feedback();
    //private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.DroneLauncher launcher = new DroneLauncher();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();
    //private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision vision = new Camera_Vision();


    //updates telemetry for all robot functions
    public void setTelemetry(){
        drivetrain.getTelemetry(telemetry);
        intake.getTelemetry(telemetry);
        //launcher.getTelemetry(telemetry);
        arm.getTelemetry(telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_drive_motors(hardwareMap);
        intake.init_intake(hardwareMap, "claw", "wrist", "pixelHolder");
        //launcher.init_Launcher(hardwareMap, "launcher");
        arm.init_arm(hardwareMap, "leftMotor", "rightMotor");
        //vision.init_cameras(hardwareMap, "Webcam1", "Webcam2");

        setTelemetry();

        Timing.Timer engameTimer = new Timing.Timer(2, TimeUnit.MINUTES);

        waitForStart();

        while(opModeIsActive()) { //robot loop

            drivetrain.run_drive_motors(gamepad1, telemetry);
            arm.run_arm(gamepad2);
            intake.run_intake(gamepad2, arm.getArmStatus()); //takes in armStatus for future use
            //launcher.run_Launcher(gamepad1);
            setTelemetry();
            telemetry.update(); //updates te telemetry for all robot functions

//            if (engameTimer.remainingTime() == 0.5) { //alerts drivers that endgame is starting
//                feedback.endGame_Alert(gamepad1, gamepad2);
//            }
        }
    }
}
