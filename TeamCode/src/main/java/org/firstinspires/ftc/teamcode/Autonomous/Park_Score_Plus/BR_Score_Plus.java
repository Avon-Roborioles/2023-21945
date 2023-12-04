package org.firstinspires.ftc.teamcode.Autonomous.Park_Score_Plus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

//@Autonomous(name="BR Score Plus", group="Park + Score Plus")
@Disabled
public class BR_Score_Plus extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        init_classes();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        //auto code here
    }
}
