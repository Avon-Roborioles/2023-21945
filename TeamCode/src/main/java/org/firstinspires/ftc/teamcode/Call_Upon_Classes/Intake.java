package org.firstinspires.ftc.teamcode.Call_Upon_Classes;
import com.arcrobotics.ftclib.gamepad.ToggleButtonReader;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
    public int stackedPixel5 = -120; //TODO - get values of wrist for stacked pixels
    public int stackedPixel4 = -111;
    public int stackedPixel3 = -104;
    public int stackedPixel2 = -97;
    public boolean stackMode = false;
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

    //Call Upon methods for multi-modual robot features
    public boolean wristIsPointingDown(){
        //TODO - test to get value
        return wristTarget > 300;
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
        //TODO - easier methods
    public void openLeftClaw(boolean open){

    }
    public void openRightClaw(boolean open){}
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
                wristTarget = stackedPixel5;
                break;
            case 4:
                wristTarget = stackedPixel4;
                break;
            case 3:
                wristTarget = stackedPixel3;
                break;
            case 2:
                wristTarget = stackedPixel2;
                break;
            default:
                wristTarget = 0;
                break;
        }
        wristMotor.setTargetPosition(wristTarget);
        wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wristMotor.setPower(.7);

    }

    public void setStackPose(int pixel){
        switch(pixel){ //TODO find exact heights with Robot_Telemetry Program
            case 5:
                wristTarget = stackedPixel5;
                break;
            case 4:
                wristTarget = stackedPixel4;
                break;
            case 3:
                wristTarget = stackedPixel3;
                break;
            case 2:
                wristTarget = stackedPixel2;
                break;
            default:
                wristTarget = 0;
                break;
        }
    }


    //TODO - Methods for TeleOp
    public void run_intake_V2(Gamepad gamepad2, ToggleButtonReader aReader, ToggleButtonReader yReader, ToggleButtonReader LBumper, ToggleButtonReader RBumper,ToggleButtonReader d_down,ToggleButtonReader d_up,ToggleButtonReader d_left, ToggleButtonReader d_right){
        double rightY = gamepad2.right_stick_y; //manual control

        if(aReader.wasJustPressed()){ //auto claw control
            clawDefault = true;
            clawsAreClosed = claw1.getPosition() == 0.4 || claw2.getPosition() == 0.2;
        } //auto claws control

        if(LBumper.wasJustPressed()){ //left claw control
            clawDefault = false;
            openClawV2(!claw1Open,true);
        } //left claw control

        if(RBumper.wasJustPressed()){ //right claw control
            clawDefault = false;
            openClawV2(!claw2Open,false);
        } //right claw control

        if(yReader.wasJustPressed()){ //auto wrist control
            wristDefault = true;
            stackMode = false;
            wristIsUp = wristMotor.getCurrentPosition() > -50;
        } //auto wrist control


        //TODO - Auto Wrist Level for Stacked Pixels
        if(d_up.wasJustPressed()){ //pixel 5
            stackMode = true;
            wristDefault = false;
            setStackPose(5);
        }
        if(d_left.wasJustPressed()){ //pixel 4
            stackMode = true;
            wristDefault = false;
            setStackPose(4);
        }
        if(d_right.wasJustPressed()){ //pixel 3
            stackMode = true;
            wristDefault = false;
            setStackPose(3);
        }
        if(d_down.wasJustPressed()){ //pixel 2
            stackMode = true;
            wristDefault = false;
            setStackPose(2);
        }


        if(rightY < -0.3 || rightY > 0.3) { //TODO: Add Safety Check In case of Joystick strafe; check if joystick is moving
            stackMode = false;
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
        } else if(rightY >= -0.3 && !wristDefault && !stackMode){
            wristTarget = wristMotor.getCurrentPosition();
            wristMotor.setTargetPosition(wristTarget);
            wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wristMotor.setPower(.7);
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

        if(stackMode){
            wristMotor.setTargetPosition(wristTarget);
            wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wristMotor.setPower(.7);
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