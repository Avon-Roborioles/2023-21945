package org.firstinspires.ftc.teamcode.TeleOp;

import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
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
        ToggleButtonReader d_up2 = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.DPAD_UP
        );

        ToggleButtonReader d_down2 = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.DPAD_DOWN
        );
        ToggleButtonReader d_left2 = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.DPAD_LEFT
        );
        ToggleButtonReader d_right2 = new ToggleButtonReader(
                gamepad2Ex, GamepadKeys.Button.DPAD_RIGHT
        );
        TriggerReader ltrigger2 = new TriggerReader(
                gamepad2Ex, GamepadKeys.Trigger.LEFT_TRIGGER
        );
        TriggerReader rtrigger2 = new TriggerReader(
                gamepad2Ex, GamepadKeys.Trigger.RIGHT_TRIGGER
        );


        while(opModeInInit()){
            telemetry.addLine("The Raven is ready to fly!");
            setTelemetry();
        }


        waitForStart();
        telemetry.speak("The Raven is ready to go!");

        while(opModeIsActive()) {
            //Driver 1 Controls
            drivetrain.run_mecanum_drive(gamepad1,telemetry);
            launcher.run_Launcher(gamepad1);

            //Driver 2 Controls
            arm.run_arm_teleOp(gamepad2,d_down2,d_up2,d_left2,d_right2,ltrigger2,rtrigger2);
            intake.run_intake_V2(gamepad2, a2Reader,y2Reader,LBumperReader,RBumperReader,d_down2,d_up2,d_left2,d_right2);

            //Driver feedback program
            feedback.run_Timer(gamepad1,gamepad2);
            setTelemetry();

        }
    }
}
