package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
public class Tshirtkevin extends LinearOpMode{
    //Drive Motors
    private DcMotor frontright = null;
    private DcMotor frontleft = null;
    private DcMotor backright = null;
    private DcMotor backleft = null;
    //Servos
    private CRServo cannona=null;
    private CRServo cannonb=null;
    private CRServo cannonc=null;
    private CRServo liftmotor=null;
    //Doubles
    private double leftsticky=0;
    private double rightsticky=0;
    private double powerlimitor=0.4;
    //buttons
    private boolean buttony=false;
    private boolean buttonx=false;
    private boolean buttonb=false;

    @Override
    public void runOpMode() throws InterruptedException {
        frontright = hardwareMap.get(DcMotor.class, "fr");
        frontleft = hardwareMap.get(DcMotor.class, "fl");
        backright = hardwareMap.get(DcMotor.class, "br");
        backleft = hardwareMap.get(DcMotor.class, "bl");
        //servos
        cannona = hardwareMap.get(CRServo.class, "ca");
        cannonb = hardwareMap.get(CRServo.class, "cb");
        cannonc = hardwareMap.get(CRServo.class, "cc");
        liftmotor = hardwareMap.get(CRServo.class, "lm");
        waitForStart();
        //Doubles
        leftsticky=this.gamepad1.left_stick_y *powerlimitor;
        rightsticky=this.gamepad1.right_stick_x *powerlimitor;
        //buttons
        buttony=this.gamepad1.y;
        buttonx=this.gamepad1.x;
        buttonb=this.gamepad1.b;

        //cannon
        if (buttonx){
            cannona.setPower(1);
        } else if (buttonb){
            cannonb.setPower(1);
        } else if (buttonx){
            cannonb.setPower(1);
        } else {
            cannona.setPower(0);
            cannonb.setPower(0);
            cannonc.setPower(0);
        }

        frontright.setPower(rightsticky);
        backright.setPower(rightsticky);
        frontleft.setPower(leftsticky);
        backleft.setPower(leftsticky);
    }
}
