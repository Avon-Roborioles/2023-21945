package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.gamepad.ButtonReader;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.arcrobotics.ftclib.gamepad.TriggerReader;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.arcrobotics.ftclib.util.Timing.Timer;
import com.qualcomm.robotcore.hardware.Servo;

//FTCLib Library

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

//Added Default Class for Intake Mechanism
public class Intake {
    private PIDController controller;
    public static double p = 0, i = 0, d = 0; //PID variables needed
    public static double f = 0; //feed forward variable
    public static int wristTarget = 0; //the variable team drivers will control to move the wrist
    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate
    private ServoEx claw = null;
    private Servo claw1 = null;
    private Servo claw2 = null;
    private ServoEx pixelHolder = null;
    private DcMotorEx wristMotor = null;
    private double rightY = 0.0;
    private int maxWristPosition = 200; //200 is level with ground
    private int wristPosition = 0;
    private boolean wristIsUp = true;
    private boolean wristState;
    private boolean wristDefault = false;
    public enum wristCommands {
        WRIST_UP,
        WRIST_DOWN,
        WRIST_MANUAL,
        WRIST_DOWN_BACK,
        WRIST_START;
    }
    private wristCommands wristStatus = wristCommands.WRIST_START;
    private boolean clawIsOpen = false;

    //Methods of intake initialization
    public void init_intake_teleOp(HardwareMap hardwareMap, String clawName, String wristName, String pixelHolderName){
        //creates the intake object with its name
        claw = new SimpleServo(hardwareMap, clawName, 0, 180);
        pixelHolder = new SimpleServo(hardwareMap, pixelHolderName, 0, 180);
        controller = new PIDController(p, i, d);
        wristMotor = hardwareMap.get(DcMotorEx.class, wristName);
        wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //sets initial wrist position to 0
        wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wristMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        claw.setPosition(0);
    }
    public void init_intake_main(HardwareMap hardwareMap, String clawName, String wristName, String pixelHolderName, boolean auto){
        claw = new SimpleServo(hardwareMap, clawName, 0, 180);
        pixelHolder = new SimpleServo(hardwareMap, pixelHolderName, 0, 180);
        wristMotor = hardwareMap.get(DcMotorEx.class, wristName);

        //used to set start position to 0
        if(auto) {wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);}

        wristMotor.setTargetPosition(0);

        wristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void init_intake_V2(HardwareMap hardwareMap, String claw1Name, String claw2Name,String wristName){
        //claw1 = new SimpleServo(hardwareMap, claw1Name, 0, 180);
        //claw2 = new SimpleServo(hardwareMap, claw2Name, 0, 180);
        claw1 = hardwareMap.get(Servo.class, claw1Name);
        claw2 = hardwareMap.get(Servo.class, claw2Name);
        wristMotor = hardwareMap.get(DcMotorEx.class, wristName);
        wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //sets initial wrist position to 0
        wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wristMotor.setDirection(DcMotorSimple.Direction.REVERSE);

//        claw1.setPosition(0);
//        claw2.setPosition(0);
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
            wristTarget = 100;
        } else if(command == wristCommands.WRIST_DOWN){
            wristTarget = 0;
        }
        controller.setPID(p, i, d);
        int wristPos = wristMotor.getCurrentPosition();
        double pid = controller.calculate(wristPos, wristTarget);
        double ff = Math.cos(Math.toRadians(wristTarget / ticks_in_degree)) * f;

        double power = pid + ff;

