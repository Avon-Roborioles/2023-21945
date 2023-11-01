package org.firstinspires.ftc.teamcode.Tests;

//simple auto program that moves based on the propPosition detected
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.arcrobotics.ftclib.util.Timing.Timer;

import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain;

import java.util.concurrent.TimeUnit;

@TeleOp
public class Marker_Detection extends LinearOpMode {
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Drivetrain drivetrain = new Drivetrain(false);
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision vision = new Camera_Vision();
    String position = ""; //final propposition

    @Override
    public void runOpMode() throws InterruptedException {
        //boots up robot subsystems
        vision.init_camera(hardwareMap, "Webcam 1");
        drivetrain.init_drive_motors(hardwareMap, "fl", "fr", "bl", "br");

        waitForStart(); //waits to start main code

        Timing.Timer startTimer = new Timer(3, TimeUnit.SECONDS);
        while(!startTimer.done()){
            position =  vision.getPropPosition(hardwareMap); //spends 3 seconds deciding on propPostion
        }

        switch(position){
            case "LEFT":
                break;
            case "MIDDLE":
                break;
            case "RIGHT":
                break;
        }
    }
}
