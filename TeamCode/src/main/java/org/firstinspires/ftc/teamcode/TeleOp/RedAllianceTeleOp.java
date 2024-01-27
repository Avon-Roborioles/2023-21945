package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;

@TeleOp
public class RedAllianceTeleOp extends LinearOpMode {
    //creating objects for robot functions
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(); //change to false if driving still isn't fixed
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
        telemetry.update();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        //drivetrain.init_drive_motors(hardwareMap);
        drivetrain.init_red_drive_motors(hardwareMap);
        //intake.init_intake_teleOp(hardwareMap, "claw", "wrist", "pixelHolder");
        intake.init_intake_V2(hardwareMap, "claw1", "claw2", "wrist");
        launcher.init_Launcher(hardwareMap, "launcher");
        arm.init_arm_manual(hardwareMap, "leftMotor", "rightMotor");
        GamepadEx gamepad2Ex = new GamepadEx(gamepad2);
        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);

        ToggleButtonReader a2Reader = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.A
        );

        ToggleButtonReader a1Reader = new ToggleButtonReader(
                gamepad1Ex, GamepadKeys.Button.A
        );

        ToggleButtonReader y2Reader = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.Y
        );

        ToggleButtonReader LBumperReader = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.LEFT_BUMPER
        );

        ToggleButtonReader RBumperReader = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.RIGHT_BUMPER
        );


        setTelemetry();

        waitForStart();

        while(opModeIsActive()) { //robot loop

            //Driver 1 Controls - Primary
            //Driver 1 Controls
            drivetrain.run_mecanum_drive(gamepad1, telemetry, arm.getRightMotorPosition());
            launcher.run_Launcher(gamepad1);

            //Driver 2 Controls
            arm.run_arm_manual(gamepad2);
            double rightArmPosition = arm.getRightMotorPosition();
            //intake.run_intake_Power(gamepad2, rightArmPosition);
            //intake.run_intake_Power(gamepad2, gamepad2Ex, a2Reader,1);
            intake.run_intake_V2(gamepad2,gamepad2Ex,a2Reader,y2Reader);
            setTelemetry();

        }
    }
}
