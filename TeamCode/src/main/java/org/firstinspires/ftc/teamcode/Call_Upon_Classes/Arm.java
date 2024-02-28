package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
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
    private boolean PIDMode = false;

    //Init Function
    public void init_arm_teleOp(HardwareMap hardwareMap, String leftMotorName, String rightMotorName){
        leftMotorEx = hardwareMap.get(DcMotorEx.class, leftMotorName);
        rightMotorEx = hardwareMap.get(DcMotorEx.class, rightMotorName);
        //leftMotorEx.setDirection(DcMotorSimple.Direction.REVERSE);

        armGroup = new MotorGroup((Motor) rightMotorEx, (Motor) leftMotorEx);
        armGroup.setRunMode(Motor.RunMode.RawPower);
    }

    //Auto Arm Methods
    public void setTargetUp(){
        target = 1600; //2000
    }
    public void setTargetDown(){
        target = 5;
    }
    public void setStackPose(int pixel){
        int stackedPixel2 = 30;
        int stackedPixel3 = 60;
        int stackedPixel4 = 80;
        int stackedPixel5 = 100;
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
    } //TODO - adjust stacked Pixels values
    public void PIDUpdate(){
        controller.setPID(p, i, d);
        int rightArmPos = rightMotorEx.getCurrentPosition();
        double pid = controller.calculate(rightArmPos, target);
        //need to check motors to be accurate
        double ticks_in_degree = 700 / 180.0;
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;
//        leftMotorEx.setPower(power);
//        rightMotorEx.setPower(power);
        armGroup.set(power);
    }
    public void hang(){
        armGroup.setRunMode(Motor.RunMode.RawPower);
        armGroup.set(-0.8);
    }

    //TeleOp functions
    public void run_arm_teleOp(Gamepad gamepad2, ToggleButtonReader d_down, ToggleButtonReader d_up, ToggleButtonReader d_left, ToggleButtonReader d_right, TriggerReader ltrigger, TriggerReader rtrigger,boolean clawPointingDown) {
        double leftY = gamepad2.left_stick_y; //manual arm control
        boolean homeButton = gamepad2.ps; //TODO change to guide button if not detected

        //stacked pixel heights
        if(d_up.wasJustPressed()){ //pixel 5
            setStackPose(5);
            PIDMode = true;
            hangingMode = false;
        }

        if(d_left.wasJustPressed()){ //pixel 4
            setStackPose(4);
            PIDMode = true;
            hangingMode = false;
        }

        if(d_right.wasJustPressed()){ //pixel 3
            setStackPose(3);
            PIDMode = true;
            hangingMode = false;
        }

        if(d_down.wasJustPressed()){ //pixel 2
            setStackPose(2);
            PIDMode = true;
            hangingMode = false;
        }

        //default arm poses
        if(ltrigger.wasJustPressed()){ //arm down auto
            setTargetDown();
            if(clawPointingDown){
                target += 300;
            }
            PIDMode = true;
            hangingMode = false;
        }

        if(rtrigger.wasJustPressed()){ //arm up auto
            setTargetUp();
            PIDMode = true;
            hangingMode = false;
        }

        //activate hanging mode
        if(homeButton){ //hang
            hangingMode = true;
            PIDMode = false;
        }

        //manual control
        if(leftY > 0 || leftY < 0) { //if controlling arm manually
            //enter manual mode
            hangingMode = false;
            PIDMode = false;
            if (rightMotorEx.getCurrentPosition() < 1600) {
                if (leftY < 0) {
//                    leftMotorEx.setPower(-0.5);
//                    rightMotorEx.setPower(0.5);
                    armGroup.set(0.5); //
                } else {
//                    leftMotorEx.setPower(0.3);
//                    rightMotorEx.setPower(-0.3);
                    armGroup.set(-0.3); //
                }
            } else {
                if (leftY > 0) {
//                    leftMotorEx.setPower(0.5);
//                    rightMotorEx.setPower(-0.5);
                    armGroup.set(-0.5);
                } else {
//                    leftMotorEx.setPower(-0.3);
//                    rightMotorEx.setPower(0.3);
                    armGroup.set(0.3);
                }
            }
        } else if(!hangingMode){ //if arm is idle - hold current pose
        target = armGroup.getCurrentPosition();
        PIDMode = true;
        }

        //Hanging Mode
        if(hangingMode){
            hang();
        }

        //PID control for Stacked Pixels & Default Arm Poses
        if(PIDMode){
            PIDUpdate();
        }

        d_down.readValue();
    }


    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Left Motor Position: ", leftMotorEx.getCurrentPosition());
        telemetry.addData("Right Motor Position: ", rightMotorEx.getCurrentPosition());
    }
}