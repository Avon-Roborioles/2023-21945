package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision;

@TeleOp
public class AprilTag_Test extends OpMode {
    Camera_Vision vision = new Camera_Vision();
    @Override
    public void init() {
        vision.init_camera(hardwareMap, "Webcam 1");
    }

    @Override
    public void loop() {
        double[] pose = vision.get_Apriltag_pose(5);
        telemetry.addData("X Value: ", pose[0]);
        telemetry.addData("Y Value: ", pose[1]);
        telemetry.addData("Z Value: ", pose[2]);
        telemetry.addData("Range Value", pose[3]);
        telemetry.addData("Bearing Value: ", pose[4]);
        telemetry.addData("Elevation Value: ",pose[5]);

        telemetry.update();

    }
}
