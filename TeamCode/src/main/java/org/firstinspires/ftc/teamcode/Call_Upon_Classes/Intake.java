package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm.armCommands;
import com.arcrobotics.ftclib.util.Timing.Timer;

//FTCLib Library

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

//Added Default Class for Intake Mechanism
public class Intake {
    private PIDController controller;

    public static double p = 0, i = 0, d = 0; //PID variables needed
    public static double f = 0; //feed forward variable

    public static int target = 0; //the variable team drivers will control to move the wrist
    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    private ServoEx claw = null;
    private ServoEx pixelHolder = null;
    private DcMotorEx wristMotor = null;
    private double rightY = 0.0;
    //private double defaultSpeed = 0.7;

    public enum wristCommands {
        WRIST_UP,
        WRIST_DOWN,
        WRIST_MANUAL,
        WRIST_DOWN_BACK,
        WRIST_START;
    }

    private wristCommands wristStatus = wristCommands.WRIST_START;
    private boolean clawIsOpen = false;

    public void init_intake(HardwareMap hardwareMap, String clawName, String wristName, String pixelHolderName){
        //creates the intake object with its name
        claw = new SimpleServo(hardwareMap, clawName, 0, 180);
        pixelHolder = new SimpleServo(hardwareMap, pixelHolderName, 0, 180);
        controller = new PIDController(p, i, d);
        wristMotor = hardwareMap.get(DcMotorEx.class, wristName);
    }

    //init intake code to test claw + wrist without pixelHolder
    public void init_intake_demo(HardwareMap hardwareMap, String clawName, String wristName){
        
    }

    //autonomous methods

    public wristCommands getWristStatus(){
        return wristStatus;
    } //Done

    public boolean getClawStatus(){
        return clawIsOpen;
    }

    public void moveWrist(wristCommands command){
        if(command == wristCommands.WRIST_UP){
            target = 100;
        } else if(command == wristCommands.WRIST_DOWN){
            target = 0;
        }
        controller.setPID(p, i, d);
        int wristPos = wristMotor.getCurrentPosition();
        double pid = controller.calculate(wristPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        wristMotor.setPower(power);
    } //Done - test

    public void openClaw(boolean open) {
        if(open){
            claw.setPosition(0);
        } else {
            claw.setPosition(180);
        }
    } //Done - test

    public void storePixel(){
        openClaw(false);
        Timing.Timer clock = new Timer(4, TimeUnit.SECONDS);
        moveWrist(wristCommands.WRIST_UP);

        if(clock.remainingTime() == 2){
            openClaw(true);
        }
        if(clock.done()){
            moveWrist(wristCommands.WRIST_DOWN);
            //TODO close pixelHolder
        }
    } //Done - wrist up, claw open, wrist down

    public void retrievePixel(){} //TODO



    public void run_intake(Gamepad gamepad2, armCommands armStatus){
//        //wrist control
//        rightY = gamepad2.right_stick_y;
//        wrist.set(rightY);
//
//        // Limits
//        if (wrist.getCurrentPosition() >= 180 && rightY > 0) {
//            rightY = 0;
//        } else if (wrist.getCurrentPosition() <= 0 && rightY < 0) {
//            rightY = 0;
//        }
//
//        //claw controls
//        if (gamepad2.left_bumper) {
//            claw.setPosition(0);
//        }
//        else if (gamepad2.right_bumper) {
//            claw.setPosition(180);
//        }
//
//        // pixelHolder automatic function
//        if(wrist.getCurrentPosition() > 90){
//            pixelHolder.setPosition(180);
//        } else {
//            pixelHolder.setPosition(0);
//        }

        //gamepad controls
        boolean leftBumper = gamepad2.left_bumper;
        boolean rightBumper = gamepad2.right_bumper;
        float rightY = gamepad2.right_stick_y;
        boolean button_a = gamepad2.a;
        boolean button_x = gamepad2.x;
        boolean button_y = gamepad2.y;
        boolean button_b = gamepad2.b;


        //claw control
        if(leftBumper){ //open claw
            claw.setPosition(0);
        } else if(rightBumper){ //close claw
            claw.setPosition(180);
        }

        //manual wrist control
        //PID control
        if(rightY > 0){
            target += 1;
        } else if(rightY < 0){
            target -= 1;
        }
        controller.setPID(p, i, d);
        int wristPos = wristMotor.getCurrentPosition();
        double pid = controller.calculate(wristPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        wristMotor.setPower(power);

        //easy to use controls to store and retrieve pixel on bot
        if(button_y){
            storePixel();
        } else if(button_a){
            retrievePixel();
        }


    }


    public void getTelemetry(Telemetry telemetry){
        //telemetry.addData("Intake Currently Moving: ", isActive);
        telemetry.addData("Intake Speed", rightY);
    }

}
