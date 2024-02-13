package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm;

public class ArmTest extends LinearOpMode {
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();

    @Override
    public void runOpMode() throws InterruptedException {

        arm.init_arm_V2(hardwareMap,"leftMotor","rightMotor");
        GamepadEx gamepad1Ex = new GamepadEx(gamepad1);

        ToggleButtonReader d_down = new ToggleButtonReader(
                gamepad1Ex, GamepadKeys.Button.DPAD_DOWN
        );
        waitForStart();


        while(opModeIsActive()){
            if(d_down.getState()){
                arm.up();
            } else {
                arm.down();
            }

            arm.update();
            d_down.readValue();
        }


    }
}
