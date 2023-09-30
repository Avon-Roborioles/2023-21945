package org.firstinspires.ftc.teamcode.TeleOp;

//import needed classes
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
//import com.qualcomm.robotcore.hardware.Gamepad;

import com.qualcomm.robotcore.eventloop.opmode.*;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;
@TeleOp
public class TeleOp_Program extends LinearOpMode {
    //creating objects for robot functions
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(false);
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake intake = new Intake();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Output output = new Output();
    private final org.firstinspires.ftc.teamcode.Call_Upon_Classes.Distance_Sensor distance_sensor = new Distance_Sensor();



    //updates telemetry for all robot functions
    public void setTelemetry(){
        drivetrain.getTelemetry(telemetry);
        intake.getTelemetry(telemetry);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain.init_drive_motors(hardwareMap, "fl", "fr", "bl", "br");
        intake.init_intake(hardwareMap, "intake");
        setTelemetry();

        waitForStart();

        drivetrain.run_drive_motors(gamepad1,telemetry);
        intake.run_intake(gamepad2, telemetry);
        telemetry.update(); //updates telemetry for all robot functions
    }
}
