package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.arcrobotics.ftclib.drivebase.MecanumDrive;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

    static final boolean FIELD_CENTRIC = false;
    RevIMU imu = null;

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
    /**
     *
     * @param right_strafe if you want right strafe set true, left set false
     */
    public Drivetrain(boolean right_strafe){
        strafe=right_strafe;
        if (strafe)
            strafe_set = 1;
        else
            strafe_set = -1;

    }

    public void init_drive_motors(HardwareMap hardwareMap) {
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
    }

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

    public void init_ftclib_drive(HardwareMap hardwareMap, GamepadEx gamepadEx){
         Motor fL = new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_435);
         Motor fR = new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_435);
         Motor bL = new Motor(hardwareMap, "leftRear", Motor.GoBILDA.RPM_435);
         Motor bR = new Motor(hardwareMap, "rightRear", Motor.GoBILDA.RPM_435);

         drivetrain = new MecanumDrive(fL, fR, bL, bR);
        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();

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

    //tank drive
    public void run_tank_drive(Gamepad gamepad1, Telemetry telemetry){
        lx =gamepad1.left_stick_x; //swtiched with x
        ly =gamepad1.left_stick_y; //switched with y
        ry = gamepad1.right_stick_y;
        rx=gamepad1.right_stick_x;

        if(ly > 0){
            leftFront.setPower(ly);
            leftRear.setPower(ly);
        } else if(ly < 0){
            leftFront.setPower(-ly);
            leftRear.setPower(-ly);
        } else {
            leftFront.setPower(0);
            leftRear.setPower(0);
        }

        if(ry > 0){
            rightFront.setPower(ry);
            rightRear.setPower(ry);
        } else if(ry < 0){
            rightFront.setPower(-ry);
            rightRear.setPower(-ry);
        } else {
            rightFront.setPower(0);
            rightRear.setPower(0);
        }
    }


    public void getTelemetry (@NonNull Telemetry telemetry){
        telemetry.addData("fl power: ", leftFront.getPower());
        telemetry.addData("fr power: ", rightFront.getPower());
        telemetry.addData("bl power: ", leftRear.getPower());
        telemetry.addData("br power: ", rightRear.getPower());
//        telemetry.addData("X Dead Wheel Encoder Value: ",x_encoder.getCurrentPosition());


    }

}

