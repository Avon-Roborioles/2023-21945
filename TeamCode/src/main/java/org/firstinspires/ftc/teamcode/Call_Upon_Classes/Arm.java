package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    //TODO Code PID Controller in arm
    private DcMotorEx leftMotorEx = null;
    private DcMotorEx rightMotorEx = null;

    private Motor leftMotor;
    private Motor rightMotor;

    private MotorGroup armGroup;

    public static double kP = 0;
    public static double kV = 0;

    private double speed = 0.0;

    private int scoreHeightHIGH = 1600; //600
    private int pixel5Height = 150; //TODO Change these values to actual height
    private int pixel4Height = 120;
    private int pixel3Height = 90;
    private int pixel2Height = 60;
    private int pixel1Height = 30;
    private double leftMotorPosition = 0;
    private double rightMotorPosition = 0;
    private int maxPosition = 4000; //Done -  find max value
    private int armStandbyPose = 200;
    private PIDController controller;
    public static int target = 0; //the variable team drivers will control to move arm
    private boolean armState;
    private boolean armDefault;
    private boolean armIsUp;

    //Methods of Arm Initialization
    public void init_arm_manual(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotorEx = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotorEx = hardwareMap.get(DcMotorEx.class, "rightMotor");
        leftMotorEx.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void init_arm_V2(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotor = new Motor(hardwareMap,"leftMotor");
        rightMotor = new Motor(hardwareMap,"rightMotor");

        //leftMotor.setInverted(true); //put in reverse

        armGroup = new MotorGroup( //RightMotor is leader, LeftMotor follows rightMotor
                rightMotor,
                leftMotor
        );
        armGroup.setRunMode(Motor.RunMode.PositionControl);
        armGroup.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

    }


    public void init_arm_main(HardwareMap hardwareMap, String leftMotorName, String rightMotorName, boolean autoProgram){
        leftMotorEx = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotorEx = hardwareMap.get(DcMotorEx.class, "rightMotor");

        //left motor should be in reverse to work with rightMotor
        leftMotorEx.setDirection(DcMotorSimple.Direction.REVERSE);

        //auto programs need motors to be reset to initial position
        if(autoProgram){
            leftMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMotorEx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        //sets motors 0 - ground position
        leftMotorEx.setTargetPosition(0);
        rightMotorEx.setTargetPosition(0);

        leftMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }



    public void up_auto(){
//        setArmTargetPosition(2000); //adust value for backboard
//        leftMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftMotorEx.setPower(-0.7);
//        rightMotorEx.setPower(0.7);
        armGroup.setRunMode(Motor.RunMode.PositionControl);
        armGroup.setPositionTolerance(6000); //TODO Testing - armGroup freaks out w/ small tolerance due to leftMotor being negative when not inverted; having a big tolerance to account for this differance might solve this
        armGroup.setTargetPosition(1600);
        armGroup.set(0.3);
    }
    public void down_auto(){
//        setArmTargetPosition(0);
//        leftMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftMotorEx.setPower(-0.7);
//        rightMotorEx.setPower(0.7);
        armGroup.setRunMode(Motor.RunMode.PositionControl);
        armGroup.setPositionTolerance(6000);
        armGroup.setTargetPosition(100); //TODO Testing
        armGroup.set(0.3);
    }

    public void standby_auto(){
        armGroup.setRunMode(Motor.RunMode.PositionControl);
        armGroup.setTargetPosition(armStandbyPose);
        armGroup.set(0.5);
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
        if(rightMotorEx.getCurrentPosition() < 1600) {
            if (leftY < 0) {
                leftMotorEx.setPower(-0.5);
                rightMotorEx.setPower(0.5);
            } else if (leftY > 0 && rightTrigger > 0.8) {
                leftMotorEx.setPower(-1);
                leftMotorEx.setPower(1);
            } else if (leftY > 0) {
                leftMotorEx.setPower(0.3);
                rightMotorEx.setPower(-0.3);
            } else {
                leftMotorEx.setPower(-0.04); //small bit of power for brakes
                rightMotorEx.setPower(0.04);
            }
        } else {
            if (leftY > 0) {
//                leftMotor.setPower(-0.3);
//                rightMotor.setPower(0.3);
                leftMotorEx.setPower(0.5);
                rightMotorEx.setPower(-0.5);
            } else if (leftY < 0 && rightTrigger > 0.8) {
                leftMotorEx.setPower(1);
                leftMotorEx.setPower(-1);
            } else if (leftY < 0) {
//                leftMotor.setPower(0.3);
//                rightMotor.setPower(-0.3);
                leftMotorEx.setPower(-0.3);
                rightMotorEx.setPower(0.3);

            } else {
                leftMotorEx.setPower(0.09); //small bit of power for brakes
                rightMotorEx.setPower(-0.09);
            }
        }
//        leftMotor.setPower(speed);
//        rightMotor.setPower(-speed);
    } //Done - just controls speed - test

    public void run_arm_V2(Gamepad gamepad2, GamepadEx gamepad2Ex, ToggleButtonReader d_down, ToggleButtonReader d_up){
        double ltrigger = gamepad2.left_trigger;
        double rtrigger = gamepad2.right_trigger;
        double leftY = gamepad2.left_stick_y; //was float—
        boolean button_a = gamepad2.a;
        boolean button_x = gamepad2.x;
        boolean button_y = gamepad2.y;
        boolean button_b = gamepad2.b;

        if(d_down.wasJustPressed()){ //when pressed activate default mode & check if arm is up or down
            armDefault = true;
            if(rightMotor.getCurrentPosition() > 800){
                armIsUp = true;
            } else {
                armIsUp = false;
            }
        }

        if(leftY < -0.3 || leftY > 0.3) { //turn off armDefault if moving joystick
            armDefault = false;

            if (rightMotor.getCurrentPosition() < 1600) {
                if (leftY < 0) {
                    armGroup.setRunMode(Motor.RunMode.RawPower);
                    armGroup.set(0.4);
                } else if (leftY > 0) {
                    armGroup.setRunMode(Motor.RunMode.RawPower);
                    armGroup.set(-0.4);
                } /*else if ((leftY >= -0.3 && leftY <= 0.3) && armDefault == false) {
                    armGroup.setRunMode(Motor.RunMode.RawPower);
                    armGroup.set(.01);
                }*/
            } else{
                if (leftY > 0) {
                    armGroup.setRunMode(Motor.RunMode.RawPower);
                    armGroup.set(-0.5);
                } else if (leftY < 0) {
                    armGroup.setRunMode(Motor.RunMode.RawPower);
                    armGroup.set(0.5);
            } /*else if ((leftY >= -0.3 && leftY <= 0.3) && armDefault == false) {
                armGroup.setRunMode(Motor.RunMode.RawPower);
                armGroup.set(-.01);
            }*/
            }
        } else if((leftY >= -0.3 && leftY <= 0.3) && armDefault == false){

            if(rightMotor.getCurrentPosition() < 1600){
                armGroup.setRunMode(Motor.RunMode.RawPower);
                armGroup.set(.01);
            } else {
                armGroup.setRunMode(Motor.RunMode.RawPower);
                armGroup.set(-.01);
            }
        }

        if(armDefault){
            if(armIsUp){
                down_auto();
            } else {
                up_auto();
            }
        }

        d_down.readValue();
    }

    //Auto Methods
    public void setArmTargetPosition(int position){
        leftMotorEx.setTargetPosition(position);
        rightMotorEx.setTargetPosition(position);
    }
    public void setPosition(int position){
        setArmTargetPosition(position);
        leftMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotorEx.setPower(-0.7);
        rightMotorEx.setPower(0.7);


    } //TEST - main auto method to move arm



    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        //telemetry.addData("Current Arm Target", target);
//        telemetry.addData("Left Arm Motor Position", leftMotorEx.getCurrentPosition());
//        telemetry.addData("Right Arm Motor Position", rightMotorEx.getCurrentPosition());
        //telemetry.addData("ArmGroup Position: ", armGroup.);
        telemetry.addData("Left Motor Position: ", leftMotor.getCurrentPosition());
        telemetry.addData("Right Motor Position: ", rightMotor.getCurrentPosition());
        telemetry.addData("Arm Default: ", armDefault);
        telemetry.addData("Arm is Up?: ", armIsUp);

        //telemetry.addData("Current Arm Status", armStatus);
    }
}

