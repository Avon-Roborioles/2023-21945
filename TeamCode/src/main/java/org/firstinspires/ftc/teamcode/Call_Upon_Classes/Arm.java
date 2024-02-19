package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    //TeleOp objects
    private DcMotorEx leftMotorEx = null;
    private DcMotorEx rightMotorEx = null;

    //auto objects
    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;

    private MotorGroup armGroup;

    private PIDController controller;

    public static double p = 0.0023, i = 0, d = 0.0008; //PID variables needed
    public static double f = 0.005; //feed forward variable
    public double pidPower = 0;

    public int target = 0; //the variable team drivers will control to move arm

    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    public static double kP = 0;
    public static double kV = 0;

    private double speed = 0.0;

    private int scoreHeightHIGH = 1600; //600

    public double leftMotorPosition = 0;
    public double rightMotorPosition = 0;
    private int maxPosition = 4000; //Done -  find max value
    private int armStandbyPose = 200;
    private boolean armState;
    private boolean hangDefault = false;
    private boolean armIsUp;

    //Methods of Arm Initialization
    public void init_arm_teleOp(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotorEx = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotorEx = hardwareMap.get(DcMotorEx.class, "rightMotor");
        leftMotorEx.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void init_arm_auto(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        controller = new PIDController(p, i, d);
        leftMotor = hardwareMap.get(DcMotorEx.class,"leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
    }



    //set arm target to ideal scoring position
    public void up(){
        target = 2000; //2000
    }

    //set arm target to ground position
    public void down(){
        target = 5;
    }

    //manual target control for Arm PID
    public void setTarget(int target){
        this.target = target;
    }

    //set arm target to stack pixel poses
    public void stackPose(int pixel){
        switch(pixel){
            case 5:
                target = 100;
                break;
            case 4:
                target = 80;
                break;
            case 3:
                target = 60;
                break;
            case 2:
                target = 30;
                break;
             default:
                 target = 10;
        }
    }

    //method to update PID controller for arm in auto
    public void update(){
        controller.setPID(p, i, d);
        int leftArmPos = leftMotor.getCurrentPosition();
        int rightArmPos = rightMotor.getCurrentPosition();
        double pid = controller.calculate(rightArmPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    //Methods of Arm TeleOp control
    public void run_arm_teleOp(Gamepad gamepad2, ToggleButtonReader d_down) {
        double leftY = gamepad2.left_stick_y;
        float rightTrigger = gamepad2.right_trigger;

        //activate Hanging Mode
        if(d_down.wasJustPressed()){
            hangDefault = true;
        }

        if(leftY > 0 || leftY < 0) {
            hangDefault = false;
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
        } else if(!hangDefault){
             if(rightMotorEx.getCurrentPosition() < 1600) {
                leftMotorEx.setPower(-0.04); //small bit of power for brakes
                rightMotorEx.setPower(0.04);
            } else {
                 leftMotorEx.setPower(0.09); //small bit of power for brakes - 0.09
                 rightMotorEx.setPower(-0.09);
             }
        }

        //hang
        if(hangDefault){
            leftMotorEx.setPower(0.8);
            rightMotorEx.setPower(-.8);
        }

        d_down.readValue();
    } //Done - just controls speed - test


    //simple arm function to make robot hang
    public void hang(){
        armGroup.setRunMode(Motor.RunMode.RawPower);
        armGroup.set(-0.7);
    }




    public void getTelemetry(Telemetry telemetry){

        telemetry.addData("Left Motor Position: ", leftMotorEx.getCurrentPosition());
        telemetry.addData("Right Motor Position: ", rightMotorEx.getCurrentPosition());
        telemetry.addData("Hang Default: ", hangDefault);
        telemetry.addData("Arm is Up?: ", armIsUp);
    }
}

