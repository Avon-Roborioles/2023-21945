package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import androidx.annotation.NonNull;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
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

    //normal motor objects
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor x_encoder;
    RevHubOrientationOnRobot.LogoFacingDirection logoDirection;
    RevHubOrientationOnRobot.UsbFacingDirection usbDirection;

    //FTCLib motor objects for FCD (Field-Centric Driving)

    IMU imu;
    MecanumDrive drivetrain;

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
    public void init_fieldCentric_drive(HardwareMap hardwareMap,boolean redAlliance){
        //fL,fR,bL, bR
        imu = hardwareMap.get(IMU.class, "imu");

        if(redAlliance) { //setup imu & wheels to RedAlliance Config
            logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD; //left
            usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;

            drivetrain = new MecanumDrive(
                    new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_312),
                    new Motor(hardwareMap, "rightRear", Motor.GoBILDA.RPM_312),
                    new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_312),
                    new Motor(hardwareMap, "leftRear", Motor.GoBILDA.RPM_312)
            );
        } else { //setup imu & wheels to BlueAlliance Config
            logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD; //left
            usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;

            drivetrain = new MecanumDrive(
                    new Motor(hardwareMap, "leftRear", Motor.GoBILDA.RPM_312),
                    new Motor(hardwareMap, "leftFront", Motor.GoBILDA.RPM_312),
                    new Motor(hardwareMap, "rightRear", Motor.GoBILDA.RPM_312),
                    new Motor(hardwareMap, "rightFront", Motor.GoBILDA.RPM_312)
            );
        }


        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        // Now initialize the IMU with this mounting orientation
        // Note: if you choose two conflicting directions, this initialization will cause a code exception.
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }


    public void run_mecanum_drive(Gamepad gamepad1, Telemetry telemetry){
        lx =gamepad1.left_stick_x; //swtiched with x
        ly =gamepad1.left_stick_y; //switched with y
        rx=gamepad1.right_stick_x;
        double powerLimit = 0.0; //variable that slows down drivetrain based on armPos

        powerLimit = 1; //TODO

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

    public void run_fieldCentric_drive(GamepadEx gamepad1Ex, boolean redAlliance){
        //code to reset imu in case of drift or other problems
        if(gamepad1Ex.getButton(GamepadKeys.Button.A)){ //reset imu
            gamepad1Ex.gamepad.rumble(500); //alert driver that imu has reset
            RevHubOrientationOnRobot.LogoFacingDirection newLogoDirection;
            RevHubOrientationOnRobot.UsbFacingDirection newUsbDirection;
            if(redAlliance) {
                //red alliance
                newLogoDirection = RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD;

            } else {
                //blue alliance
               newLogoDirection = RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
            }
            newUsbDirection = RevHubOrientationOnRobot.UsbFacingDirection.UP;
            RevHubOrientationOnRobot currentOrientation = new RevHubOrientationOnRobot(newLogoDirection, newUsbDirection);
            imu.initialize(new IMU.Parameters(currentOrientation));
        }


        double strafeSpeed = -gamepad1Ex.getLeftY();
        double forwardSpeed = gamepad1Ex.getLeftX();
        double turnSpeed = -gamepad1Ex.getRightX();
        double gyroAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        drivetrain.driveFieldCentric(strafeSpeed,forwardSpeed,turnSpeed,gyroAngle,true);
    }

    public void getTelemetry (@NonNull Telemetry telemetry){
//        telemetry.addData("fl power: ", leftFront.getPower());
//        telemetry.addData("fr power: ", rightFront.getPower());
//        telemetry.addData("bl power: ", leftRear.getPower());
//        telemetry.addData("br power: ", rightRear.getPower());
        //telemetry.addData("IMU Value: ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

        //telemetry.update();
//        telemetry.addData("X Dead Wheel Encoder Value: ",x_encoder.getCurrentPosition());


    }

}

