package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


//FTCLib Library
import com.arcrobotics.ftclib.hardware.motors.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;

//Added Default Class for Intake Mechanism
public class Intake {
    private Motor intake = null;
    private double speed = 0.0;

    private boolean isActive = false;

    public void init_intake(HardwareMap hardwareMap, String name){
        //creates the intake object with its name
        intake = new Motor(hardwareMap, name);

    }

    public void run_intake(Gamepad gamepad, Telemetry telemetry){
    }

    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Intake Speed", speed);
    }

}
