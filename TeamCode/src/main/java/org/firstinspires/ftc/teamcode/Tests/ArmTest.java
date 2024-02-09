package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm;

public class ArmTest extends LinearOpMode {
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();

    @Override
    public void runOpMode() throws InterruptedException {

        arm.init_arm_V2(hardwareMap,"leftMotor","rightMotor");
        GamepadEx gamepad2Ex = new GamepadEx(gamepad2);

        waitForStart();



        while(opModeIsActive()){

        }
    }
}
