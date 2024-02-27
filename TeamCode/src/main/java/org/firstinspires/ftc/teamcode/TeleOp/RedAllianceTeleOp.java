package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;

@TeleOp(name = "RedAllianceTeleOp", group = "TeleOp")
public class RedAllianceTeleOp extends LinearOpMode {
    //creating objects for robot functions
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(); //change to false if driving still isn't fixed
    private final Intake intake = new Intake();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Haptic_Feedback feedback = new Haptic_Feedback();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.DroneLauncher launcher = new DroneLauncher();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();


    //updates telemetry for all robot functions
    public void setTelemetry(){
        //arm.getTelemetry(telemetry);

        telemetry.update();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.speak("The Raven is Initializing!");
        drivetrain.init_red_drive_motors(hardwareMap);
        intake.init_intake_V2(hardwareMap, "claw1", "claw2", "wrist");
        launcher.init_Launcher(hardwareMap, "launcher");
        arm.init_arm_teleOp(hardwareMap, "leftMotor", "rightMotor");
        feedback.init_Timer();

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

        //TODO - New Features
        ToggleButtonReader d_down = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.DPAD_DOWN
        );

        ToggleButtonReader d_up = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.DPAD_UP
        );


        //setTelemetry();

        waitForStart();
        telemetry.speak("The Raven is ready to go!");

        while(opModeIsActive()) {
            //Driver 1 Controls
            drivetrain.run_mecanum_drive(gamepad1,telemetry);
            launcher.run_Launcher(gamepad1);

            //Driver 2 Controls
            arm.run_arm_teleOp(gamepad2,d_down);
            intake.run_intake_V2(gamepad2, a2Reader,y2Reader,LBumperReader,RBumperReader);

            //Driver feedback program
            feedback.run_Timer(gamepad1,gamepad2);
            setTelemetry();

        }
    }
}
