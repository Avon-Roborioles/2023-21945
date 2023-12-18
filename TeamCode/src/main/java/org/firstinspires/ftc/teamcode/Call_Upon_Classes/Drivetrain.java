package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drivetrain {

    private boolean strafe;
    private int strafe_set=1;

    //right == 1
    //left  == -1
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
    private DcMotor x_encoder = null;
    MecanumDrive drivetrain;
    IMU imu;

//
//    /// new library stuff ///
//
//    private Motor fL, fR, bL, bR;
//    private MecanumDrive drive;
//    private GamepadEx driverOp;
//
//    public void init_main() {
//        drive = new MecanumDrive(fL, fR, bL, bR);
//        driverOp = new GamepadEx(gamepad1);
//    }
//
//    public void run_drivetrain() {
//        drive.driveRobotCentric(
//                driverOp.getLeftX(),
//                driverOp.getLeftY(),
//                driverOp.getRightY()
//        );
//    }


    public void init_drive_motors(HardwareMap hardwareMap) {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        leftRear.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftFront.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //TODO - add fieldCentric Driving
    public void init_red_drive_motors(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotor.class, "rightFront"); //leftFront
        rightFront = hardwareMap.get(DcMotor.class, "rightRear"); //rightFront
        leftRear = hardwareMap.get(DcMotor.class, "leftFront"); //leftRear
        rightRear = hardwareMap.get(DcMotor.class, "leftRear"); //rightRear
        //-------------------Resets Motors to 0 pos---------------------
//        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //---------------------------------------------------------------
        //**********Drivetrain Use without Encoder Wires************
//        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //OR------
        //**********Drivetrain Use with Encoder Wires***************
//        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //----------------------------------------------------------------
        leftRear.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftFront.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    } //Done

    //TODO - add fieldCentric Driving
    public void init_blue_drive_motors(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightFront");
//        x_encoder = hardwareMap.get(DcMotor.class, "x");
        leftRear.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftFront.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    } //Done

    //generic fieldCentric drive setup
    public void init_fieldCentric_drive(HardwareMap hardwareMap){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        leftRear.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);

         imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        ));
        imu.initialize(parameters);

    }

//    public void run_ftclib_drive(HardwareMap hardwareMap, GamepadEx gamepad1Ex) {
//            drivetrain.driveFieldCentric(
//                    gamepad1Ex.getLeftX(),
//                    gamepad1Ex.getLeftY(),
//                    gamepad1Ex.getRightX()),
//                    imu.getRotation2d().getDegrees(),
//                    false
//    };

    //Old method running Drivetrain
    public void run_drive_motors(Gamepad gamepad1, Telemetry telemetry){
        lx =-1 * gamepad1.left_stick_y; //switched left_stick y with left stick x
        ly =gamepad1.left_stick_x;
        rx=gamepad1.right_stick_x;
        denominator = Math.max(Math.abs(lx)+Math.abs(ly)+Math.abs(rx), 1);

        leftFront.setPower((lx + ly +rx)/denominator);
        leftRear.setPower((lx + ly * strafe_set -rx* strafe_set )/denominator);
        rightFront.setPower((lx - ly -rx)/denominator);
        rightRear.setPower((lx - ly * strafe_set +rx* strafe_set )/denominator);

        getTelemetry(telemetry);

    }

    public void run_mecanum_drive(Gamepad gamepad1, Telemetry telemetry, double rightArmPosition){
        lx =gamepad1.left_stick_x; //swtiched with x
        ly =gamepad1.left_stick_y; //switched with y
        rx=gamepad1.right_stick_x;
        double powerLimit = 0.0; //variable that slows down drivetrain based on armPos

        if(rightArmPosition > 1600){
            powerLimit = 0.5;
        } else {
            powerLimit = 1;
        }

        if (Math.abs(ly)>Math.abs(lx)) {//movement
            leftFront.setPower(-ly -rx * powerLimit); // -+
            rightFront.setPower(-ly +rx * powerLimit); // --
            rightRear.setPower(-ly +rx * powerLimit); // --
            leftRear.setPower(-ly -rx * powerLimit); // -+

        }else{//rotation
            leftFront.setPower(lx +rx * powerLimit); //+
            rightFront.setPower(-lx -rx * powerLimit);
            leftRear.setPower(-lx +rx * powerLimit);
            rightRear.setPower(lx -rx * powerLimit); //-
        }

        getTelemetry(telemetry);
    }

    public void run_fieldCentric_drive(Gamepad gamepad1){
        //tutorial -> https://youtu.be/4rG5G9Mjw-g?si=b2D4wEkw01eRBSOd

        double lx = gamepad1.left_stick_x;
        double ly = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;

        double max = Math.max(Math.abs(lx) + Math.abs(ly) + Math.abs(rx), 1 );

        double power = 0.2 + (0.6 * gamepad1.right_trigger);

        double heading = -imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double adjustedLx = -ly * Math.sin(heading) + lx * Math.cos(heading);
        double adjustedLy = ly * Math.cos(heading) + lx * Math.sin(heading);

        leftFront.setPower(((adjustedLy + adjustedLx + rx) / max) * power);
        leftRear.setPower(((adjustedLy - adjustedLx + rx) / max) * power);
        rightFront.setPower(((adjustedLy - adjustedLx - rx) / max) * power);
        rightRear.setPower(((adjustedLy + adjustedLx - rx) / max) * power);


    }

    public void getTelemetry (@NonNull Telemetry telemetry){
        telemetry.addData("fl power: ", leftFront.getPower());
        telemetry.addData("fr power: ", rightFront.getPower());
        telemetry.addData("bl power: ", leftRear.getPower());
        telemetry.addData("br power: ", rightRear.getPower());
//        telemetry.addData("X Dead Wheel Encoder Value: ",x_encoder.getCurrentPosition());


    }

}

