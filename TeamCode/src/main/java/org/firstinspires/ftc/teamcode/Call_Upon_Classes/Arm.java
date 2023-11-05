package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
    private int scoreHeight = 600;
    private int pixel5Height = 500; //TODO Change these values to actual height
    private int pixel4Height = 400;
    private int pixel3Height = 300;
    private int pixel2Height = 200;
    private int pixel1Height = 100;
    private double leftMotorPosition = 0;
    private double rightMotorPosition = 0;
    private int maxPosition = 4000; //TODO find max value
    private PIDController controller;

    public static double p = 0, i = 0, d = 0; //PID variables needed
    public static double f = 0; //feed forward variable

    public static int target = 0; //the variable team drivers will control to move arm

    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    /**
     * easy to use commands to refer to arm status
     */

    //old method that can be used any time
    //just manual control with no position holding
    public void init_arm_manual(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
    }

    //experimental arm position control with PID Controller --> FTCLib
    public void init_arm_PID(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        controller = new PIDController(p, i, d);
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
    }

    //normal arm control with .setTargetPostion() and .setMode(DcMotor.RunMode.RUN_TO_POSITION)
    //if PID control doesn't work or can't figure out, we'll use this
    public void init_arm_main(HardwareMap hardwareMap, String leftMotorName, String rightMotorName, boolean autoProgram){
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");

        //right motor should be in reverse to work with leftMotor
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

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

        if (leftY > 0) {
            leftMotor.setPower(0.3);
            rightMotor.setPower(-0.3);
        }  else if(leftY < 0 && rightTrigger > 0.8) {
            leftMotor.setPower(-1);
            leftMotor.setPower(1);
        }else if (leftY < 0) {
            leftMotor.setPower(-0.3);
            rightMotor.setPower(0.3);
        }else {
            leftMotor.setPower(0.04); //small bit of power for brakes
            rightMotor.setPower(0.04);
        }
//        leftMotor.setPower(speed);
//        rightMotor.setPower(-speed);
    } //Done - just controls speed - test

    //Done ideal arm control wit PID Control
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

    //typical feedback control - will need to adjust target value
    public void run_arm_main(Gamepad gamepad2, Telemetry telemetry){
       float leftY = gamepad2.left_stick_y;

       leftMotorPosition = leftMotor.getCurrentPosition();
       rightMotorPosition = rightMotor.getCurrentPosition();

       //driver control
       if(leftY > 0){ //arm up
           target += 10;
           setArmTargetPosition(target);
       } else if(leftY < 0){
           target -= 10;
           setArmTargetPosition(target);
       }

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
        leftMotor.setPower(0.7); //will start with 70% power
        rightMotor.setPower(0.7);

        getTelemetry(telemetry);
    }

    //quick method to get armStatus (returns armCommands result)
    public armCommands getArmStatus(){
        return armStatus;
    }

    //gets the current arm target position
    public  int getArmTargetPositiion(){
        return target;
    }

    //auto-internal arm function
    //sets target positon for both motors at once to save time
    public void setArmTargetPosition(int position){
        leftMotor.setTargetPosition(position);
        rightMotor.setTargetPosition(position);
    }

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

