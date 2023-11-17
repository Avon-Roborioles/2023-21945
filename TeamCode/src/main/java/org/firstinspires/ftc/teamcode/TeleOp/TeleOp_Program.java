package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;

import java.util.concurrent.TimeUnit;

@TeleOp
public class TeleOp_Program extends LinearOpMode {
    //creating objects for robot functions
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(true); //change to false if driving still isn't fixed
    private final Intake intake = new Intake();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Haptic_Feedback feedback = new Haptic_Feedback();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.DroneLauncher launcher = new DroneLauncher();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();
    //private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision vision = new Camera_Vision();


    //updates telemetry for all robot functions
    public void setTelemetry(){
        drivetrain.getTelemetry(telemetry);
        intake.getTelemetry(telemetry);
        launcher.getTelemetry(telemetry);
        arm.getTelemetry(telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_drive_motors(hardwareMap);
        //drivetrain.init_ftclib_drive(hardwareMap, gamepad1);
        //drivetrain.init_main();

        intake.init_intake_teleOp(hardwareMap, "claw", "wrist", "pixelHolder");
        //intake.init_intake_main(hardwareMap, "claw", "wrist", "pixelHolder");

        launcher.init_Launcher(hardwareMap, "launcher");

        //arm.init_arm_main(hardwareMap, "leftMotor", "rightMotor", false);
        arm.init_arm_manual(hardwareMap, "leftMotor", "rightMotor");

        //vision.init_cameras(hardwareMap, "Webcam1", "Webcam2");

        setTelemetry();

        Timing.Timer engameTimer = new Timing.Timer(2, TimeUnit.MINUTES);

        waitForStart();

        while(opModeIsActive()) { //robot loop

            //Driver 1 Controls - Primary
            drivetrain.run_mecanum_drive(gamepad1, telemetry);
            //drivetrain.run_ftclib_drive(hardwareMap,gamepad1);
            launcher.run_Launcher(gamepad1);


            //Driver 2 Controls - Secondary
            //arm.run_arm_main(gamepad2, telemetry);
            arm.run_arm_manual(gamepad2);
            // arm.run_arm(gamepad2);

            //int armTarget = arm.getArmTargetPositiion();
            //intake.run_intake_main(gamepad2, armTarget);
            //intake.run_intake_manual(gamepad2);
            double rightArmPosition = arm.getRightMotorPosition();
            intake.run_intake_Power(gamepad2, rightArmPosition);

            setTelemetry();

            if (engameTimer.remainingTime() == 0.5) { //alerts drivers that endgame is starting
                feedback.endGame_Alert(gamepad1, gamepad2);
            }
        }
    }
}
