package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;

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
    private MecanumDrive drive;
    private GamepadEx driverOp;

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
//        x_encoder = hardwareMap.get(DcMotor.class, "x");
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
    } //TODO

    public void init_ftclib_drive(HardwareMap hardwareMap, Gamepad gamepad1){
        // constructor takes in frontLeft, frontRight, backLeft, backRight motors
        // IN THAT ORDER
        MecanumDrive drive = new MecanumDrive(
                new Motor(hardwareMap, "fl", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "fr", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "bl", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "br", Motor.GoBILDA.RPM_435)
        );

        // This is the built-in IMU in the REV hub.
        // We're initializing it by its default parameters
        // and name in the config ('imu'). The orientation
        // of the hub is important. Below is a model
        // of the REV Hub and the orientation axes for the IMU.
        //
        //                           | Z axis
        //                           |
        //     (Motor Port Side)     |   / X axis
        //                       ____|__/____
        //          Y axis     / *   | /    /|   (IO Side)
        //          _________ /______|/    //      I2C
        //                   /___________ //     Digital
        //                  |____________|/      Analog
        //
        //                 (Servo Port Side)
        //
        // (unapologetically stolen from the road-runner-quickstart)

        RevIMU imu = new RevIMU(hardwareMap);
        imu.init();


    }

    public void run_ftclib_drive(HardwareMap hardwareMap, Gamepad gamepad) {
        MecanumDrive drive = new MecanumDrive(
                new Motor(hardwareMap, "fl", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "fr", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "bl", Motor.GoBILDA.RPM_435),
                new Motor(hardwareMap, "br", Motor.GoBILDA.RPM_435)
        );
        // Driving the mecanum base takes 3 jo5ystick parameters: leftX, leftY, rightX.
        // These are related to the left stick x value, left stick y value, and
        // right stick x value respectively. These values are passed in to represent the
        // strafing speed, the forward speed, and the turning speed of the robot frame
        // respectively from [-1, 1].

        if (!FIELD_CENTRIC) {

            // For a robot centric model, the input of (0,1,0) for (leftX, leftY, rightX)
            // will move the robot in the direction of its current heading. Every movement
            // is relative to the frame of the robot itself.
            //
            //                 (0,1,0)
            //                   /
            //                  /
            //           ______/_____
            //          /           /
            //         /           /
            //        /___________/
            //           ____________
            //          /  (0,0,1)  /
            //         /     ↻     /
            //        /___________/

            // optional fourth parameter for squared inputs
            drive.driveRobotCentric(
                    -gamepad.left_stick_x,
                    gamepad.left_stick_y,
                    -gamepad.right_stick_x,
                    false);
        } else {

            // Below is a model for how field centric will drive when given the inputs
            // for (leftX, leftY, rightX). As you can see, for (0,1,0), it will travel forward
            // regardless of the heading. For (1,0,0) it will strafe right (ref to the 0 heading)
            // regardless of the heading.
            //
            //                   heading
            //                     /
            //            (0,1,0) /
            //               |   /
            //               |  /
            //            ___|_/_____
            //          /           /
            //         /           / ---------- (1,0,0)
            //        /__________ /

            // optional fifth parameter for squared inputs
            drive.driveFieldCentric(
                    gamepad.left_stick_x,
                    gamepad.left_stick_y,
                    gamepad.right_stick_x,
                    imu.getRotation2d().getDegrees(),   // gyro value passed in here must be in degrees
                    false
            );
        }
    }

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

    public void run_mecanum_drive(Gamepad gamepad1, Telemetry telemetry){
        lx =gamepad1.left_stick_x; //swtiched with x
        ly =gamepad1.left_stick_y; //switched with y
        rx=gamepad1.right_stick_x;

        if (Math.abs(ly)>Math.abs(lx)) {//movement
            leftFront.setPower(-ly -rx); // -+
            rightFront.setPower(-ly +rx); // --
            rightRear.setPower(-ly +rx); // --
            leftRear.setPower(-ly -rx); // -+

        }else{//rotation
            leftFront.setPower(lx +rx); //+
            rightFront.setPower(-lx -rx);
            leftRear.setPower(-lx +rx);
            rightRear.setPower(lx -rx); //-
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

