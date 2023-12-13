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
        //intake.init_intake_teleOp(hardwareMap, "claw", "wrist", "pixelHolder");
        //TODO - testing TP mode for wrist
        intake.init_intake_main(hardwareMap, "claw", "wrist", "pixelHolder", false);

        launcher.init_Launcher(hardwareMap, "launcher");
        //arm.init_arm_manual(hardwareMap, "leftMotor", "rightMotor");

        //TODO - testing TP mode for arm
        arm.init_arm_manual(hardwareMap, "leftMotor", "rightMotor");

        setTelemetry();

        waitForStart();

        while(opModeIsActive()) { //robot loop

            //Driver 1 Controls - Primary
            //Driver 1 Controls
            //double rightArmPosition = arm.getRightMotorPosition();


            drivetrain.run_mecanum_drive(gamepad1, telemetry, 1);
            launcher.run_Launcher(gamepad1);

            //Driver 2 Controls
            //arm.run_arm_manual(gamepad2);
            arm.run_arm_manual(gamepad2);
            //intake.run_intake_Power(gamepad2, rightArmPosition);
            intake.run_intake_main(gamepad2);

            setTelemetry();

        }
    }
}
