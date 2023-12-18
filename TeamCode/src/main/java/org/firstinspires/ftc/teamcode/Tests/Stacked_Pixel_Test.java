package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision;

public class Stacked_Pixel_Test extends LinearOpMode {
    Camera_Vision vision = new Camera_Vision();
    public double alignmentX = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        vision.init_cameras(hardwareMap, "Webcam 1", "Webcam 2");
            alignmentX = 0;

        waitForStart();

        vision.init_stack_detection(hardwareMap);

        while(opModeIsActive()){
            alignmentX = vision.getStackAlignment();
            telemetry.addData("Strafe Length Needed", alignmentX);
            telemetry.update();
        }
    }
}
