package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    //TODO Code PID Controller in arm
    private DcMotorEx leftMotor = null;
    private DcMotorEx rightMotor = null;
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
    private int scoreHeight = 600;
    private int pixel5Height = 500; //TODO Change these values to actual height
    private int pixel4Height = 400;
    private int pixel3Height = 300;
    private int pixel2Height = 200;
    private int pixel1Height = 100;
    private PIDController controller;

    public static double p = 0, i = 0, d = 0; //PID variables needed
    public static double f = 0; //feed forward variable

    public static int target = 0; //the variable team drivers will control to move arm

    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    /**
     * easy to use commands to refer to arm status
     */

    public void init_arm(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        controller = new PIDController(p, i, d);
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
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

        leftMotor.setPower(speed);
        rightMotor.setPower(-speed);
    } //Done - just controls speed - test

    //Done ideal arm control wit PID Control
    public void run_arm(Gamepad gamepad2){
        armStatus = armCommands.MANUAL;

        double leftY = gamepad2.left_stick_y;
        boolean dpadLeft = gamepad2.dpad_left;
        boolean dpadUp = gamepad2.dpad_up;
        boolean dpadRight = gamepad2.dpad_right;
        boolean dpadDown = gamepad2.dpad_down;

        //limits
        if(target > 180){
            target = 180;
        } else if(target < 0){
            target = 0;
        }

        //driver control
        if(leftY > 0){ //left joystick up
            target += 10; //TODO - test code to adjust
        } else if (leftY < 0){
            target-= 10;
        }
        if(dpadUp){ //score height
            armStatus = armCommands.SCORE;
            target = scoreHeight;
        } else if (dpadDown){
            armStatus = armCommands.GROUND;
            target = 0;
        }

        //PID control
        controller.setPID(p, i, d);
        int leftArmPos = leftMotor.getCurrentPosition();
        int rightArmPos = rightMotor.getCurrentPosition();
        double pid = controller.calculate(leftArmPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        leftMotor.setPower(power);
        rightMotor.setPower(-power);
    } //Done - PID Control - test

    //quick method to get armStatus (returns armCommands result)
    public armCommands getArmStatus(){
        return armStatus;
    }

    //auto arm functions
    //Done - moves arm to an exact position
    public void setArmPosition(int position){
    armStatus = armCommands.MANUAL;

        target = position;
        //PID control
        controller.setPID(p, i, d);
        int leftArmPos = leftMotor.getCurrentPosition();
        int rightArmPos = rightMotor.getCurrentPosition();
        double pid = controller.calculate(leftArmPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        leftMotor.setPower(power);
        rightMotor.setPower(-power);
    } //done - test

    //Done - sets arm to preset for stackedCone
    public void setToPreset(armCommands command){
        switch(command){
            //sets the target position based on the armCommands
            case GROUND:
                armStatus = armCommands.GROUND;
                target = 0;
                break;

            case SCORE:
                armStatus = armCommands.SCORE;
                target = scoreHeight;
                break;

            case PIXEL1:
                armStatus = armCommands.PIXEL1;
                target = pixel1Height;
                break;

            case PIXEL2:
                armStatus = armCommands.PIXEL2;
                target = pixel2Height;
                break;

            case PIXEL3:
                armStatus = armCommands.PIXEL3;
                target = pixel3Height;
                break;

            case PIXEL4:
                armStatus = armCommands.PIXEL4;
                target = pixel4Height;
                break;

            case PIXEL5:
                armStatus = armCommands.PIXEL5;
                target = pixel5Height;
                break;
        } //sets the target based on the Arm Command

        controller.setPID(p, i, d);
        int leftArmPos = leftMotor.getCurrentPosition();
        int rightArmPos = rightMotor.getCurrentPosition();
        double pid = controller.calculate(leftArmPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        leftMotor.setPower(power);
        rightMotor.setPower(-power);
    }


    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Current Arm Target", target);
        telemetry.addData("Left Arm Motor Position", leftMotor.getCurrentPosition());
        telemetry.addData("Right Arm Motor Position", rightMotor.getCurrentPosition());
        telemetry.addData("Current Arm Status", armStatus);
    }
}

