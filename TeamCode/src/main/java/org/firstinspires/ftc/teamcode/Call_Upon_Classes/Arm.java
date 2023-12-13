package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

public class Arm {
    //TODO Code PID Controller in arm
    private DcMotorEx leftMotor = null;
    private DcMotorEx rightMotor = null;
    private double speed = 0.0;
    public enum armCommands {
        GROUND,
        MAX,
        SCORE,
        MANUAL,
        PIXEL5,
        PIXEL4,
        PIXEL3,
        PIXEL2,
        PIXEL1;
    }
    private armCommands armStatus = armCommands.GROUND;
    private int scoreHeightHIGH = 2000; //600
    private int scoreHeightLOW = 400; //TODO find optimal arm height to score with arm in front of bot
    private int pixel5Height = 500; //TODO Change these values to actual height
    private int pixel4Height = 400;
    private int pixel3Height = 300;
    private int pixel2Height = 200;
    private int pixel1Height = 100;
    private double leftMotorPosition = 0;
    private double rightMotorPosition = 0;
    private int maxPosition = 4000; //Done -  find max value
    private PIDController controller;
    public static double p = 0, i = 0, d = 0; //PID variables needed
    public static double f = 0; //feed forward variable
    public static int target = 0; //the variable team drivers will control to move arm
    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    //Methods of Arm Initialization
    public void init_arm_manual(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void init_arm_PID(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        controller = new PIDController(p, i, d);
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        //leftMotor.setDirection(DcMotorSimple.Direction.REVERSE); - already accounted for in run_PID method

    }
    public void init_arm_main(HardwareMap hardwareMap, String leftMotorName, String rightMotorName, boolean autoProgram){
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");

        //left motor should be in reverse to work with rightMotor
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //auto programs need motors to be reset to initial position
        if(autoProgram){
            leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        //sets motors 0 - ground position
        leftMotor.setTargetPosition(0);
        rightMotor.setTargetPosition(0);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    //Methods of Arm TeleOp control
    public void run_arm_manual(Gamepad gamepad2) {
        double leftY = gamepad2.left_stick_y;
        float rightTrigger = gamepad2.right_trigger;

//        // Limits
//        if (leftMotor.getCurrentPosition() >= 180 && speed > 0) {
//            speed = 0;
//            armStatus = armCommands.SCORE;
//        } else if (leftMotor.getCurrentPosition() <= 0 && speed < 0) {
//            speed = 0;
//            armStatus = armCommands.GROUND;
//        }
        if(rightMotor.getCurrentPosition() < 1600) {
            if (leftY < 0) {
                leftMotor.setPower(-0.5);
                rightMotor.setPower(0.5);
            } else if (leftY > 0 && rightTrigger > 0.8) {
                leftMotor.setPower(-1);
                leftMotor.setPower(1);
            } else if (leftY > 0) {
                leftMotor.setPower(0.3);
                rightMotor.setPower(-0.3);
            } else {
                leftMotor.setPower(-0.04); //small bit of power for brakes
                rightMotor.setPower(0.04);
            }
        } else {
            if (leftY > 0) {
//                leftMotor.setPower(-0.3);
//                rightMotor.setPower(0.3);
                leftMotor.setPower(0.5);
                rightMotor.setPower(-0.5);
            } else if (leftY < 0 && rightTrigger > 0.8) {
                leftMotor.setPower(1);
                leftMotor.setPower(-1);
            } else if (leftY < 0) {
//                leftMotor.setPower(0.3);
//                rightMotor.setPower(-0.3);
                leftMotor.setPower(-0.3);
                rightMotor.setPower(0.3);

            } else {
                leftMotor.setPower(0.09); //small bit of power for brakes
                rightMotor.setPower(-0.09);
            }
        }
//        leftMotor.setPower(speed);
//        rightMotor.setPower(-speed);
    } //Done - just controls speed - test
    public void run_arm_PID(Gamepad gamepad2){
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
            target = scoreHeightHIGH;
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

        leftMotor.setPower(-power);
        rightMotor.setPower(power);
    } //Done - PID Control - test
    public void run_arm_main(Gamepad gamepad2, Telemetry telemetry){
       double leftY = gamepad2.left_stick_y;
       boolean d_up = gamepad2.dpad_up;
       boolean d_left = gamepad2.dpad_left;
       boolean d_down = gamepad2.dpad_down;
       boolean d_right = gamepad2.dpad_right;

       leftMotorPosition = leftMotor.getCurrentPosition();
       rightMotorPosition = rightMotor.getCurrentPosition();

       //driver control
       if(leftY > 0){ //arm up
           target += 20;
           setArmTargetPosition(target);
       } else if(leftY < 0){
           target -= 20;
           setArmTargetPosition(target);
       }

       if(d_up){
           setArmTargetPosition(scoreHeightHIGH);
       } //arm to score height
       if(d_down){
           setArmTargetPosition(0);
       } //arm to ground

       //limits for arm
        if(target > maxPosition){
            armStatus = armCommands.MAX;
            target = maxPosition;
        } else if (target < 0){
            armStatus = armCommands.GROUND;
            target = 0;
        }

        //final steps to get arm to position
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //amount of power to get to position - BE CAREFUL WITH THESE VALUES!!!
        leftMotor.setPower(-0.7); //will start with 70% power
        rightMotor.setPower(0.7);

        getTelemetry(telemetry);
    }


    //Auto Methods
    public armCommands getArmStatus(){
        return armStatus;
    }
    public  int getArmTargetPositiion(){
        return target;
    }
    public void setArmTargetPosition(int position){
        leftMotor.setTargetPosition(position);
        rightMotor.setTargetPosition(position);
    }
    public double getRightMotorPosition(){
        return rightMotor.getCurrentPosition();
    }
    public double getLeftMotorPosition(){
        return leftMotor.getCurrentPosition();
    }
    public void setPosition(int position){
        setArmTargetPosition(position);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setPower(-0.7);
        rightMotor.setPower(0.7);


    } //TEST - main auto method to move arm
    public void auto_score(){
        //create a 4 second timer to score pixel
        Timing.Timer clock = new Timing.Timer(4, TimeUnit.SECONDS);

        //lift arm at start of timer
        setPosition(scoreHeightLOW);

        //lower arm after 4.5 second timer
        if(clock.done()){
            setPosition(0);
        }

    } //TEST - moves arm to score and back down in sync with intake

    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Current Arm Target", target);
        telemetry.addData("Left Arm Motor Position", leftMotor.getCurrentPosition());
        telemetry.addData("Right Arm Motor Position", rightMotor.getCurrentPosition());
        telemetry.addData("Current Arm Status", armStatus);
    }
}

