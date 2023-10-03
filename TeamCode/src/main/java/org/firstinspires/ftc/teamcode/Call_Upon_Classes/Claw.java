package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


//FTCLib Library
import com.arcrobotics.ftclib.hardware.motors.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//Added Default Class for Intake Mechanism
public class Claw {
    private ServoEx claw = null;
    private Motor wrist = null;
    private double speed = 0.0;

    private boolean isActive = false;

    public void init_claw(HardwareMap hardwareMap, String clawName, String wristName){
        //creates the intake object with its name
        claw = new SimpleServo(hardwareMap, clawName, 0, 180);
        wrist = new Motor(hardwareMap, wristName);

    }

    public void run_claw(Gamepad gamepad2){
        speed = gamepad2.right_stick_y;
        wrist.set(speed);

        // Limits
        if (wrist.getCurrentPosition() >= 180 && speed > 0) {
            speed = 0;
        } else if (wrist.getCurrentPosition() <= 0 && speed < 0) {
            speed = 0;
        }

        if (gamepad2.left_bumper) {
            claw.setPosition(0);
        }
        else if (gamepad2.right_bumper) {
            claw.setPosition(180);
        }

    }

    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Intake Speed", speed);
    }

}
