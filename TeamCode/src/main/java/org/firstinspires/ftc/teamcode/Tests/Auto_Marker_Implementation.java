package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBase;

import java.util.concurrent.TimeUnit;

//@Autonomous(name="Auto Marker Implementation", group="Park Programs")
@Autonomous
public class Auto_Marker_Implementation extends AutoBase {
    String propPosition = "";

    public void runOpMode() throws InterruptedException {
        String propPosition = "NONE";
        int aprilTagID = 5;
        init_classes();
        vision.init_spike_detection(hardwareMap, true);


        waitForStart();

        while(opModeIsActive()){
            propPosition = vision.getPropPosition();
            telemetry.addData("Prop Position: ", propPosition);
            telemetry.update();
        }
    }
}
