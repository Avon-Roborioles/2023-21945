package org.firstinspires.ftc.teamcode.Autonomous.Park_Score;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="RR Score", group="Park + Score")
public class RR_Score extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        init_classes();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    }
}
