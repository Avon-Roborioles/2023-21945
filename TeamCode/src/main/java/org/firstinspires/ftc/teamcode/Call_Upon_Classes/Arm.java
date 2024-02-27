package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {

    //2 arm motors
    private DcMotorEx leftMotorEx = null;
    private DcMotorEx rightMotorEx = null;

    private MotorGroup armGroup;
    private PIDController controller;

    public static double p = 0.0023, i = 0, d = 0.0008; //PID variables needed
    public static double f = 0.005; //feed forward variable
    public double pidPower = 0;

    public int target = 0; //the variable team drivers will control to move arm
    public static double kP = 0;
    public static double kV = 0;
    public double rightMotorPosition = 0;
    private boolean hangingMode = false;
    private boolean stackMode = false;
    private int stackedPixel2 = 30;
    private int stackedPixel3 = 60;
    private int stackedPixel4 = 80;
    private int stackedPixel5 = 100;

    //Init Function
    public void init_arm_teleOp(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotorEx = hardwareMap.get(DcMotorEx.class, leftMotorName);
        rightMotorEx = hardwareMap.get(DcMotorEx.class, rightMotorName);
        //leftMotorEx.setDirection(DcMotorSimple.Direction.REVERSE);

        armGroup = new MotorGroup((Motor) rightMotorEx, (Motor) leftMotorEx);

    }


    //Auto Arm Methods
    public void up(){
        target = 2000; //2000
    }
    public void down(){
        target = 5;
    }
    public void stackPose(int pixel){
        switch(pixel){
            case 5:
                target = stackedPixel5;
                break;
            case 4:
                target = stackedPixel4;
                break;
            case 3:
                target = stackedPixel3;
                break;
            case 2:
                target = stackedPixel2;
                break;
             default:
                 target = 0;
        }
    }
    public void update(){
        controller.setPID(p, i, d);
        int rightArmPos = rightMotorEx.getCurrentPosition();
        double pid = controller.calculate(rightArmPos, target);
        //need to check motors to be accurate
        double ticks_in_degree = 700 / 180.0;
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        leftMotorEx.setPower(power);
        rightMotorEx.setPower(power);
    }

    //TeleOp functions
    public void run_arm_teleOp(Gamepad gamepad2, ToggleButtonReader d_down) {
        double leftY = gamepad2.left_stick_y;
        boolean homeButton = gamepad2.ps; //TODO change to guide button if not detected

        //TODO stacked pixel heights
//        if(d_down.wasJustPressed()){
//
//        }

        //activate hanging mode
        if(homeButton){
            hangingMode = true;
        }

        if(leftY > 0 || leftY < 0) {
            hangingMode = false;
            if (rightMotorEx.getCurrentPosition() < 1600) {
                if (leftY < 0) {
                    leftMotorEx.setPower(-0.5);
                    rightMotorEx.setPower(0.5);
                } else {
                    leftMotorEx.setPower(0.3);
                    rightMotorEx.setPower(-0.3);
                }
            } else {
                if (leftY > 0) {
                    leftMotorEx.setPower(0.5);
                    rightMotorEx.setPower(-0.5);
                } else {
                    leftMotorEx.setPower(-0.3);
                    rightMotorEx.setPower(0.3);
                }
            }
        } else if(!hangingMode){
             if(rightMotorEx.getCurrentPosition() < 1600) {
                leftMotorEx.setPower(-0.04); //small bit of power for brakes
                rightMotorEx.setPower(0.04);
            } else {
                 leftMotorEx.setPower(0.09); //small bit of power for brakes - 0.09
                 rightMotorEx.setPower(-0.09);
             }
        }

        //hanging mode
        if(hangingMode){
            hang();
        }

        d_down.readValue();
    }
    public void hang(){
        armGroup.setRunMode(Motor.RunMode.RawPower);
        armGroup.set(-0.8);
    }


    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Left Motor Position: ", leftMotorEx.getCurrentPosition());
        telemetry.addData("Right Motor Position: ", rightMotorEx.getCurrentPosition());
    }
}