        wristMotor.setPower(power);
    } //Done - test
    public void openClaw(boolean close) {
        if(close){
            claw.setPosition(.4); //.5
        } else {
            claw.setPosition(0); //180 - position is set to 0.1 for testing
        }
    } //Done - test
    public void openClawV2(boolean open, boolean leftClaw){ //Done
        if(leftClaw){ //done - control left claw
            if (open){
                claw1.setPosition(.3);
            } else {
                claw1.setPosition(0.6);
            }
        } else { //Done control right claw
            if (open){
                claw2.setPosition(.2);
            } else {
                claw2.setPosition(0);
            }
        }
    }
    public void openClaws(boolean close){ //Done
        if (close){
            claw1.setPosition(.6); //.4
            claw2.setPosition(0); //-.2
        } else {
            claw1.setPosition(.3); //-.2
            claw2.setPosition(.2); //.4
        }
    }
    public void closePixelHolder(boolean close){
//        if(close){
//            pixelHolder.setPosition(1); //close
//            holder_up = true;
//        } else {
//            pixelHolder.setPosition(0.7); //open
//            holder_up = false;
//        }
    }
    public void wrist_up(){
        wristTarget = 0; //0
        wristMotor.setTargetPosition(wristTarget);
        wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMotor.setPower(.7);

    } //TEST - moves wrist to pos and opens claw to score

    public void wrist_down(){
        wristTarget = -90; //-200
        wristMotor.setTargetPosition(wristTarget);
        wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMotor.setPower(.7);
    }

    //manages the pixelHolder based on certain conditions
    public void runAutoPixelHolder(boolean override){
        if(override){ //if we need pixelHolder closed
            //close pixelHolder
            closePixelHolder(false);
        } else {
            if (wristTarget <= 200) {
                //open pixelHolder
                closePixelHolder(true);
            } else {
                //close pixelHolder
                closePixelHolder(false);
            }
        }
    }
    public void storePixel(){
        int initTarget = wristMotor.getTargetPosition();
        Timing.Timer clock = new Timer(3, TimeUnit.SECONDS);
        if(clock.remainingTime() == 3){
            openClaw(false);
            closePixelHolder(true);
        }
        if(clock.remainingTime() == 2){
            wristTarget = 0;
            wristMotor.setTargetPosition(wristTarget);
            wristMotor.setPower(0.6);
        }
        if(clock.remainingTime() == 1){
            openClaw(true);
        }
        if(clock.remainingTime() == 0){
            wristTarget = initTarget;
            wristMotor.setTargetPosition(wristTarget);
            wristMotor.setPower(0.6);
        }
        if(clock.done()){
            closePixelHolder(false);
        }

    } //TEST - close claw, wrist up, open claw, wrist down - test
    public void retrievePixel(){
        int initTarget = wristMotor.getTargetPosition(); //allows robot to retrurn to initial pos to not hit things
        Timing.Timer clock = new Timer(3, TimeUnit.SECONDS);
        if(clock.remainingTime() == 3){
            openClaw(true);
        }
        if(clock.remainingTime() == 2){
            wristTarget = 0;
            wristMotor.setTargetPosition(wristTarget);
            wristMotor.setPower(0.6);
        }
        if(clock.remainingTime() == 1){
            openClaw(false);
        }
        if(clock.remainingTime() == 0){
            wristTarget = initTarget;
            wristMotor.setTargetPosition(wristTarget);
            wristMotor.setPower(0.6);
        }
    } //TEST - open claw, wrist up, close claw, wrist down


    //Methods of TeleOp Intake Control
    public void run_intake_PID(Gamepad gamepad2){
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
            openClaw(true);
        } else if(rightBumper){ //close claw
            openClaw(false);
        }

        //manual wrist control
        //PID control
        if(rightY > 0){
            wristTarget += 1;
        } else if(rightY < 0){
            wristTarget -= 1;
        }
        controller.setPID(p, i, d);
        int wristPos = wristMotor.getCurrentPosition();
        double pid = controller.calculate(wristPos, wristTarget);
        double ff = Math.cos(Math.toRadians(wristTarget / ticks_in_degree)) * f;

        double power = pid + ff;

        wristMotor.setPower(power);

        //easy to use controls to store and retrieve pixel on bot
        if(button_y){
            storePixel();
        } else if(button_a){
            retrievePixel();
        }


    }
    public void run_intake_Power(Gamepad gamepad2, GamepadEx gamepad2Ex,ToggleButtonReader aReader, double rightArmPosition) {
        boolean leftBumper = gamepad2.left_bumper;
        boolean rightBumper = gamepad2.right_bumper;
        double ltrigger = gamepad2.left_trigger;
        double rtrigger = gamepad2.right_trigger;
        double rightY = gamepad2.right_stick_y; //was float—
        boolean button_a = gamepad2.a;
        boolean button_x = gamepad2.x;
        boolean button_y = gamepad2.y;
        boolean button_b = gamepad2.b;


        //TODO Add toggle pixelHolder

        if (leftBumper) { //set to 0.3 instead of 0 to stop accidental hits
            openClaw(true);
        } else if (rightBumper) {
            openClaw(false);
        }

        wristPosition = wristMotor.getCurrentPosition();

        if (rightY > 0) {
            wristMotor.setPower(-0.35); //0.25
        } else if (rightY < 0) {
            wristMotor.setPower(0.35); //-0.25
        } else {
            wristMotor.setPower(0);
        }
//
//
//        //TODO - store & retrieve pixel from Holder
//        if(button_y){}
//
//        //TODO - move wrist down
//        if(button_a){}

        //manual pixelHolder function
//        if(button_x){
//            if(holder_up){ //if pixelHolder is already up
//                holder_up = false;
//                openPixelHolder(false);
//            } else{ //if pixelHolder is already down
//                holder_up = true;
//                openPixelHolder(true);
//            }
//        }

        //

//        if (rightArmPosition > 490) {
//            openPixelHolder(false);
//        } else {
//            if (wristPosition < 120) { //90
//                openPixelHolder(true);
//            } else {
//                openPixelHolder(false);
//            }
//        if(ltrigger > 0) {
//            closePixelHolder(true);
//        } else {
//            closePixelHolder(false);
//        }

        if (aReader.getState()) {
            // if toggle state true
            closePixelHolder(true);
        } else {
            // if toggle state false
            closePixelHolder(false);
        }
        aReader.readValue();
//        if(button_a) {
//            if (holder_up) {
//                openPixelHolder(false);
//            } else {
//                openPixelHolder(true);
//            }
        //closePixelHolder(true);
    }

    public void run_intake_V2(Gamepad gamepad2, GamepadEx gamepad2Ex, ToggleButtonReader aReader, ToggleButtonReader yReader){
        double ltrigger = gamepad2.left_trigger;
        double rtrigger = gamepad2.right_trigger;
        double rightY = gamepad2.right_stick_y; //was float—
        boolean button_a = gamepad2.a;
        boolean button_x = gamepad2.x;
        boolean button_y = gamepad2.y;
        boolean button_b = gamepad2.b;
        wristState = yReader.getState();

        //open-close claws -- Caused Twitching Problem
//        if(LBumperReader.getState()) {
//            //done close claw1
//            openClawV2(false,true);
//        } else {
//            //Done open claw1
//            openClawV2(true, true);
//        }
//
//        if(RBumperReader.getState()){
//            //Done close claw2
//            openClawV2(false, false);
//        } else {
//            //Done open claw2
//            openClawV2(true, false);
//        }

        //open-close claws
        if(aReader.getState()){
            //done close both claws
            openClaws(false);
        } else {
            //Done open both claws
            openClaws(true);
        }


        if(yReader.wasJustPressed()){ //when pressed activate default mode & switch between up or down
            wristDefault = true;
            if(wristMotor.getCurrentPosition() > -50){
                wristIsUp = true;
            } else {
                wristIsUp = false;
            }
//                wristTarget = -100;
//                wristMotor.setTargetPosition(wristTarget);
//                wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                wristMotor.setPower(.5);      }
            }

        if(rightY < -0.3 || rightY > 0.3) { //TODO: Add Safety Check In case of Joystick strafe; check if joystick is moving
            wristDefault = false;
            //control wrist
            if (rightY > 0) {
                //wristDefault = false;
                wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                wristMotor.setPower(-0.35); //0.25
            } else if (rightY < 0) {
                //wristDefault = false;
                wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                wristMotor.setPower(0.35); //-0.25
//            } else {
//                wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//                wristMotor.setPower(0);
//            }
            }
        } else if((rightY >= -0.3 && rightY <= 0.3) && wristDefault == false){
            wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            wristMotor.setPower(0); //small power to hold wrist
        }

        if(wristDefault){ //switch between up and down if wristDefault is activated
            if(wristIsUp){
                wrist_down();
            } else {
                wrist_up();
            }
        }

        aReader.readValue();
        yReader.readValue();
//        RBumperReader.readValue();
//        LBumperReader.readValue();
    }

    public void run_intake_default(Gamepad gamepad2, GamepadEx gamepad2Ex, ToggleButtonReader aReader) {
        double ltrigger = gamepad2.left_trigger;
        double rtrigger = gamepad2.right_trigger;
        double rightY = gamepad2.right_stick_y; //was float—
        boolean button_a = gamepad2.a;
        boolean button_x = gamepad2.x;
        boolean button_y = gamepad2.y;
        boolean button_b = gamepad2.b;

        if(aReader.getState()){
            //done close both claws
            openClaws(false);
        } else {
            //Done open both claws
            openClaws(true);
        }

        //control wrist
        if (rightY > 0) {
            wristMotor.setPower(-0.35); //0.25
        } else if (rightY < 0) {
            wristMotor.setPower(0.35); //-0.25
        } else {
            wristMotor.setPower(0);
        }

        aReader.readValue();
    }

    public void run_intake_main(Gamepad gamepad2){

        GamepadEx gamepad2Ex = new GamepadEx(gamepad2); //added ftclib extension functions

        //TODO - test Gamepad2Ex features
        boolean leftBumper = gamepad2Ex.wasJustReleased(GamepadKeys.Button.LEFT_BUMPER);//gamepad2.left_bumper;
        boolean rightBumper = gamepad2Ex.wasJustReleased(GamepadKeys.Button.RIGHT_BUMPER);//gamepad2.right_bumper;
        // double ltrigger = gamepad2.left_trigger;
        TriggerReader ltreader = new TriggerReader(gamepad2Ex, GamepadKeys.Trigger.LEFT_TRIGGER);
        TriggerReader rtreader = new TriggerReader(gamepad2Ex, GamepadKeys.Trigger.RIGHT_TRIGGER);
        boolean ltrigger = ltreader.isDown();
        boolean rtrigger = rtreader.isDown();
        float rightY = gamepad2.right_stick_y;
        boolean button_a = gamepad2Ex.wasJustReleased(GamepadKeys.Button.A);//gamepad2.a;
        boolean button_x = gamepad2Ex.wasJustReleased(GamepadKeys.Button.X);//gamepad2.x;
        boolean button_y = gamepad2Ex.wasJustReleased(GamepadKeys.Button.Y);//gamepad2.y;
        boolean button_b = gamepad2Ex.wasJustReleased(GamepadKeys.Button.B);//gamepad2.b;
        boolean start_button = gamepad2Ex.wasJustReleased(GamepadKeys.Button.START);

        //driver control
        if(leftBumper){ //claw control
            openClaw(false);
        } else if(rightBumper){
            openClaw(true);
        } //open/close claw

        if(rightY > 0){//wrist control
            wristTarget += 3; //increment of 3 is perfect after testing
        } else if(rightY < 0){
            wristTarget -= 5;
            wristMotor.setTargetPosition(wristTarget);
        } //move wrist

        if(button_y){
            storePixel();
        } //store pixel
        if(button_a){
            retrievePixel();
        } //retrieve pixel
        if(button_x){
            wristTarget = 195;
            wristMotor.setTargetPosition(wristTarget);
        } //left - bring wrist back up
        if(button_b){
            wristTarget = 10;
            wristMotor.setTargetPosition(wristTarget);
        } //right - brings wrist back down


        //limits for wrist
        if(wristTarget > maxWristPosition){
            wristTarget = maxWristPosition;
            wristMotor.setTargetPosition(wristTarget);
        } else if(wristTarget < 0){
            wristTarget = 0;
            wristMotor.setTargetPosition(wristTarget);
        }

        wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //BE CAREFUL WITH POWER VALUE!!!!!
        wristMotor.setPower(.5);//50% power

        //auto PixelHolder Control - change arm value as needed
//        if(armTarget > 2000 ){ //keeps pixel stored if arm is backwards - keeps pixel from falling
//            runAutoPixelHolder(true);
//        } else {
//            runAutoPixelHolder(false);
//        }

        if(ltrigger) {
            closePixelHolder(true);
        } else {
            closePixelHolder(false);
        }

    }

    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Claw 1 Pose: ", claw1.getPosition());
        telemetry.addData("Claw 2 Pose: ", claw2.getPosition());
        telemetry.addData("Wrist Pose: ", wristMotor.getCurrentPosition());
        telemetry.addData("WristDefault: ", wristDefault);
        telemetry.addData("Wrist Up?: ", wristIsUp);
        telemetry.addData("Wrist State: ", wristState);
    }

}

//----------------Comment Dump-------------
//Done Change pixelHolder behavior to just moving when a button is pressed
//        if (rightArmPosition > 490) {
//            openPixelHolder(false);
//        } else {
//            if (wristPosition < 90) {
//                openPixelHolder(true);
//            } else {
//                openPixelHolder(false);
//            }
//
//        }