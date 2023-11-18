package org.firstinspires.ftc.teamcode.Autonomous.Park;

import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.arcrobotics.ftclib.util.Timing.Timer;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Auto_Marker;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.Auto_Marker_Processor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.concurrent.TimeUnit;

@Autonomous(name="BL Park", group="Park Programs")
public class BL_Park extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        init_classes();
        Auto_Marker camera = new Auto_Marker();
        camera.init(hardwareMap);

        waitForStart();
        //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        while(opModeIsActive()) {
            camera.start();
            camera.setTelemetry(telemetry);
            telemetry.update();
        }
    }
}
