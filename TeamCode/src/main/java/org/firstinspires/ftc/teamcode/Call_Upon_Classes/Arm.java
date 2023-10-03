package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    private Motor arm = null;
    private double speed = 0.0;

    public void init_arm(HardwareMap hardwareMap, String armName){
        arm = new Motor(hardwareMap, armName);
    }

    public void run_arm(Gamepad gamepad2){
        speed = gamepad2.left_stick_y;

        // Limits
        if (arm.getCurrentPosition() >= 180 && speed > 0) {
            speed = 0;
        } else if (arm.getCurrentPosition() <= 0 && speed < 0) {
            speed = 0;
        }

        arm.set(speed);
    }

    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Arm Speed", speed);
    }
}

