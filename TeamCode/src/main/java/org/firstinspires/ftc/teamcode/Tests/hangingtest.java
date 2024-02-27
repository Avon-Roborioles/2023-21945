package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm;

@TeleOp
public class hangingtest extends LinearOpMode {

    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();


    @Override
    public void runOpMode() throws InterruptedException {

        //arm.init_arm_auto(hardwareMap,"leftMotor", "rightMotor");
        arm.init_arm_teleOp(hardwareMap,"leftMotor","rightMotor");
        waitForStart();

        while(opModeIsActive()){
            arm.hang();
        }
        //main code

    }
}
