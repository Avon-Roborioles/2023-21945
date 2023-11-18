package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class Drivetrain_Testing extends LinearOpMode {
    private double denominator;
    private double lx;
    private double ly;
    private double rx;
    private double ry;
    private double lt;
    private double rt;
    private DcMotor leftFront = null;
    private DcMotor leftRear = null;
    private DcMotor rightFront = null;
    private DcMotor rightRear = null;
    private Gamepad gamepad;


    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
//        x_encoder = hardwareMap.get(DcMotor.class, "x");
        leftRear.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftFront.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        while(opModeIsActive()){

            boolean d_up = gamepad1.dpad_up;
            boolean d_right = gamepad1.dpad_right;
            boolean d_down = gamepad1.dpad_down;
            boolean d_left = gamepad1.dpad_left;

            if(d_up){
                leftFront.setPower(0.5);
            } else {
                leftFront.setPower(0);
            }

            if (d_right){
                rightFront.setPower(.5);
            }else  {
                rightFront.setPower(0);
            }

            if(d_down){
                leftRear.setPower(.5);
            }else {
                leftRear.setPower(0);
            }

            if (d_left){
                rightRear.setPower(.5);
            }else {
                rightRear.setPower(0);
            }
        }
    }
}
