package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;

//create robot motors/servos


@TeleOp(name="Robot Telemetry", group = "Tests")
public class Robot_Telemetry extends OpMode {
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(); //change to false if driving still isn't fixed
    private final Intake intake = new Intake();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.DroneLauncher launcher = new DroneLauncher();

    public void setTelemetry(){
        drivetrain.getTelemetry(telemetry);
        intake.getTelemetry(telemetry);
        intake.getTelemetry(telemetry);
        launcher.getTelemetry(telemetry);
        arm.getTelemetry(telemetry);
    }

    @Override
    public void init() {
        drivetrain.init_drive_motors(hardwareMap);
       // intake.init_intake_teleOp(hardwareMap, "claw", "wrist", "pixelHolder");
        intake.init_intake_V2(hardwareMap,"claw1", "claw2","wrist");
        launcher.init_Launcher(hardwareMap, "launcher");
        //arm.init_arm_manual(hardwareMap, "leftMotor", "rightMotor");
        arm.init_arm_V2(hardwareMap,"leftMotor", "rightMotor");
        setTelemetry();
    }

    @Override
    public void loop() {
        setTelemetry();
        telemetry.update();
    }
}
