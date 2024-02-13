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

    //enum armStates for arm
    public enum armStates {
        SCORE,
        STACK5,
        STACK4,
        STACK3,
        STACK2
    }

    private Motor leftMotor;
    private Motor rightMotor;

    private MotorGroup armGroup;

    private PIDController controller;

    public static double p = 0.0023, i = 0, d = 0.0008; //PID variables needed
    public static double f = 0.005; //feed forward variable

    public int target = 0; //the variable team drivers will control to move arm

    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    public static double kP = 0;
    public static double kV = 0;

    private double speed = 0.0;

    private int scoreHeightHIGH = 1600; //600
    private int pixel5Height = 90; //TODO Change these values to actual height
    private int pixel4Height = 60;
    private int pixel3Height = 30;
    private int pixel2Height = 15;
    private double leftMotorPosition = 0;
    private double rightMotorPosition = 0;
    private int maxPosition = 4000; //Done -  find max value
    private int armStandbyPose = 200;
    private boolean armState;
    private boolean armDefault = false;
    private boolean armIsUp;

    //Methods of Arm Initialization
    public void init_arm_manual(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotorEx = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotorEx = hardwareMap.get(DcMotorEx.class, "rightMotor");
        leftMotorEx.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void init_arm_V2(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotor = new Motor(hardwareMap,leftMotorName);
        rightMotor = new Motor(hardwareMap,rightMotorName);
        controller = new PIDController(p, i, d);

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



    public void up(){
        target = 2000;

//        controller.setPID(p, i, d);
//        int leftArmPos = leftMotor.getCurrentPosition();
//        int rightArmPos = rightMotor.getCurrentPosition();
//        double pid = controller.calculate(rightArmPos, target);
//        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;
//
//        double power = pid + ff;
//        armGroup.set(power);
    }

    public void down(){
        target = 20;

//        controller.setPID(p, i, d);
//        int leftArmPos = leftMotor.getCurrentPosition();
//        int rightArmPos = rightMotor.getCurrentPosition();
//        double pid = controller.calculate(rightArmPos, target);
//        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;
//
//        double power = pid + ff;
//        armGroup.set(power);
    }

    public void move_auto(int pixel){
        //logic just like wrist
        switch(pixel){
            case 5:
                target = pixel5Height;
                break;
            case 4:
                target = pixel4Height;
                break;
            case 3:
                target = pixel3Height;
                break;
            case 2:
                target = pixel2Height;
                break;
             default:
                 target = 10;
        }

//        controller.setPID(p, i, d);
//        int leftArmPos = leftMotor.getCurrentPosition();
//        int rightArmPos = rightMotor.getCurrentPosition();
//        double pid = controller.calculate(rightArmPos, target);
//        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;
//
//        double power = pid + ff;
//        armGroup.set(power);
    }

    //method to update PID controller for arm in auto
    public void update(){
        controller.setPID(p, i, d);
        int leftArmPos = leftMotor.getCurrentPosition();
        int rightArmPos = rightMotor.getCurrentPosition();
        double pid = controller.calculate(rightArmPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;
        armGroup.set(power);
    }

    //Methods of Arm TeleOp control
    public void run_arm_manual(Gamepad gamepad2, ToggleButtonReader d_down) {
        double leftY = gamepad2.left_stick_y;
        float rightTrigger = gamepad2.right_trigger;

        if(d_down.wasJustPressed()){
            armDefault = true;
        }

        if(leftY > 0 || leftY < 0) {
            armDefault = false;
            if (rightMotorEx.getCurrentPosition() < 1600) {
                if (leftY < 0) {
                    leftMotorEx.setPower(-0.5);
                    rightMotorEx.setPower(0.5);
                } else if (leftY > 0) {
                    leftMotorEx.setPower(0.3);
                    rightMotorEx.setPower(-0.3);
                }
            } else {
                if (leftY > 0) {
                    leftMotorEx.setPower(0.5);
                    rightMotorEx.setPower(-0.5);
                } else if (leftY < 0) {
                    leftMotorEx.setPower(-0.3);
                    rightMotorEx.setPower(0.3);
                }
            }
        } else if(armDefault == false){
             if(rightMotorEx.getCurrentPosition() < 1600) {
                leftMotorEx.setPower(-0.04); //small bit of power for brakes
                rightMotorEx.setPower(0.04);
            } else {
                 leftMotorEx.setPower(0.09); //small bit of power for brakes
                 rightMotorEx.setPower(-0.09);
             }
        }

        //hang
        if(armDefault){
            leftMotorEx.setPower(0.8);
            rightMotorEx.setPower(-.8);
        }

        d_down.readValue();
    } //Done - just controls speed - test


    public void run_arm_V2(Gamepad gamepad2, GamepadEx gamepad2Ex, ToggleButtonReader d_down, ToggleButtonReader d_up){
        double ltrigger = gamepad2.left_trigger;
        double rtrigger = gamepad2.right_trigger;
        double leftY = gamepad2.left_stick_y; //was float—
        boolean button_a = gamepad2.a;
        boolean button_x = gamepad2.x;
        boolean button_y = gamepad2.y;
        boolean button_b = gamepad2.b;

        //armDefault Logic

        //TeleOp Control logic

        //Set point Logic

        //stack level logic

        d_down.readValue();
    }

    //simple arm function to make robot hang
    public void hang(){
        armGroup.setRunMode(Motor.RunMode.RawPower);
        armGroup.set(-0.7);
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

