package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    //TODO Code PID Controller in arm
    private Motor leftMotor = null;
    private Motor rightMotor = null;
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
    private double pixel5Height = 5; //TODO Change these values to actual height
    private double pixel4Height = 4;
    private double pixel3Height = 3;
    private double pixel2Height = 2;
    private double pixel1Height = 1;

    /**
     * easy to use commands to refer to arm status
     */

    public void init_arm(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotor = new Motor(hardwareMap, leftMotorName);
        rightMotor = new Motor(hardwareMap, rightMotorName);
    }

    public void run_arm_manual(Gamepad gamepad2){
        speed = gamepad2.left_stick_y;

        // Limits
        if (leftMotor.getCurrentPosition() >= 180 && speed > 0) {
            speed = 0;
            armStatus = armCommands.SCORE;
        } else if (leftMotor.getCurrentPosition() <= 0 && speed < 0) {
            speed = 0;
            armStatus = armCommands.GROUND;
        }

        leftMotor.set(speed);
        rightMotor.set(-speed);
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

    public void setToGround(){
        armStatus = armCommands.GROUND;
    } //TODO - moves the arm down to pickup position

    public void setToScore(){
        armStatus = armCommands.SCORE;
    }//TODO - sets arm to position for scoring

    public void setToPreset(armCommands command){
        switch(command){
            case PIXEL1:
                armStatus = armCommands.PIXEL1;
                break;
            case PIXEL2:
                armStatus = armCommands.PIXEL2;
                break;
            case PIXEL3:
                armStatus = armCommands.PIXEL3;
                break;
            case PIXEL4:
                armStatus = armCommands.PIXEL4;
                break;
            case PIXEL5:
                armStatus = armCommands.PIXEL5;
                break;
        }
    } //TODO - sets arm to preset for stackedCone


    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Arm Speed", speed);
    }
}

