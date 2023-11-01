package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


//FTCLib Library
import com.arcrobotics.ftclib.hardware.motors.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//Added Default Class for Intake Mechanism
public class Intake {
    private ServoEx claw = null;
    private ServoEx pixelHolder = null;
    private Motor wrist = null;
    private double speed = 0.0;
    private double defaultSpeed = 0.7;
    private boolean wristIsUp = false;

    private boolean isActive = false;

    public void init_intake(HardwareMap hardwareMap, String clawName, String wristName, String pixelHolderName){
        //creates the intake object with its name
        claw = new SimpleServo(hardwareMap, clawName, 0, 180);
        wrist = new Motor(hardwareMap, wristName);
        pixelHolder = new SimpleServo(hardwareMap, pixelHolderName, 0, 180);
    }

    public void run_intake(Gamepad gamepad2){
        //wrist control
        speed = gamepad2.right_stick_y;
        wrist.set(speed);

        // Limits
        if (wrist.getCurrentPosition() >= 180 && speed > 0) {
            speed = 0;
        } else if (wrist.getCurrentPosition() <= 0 && speed < 0) {
            speed = 0;
        }

        //claw controls
        if (gamepad2.left_bumper) {
            claw.setPosition(0);
        }
        else if (gamepad2.right_bumper) {
            claw.setPosition(180);
        }

        // pixelHolder automatic function
        if(wrist.getCurrentPosition() > 90){
            pixelHolder.setPosition(180);
        } else {
            pixelHolder.setPosition(0);
        }
    }

    //autonomous methods
    public void moveWrist(String direction){

    } //TODO

    public void openClaw(boolean open){

    } //TODO



    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Intake Speed", speed);
    }

}
