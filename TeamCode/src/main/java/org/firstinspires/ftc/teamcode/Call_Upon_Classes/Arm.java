package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    //TODO Code PID Controller in arm
    private Motor arm = null;
    private double speed = 0.0;
    public enum armCommands {
        GROUND,
        SCORE,
        MANUAL,
        PIXEL5,
        PIXEL4,
        PIXEL3,
        PIXEL2,
        PIXEL1;
    }
    private armCommands armStatus = armCommands.GROUND;
    private double pixel5Height = 5;
    private double pixel4Height = 4;
    private double pixel3Height = 3;
    private double pixel2Height = 2;
    private double pixel1Height = 1;

    /**
     * easy to use commands to refer to arm status
     */

    public void init_arm(HardwareMap hardwareMap, String armName){
        arm = new Motor(hardwareMap, armName);
    }

    public void run_arm_manual(Gamepad gamepad2){
        speed = gamepad2.left_stick_y;

        // Limits
        if (arm.getCurrentPosition() >= 180 && speed > 0) {
            speed = 0;
            armStatus = armCommands.SCORE;
        } else if (arm.getCurrentPosition() <= 0 && speed < 0) {
            speed = 0;
            armStatus = armCommands.GROUND;
        }

        arm.set(speed);
    } //basic arm control - just controls speed

    public void run_arm(Gamepad gamepad2){

    } //TODO ideal arm control wit PID Control

    public armCommands getArmStatus(){
        return armStatus;
    }

    //auto arm functions
    public void setArmPostition(double position){
    armStatus = armCommands.MANUAL;
    } //TODO - moves arm to an exact position

    public void setArmDown(){
        armStatus = armCommands.GROUND;
    } //TODO - moves the arm down to pickup position

    public void setArmUp(){
        armStatus = armCommands.SCORE;
    }//TODO - sets arm to position for scoring



    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Arm Speed", speed);
    }
}

