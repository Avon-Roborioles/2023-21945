package org.firstinspires.ftc.teamcode.Autonomous.Park;

import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.arcrobotics.ftclib.util.Timing.Timer;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import java.util.concurrent.TimeUnit;

@Autonomous(name="BL Park", group="Park Programs")
public class BL_Park extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        init_classes();
        String propPosition = "";

        waitForStart();
        //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        while(opModeIsActive()) {
            //Timing.Timer clock = new Timing.Timer(8, TimeUnit.SECONDS);
            //while (clock.isTimerOn()) {
                propPosition = vision.getPropPosition(hardwareMap, telemetry);
                telemetry.addData("Final Prop Position: ", propPosition);
                telemetry.update();
            //}

        }
    }
}
