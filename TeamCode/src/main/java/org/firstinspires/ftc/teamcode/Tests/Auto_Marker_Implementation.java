package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;

import java.util.concurrent.TimeUnit;

@Autonomous(name="BL Park", group="Park Programs")
public class Auto_Marker_Implementation extends AutoBase {
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
