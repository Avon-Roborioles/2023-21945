package org.firstinspires.ftc.teamcode.Call_Upon_Classes;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//FTCLib Library

import org.firstinspires.ftc.robotcore.external.Telemetry;


//Added Default Class for Intake Mechanism
public class Intake {
    public static int wristTarget = 0; //the variable team drivers will control to move the wrist
    private Servo claw1 = null;
    private Servo claw2 = null;
    private DcMotorEx wristMotor = null;
    private boolean wristIsUp = true;
    private boolean wristDefault = false;
    private boolean clawDefault = false;
    public int pixel5 = -120;
    public int pixel4 = -111;
    public int pixel3 = -104;
    public int pixel2 = -97;
    private boolean clawsAreClosed = true;
    private boolean claw1Open = true;
    private boolean claw2Open = true;

    //init method
    public void init_intake_V2(HardwareMap hardwareMap, String claw1Name, String claw2Name,String wristName){
        claw1 = hardwareMap.get(Servo.class, claw1Name);
        claw2 = hardwareMap.get(Servo.class, claw2Name);
        wristMotor = hardwareMap.get(DcMotorEx.class, wristName);
        wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //sets initial wrist position to 0
        wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wristMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        wristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }

    //autonomous methods
    public void openClawV2(boolean open, boolean leftClaw){ //Done
        if(leftClaw){ //done - control left claw
            if (open){
                claw1Open = true;
                //clawIsOpen = true;
                claw1.setPosition(.4);
            } else {
                claw1Open = false;
                //clawIsOpen = false;
                claw1.setPosition(0.55); //0.6
            }
        } else { //Done control right claw
            if (open){
                claw2Open = true;
                //clawIsOpen = true;
                claw2.setPosition(.2);
            } else {
                claw2Open = false;
              //  clawIsOpen = false;
                claw2.setPosition(0.05); //0
            }
        }
    }
    public void closeClaws(boolean close){ //Done
        if (close){
           // clawIsOpen = false;
            claw1Open = false;
            claw2Open = false;
            claw1.setPosition(.55); //.6
            claw2.setPosition(0.05); //0
        } else {
           // clawIsOpen = true;
            claw1Open = true;
            claw2Open = true;
            claw1.setPosition(.4); //.3
            claw2.setPosition(.2); //.4
        }
    }
    public void wrist_up(){
        wristTarget = 0; //0
        wristMotor.setTargetPosition(wristTarget);
        wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMotor.setPower(.7);

    } //TEST - moves wrist to pos and opens claw to score
    public void wrist_down(){
        wristTarget = -120; //-200
        wristMotor.setTargetPosition(wristTarget);
        wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMotor.setPower(.7);
    }
    public void wrist_auto(int pixel){ //adjust wrist pose based on pixel stack height
        switch(pixel){ //TODO find exact heights with Robot_Telemetry Program
            case 5:
                wristTarget = pixel5;
                break;
            case 4:
                wristTarget = pixel4;
                break;
            case 3:
                wristTarget = pixel3;
                break;
            case 2:
                wristTarget = pixel2;
                break;
            default:
                wristTarget = -90;
                break;
        }
        wristMotor.setTargetPosition(wristTarget);
        wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMotor.setPower(.7);

    }


    //Methods for TeleOp
    public void run_intake_V2(Gamepad gamepad2, ToggleButtonReader aReader, ToggleButtonReader yReader, ToggleButtonReader LBumper, ToggleButtonReader RBumper){
        double rightY = gamepad2.right_stick_y; //was float—

        if(aReader.wasJustPressed()){
            clawDefault = true;
            clawsAreClosed = claw1.getPosition() == 0.4 || claw2.getPosition() == 0.2;
        }

        if(LBumper.wasJustPressed()){
            clawDefault = false;
            openClawV2(!claw1Open,true);
        }

        if(RBumper.wasJustPressed()){
            clawDefault = false;
            openClawV2(!claw2Open,false);
        }




        if(yReader.wasJustPressed()){ //when pressed activate default mode & switch between up or down
            wristDefault = true;
            wristIsUp = wristMotor.getCurrentPosition() > -50;
            }

        if(rightY < -0.3 || rightY > 0.3) { //TODO: Add Safety Check In case of Joystick strafe; check if joystick is moving
            wristDefault = false;
            //control wrist
            wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            if (rightY > 0) {
                //wristDefault = false;
                wristMotor.setPower(-0.35); //0.25
            } else {
                //wristDefault = false;
                wristMotor.setPower(0.35); //-0.25

            }
        } else if(rightY >= -0.3 && !wristDefault){
            wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            wristMotor.setPower(0); //small power to hold wrist
        }

        if(wristDefault) { //switch between up and down if wristDefault is activated
            if (wristIsUp) {
                wrist_down();
            } else {
                wrist_up();
            }
        }

        if(clawDefault){
            closeClaws(clawsAreClosed);
        }

        aReader.readValue();
        yReader.readValue();
        LBumper.readValue();
        RBumper.readValue();

    }

    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Claw 1 Pose: ", claw1.getPosition());
        telemetry.addData("Claw 2 Pose: ", claw2.getPosition());
        telemetry.addData("Claws Closed?: ", clawsAreClosed);
        telemetry.addData("Wrist Pose: ", wristMotor.getCurrentPosition());
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