package org.firstinspires.ftc.teamcode.Autonomous.Park;

import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.arcrobotics.ftclib.util.Timing.Timer;

import org.checkerframework.checker.units.qual.A;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Auto_Marker;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Processors.Auto_Marker_Processor;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.concurrent.TimeUnit;

@Autonomous(name="BL Park", group="Park Programs")
public class BL_Park extends AutoBase {
    String propPosition = "";

    public void runOpMode() throws InterruptedException {
        init_classes();
        //Auto_Marker camera = new Auto_Marker();
        //camera.init(hardwareMap);

        waitForStart();

        vision.init_marker_detection(hardwareMap); //starts auto_marker processor

        Timing.Timer clock = new Timing.Timer(8,TimeUnit.SECONDS);

        while(clock.isTimerOn()){
            propPosition = vision.getPropPosition(); //determines prop Position for 8 seconds
        }

        while(opModeIsActive()){
            telemetry.addData("Prop Position: ", propPosition);
        }
    }
}
